/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.unittestjpademo.data.access;

import com.blazartech.products.crypto.BlazarCryptoFile;
import com.blazartech.unittestjpademo.data.PersonData;
import com.blazartech.unittestjpademo.data.config.PersonDataConfig;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockitoBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author AAR1069
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(
        classes = {
            PersonDataConfig.class,
            PersonDataDALJPAImplTest.PersonDataDALJPAImplTestConfig.class
        })
@Transactional
public class PersonDataDALJPAImplTest {

    private static final Logger logger = LoggerFactory.getLogger(PersonDataDALJPAImplTest.class);
    
    @Configuration
    static class PersonDataDALJPAImplTestConfig {

        @Bean
        public PersonDataDALJPAImpl instance() {
            return new PersonDataDALJPAImpl();
        }

    }
    
    @MockitoBean
    private BlazarCryptoFile cryptoFile;

    @Autowired
    private PersonDataDALJPAImpl instance;

    public PersonDataDALJPAImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        Mockito.when(cryptoFile.getPassword("aar1069", "blah"))
                .thenReturn("blah");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllPeople method, of class PersonDataDALJPAImpl.
     */
    @Test
    @Sql("testPerson.sql")
    public void testGetAllPeople() {
        logger.info("getAllPeople");

        List<PersonData> result = instance.getAllPeople();

        assertEquals(5, result.size());
        
        result.forEach((p) -> {
            logger.info("perso " + p);
        });
    }

    @Test
    @Sql("testPerson.sql")
    public void testGetAllKlingons() {
        logger.info("getAllKlingons");

        List<PersonData> result = instance.getAllKlingons();
        assertEquals(1, result.size());
        assertEquals("Worf", result.get(0).getName());
    }
}
