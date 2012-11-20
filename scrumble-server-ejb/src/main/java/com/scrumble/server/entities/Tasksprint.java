/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cyril
 */
@Entity
@Table(name = "tasksprint")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tasksprint.findAll", query = "SELECT t FROM Tasksprint t"),
    @NamedQuery(name = "Tasksprint.findByIdTask", query = "SELECT t FROM Tasksprint t WHERE t.tasksprintPK.idTask = :idTask"),
    @NamedQuery(name = "Tasksprint.findByIdSprint", query = "SELECT t FROM Tasksprint t WHERE t.tasksprintPK.idSprint = :idSprint"),
    @NamedQuery(name = "Tasksprint.findByDateStart", query = "SELECT t FROM Tasksprint t WHERE t.dateStart = :dateStart"),
    @NamedQuery(name = "Tasksprint.findByDateEnd", query = "SELECT t FROM Tasksprint t WHERE t.dateEnd = :dateEnd")})
public class Tasksprint implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TasksprintPK tasksprintPK;
    @Column(name = "date_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStart;
    @Column(name = "date_end")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnd;
    @JoinColumn(name = "id_sprint", referencedColumnName = "id_sprint", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Sprint sprint;
    @JoinColumn(name = "id_task", referencedColumnName = "id_task", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Task task;

    public Tasksprint() {
    }

    public Tasksprint(TasksprintPK tasksprintPK) {
        this.tasksprintPK = tasksprintPK;
    }

    public Tasksprint(int idTask, int idSprint) {
        this.tasksprintPK = new TasksprintPK(idTask, idSprint);
    }

    public TasksprintPK getTasksprintPK() {
        return tasksprintPK;
    }

    public void setTasksprintPK(TasksprintPK tasksprintPK) {
        this.tasksprintPK = tasksprintPK;
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

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tasksprintPK != null ? tasksprintPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tasksprint)) {
            return false;
        }
        Tasksprint other = (Tasksprint) object;
        if ((this.tasksprintPK == null && other.tasksprintPK != null) || (this.tasksprintPK != null && !this.tasksprintPK.equals(other.tasksprintPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scrumble.server.entities.Tasksprint[ tasksprintPK=" + tasksprintPK + " ]";
    }
    
}
