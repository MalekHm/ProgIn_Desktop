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
@Table(name = "categoryactivity", catalog = "evedb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Categoryactivity.findAll", query = "SELECT c FROM Categoryactivity c"),
    @NamedQuery(name = "Categoryactivity.findById", query = "SELECT c FROM Categoryactivity c WHERE c.id = :id"),
    @NamedQuery(name = "Categoryactivity.findByNameCategoryActivity", query = "SELECT c FROM Categoryactivity c WHERE c.nameCategoryActivity = :nameCategoryActivity")})
public class Categoryactivity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name_category_activity", nullable = false, length = 255)
    private String nameCategoryActivity;
    @OneToMany(mappedBy = "categoryId")
    private Collection<Activity> activityCollection;

    public Categoryactivity() {
    }

    public Categoryactivity(Integer id) {
        this.id = id;
    }

    public Categoryactivity(Integer id, String nameCategoryActivity) {
        this.id = id;
        this.nameCategoryActivity = nameCategoryActivity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameCategoryActivity() {
        return nameCategoryActivity;
    }

    public void setNameCategoryActivity(String nameCategoryActivity) {
        this.nameCategoryActivity = nameCategoryActivity;
    }

    @XmlTransient
    public Collection<Activity> getActivityCollection() {
        return activityCollection;
    }

    public void setActivityCollection(Collection<Activity> activityCollection) {
        this.activityCollection = activityCollection;
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
        if (!(object instanceof Categoryactivity)) {
            return false;
        }
        Categoryactivity other = (Categoryactivity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Categoryactivity{" + "id=" + id + ", nameCategoryActivity=" + nameCategoryActivity + ", activityCollection=" + activityCollection + '}';
    }

    
}
