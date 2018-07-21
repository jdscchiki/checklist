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
import javax.persistence.Id;
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
@Table(name = "state")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "State.findAll", query = "SELECT s FROM State s")})
public class State implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 20)
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "state")
    @JsonView(GenericView.Collection.class)
    private Collection<RevisionResultItem> revisionResultItemCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "state")
    @JsonView(GenericView.Collection.class)
    private Collection<Project> projectCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "state")
    @JsonView(GenericView.Collection.class)
    private Collection<Checklist> checklistCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "state")
    @JsonView(GenericView.Collection.class)
    private Collection<ChecklistVersion> checklistVersionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "state")
    @JsonView(GenericView.Collection.class)
    private Collection<ChecklistItem> checklistItemCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "state")
    @JsonView(GenericView.Collection.class)
    private Collection<AppUser> appUserCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "state")
    @JsonView(GenericView.Collection.class)
    private Collection<ProjectOwner> projectOwnerCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "state")
    @JsonView(GenericView.Collection.class)
    private Collection<ProjectSubversion> projectSubversionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "state")
    @JsonView(GenericView.Collection.class)
    private Collection<ProjectVersion> projectVersionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "state")
    @JsonView(GenericView.Collection.class)
    private Collection<ProjectRevision> projectRevisionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "state")
    @JsonView(GenericView.Collection.class)
    private Collection<ChecklistOwner> checklistOwnerCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "state")
    @JsonView(GenericView.Collection.class)
    private Collection<RevisionResult> revisionResultCollection;

    public State() {
    }

    public State(Integer id) {
        this.id = id;
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

    public Collection<RevisionResultItem> getRevisionResultItemCollection() {
        return revisionResultItemCollection;
    }

    public void setRevisionResultItemCollection(Collection<RevisionResultItem> revisionResultItemCollection) {
        this.revisionResultItemCollection = revisionResultItemCollection;
    }

    public Collection<Project> getProjectCollection() {
        return projectCollection;
    }

    public void setProjectCollection(Collection<Project> projectCollection) {
        this.projectCollection = projectCollection;
    }

    public Collection<Checklist> getChecklistCollection() {
        return checklistCollection;
    }

    public void setChecklistCollection(Collection<Checklist> checklistCollection) {
        this.checklistCollection = checklistCollection;
    }

    public Collection<ChecklistVersion> getChecklistVersionCollection() {
        return checklistVersionCollection;
    }

    public void setChecklistVersionCollection(Collection<ChecklistVersion> checklistVersionCollection) {
        this.checklistVersionCollection = checklistVersionCollection;
    }

    public Collection<ChecklistItem> getChecklistItemCollection() {
        return checklistItemCollection;
    }

    public void setChecklistItemCollection(Collection<ChecklistItem> checklistItemCollection) {
        this.checklistItemCollection = checklistItemCollection;
    }

    public Collection<AppUser> getAppUserCollection() {
        return appUserCollection;
    }

    public void setAppUserCollection(Collection<AppUser> appUserCollection) {
        this.appUserCollection = appUserCollection;
    }

    public Collection<ProjectOwner> getProjectOwnerCollection() {
        return projectOwnerCollection;
    }

    public void setProjectOwnerCollection(Collection<ProjectOwner> projectOwnerCollection) {
        this.projectOwnerCollection = projectOwnerCollection;
    }

    public Collection<ProjectSubversion> getProjectSubversionCollection() {
        return projectSubversionCollection;
    }

    public void setProjectSubversionCollection(Collection<ProjectSubversion> projectSubversionCollection) {
        this.projectSubversionCollection = projectSubversionCollection;
    }

    public Collection<ProjectVersion> getProjectVersionCollection() {
        return projectVersionCollection;
    }

    public void setProjectVersionCollection(Collection<ProjectVersion> projectVersionCollection) {
        this.projectVersionCollection = projectVersionCollection;
    }

    public Collection<ProjectRevision> getProjectRevisionCollection() {
        return projectRevisionCollection;
    }

    public void setProjectRevisionCollection(Collection<ProjectRevision> projectRevisionCollection) {
        this.projectRevisionCollection = projectRevisionCollection;
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
        if (!(object instanceof State)) {
            return false;
        }
        State other = (State) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jbadcode.checklist.persistence.entity.State[ id=" + id + " ]";
    }
    
}
