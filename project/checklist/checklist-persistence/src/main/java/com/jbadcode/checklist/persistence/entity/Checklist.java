/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.persistence.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.jbadcode.checklist.entityfiltering.GenericView;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juan David Segura
 */
@Entity
@Table(name = "checklist")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Checklist.findAll", query = "SELECT c FROM Checklist c")})
public class Checklist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "state", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private State state;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "checklist")
    @JsonView(GenericView.Collection.class)
    private Collection<ChecklistVersion> checklistVersionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "checklist")
    @JsonView(GenericView.Collection.class)
    private Collection<ChecklistOwner> checklistOwnerCollection;

    public Checklist() {
    }

    public Checklist(Integer id) {
        this.id = id;
    }

    public Checklist(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Collection<ChecklistVersion> getChecklistVersionCollection() {
        return checklistVersionCollection;
    }

    public void setChecklistVersionCollection(Collection<ChecklistVersion> checklistVersionCollection) {
        this.checklistVersionCollection = checklistVersionCollection;
    }

    public Collection<ChecklistOwner> getChecklistOwnerCollection() {
        return checklistOwnerCollection;
    }

    public void setChecklistOwnerCollection(Collection<ChecklistOwner> checklistOwnerCollection) {
        this.checklistOwnerCollection = checklistOwnerCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Checklist)) {
            return false;
        }
        Checklist other = (Checklist) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jbadcode.checklist.persistence.entity.Checklist[ id=" + id + " ]";
    }
    
}
