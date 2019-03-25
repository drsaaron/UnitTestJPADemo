/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.unittestjpademo;

import com.blazartech.unittestjpademo.data.PersonData;
import com.blazartech.unittestjpademo.data.access.PersonDataDAL;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author AAR1069
 */
@Component
public class ListAllCommandLineRunner implements CommandLineRunner {
    
    private static final Logger logger = LoggerFactory.getLogger(ListAllCommandLineRunner.class);

    @Autowired
    private PersonDataDAL dal;
    
    @Override
    public void run(String... args) throws Exception {
        logger.info("listing all");
        
        List<PersonData> people = dal.getAllPeople();
        people.forEach((p) -> {
            logger.info("person " + p);
        });
    }
}
