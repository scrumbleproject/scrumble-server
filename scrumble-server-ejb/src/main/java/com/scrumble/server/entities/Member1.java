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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author cyril
 */
@Entity
@Table(name = "member")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Member1.findAll", query = "SELECT m FROM Member1 m"),
    @NamedQuery(name = "Member1.findByIdMember", query = "SELECT m FROM Member1 m WHERE m.idMember = :idMember"),
    @NamedQuery(name = "Member1.findByFirstname", query = "SELECT m FROM Member1 m WHERE m.firstname = :firstname"),
    @NamedQuery(name = "Member1.findByLastname", query = "SELECT m FROM Member1 m WHERE m.lastname = :lastname"),
    @NamedQuery(name = "Member1.findByLogin", query = "SELECT m FROM Member1 m WHERE m.login = :login"),
    //@NamedQuery(name = "Member1.findByPassword", query = "SELECT m FROM Member1 m WHERE m.password = :password")
    @NamedQuery(name = "Member1.quickSearchSimple", query = "SELECT m FROM Member1 m WHERE m.firstname like :pattern or m.lastname like :pattern or m.login like :pattern or m.email like :pattern"),
    @NamedQuery(name = "Member1.quickSearchExact", query = "SELECT m FROM Member1 m WHERE m.firstname = :pattern or m.lastname = :pattern or m.login = :pattern or m.email = :pattern")})
public class Member1 implements Serializable {
    @Size(max = 20)
    @Column(name = "internal_phone")
    private String internalPhone;
    @Size(max = 20)
    @Column(name = "mobile_phone")
    private String mobilePhone;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member1")
    private Collection<Sprinttaskassignation> sprinttaskassignationCollection;
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 75)
    @Column(name = "email")
    private String email;
    @ManyToMany(mappedBy = "member1Collection")
    private Collection<Project> projectCollection;
    @JoinColumn(name = "id_role", referencedColumnName = "id_role")
    @ManyToOne
    private Role idRole;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_member")
    private Integer idMember;
    @Size(max = 45)
    @Column(name = "firstname")
    private String firstname;
    @Size(max = 75)
    @Column(name = "lastname")
    private String lastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "login")
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "password")
    private String password;

    public Member1() {
    }

    public Member1(Integer idMember) {
        this.idMember = idMember;
    }

    public Member1(Integer idMember, String login, String password) {
        this.idMember = idMember;
        this.login = login;
        this.password = password;
    }

    public Integer getIdMember() {
        return idMember;
    }

    public void setIdMember(Integer idMember) {
        this.idMember = idMember;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMember != null ? idMember.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Member1)) {
            return false;
        }
        Member1 other = (Member1) object;
        if ((this.idMember == null && other.idMember != null) || (this.idMember != null && !this.idMember.equals(other.idMember))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Member[ idMember=" + idMember + ", firstname="+firstname+", lastname="+lastname+", login="+login+", password="+password+", email="+email+", mobilePhone="+mobilePhone+", internalPhone="+internalPhone+" ]";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public Collection<Project> getProjectCollection() {
        return projectCollection;
    }

    public void setProjectCollection(Collection<Project> projectCollection) {
        this.projectCollection = projectCollection;
    }

    public Role getIdRole() {
        return idRole;
    }

    public void setIdRole(Role idRole) {
        this.idRole = idRole;
    }

    @XmlTransient
    public Collection<Sprinttaskassignation> getSprinttaskassignationCollection() {
        return sprinttaskassignationCollection;
    }

    public void setSprinttaskassignationCollection(Collection<Sprinttaskassignation> sprinttaskassignationCollection) {
        this.sprinttaskassignationCollection = sprinttaskassignationCollection;
    }

    public String getInternalPhone() {
        return internalPhone;
    }

    public void setInternalPhone(String internalPhone) {
        this.internalPhone = internalPhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
    
}
