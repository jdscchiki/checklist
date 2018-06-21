/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.persistence.entity;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juan David Segura
 */
@Entity
@Table(name = "checklist_version")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChecklistVersion.findAll", query = "SELECT c FROM ChecklistVersion c")})
public class ChecklistVersion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "value")
    private int value;
    @JoinColumn(name = "state", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private State state;
    @JoinColumn(name = "checklist", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Checklist checklist;
    @OneToMany(mappedBy = "checklistVersion")
    private Collection<ChecklistItem> checklistItemCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "checklistVersion")
    private Collection<RevisionResult> revisionResultCollection;

    public ChecklistVersion() {
    }

    public ChecklistVersion(Integer id) {
        this.id = id;
    }

    public ChecklistVersion(Integer id, int value) {
        this.id = id;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Checklist getChecklist() {
        return checklist;
    }

    public void setChecklist(Checklist checklist) {
        this.checklist = checklist;
    }

    @XmlTransient
    public Collection<ChecklistItem> getChecklistItemCollection() {
        return checklistItemCollection;
    }

    public void setChecklistItemCollection(Collection<ChecklistItem> checklistItemCollection) {
        this.checklistItemCollection = checklistItemCollection;
    }

    @XmlTransient
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
        if (!(object instanceof ChecklistVersion)) {
            return false;
        }
        ChecklistVersion other = (ChecklistVersion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jbadcode.checklist.persistence.entity.ChecklistVersion[ id=" + id + " ]";
    }
    
}
