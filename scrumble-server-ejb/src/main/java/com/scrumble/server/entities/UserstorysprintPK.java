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
public class UserstorysprintPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_sprint")
    private int idSprint;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_userstory")
    private int idUserstory;

    public UserstorysprintPK() {
    }

    public UserstorysprintPK(int idSprint, int idUserstory) {
        this.idSprint = idSprint;
        this.idUserstory = idUserstory;
    }

    public int getIdSprint() {
        return idSprint;
    }

    public void setIdSprint(int idSprint) {
        this.idSprint = idSprint;
    }

    public int getIdUserstory() {
        return idUserstory;
    }

    public void setIdUserstory(int idUserstory) {
        this.idUserstory = idUserstory;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idSprint;
        hash += (int) idUserstory;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserstorysprintPK)) {
            return false;
        }
        UserstorysprintPK other = (UserstorysprintPK) object;
        if (this.idSprint != other.idSprint) {
            return false;
        }
        if (this.idUserstory != other.idUserstory) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scrumble.server.entities.UserstorysprintPK[ idSprint=" + idSprint + ", idUserstory=" + idUserstory + " ]";
    }
    
}
