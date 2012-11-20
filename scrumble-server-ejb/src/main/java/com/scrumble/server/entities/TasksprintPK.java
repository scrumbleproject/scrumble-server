/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author cyril
 */
@Embeddable
public class TasksprintPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_task")
    private int idTask;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_sprint")
    private int idSprint;

    public TasksprintPK() {
    }

    public TasksprintPK(int idTask, int idSprint) {
        this.idTask = idTask;
        this.idSprint = idSprint;
    }

    public int getIdTask() {
        return idTask;
    }

    public void setIdTask(int idTask) {
        this.idTask = idTask;
    }

    public int getIdSprint() {
        return idSprint;
    }

    public void setIdSprint(int idSprint) {
        this.idSprint = idSprint;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTask;
        hash += (int) idSprint;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TasksprintPK)) {
            return false;
        }
        TasksprintPK other = (TasksprintPK) object;
        if (this.idTask != other.idTask) {
            return false;
        }
        if (this.idSprint != other.idSprint) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scrumble.server.entities.TasksprintPK[ idTask=" + idTask + ", idSprint=" + idSprint + " ]";
    }
    
}
