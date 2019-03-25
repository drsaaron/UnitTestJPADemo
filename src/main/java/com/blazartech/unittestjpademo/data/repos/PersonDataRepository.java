/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.unittestjpademo.data.repos;

import com.blazartech.unittestjpademo.data.PersonData;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author AAR1069
 */
@Repository
public interface PersonDataRepository extends JpaRepository<PersonData, Long> {
    
    public List<PersonData> findByPersonTypCde(@Param("personTypCde") short personTypCde);
}
