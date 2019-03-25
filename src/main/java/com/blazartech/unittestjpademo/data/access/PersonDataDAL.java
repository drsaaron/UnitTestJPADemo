/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.unittestjpademo.data.access;

import com.blazartech.unittestjpademo.data.PersonData;
import java.util.List;

/**
 *
 * @author AAR1069
 */
public interface PersonDataDAL {
    
    List<PersonData> getAllPeople();
    
    List<PersonData> getAllKlingons();
}
