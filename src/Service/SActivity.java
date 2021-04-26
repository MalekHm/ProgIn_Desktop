/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Activity;
import Entity.Categoryactivity;
import IService.IActivity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author Oumeyma
 */
public class SActivity implements IActivity <Activity> {
Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Activity b) {
        try {
            String requete = "INSERT INTO Activity (category_id , title_activity, adress_activity ,description_activity, price_activity,likes) VALUES (?,?,?,?,?, ?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, b.getCategoryId().getId());
            pst.setString(2, b.getTitleActivity());
            pst.setString(3, b.getAdressActivity());
            pst.setString(4, b.getDescriptionActivity());
            pst.setDouble(5, b.getPriceActivity());
            pst.setInt(6, b.getLikes());
            
            pst.executeUpdate();
            System.out.println("Activity  ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Activity b) {
        try {
            String requete = "DELETE FROM Activity WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, b.getId());
            pst.executeUpdate();
            System.out.println("Activite supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Activity b) {
        try {
            String requete = "UPDATE Activity SET category_id=? titl_activity=? ,adress_activity=? ,description_activity=?,price_activity=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, b.getId());
           pst.setString(2, b.getTitleActivity());
            pst.setString(3, b.getAdressActivity());
            pst.setString(4, b.getDescriptionActivity());
            pst.setDouble(5, b.getPriceActivity());
             pst.setInt(6, b.getCategoryId().getId());
            pst.executeUpdate();
            System.out.println("Activite modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    @Override
    public List<Activity> afficher() {
        ObservableList<Activity> Activity=FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM activity";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
             String req2= "SELECT * FROM categoryactivity where id='"+rs.getInt(1)+"'";
             Statement st2 = cnx.createStatement();
             ResultSet rs2= st2.executeQuery(req2);
             Categoryactivity c =new Categoryactivity();
             while(rs2.next()){
                 c.setId(rs2.getInt(1));
                 c.setNameCategoryActivity(rs2.getString(2));
             }
             System.out.println(c);
             Activity p = new Activity();
                p.setId(rs.getInt(1));
                p.setTitleActivity(rs.getString(3));
                p.setAdressActivity(rs.getString(4));
                p.setDescriptionActivity(rs.getString(5));
                p.setPriceActivity(rs.getDouble(6));
                
                p.setCategoryId(c);
                
                
                System.out.println(rs.getString(1));
                 Activity.add(p);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return Activity;
    }

    /**
     *
     * @param titleActivity
     * @return
     */

    public  List<Activity> rechercheByName(String titleActivity) {
          ObservableList<Activity> Activity =FXCollections.observableArrayList(); 
         try {
          String requete = " SELECT * from activity  where (title_activity like '"+titleActivity+"%')" ;
          Statement st =cnx.createStatement();
           ResultSet rs=st.executeQuery(requete);
        while (rs.next()) {
             String req2= "SELECT * FROM categoryactivity where id='"+rs.getInt(1)+"'";
             Statement st2 = cnx.createStatement();
             ResultSet rs2= st2.executeQuery(req2);
             Categoryactivity c =new Categoryactivity();
             while(rs2.next()){
                 c.setId(rs2.getInt(1));
                 c.setNameCategoryActivity(rs2.getString(2));
             }
             System.out.println(c);
             Activity p = new Activity();
                p.setId(rs.getInt(1));
                p.setTitleActivity(rs.getString(3));
                p.setAdressActivity(rs.getString(4));
                p.setDescriptionActivity(rs.getString(5));
                p.setPriceActivity(rs.getDouble(6));
                
                p.setCategoryId(c);
                
                
                System.out.println(rs.getString(1));
                 Activity.add(p);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    

        return Activity;
    }

 public List<Activity> FindByCategory(int i){
          
                    
        ObservableList<Activity> Activity=FXCollections.observableArrayList();
    
        try {
            
            
            String req="SELECT * FROM Activity where category_id="+i+" " ;
            Statement st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs= st.executeQuery(req);
                        
            
           while (rs.next()) {
             String req2= "SELECT * FROM categoryactivity where id='"+rs.getInt(1)+"'";
             Statement st2 = cnx.createStatement();
             ResultSet rs2= st2.executeQuery(req2);
             Categoryactivity c =new Categoryactivity();
             while(rs2.next()){
                 c.setId(rs2.getInt(1));
                 c.setNameCategoryActivity(rs2.getString(2));
             }
             System.out.println(c);
             Activity p = new Activity();
                p.setId(rs.getInt(1));
                p.setTitleActivity(rs.getString(3));
                p.setAdressActivity(rs.getString(4));
                p.setDescriptionActivity(rs.getString(5));
                p.setPriceActivity(rs.getDouble(6));
                
                p.setCategoryId(c);
                
                
                System.out.println(rs.getString(1));
                 Activity.add(p);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    

        return Activity;
    }
}