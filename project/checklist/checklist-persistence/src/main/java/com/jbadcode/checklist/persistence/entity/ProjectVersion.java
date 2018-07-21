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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juan David Segura
 */
@Entity
@Table(name = "project_version")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProjectVersion.findAll", query = "SELECT p FROM ProjectVersion p")})
public class ProjectVersion implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectVersion")
    @JsonView(GenericView.Collection.class)
    private Collection<ProjectSubversion> projectSubversionCollection;
    @JoinColumn(name = "state", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private State state;
    @JoinColumn(name = "project", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Project project;

    public ProjectVersion() {
    }

    public ProjectVersion(Integer id) {
        this.id = id;
    }

    public ProjectVersion(Integer id, int value) {
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

    public Collection<ProjectSubversion> getProjectSubversionCollection() {
        return projectSubversionCollection;
    }

    public void setProjectSubversionCollection(Collection<ProjectSubversion> projectSubversionCollection) {
        this.projectSubversionCollection = projectSubversionCollection;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectVersion)) {
            return false;
        }
        ProjectVersion other = (ProjectVersion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jbadcode.checklist.persistence.entity.ProjectVersion[ id=" + id + " ]";
    }
    
}
