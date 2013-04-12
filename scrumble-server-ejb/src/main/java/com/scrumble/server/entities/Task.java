/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.entities;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cyril
 */
@Entity
@Table(name = "task")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Task.findAll", query = "SELECT t FROM Task t"),
    @NamedQuery(name = "Task.findByIdTask", query = "SELECT t FROM Task t WHERE t.idTask = :idTask"),
    @NamedQuery(name = "Task.findByTitle", query = "SELECT t FROM Task t WHERE t.title = :title"),
    @NamedQuery(name = "Task.findByEstimation", query = "SELECT t FROM Task t WHERE t.estimation = :estimation"),
    @NamedQuery(name = "Task.findByIdUserstory", query = "SELECT t FROM Task t WHERE t.idUserstory = :idUserstory"),
    @NamedQuery(name = "Task.quickSearchSimple", query = "SELECT t FROM Task t WHERE t.title like :pattern"),
    @NamedQuery(name = "Task.quickSearchExact", query = "SELECT t FROM Task t WHERE t.title = :pattern")})

public class Task implements Serializable, Comparable<Task> {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "task")
    private Collection<Sprinttaskassignation> sprinttaskassignationCollection;
    @JoinColumn(name = "id_process_status", referencedColumnName = "id_process_status")
    @ManyToOne
    private Processstatus idProcessStatus;
    @Column(name = "estimation")
    private Integer estimation;
    @Size(max = 20)
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_task")
    private Integer idTask;
    @Size(max = 45)
    @Column(name = "title")
    private String title;
    @JoinColumn(name = "id_userstory", referencedColumnName = "id_userstory")
    @ManyToOne
    private Userstory idUserstory;

    public Task() {
    }

    public Task(Integer idTask) {
        this.idTask = idTask;
    }

    public Integer getIdTask() {
        return idTask;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlTransient
    public Userstory getIdUserstory() {
        return idUserstory;
    }

    public void setIdUserstory(Userstory idUserstory) {
        this.idUserstory = idUserstory;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTask != null ? idTask.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Task)) {
            return false;
        }
        Task other = (Task) object;
        if ((this.idTask == null && other.idTask != null) || (this.idTask != null && !this.idTask.equals(other.idTask))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scrumble.server.entities.Task[ idTask=" + idTask + " ]";
    }

    public Integer getEstimation() {
        return estimation;
    }

    public void setEstimation(Integer estimation) {
        this.estimation = estimation;
    }

    @XmlTransient
    public Collection<Sprinttaskassignation> getSprinttaskassignationCollection() {
        return sprinttaskassignationCollection;
    }

    public void setSprinttaskassignationCollection(Collection<Sprinttaskassignation> sprinttaskassignationCollection) {
        this.sprinttaskassignationCollection = sprinttaskassignationCollection;
    }

    public Processstatus getIdProcessStatus() {
        return idProcessStatus;
    }

    public void setIdProcessStatus(Processstatus idProcessStatus) {
        this.idProcessStatus = idProcessStatus;
    }

    @Override
    public int compareTo(Task t) {
        return this.idProcessStatus.getSortOrder().compareTo(t.getIdProcessStatus().getSortOrder());
    }
    
}
