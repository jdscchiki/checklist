/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan David Segura
 */
@Entity
@Table(name = "project_owner")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProjectOwner.findAll", query = "SELECT p FROM ProjectOwner p")})
public class ProjectOwner implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @JoinColumn(name = "state", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private State state;
    @JoinColumn(name = "project", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Project project;
    @JoinColumn(name = "app_user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AppUser appUser;

    public ProjectOwner() {
    }

    public ProjectOwner(Integer id) {
        this.id = id;
    }

    public ProjectOwner(Integer id, Date creationDate) {
        this.id = id;
        this.creationDate = creationDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
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
        if (!(object instanceof ProjectOwner)) {
            return false;
        }
        ProjectOwner other = (ProjectOwner) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jbadcode.checklist.persistence.entity.ProjectOwner[ id=" + id + " ]";
    }
    
}
