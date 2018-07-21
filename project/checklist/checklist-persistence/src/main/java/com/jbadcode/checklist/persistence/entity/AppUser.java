/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.persistence.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.jbadcode.checklist.entityfiltering.AppUserView;
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

/**
 *
 * @author Juan David Segura
 */
@Entity
@Table(name = "app_user")
@XmlRootElement
public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "nick")
    private String nick;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "password")
    @JsonView(AppUserView.Password.class)
    private String password;
    @Size(max = 128)
    @Column(name = "firts_name")
    private String firtsName;
    @Size(max = 128)
    @Column(name = "last_name")
    private String lastName;
    @JoinColumn(name = "state", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private State state;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appUser")
    @JsonView(GenericView.Collection.class)
    private Collection<ProjectOwner> projectOwnerCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "appUser")
    @JsonView(GenericView.Collection.class)
    private Collection<ChecklistOwner> checklistOwnerCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "executor")
    @JsonView(GenericView.Collection.class)
    private Collection<RevisionResult> revisionResultCollection;

    public AppUser() {
    }

    public AppUser(Integer id) {
        this.id = id;
    }

    public AppUser(Integer id, String nick, String password) {
        this.id = id;
        this.nick = nick;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirtsName() {
        return firtsName;
    }

    public void setFirtsName(String firtsName) {
        this.firtsName = firtsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
    
    public Collection<ProjectOwner> getProjectOwnerCollection() {
        return projectOwnerCollection;
    }

    public void setProjectOwnerCollection(Collection<ProjectOwner> projectOwnerCollection) {
        this.projectOwnerCollection = projectOwnerCollection;
    }

    public Collection<ChecklistOwner> getChecklistOwnerCollection() {
        return checklistOwnerCollection;
    }

    public void setChecklistOwnerCollection(Collection<ChecklistOwner> checklistOwnerCollection) {
        this.checklistOwnerCollection = checklistOwnerCollection;
    }

    public Collection<RevisionResult> getRevisionResultCollection() {
        return revisionResultCollection;
    }

    public void setRevisionResultCollection(Collection<RevisionResult> revisionResultCollection) {
        this.revisionResultCollection = revisionResultCollection;
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
        if (!(object instanceof AppUser)) {
            return false;
        }
        AppUser other = (AppUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jbadcode.checklist.persistence.entity.AppUser[ id=" + id + " ]";
    }
    
}
