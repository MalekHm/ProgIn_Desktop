/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Categoryactivity;
import IService.ICategoryActivity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author Oumeyma
 */
public class SCategoryActivity implements ICategoryActivity <Categoryactivity>{
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Categoryactivity a) {
        try {
            String requete = "INSERT INTO categoryactivity (name_category_activity) VALUES (?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, a.getNameCategoryActivity());
            pst.executeUpdate();
            System.out.println("CategoryActivity ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Categoryactivity a) {
       try {
            String requete = "DELETE FROM categoryactivity WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, a.getId());
            pst.executeUpdate();
            System.out.println("Categorie supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Categoryactivity a) {
        try {
            String requete = "UPDATE categoryactivity SET name_category_activity=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, a.getId());
            pst.setString(2, a.getNameCategoryActivity());
            pst.executeUpdate();
            System.out.println("CategoryActivitymodifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    @Override
    public List<Categoryactivity> afficher() {
        List<Categoryactivity> list;
        list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM Categoryactivity";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               list.add(new Categoryactivity(rs.getInt(1),rs.getString("name_category_activity")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    public List<Categoryactivity> getTrier() throws SQLException  {
        
   
    List<Categoryactivity> arr1=new ArrayList<>();
     try {
    List<Categoryactivity> arr=new ArrayList<>();
    Statement st =cnx.createStatement();
    ResultSet rs=st.executeQuery("select * from Categoryactivity ORDER BY name_category_activity DESC");
     while (rs.next()) {                
              
              int id=rs.getInt(1);
             
              String nameCategoryActivity=rs.getString(2);
              
           Categoryactivity  a =new Categoryactivity(id,nameCategoryActivity);
               arr.add(a);
     }  
       
        return arr;

    }
     catch (SQLException ex) {
            Logger.getLogger(SCategoryActivity.class.getName()).log(Level.SEVERE, null, ex);
     }
        
     return arr1;
    }
    
    
    
    
    
}

    

