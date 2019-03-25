/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blazartech.unittestjpademo.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AAR1069
 */
@Entity
@Table(name = "Person")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonData.findAll", query = "SELECT p FROM PersonData p")
    , @NamedQuery(name = "PersonData.findByPersonId", query = "SELECT p FROM PersonData p WHERE p.personId = :personId")
    , @NamedQuery(name = "PersonData.findByName", query = "SELECT p FROM PersonData p WHERE p.name = :name")
    , @NamedQuery(name = "PersonData.findByPersonTypCde", query = "SELECT p FROM PersonData p WHERE p.personTypCde = :personTypCde")})
public class PersonData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PersonId")
    private Long personId;
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    @Basic(optional = false)
    @Column(name = "PersonTypCde")
    private short personTypCde;

    public PersonData() {
    }

    public PersonData(Long personId) {
        this.personId = personId;
    }

    public PersonData(Long personId, String name, short personTypCde) {
        this.personId = personId;
        this.name = name;
        this.personTypCde = personTypCde;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getPersonTypCde() {
        return personTypCde;
    }

    public void setPersonTypCde(short personTypCde) {
        this.personTypCde = personTypCde;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personId != null ? personId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonData)) {
            return false;
        }
        PersonData other = (PersonData) object;
        if ((this.personId == null && other.personId != null) || (this.personId != null && !this.personId.equals(other.personId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PersonData{" + "personId=" + personId + ", name=" + name + ", personTypCde=" + personTypCde + '}';
    }

    
}
