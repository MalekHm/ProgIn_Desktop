/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Oumeyma
 */
@Entity
@Table(name = "admin", catalog = "evedb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Admin.findAll", query = "SELECT a FROM Admin a"),
    @NamedQuery(name = "Admin.findById", query = "SELECT a FROM Admin a WHERE a.id = :id"),
    @NamedQuery(name = "Admin.findByFirstNameAdmin", query = "SELECT a FROM Admin a WHERE a.firstNameAdmin = :firstNameAdmin"),
    @NamedQuery(name = "Admin.findByLastNameAdmin", query = "SELECT a FROM Admin a WHERE a.lastNameAdmin = :lastNameAdmin"),
    @NamedQuery(name = "Admin.findByEmail", query = "SELECT a FROM Admin a WHERE a.email = :email"),
    @NamedQuery(name = "Admin.findByPassword", query = "SELECT a FROM Admin a WHERE a.password = :password"),
    @NamedQuery(name = "Admin.findByBirthday", query = "SELECT a FROM Admin a WHERE a.birthday = :birthday"),
    @NamedQuery(name = "Admin.findByPicture", query = "SELECT a FROM Admin a WHERE a.picture = :picture")})
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "first_name_admin", nullable = false, length = 255)
    private String firstNameAdmin;
    @Basic(optional = false)
    @Column(name = "last_name_admin", nullable = false, length = 255)
    private String lastNameAdmin;
    @Basic(optional = false)
    @Column(name = "email", nullable = false, length = 255)
    private String email;
    @Basic(optional = false)
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    @Basic(optional = false)
    @Column(name = "birthday", nullable = false, length = 255)
    private String birthday;
    @Basic(optional = false)
    @Column(name = "picture", nullable = false, length = 255)
    private String picture;
    @OneToMany(mappedBy = "idAdminId")
    private Collection<Blog> blogCollection;
    @OneToMany(mappedBy = "idAdminId")
    private Collection<Community> communityCollection;

    public Admin() {
    }

    public Admin(Integer id) {
        this.id = id;
    }

    public Admin(Integer id, String firstNameAdmin, String lastNameAdmin, String email, String password, String birthday, String picture) {
        this.id = id;
        this.firstNameAdmin = firstNameAdmin;
        this.lastNameAdmin = lastNameAdmin;
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.picture = picture;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstNameAdmin() {
        return firstNameAdmin;
    }

    public void setFirstNameAdmin(String firstNameAdmin) {
        this.firstNameAdmin = firstNameAdmin;
    }

    public String getLastNameAdmin() {
        return lastNameAdmin;
    }

    public void setLastNameAdmin(String lastNameAdmin) {
        this.lastNameAdmin = lastNameAdmin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @XmlTransient
    public Collection<Blog> getBlogCollection() {
        return blogCollection;
    }

    public void setBlogCollection(Collection<Blog> blogCollection) {
        this.blogCollection = blogCollection;
    }

    @XmlTransient
    public Collection<Community> getCommunityCollection() {
        return communityCollection;
    }

    public void setCommunityCollection(Collection<Community> communityCollection) {
        this.communityCollection = communityCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Admin)) {
            return false;
        }
        Admin other = (Admin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Admin[ id=" + id + " ]";
    }
    
}
