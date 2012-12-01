/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.entities;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cyril
 */
@Entity
@Table(name = "sprint")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sprint.findAll", query = "SELECT s FROM Sprint s"),
    @NamedQuery(name = "Sprint.findByIdSprint", query = "SELECT s FROM Sprint s WHERE s.idSprint = :idSprint"),
    @NamedQuery(name = "Sprint.findByNumSprint", query = "SELECT s FROM Sprint s WHERE s.numSprint = :numSprint"),
    @NamedQuery(name = "Sprint.findByTitle", query = "SELECT s FROM Sprint s WHERE s.title = :title"),
    @NamedQuery(name = "Sprint.findByVelocity", query = "SELECT s FROM Sprint s WHERE s.velocity = :velocity"),
    @NamedQuery(name = "Sprint.findByDateStart", query = "SELECT s FROM Sprint s WHERE s.dateStart = :dateStart"),
    @NamedQuery(name = "Sprint.findByDateEnd", query = "SELECT s FROM Sprint s WHERE s.dateEnd = :dateEnd"),
    @NamedQuery(name = "Sprint.findByDuree", query = "SELECT s FROM Sprint s WHERE s.duree = :duree")})
public class Sprint implements Serializable {
    @Column(name = "velocity")
    private Integer velocity;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sprint")
    private Collection<Sprinttaskassignation> sprinttaskassignationCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sprint")
    private Integer idSprint;
    @Column(name = "num_sprint")
    private Integer numSprint;
    @Size(max = 45)
    @Column(name = "title")
    private String title;
    @Column(name = "date_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStart;
    @Column(name = "date_end")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnd;
    @Column(name = "duree")
    private Integer duree;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sprint")
    private Collection<Tasksprint> tasksprintCollection;
    @JoinColumn(name = "id_project", referencedColumnName = "id_project")
    @ManyToOne
    private Project idProject;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sprint")
    private Collection<Userstorysprint> userstorysprintCollection;

    public Sprint() {
    }

    public Sprint(Integer idSprint) {
        this.idSprint = idSprint;
    }

    public Integer getIdSprint() {
        return idSprint;
    }

    public void setIdSprint(Integer idSprint) {
        this.idSprint = idSprint;
    }

    public Integer getNumSprint() {
        return numSprint;
    }

    public void setNumSprint(Integer numSprint) {
        this.numSprint = numSprint;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    @XmlTransient
    public Collection<Tasksprint> getTasksprintCollection() {
        return tasksprintCollection;
    }

    public void setTasksprintCollection(Collection<Tasksprint> tasksprintCollection) {
        this.tasksprintCollection = tasksprintCollection;
    }

    public Project getIdProject() {
        return idProject;
    }

    public void setIdProject(Project idProject) {
        this.idProject = idProject;
    }

    @XmlTransient
    public Collection<Userstorysprint> getUserstorysprintCollection() {
        return userstorysprintCollection;
    }

    public void setUserstorysprintCollection(Collection<Userstorysprint> userstorysprintCollection) {
        this.userstorysprintCollection = userstorysprintCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSprint != null ? idSprint.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sprint)) {
            return false;
        }
        Sprint other = (Sprint) object;
        if ((this.idSprint == null && other.idSprint != null) || (this.idSprint != null && !this.idSprint.equals(other.idSprint))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scrumble.server.entities.Sprint[ idSprint=" + idSprint + " ]";
    }

    public Integer getVelocity() {
        return velocity;
    }

    public void setVelocity(Integer velocity) {
        this.velocity = velocity;
    }

    @XmlTransient
    public Collection<Sprinttaskassignation> getSprinttaskassignationCollection() {
        return sprinttaskassignationCollection;
    }

    public void setSprinttaskassignationCollection(Collection<Sprinttaskassignation> sprinttaskassignationCollection) {
        this.sprinttaskassignationCollection = sprinttaskassignationCollection;
    }
    
}
