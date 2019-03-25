/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.unittestjpademo.data.access;

import com.blazartech.unittestjpademo.data.PersonData;
import com.blazartech.unittestjpademo.data.repos.PersonDataRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author AAR1069
 */
@Service
public class PersonDataDALJPAImpl implements PersonDataDAL {
    
    private static final Logger logger = LoggerFactory.getLogger(PersonDataDALJPAImpl.class);

    @Autowired
    private PersonDataRepository personRepo;
    
    @Override
    public List<PersonData> getAllPeople() {
        logger.info("finding all people");
        return personRepo.findAll();
    }

    @Override
    public List<PersonData> getAllKlingons() {
        logger.info("finding all klingons");
        return personRepo.findByPersonTypCde((short) 4);
    }
}
