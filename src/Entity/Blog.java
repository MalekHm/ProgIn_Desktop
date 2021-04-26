/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;
import java.util.Date;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Oumeyma
 */
public class Blog implements Serializable {
        
 
    Date dateCourante = new Date();
    DateFormat formatJJMMAAAA = new SimpleDateFormat("dd/MM/yyyy");
    String a=formatJJMMAAAA.format(dateCourante);

    private static final long serialVersionUID = 1L;
    
    
    private Integer id;
   
    private String title;
   
    private String description;
   
    private String urlimg;
    
    private String dateAjout;
   
    private Admin idAdminId;
    

    public Blog() {
    }

    public Blog(Integer id) {
        this.id = id;
    }

    public Blog(Integer id, String title, String description, String dateAjout) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateAjout = a;
    }

    public Blog(Integer id, String title, String description, String urlimg, String dateAjout) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.urlimg = urlimg;
        this.dateAjout = dateAjout;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlimg() {
        return urlimg;
    }

    public void setUrlimg(String urlimg) {
        this.urlimg = urlimg;
    }

    public String getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(String dateAjout) {
        this.dateAjout = a;
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
        if (!(object instanceof Blog)) {
            return false;
        }
        Blog other = (Blog) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Blog{" + "dateCourante=" + dateCourante + ", formatJJMMAAAA=" + formatJJMMAAAA + ", a=" + a + ", id=" + id + ", title=" + title + ", description=" + description + ", urlimg=" + urlimg + ", dateAjout=" + dateAjout + '}';
    }

   

    

    
    
}

