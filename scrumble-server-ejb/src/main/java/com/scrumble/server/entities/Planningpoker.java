/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scrumble.server.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author cyril
 */
@Entity
@Table(name = "planningpoker")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Planningpoker.findAll", query = "SELECT p FROM Planningpoker p"),
    @NamedQuery(name = "Planningpoker.findByIdPlanningpoker", query = "SELECT p FROM Planningpoker p WHERE p.idPlanningpoker = :idPlanningpoker"),
    @NamedQuery(name = "Planningpoker.findByValue", query = "SELECT p FROM Planningpoker p WHERE p.value = :value")})
public class Planningpoker implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_planningpoker")
    private Integer idPlanningpoker;
    @Size(max = 20)
    @Column(name = "value")
    private String value;

    public Planningpoker() {
    }

    public Planningpoker(Integer idPlanningpoker) {
        this.idPlanningpoker = idPlanningpoker;
    }

    public Integer getIdPlanningpoker() {
        return idPlanningpoker;
    }

    public void setIdPlanningpoker(Integer idPlanningpoker) {
        this.idPlanningpoker = idPlanningpoker;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlanningpoker != null ? idPlanningpoker.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Planningpoker)) {
            return false;
        }
        Planningpoker other = (Planningpoker) object;
        if ((this.idPlanningpoker == null && other.idPlanningpoker != null) || (this.idPlanningpoker != null && !this.idPlanningpoker.equals(other.idPlanningpoker))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.scrumble.server.entities.Planningpoker[ idPlanningpoker=" + idPlanningpoker + " ]";
    }
    
}
