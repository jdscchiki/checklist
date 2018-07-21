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
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juan David Segura
 */
@Entity
@Table(name = "revision_result")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RevisionResult.findAll", query = "SELECT r FROM RevisionResult r")})
public class RevisionResult implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 1024)
    @Column(name = "comment")
    private String comment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "revisionResult")
    @JsonView(GenericView.Collection.class)
    private Collection<RevisionResultItem> revisionResultItemCollection;
    @JoinColumn(name = "state", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private State state;
    @JoinColumn(name = "checklist_version", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ChecklistVersion checklistVersion;
    @JoinColumn(name = "project_revision", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ChecklistItem projectRevision;
    @JoinColumn(name = "executor", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AppUser executor;

    public RevisionResult() {
    }

    public RevisionResult(Integer id) {
        this.id = id;
    }

    public RevisionResult(Integer id, Date creationDate) {
        this.id = id;
        this.creationDate = creationDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Collection<RevisionResultItem> getRevisionResultItemCollection() {
        return revisionResultItemCollection;
    }

    public void setRevisionResultItemCollection(Collection<RevisionResultItem> revisionResultItemCollection) {
        this.revisionResultItemCollection = revisionResultItemCollection;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public ChecklistVersion getChecklistVersion() {
        return checklistVersion;
    }

    public void setChecklistVersion(ChecklistVersion checklistVersion) {
        this.checklistVersion = checklistVersion;
    }

    public ChecklistItem getProjectRevision() {
        return projectRevision;
    }

    public void setProjectRevision(ChecklistItem projectRevision) {
        this.projectRevision = projectRevision;
    }

    public AppUser getExecutor() {
        return executor;
    }

    public void setExecutor(AppUser executor) {
        this.executor = executor;
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
        if (!(object instanceof RevisionResult)) {
            return false;
        }
        RevisionResult other = (RevisionResult) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jbadcode.checklist.persistence.entity.RevisionResult[ id=" + id + " ]";
    }
    
}
