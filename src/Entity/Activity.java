/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Oumeyma
 */

public class Activity implements Serializable {

   
    private Integer id;
   
    private String titleActivity;
   
    private String adressActivity;
    
    private String descriptionActivity;
   
    private double priceActivity;
    
    private int likes;
   
   
    private Categoryactivity categoryId;

    public Activity() {
    }

    public Activity(Integer id) {
        this.id = id;
    }

    public Activity(Categoryactivity categoryId, String titleActivity, String adressActivity, String descriptionActivity, double priceActivity ) {
        this.titleActivity = titleActivity;
        this.adressActivity = adressActivity;
        this.descriptionActivity = descriptionActivity;
        this.priceActivity = priceActivity;
        this.categoryId = categoryId ;
    }

    public Activity(Integer id, String titleActivity, String adressActivity, String descriptionActivity, double priceActivity) {
        this.id = id;
        this.titleActivity = titleActivity;
        this.adressActivity = adressActivity;
        this.descriptionActivity = descriptionActivity;
        this.priceActivity = priceActivity;
    }

    public Activity(int aInt, String string, String string0, double aDouble) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitleActivity() {
        return titleActivity;
    }

    public void setTitleActivity(String titleActivity) {
        this.titleActivity = titleActivity;
    }

    public String getAdressActivity() {
        return adressActivity;
    }

    public void setAdressActivity(String adressActivity) {
        this.adressActivity = adressActivity;
    }

    public String getDescriptionActivity() {
        return descriptionActivity;
    }

    public void setDescriptionActivity(String descriptionActivity) {
        this.descriptionActivity = descriptionActivity;
    }

    public double getPriceActivity() {
        return priceActivity;
    }

    public void setPriceActivity(double priceActivity) {
        this.priceActivity = priceActivity;
    }

    

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Categoryactivity getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Categoryactivity categoryId) {
        this.categoryId = categoryId;
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
        if (!(object instanceof Activity)) {
            return false;
        }
        Activity other = (Activity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Activity{" + "id=" + id + ", titleActivity=" + titleActivity + ", adressActivity=" + adressActivity + ", descriptionActivity=" + descriptionActivity + ", priceActivity=" + priceActivity + ", categoryId=" + categoryId + '}';
    }

    
    
    
}
