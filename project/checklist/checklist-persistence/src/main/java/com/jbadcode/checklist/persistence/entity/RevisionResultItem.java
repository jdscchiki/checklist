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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan David Segura
 */
@Entity
@Table(name = "revision_result_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RevisionResultItem.findAll", query = "SELECT r FROM RevisionResultItem r")})
public class RevisionResultItem implements Serializable {

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
    @JoinColumn(name = "state", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private State state;
    @JoinColumn(name = "value", referencedColumnName = "id")
    @ManyToOne
    private ResultValue value;
    @JoinColumn(name = "revision_result", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private RevisionResult revisionResult;
    @JoinColumn(name = "checklist_item", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ChecklistItem checklistItem;

    public RevisionResultItem() {
    }

    public RevisionResultItem(Integer id) {
        this.id = id;
    }

    public RevisionResultItem(Integer id, Date creationDate) {
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public ResultValue getValue() {
        return value;
    }

    public void setValue(ResultValue value) {
        this.value = value;
    }

    public RevisionResult getRevisionResult() {
        return revisionResult;
    }

    public void setRevisionResult(RevisionResult revisionResult) {
        this.revisionResult = revisionResult;
    }

    public ChecklistItem getChecklistItem() {
        return checklistItem;
    }

    public void setChecklistItem(ChecklistItem checklistItem) {
        this.checklistItem = checklistItem;
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
        if (!(object instanceof RevisionResultItem)) {
            return false;
        }
        RevisionResultItem other = (RevisionResultItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jbadcode.checklist.persistence.entity.RevisionResultItem[ id=" + id + " ]";
    }
    
}
