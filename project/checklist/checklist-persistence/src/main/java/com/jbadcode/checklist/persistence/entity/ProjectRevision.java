/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jbadcode.checklist.persistence.entity;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan David Segura
 */
@Entity
@Table(name = "project_revision")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProjectRevision.findAll", query = "SELECT p FROM ProjectRevision p")})
public class ProjectRevision implements Serializable {

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
    @JoinColumn(name = "project_subversion", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ProjectSubversion projectSubversion;

    public ProjectRevision() {
    }

    public ProjectRevision(Integer id) {
        this.id = id;
    }

    public ProjectRevision(Integer id, int value) {
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

    public ProjectSubversion getProjectSubversion() {
        return projectSubversion;
    }

    public void setProjectSubversion(ProjectSubversion projectSubversion) {
        this.projectSubversion = projectSubversion;
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
        if (!(object instanceof ProjectRevision)) {
            return false;
        }
        ProjectRevision other = (ProjectRevision) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jbadcode.checklist.persistence.entity.ProjectRevision[ id=" + id + " ]";
    }
    
}
