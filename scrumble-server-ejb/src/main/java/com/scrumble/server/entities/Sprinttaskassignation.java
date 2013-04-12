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
@Table(name = "sprinttaskassignation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sprinttaskassignation.findAll", query = "SELECT s FROM Sprinttaskassignation s"),
    @NamedQuery(name = "Sprinttaskassignation.findByIdTask", query = "SELECT s FROM Sprinttaskassignation s WHERE s.sprinttaskassignationPK.idTask = :idTask"),
    @NamedQuery(name = "Sprinttaskassignation.findByIdSprint", query = "SELECT s FROM Sprinttaskassignation s WHERE s.sprinttaskassignationPK.idSprint = :idSprint"),
    @NamedQuery(name = "Sprinttaskassignation.findByIdMember", query = "SELECT s FROM Sprinttaskassignation s WHERE s.sprinttaskassignationPK.idMember = :idMember"),
    @NamedQuery(name = "Sprinttaskassignation.findByIdSprintAndIdTask", query = "SELECT s FROM Sprinttaskassignation s WHERE s.sprinttaskassignationPK.idSprint = :idSprint and s.sprinttaskassignationPK.idTask = :idTask"),
    @NamedQuery(name = "Sprinttaskassignation.findByAssignation", query = "SELECT s FROM Sprinttaskassignation s WHERE s.sprinttaskassignationPK.idTask = :idTask and s.sprinttaskassignationPK.idSprint = :idSprint and s.sprinttaskassignationPK.idMember = :idMember")})
public class Sprinttaskassignation implements Serializable {
    @Column(name = "date_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStart;
    @Column(name = "date_end")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnd;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SprinttaskassignationPK sprinttaskassignationPK;
    @JoinColumn(name = "id_member", referencedColumnName = "id_member", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Member1 member1;
    @JoinColumn(name = "id_sprint", referencedColumnName = "id_sprint", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Sprint sprint;
    @JoinColumn(name = "id_task", referencedColumnName = "id_task", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Task task;

    public Sprinttaskassignation() {
    }

    public Sprinttaskassignation(SprinttaskassignationPK sprinttaskassignationPK) {
        this.sprinttaskassignationPK = sprinttaskassignationPK;
    }

    public Sprinttaskassignation(int idTask, int idSprint, int idMember) {
        this.sprinttaskassignationPK = new SprinttaskassignationPK(idTask, idSprint, idMember);
    }

    public SprinttaskassignationPK getSprinttaskassignationPK() {
        return sprinttaskassignationPK;
    }

    public void setSprinttaskassignationPK(SprinttaskassignationPK sprinttaskassignationPK) {
        this.sprinttaskassignationPK = sprinttaskassignationPK;
    }

    public Member1 getMember1() {
        return member1;
    }

    public void setMember1(Member1 member1) {
        this.member1 = member1;
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
        hash += (sprinttaskassignationPK != null ? sprinttaskassignationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sprinttaskassignation)) {
            return false;
        }
        Sprinttaskassignation other = (Sprinttaskassignation) object;
        if ((this.sprinttaskassignationPK == null && other.sprinttaskassignationPK != null) || (this.sprinttaskassignationPK != null && !this.sprinttaskassignationPK.equals(other.sprinttaskassignationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scrumble.server.entities.Sprinttaskassignation[ sprinttaskassignationPK=" + sprinttaskassignationPK + " ]";
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
    
}
