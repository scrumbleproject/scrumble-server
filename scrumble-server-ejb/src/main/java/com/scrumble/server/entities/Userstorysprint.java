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
@Table(name = "userstorysprint")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Userstorysprint.findAll", query = "SELECT u FROM Userstorysprint u"),
    @NamedQuery(name = "Userstorysprint.findByIdSprint", query = "SELECT u FROM Userstorysprint u WHERE u.userstorysprintPK.idSprint = :idSprint"),
    @NamedQuery(name = "Userstorysprint.findByIdSprintAndIdUserstory", query = "SELECT u FROM Userstorysprint u WHERE u.userstorysprintPK.idSprint = :idSprint and u.userstorysprintPK.idUserstory = :idUserstory"),
    @NamedQuery(name = "Userstorysprint.findByIdUserstory", query = "SELECT u FROM Userstorysprint u WHERE u.userstorysprintPK.idUserstory = :idUserstory"),
    @NamedQuery(name = "Userstorysprint.findByDateStart", query = "SELECT u FROM Userstorysprint u WHERE u.dateStart = :dateStart"),
    @NamedQuery(name = "Userstorysprint.findByDateEnd", query = "SELECT u FROM Userstorysprint u WHERE u.dateEnd = :dateEnd")})
public class Userstorysprint implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserstorysprintPK userstorysprintPK;
    @Column(name = "date_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStart;
    @Column(name = "date_end")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnd;
    @JoinColumn(name = "id_userstory", referencedColumnName = "id_userstory", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Userstory userstory;
    @JoinColumn(name = "id_sprint", referencedColumnName = "id_sprint", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Sprint sprint;

    public Userstorysprint() {
    }

    public Userstorysprint(UserstorysprintPK userstorysprintPK) {
        this.userstorysprintPK = userstorysprintPK;
    }

    public Userstorysprint(int idSprint, int idUserstory) {
        this.userstorysprintPK = new UserstorysprintPK(idSprint, idUserstory);
    }

    public UserstorysprintPK getUserstorysprintPK() {
        return userstorysprintPK;
    }

    public void setUserstorysprintPK(UserstorysprintPK userstorysprintPK) {
        this.userstorysprintPK = userstorysprintPK;
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

    public Userstory getUserstory() {
        return userstory;
    }

    public void setUserstory(Userstory userstory) {
        this.userstory = userstory;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userstorysprintPK != null ? userstorysprintPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userstorysprint)) {
            return false;
        }
        Userstorysprint other = (Userstorysprint) object;
        if ((this.userstorysprintPK == null && other.userstorysprintPK != null) || (this.userstorysprintPK != null && !this.userstorysprintPK.equals(other.userstorysprintPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scrumble.server.entities.Userstorysprint[ userstorysprintPK=" + userstorysprintPK + " ]";
    }
    
}
