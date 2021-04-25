/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.user;
import interfaceservice.userinterface;
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
import utils.Maconnexion;

/**
 *
 * @author Dell
 */
public class userservice implements userinterface {
    Connection ds ;
ObservableList<user> userliste= FXCollections.observableArrayList();
    public userservice() {
        ds=Maconnexion.getInstance().getConnection();
    }
    
    
    @Override
    public void ajouterUser(user u) {
        try {
            String requete  = "INSERT INTO `user` (`id`, `username` , `nom`, `prenom`, `mail`, `password`, `role`) VALUES (NULL, '"+u.getUsername()+"' , '"+u.getNom()+"', '"+u.getPrenom()+"', '"+u.getMail()+"', '"+u.getPassword()+"', '"+u.getRole()+"') ";
            Statement st = ds.createStatement() ;
            st.executeUpdate(requete);
            System.out.println("User ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
   

    @Override
    public List<user> listuser() {
        
        List<user> myList = new ArrayList<>();
        try { // LES var declaré dans le try ne sont vue que dans le try, et inversement pour en dhors du try
            String requete = "SELECT * from user"; //MAJUSCULE NON OBLIGATOIRE 
            
            Statement st = ds.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                user u = new user();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString("username"));
                u.setNom(rs.getString("nom"));
                u.setPrenom(rs.getString("prenom"));
                u.setMail(rs.getString("mail"));
                u.setPassword(rs.getString("password"));
                 u.setRole(rs.getString("role"));
                
                userliste.addAll(u);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return userliste;

    }
    

    @Override
    public void supprimerUser(user u) {
        try {
            String requete = "DELETE FROM user WHERE id=?";
            PreparedStatement pst = ds.prepareStatement(requete);
            pst.setInt(1, u.getId());
            pst.executeUpdate();
            System.out.println("User supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @Override
    public void modifierUser(user u) {
        try {
            String requete = " UPDATE user SET username=?, nom=?, prenom=?, mail=?, password=?, role=? WHERE id=?" ;
            PreparedStatement pst= ds.prepareStatement(requete);
            pst.setString(1,u.getUsername());
            pst.setString(2,u.getNom());
            pst.setString(3,u.getPrenom());
            pst.setString(4,u.getMail());
            pst.setString(5,u.getPassword());
              pst.setString(6, u.getRole());
            pst.setInt(7, u.getId());
          
            pst.executeUpdate();
            System.out.println("User modifié!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
}
