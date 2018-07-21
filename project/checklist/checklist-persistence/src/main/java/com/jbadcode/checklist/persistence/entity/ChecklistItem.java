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
@Table(name = "checklist_item")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ChecklistItem.findAll", query = "SELECT c FROM ChecklistItem c")})
public class ChecklistItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2048)
    @Column(name = "value")
    private String value;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "checklistItem")
    @JsonView(GenericView.Collection.class)
    private Collection<RevisionResultItem> revisionResultItemCollection;
    @JoinColumn(name = "state", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private State state;
    @JoinColumn(name = "checklist_version", referencedColumnName = "id")
    @ManyToOne
    private ChecklistVersion checklistVersion;
    @OneToMany(mappedBy = "checklistParentItem")
    @JsonView(GenericView.Collection.class)
    private Collection<ChecklistItem> checklistItemCollection;
    @JoinColumn(name = "checklist_parent_item", referencedColumnName = "id")
    @ManyToOne
    private ChecklistItem checklistParentItem;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectRevision")
    @JsonView(GenericView.Collection.class)
    private Collection<RevisionResult> revisionResultCollection;

    public ChecklistItem() {
    }

    public ChecklistItem(Integer id) {
        this.id = id;
    }

    public ChecklistItem(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    public Collection<ChecklistItem> getChecklistItemCollection() {
        return checklistItemCollection;
    }

    public void setChecklistItemCollection(Collection<ChecklistItem> checklistItemCollection) {
        this.checklistItemCollection = checklistItemCollection;
    }

    public ChecklistItem getChecklistParentItem() {
        return checklistParentItem;
    }

    public void setChecklistParentItem(ChecklistItem checklistParentItem) {
        this.checklistParentItem = checklistParentItem;
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
        if (!(object instanceof ChecklistItem)) {
            return false;
        }
        ChecklistItem other = (ChecklistItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jbadcode.checklist.persistence.entity.ChecklistItem[ id=" + id + " ]";
    }
    
}
