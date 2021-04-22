/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import IServices.IProduct;
import entity.CategoryProduct;
import entity.Product;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author hela1
 */
public class SProduct implements IProduct<Product> {
Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void add(Product t) {
         try {
            String requete = "INSERT INTO Product (Name ,Description, Price, Pricer, Quantity, category_product_id, urlimg) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getName());
            pst.setString(2, t.getDescription());
            pst.setDouble(3, t.getPrice());
            pst.setDouble(4, t.getPricer());
            pst.setInt(5, t.getQuantity());
            pst.setInt(6, t.getCategoryProductId().getId());
            pst.setString(7, t.getUrlimg());
            pst.executeUpdate();
            System.out.println("Produit ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void delete(Product t) {
        try {
            String requete = "DELETE FROM Product WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Produit supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void edit(Product p) {
        
 
        try {
            String requete = "UPDATE Product SET Name=?,Description=?,Price=?,Pricer=?,Quantity=?, category_product_id=? "
                    + " WHERE id=?";
            PreparedStatement prst =  DataSource.getInstance().getCnx().prepareStatement(requete);
            
            prst.setInt(1, p.getId());
            prst.setString(2, p.getName());
            prst.setString(3, p.getDescription());    
            prst.setDouble(4, p.getPrice());
            prst.setDouble(5, p.getPricer());
            prst.setInt(6, p.getQuantity());
            prst.setInt(7, p.getCategoryProductId().getId());
            
            prst.executeUpdate();
            System.out.println("Produit Modifié.");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    

    @Override
    public List<Product> show() {
         ObservableList<Product> Product=FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM product";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
             String req2= "SELECT * FROM category_product where id='"+rs.getInt(1)+"'";
             Statement st2 = cnx.createStatement();
             ResultSet rs2= st2.executeQuery(req2);
             CategoryProduct c =new CategoryProduct();
             while(rs2.next()){
                 c.setId(rs2.getInt(1));
                 c.setNameCategory(rs2.getString(2));
             }
             System.out.println(c);
             Product p = new Product();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(10));
                p.setDescription(rs.getString(7));
                p.setPrice(rs.getDouble(2));
                p.setPricer(rs.getDouble(2));
                p.setCategoryProductId(c);
                p.setUrlimg(rs.getString(9));
                
                System.out.println(rs.getString(16));
                 Product.add(p);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return Product;
    }

@Override
    public List<Product> searchByName(String Name) {
         ObservableList<Product> Product=FXCollections.observableArrayList(); 
         try {
          String requete = " SELECT * from product  where (Name like '"+Name+"%')" ;
          Statement st =cnx.createStatement();
           ResultSet rs=st.executeQuery(requete);
        while (rs.next()) {
             String req2= "SELECT * FROM category_product where id='"+rs.getInt(1)+"'";
             Statement st2 = cnx.createStatement();
             ResultSet rs2= st2.executeQuery(req2);
             CategoryProduct c =new CategoryProduct();
             
             while(rs2.next()){
                 c.setId(rs2.getInt(1));
                 c.setNameCategory(rs2.getString(2));
             }
             
             Product p = new Product();
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setDescription(rs.getString(3));
                p.setPrice(rs.getDouble(4));
                p.setPricer(rs.getDouble(5));
                p.setQuantity(rs.getInt(6));
                p.setCategoryProductId(c);
                p.setUrlimg(rs.getString(8));
                
                System.out.println(rs.getString(6));
                 Product.add(p);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
             System.out.println(Product);
    }
    return Product;
    }
    
    @Override
      public List<Product> list(){
    
        ObservableList<Product> Produits=FXCollections.observableArrayList();
    
        try {
            
            
            String req="SELECT * FROM Product";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(req);
 
            while(rs.next()){
                
                
             String req2= "SELECT * FROM category_product where id='"+rs.getInt(1)+"'";
             Statement st2 = DataSource.getInstance().getCnx().createStatement();
             ResultSet rs2= st2.executeQuery(req2);
             CategoryProduct c=new CategoryProduct();
             
             while(rs2.next()){
                 
                 c.setId(rs2.getInt(1));
                 c.setNameCategory(rs2.getString(2));
                 
             }
             
             
             System.out.println(c);
             
                Product p = new Product();
                
                
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setDescription(rs.getString(3));
                p.setPrice(rs.getDouble(4));
                p.setPricer(rs.getDouble(5));
                p.setQuantity(rs.getInt(6));
                p.setCategoryProductId(c);
                p.setUrlimg(rs.getString(8));
                
                

                System.out.println(rs.getString(2));
                
            Produits.add(p);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    
        
        
            
    
    return Produits;
}

    @Override
    public void count() {
         String requete = "SELECT COUNT(id), Category_product_id FROM Product GROUP BY Category_product_id" ;
        try {
           
           Statement st =cnx.createStatement();
           ResultSet rs=st.executeQuery(requete);
            
           int colonne1;
           int colonne2;
             
             while(rs.next()){
                colonne1=rs.getInt(1);
                colonne2=rs.getInt(2);
                 System.out.println("Nbproduits = "+colonne1 +" --> " + "categoryId= "+colonne2);
             }
                     
        } catch (SQLException ex) {
            Logger.getLogger(SProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void saveFile(File f,String name) {
        InputStream inStream = null;
	OutputStream outStream = null;
		
    	try{
    	  File Copyfile =new File("C:\\Users\\hela1\\Documents\\NetBeansProjects\\Progin\\web\\uploads\\ImgProduit"+name);
    		
    	    inStream = new FileInputStream(f);
    	    outStream = new FileOutputStream(Copyfile);
    	    byte[] buffer = new byte[1024];
    		
    	    int length;
    	    while ((length = inStream.read(buffer)) > 0){
    	    	outStream.write(buffer, 0, length);
    	    }
    	 
    	    inStream.close();
    	    outStream.close();
    	    
    	    System.out.println("File is copied successful!");
    	    
    	}catch(IOException e){
              }
   
}
    
     public List<Product> FindByPrice(double min,double max){
          
        ObservableList<Product> Produits=FXCollections.observableArrayList();
    
        try {
            
            
            String req="SELECT * FROM Product where price BETWEEN '"+min+"' AND '"+max+"' " ;
            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(req);
                        
            
            while(rs.next()){
                
                
             String req2= "SELECT * FROM category_product where id='"+rs.getInt(1)+"'";
             Statement st2 = DataSource.getInstance().getCnx().createStatement();
             ResultSet rs2= st2.executeQuery(req2);
             CategoryProduct c=new CategoryProduct();
             
             while(rs2.next()){
                 
                 c.setId(rs2.getInt(1));
                 c.setNameCategory(rs2.getString(2));
                 
             }

                Product p = new Product();
                
                
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setDescription(rs.getString(3));
                p.setPrice(rs.getDouble(4));
                p.setPricer(rs.getDouble(5));
                p.setQuantity(rs.getInt(6));
                p.setCategoryProductId(c);
                p.setUrlimg(rs.getString(8));
                

                
                Produits.add(p);
            }
            
            } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    
                   
    
    return Produits;
      
      }  
     
     
     public List<Product> FindByPricer(double min,double max){
          
        ObservableList<Product> Produits=FXCollections.observableArrayList();
    
        try {
            
            
            String req="SELECT * FROM Product where pricer BETWEEN '"+min+"' AND '"+max+"' " ;
            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(req);
                        
            
            while(rs.next()){
                
                
             String req2= "SELECT * FROM category_product where id='"+rs.getInt(1)+"'";
             Statement st2 = DataSource.getInstance().getCnx().createStatement();
             ResultSet rs2= st2.executeQuery(req2);
             CategoryProduct c=new CategoryProduct();
             
             while(rs2.next()){
                 
                 c.setId(rs2.getInt(1));
                 c.setNameCategory(rs2.getString(2));
                 
             }

                Product p = new Product();
                
                
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setDescription(rs.getString(3));
                p.setPrice(rs.getDouble(4));
                p.setPricer(rs.getDouble(5));
                p.setQuantity(rs.getInt(6));
                p.setCategoryProductId(c);
                p.setUrlimg(rs.getString(8));
                

                
                Produits.add(p);
            }
            
            } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    
                   
    
    return Produits;
      
      }  
 //---------------------------------------------------------------------------
      
      public List<Product> FindByCategory(int i){
          
                    
        ObservableList<Product> Produits=FXCollections.observableArrayList();
    
        try {
            
            
            String req="SELECT * FROM Product where category_product_id="+i+" " ;
            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(req);
                        
            
            while(rs.next()){
                
                
             String req2= "SELECT * FROM category_product where id='"+rs.getInt(1)+"'";
             Statement st2 = DataSource.getInstance().getCnx().createStatement();
             ResultSet rs2= st2.executeQuery(req2);
             CategoryProduct c=new CategoryProduct();
             
              while(rs2.next()){
                 
                 c.setId(rs2.getInt(1));
                 c.setNameCategory(rs2.getString(2));
                 
             }

                Product p = new Product();
                
                
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setDescription(rs.getString(3));
                p.setPrice(rs.getDouble(4));
                p.setPricer(rs.getDouble(5));
                p.setQuantity(rs.getInt(6));
                p.setCategoryProductId(c);
                p.setUrlimg(rs.getString(8));
                

                
                Produits.add(p);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
              
    
    return Produits;
          
      }     
      
    //--------------------------------------------------------------------------
      
      public List<Product> OrderByAsc(){
          
          ObservableList<Product> Produits=FXCollections.observableArrayList();
    
        try {
            
            
            String req="SELECT * FROM product ORDER BY price ASC ";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(req);
            

            while(rs.next()){
                
                
             String req2= "SELECT * FROM category_product where id='"+rs.getInt(1)+"'";
             Statement st2 = DataSource.getInstance().getCnx().createStatement();
             ResultSet rs2= st2.executeQuery(req2);
             CategoryProduct c=new CategoryProduct();
             
              while(rs2.next()){
                 
                 c.setId(rs2.getInt(1));
                 c.setNameCategory(rs2.getString(2));
                 
             }

                Product p = new Product();
                
                
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setDescription(rs.getString(3));
                p.setPrice(rs.getDouble(4));
                p.setPricer(rs.getDouble(5));
                p.setQuantity(rs.getInt(6));
                p.setCategoryProductId(c);
                p.setUrlimg(rs.getString(8));
                

                
                Produits.add(p);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    
        
        
            
    
    return Produits;
      }     

      //------------------------------------------------------------------------
      
      public List<Product> OrderByDesc(){
          
          ObservableList<Product> Produits=FXCollections.observableArrayList();
    
        try {
            
            
            String req="SELECT * FROM Product ORDER BY price DESC";
            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(req);
            

            while(rs.next()){
                
                
             String req2= "SELECT * FROM category_product where id='"+rs.getInt(1)+"'";
             Statement st2 = DataSource.getInstance().getCnx().createStatement();
             ResultSet rs2= st2.executeQuery(req2);
             CategoryProduct c=new CategoryProduct();
             
              while(rs2.next()){
                 
                 c.setId(rs2.getInt(1));
                 c.setNameCategory(rs2.getString(2));
                 
             }

                Product p = new Product();
                
                
                p.setId(rs.getInt(1));
                p.setName(rs.getString(2));
                p.setDescription(rs.getString(3));
                p.setPrice(rs.getDouble(4));
                p.setPricer(rs.getDouble(5));
                p.setQuantity(rs.getInt(6));
                p.setCategoryProductId(c);
                p.setUrlimg(rs.getString(8));
                

                
                Produits.add(p);
            }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    
        
        
            
    
    return Produits;
      }     
      
}




