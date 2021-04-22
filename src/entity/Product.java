/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author hela1
 */

public class Product  {
    private Integer id; 
    private String name;
    private String description;
    private double price;
    private double pricer;
    private int quantity;
    private CategoryProduct categoryProductId;
    private String urlimg;

    public Product() {
    }

    public Product(Integer id) {
        this.id = id;
    }

    public Product(Integer id, String name, String description, double price, double pricer, int quantity, CategoryProduct categoryId, String urlimg) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.pricer = pricer;
        this.quantity = quantity;
        this.categoryProductId = categoryId;
        this.urlimg = urlimg;
    }
     public Product(Integer id, String name, String description, double price, double pricer, int quantity, String urlimg) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.pricer = pricer;
        this.quantity = quantity;
        this.urlimg = urlimg;
        
    }
 
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPricer() {
        return pricer;
    }

    public void setPricer(double pricer) {
        this.pricer = pricer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public CategoryProduct getCategoryProductId() {
        return categoryProductId;
    }

    public void setCategoryProductId(CategoryProduct categoryProductId) {
        this.categoryProductId = categoryProductId;
    }
    
    
     public String getUrlimg() {
        return urlimg;
    }

    public void setUrlimg(String urlimg) {
        this.urlimg = urlimg;
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
        if (!(object instanceof Product)) {
            return false;
        }
        Product other = (Product) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", description=" + description + ", price=" + price + ", pricer=" + pricer + ", quantity=" + quantity + ", categoryProductId=" + categoryProductId + '}';
    }

   
    
}
