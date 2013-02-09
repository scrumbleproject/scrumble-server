/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "processstatus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Processstatus.findAll", query = "SELECT p FROM Processstatus p"),
    @NamedQuery(name = "Processstatus.findByIdProcessStatus", query = "SELECT p FROM Processstatus p WHERE p.idProcessStatus = :idProcessStatus"),
    @NamedQuery(name = "Processstatus.findByCodeStatus", query = "SELECT p FROM Processstatus p WHERE p.codeStatus = :codeStatus"),
    @NamedQuery(name = "Processstatus.findByTitleStatus", query = "SELECT p FROM Processstatus p WHERE p.titleStatus = :titleStatus")})
public class Processstatus implements Serializable {
    @OneToMany(mappedBy = "idProcessStatus")
    private Collection<Sprint> sprintCollection;
    @Column(name = "sort_order")
    private Integer sortOrder;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_process_status")
    private Integer idProcessStatus;
    @Size(max = 3)
    @Column(name = "code_status")
    private String codeStatus;
    @Size(max = 45)
    @Column(name = "title_status")
    private String titleStatus;
    @OneToMany(mappedBy = "idProcessStatus")
    private Collection<Task> taskCollection;
    @OneToMany(mappedBy = "idProcessStatus")
    private Collection<Userstory> userstoryCollection;

    public Processstatus() {
    }

    public Processstatus(Integer idProcessStatus) {
        this.idProcessStatus = idProcessStatus;
    }

    public Integer getIdProcessStatus() {
        return idProcessStatus;
    }

    public void setIdProcessStatus(Integer idProcessStatus) {
        this.idProcessStatus = idProcessStatus;
    }

    public String getCodeStatus() {
        return codeStatus;
    }

    public void setCodeStatus(String codeStatus) {
        this.codeStatus = codeStatus;
    }

    public String getTitleStatus() {
        return titleStatus;
    }

    public void setTitleStatus(String titleStatus) {
        this.titleStatus = titleStatus;
    }

    @XmlTransient
    public Collection<Task> getTaskCollection() {
        return taskCollection;
    }

    public void setTaskCollection(Collection<Task> taskCollection) {
        this.taskCollection = taskCollection;
    }

    @XmlTransient
    public Collection<Userstory> getUserstoryCollection() {
        return userstoryCollection;
    }

    public void setUserstoryCollection(Collection<Userstory> userstoryCollection) {
        this.userstoryCollection = userstoryCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProcessStatus != null ? idProcessStatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Processstatus)) {
            return false;
        }
        Processstatus other = (Processstatus) object;
        if ((this.idProcessStatus == null && other.idProcessStatus != null) || (this.idProcessStatus != null && !this.idProcessStatus.equals(other.idProcessStatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scrumble.server.entities.Processstatus[ idProcessStatus=" + idProcessStatus + " ]";
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    @XmlTransient
    public Collection<Sprint> getSprintCollection() {
        return sprintCollection;
    }

    public void setSprintCollection(Collection<Sprint> sprintCollection) {
        this.sprintCollection = sprintCollection;
    }
    
}
