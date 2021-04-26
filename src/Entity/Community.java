/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "community", catalog = "evedb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Community.findAll", query = "SELECT c FROM Community c"),
    @NamedQuery(name = "Community.findById", query = "SELECT c FROM Community c WHERE c.id = :id"),
    @NamedQuery(name = "Community.findByGrpName", query = "SELECT c FROM Community c WHERE c.grpName = :grpName"),
    @NamedQuery(name = "Community.findByEmail", query = "SELECT c FROM Community c WHERE c.email = :email"),
    @NamedQuery(name = "Community.findByPhone", query = "SELECT c FROM Community c WHERE c.phone = :phone")})
public class Community implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "grp_name", nullable = false, length = 255)
    private String grpName;
    @Basic(optional = false)
    @Column(name = "email", nullable = false, length = 255)
    private String email;
    @Basic(optional = false)
    @Column(name = "phone", nullable = false)
    private int phone;
    @Basic(optional = false)
    @Lob
    @Column(name = "description", nullable = false, length = 2147483647)
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "communityId")
    
    @JoinColumn(name = "id_admin_id", referencedColumnName = "id")
    @ManyToOne
    private Admin idAdminId;

    public Community() {
    }

    public Community(Integer id) {
        this.id = id;
    }

    public Community(Integer id, String grpName, String email, int phone, String description) {
        this.id = id;
        this.grpName = grpName;
        this.email = email;
        this.phone = phone;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGrpName() {
        return grpName;
    }

    public void setGrpName(String grpName) {
        this.grpName = grpName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    

    public Admin getIdAdminId() {
        return idAdminId;
    }

    public void setIdAdminId(Admin idAdminId) {
        this.idAdminId = idAdminId;
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
        if (!(object instanceof Community)) {
            return false;
        }
        Community other = (Community) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Community[ id=" + id + " ]";
    }
    
}
