/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services.impl;

import com.esprit.models.Event;
import com.esprit.services.IService;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ousse
 */
public class EventServiceImpl implements IService<Event>{
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Event t) {
 try {
            String requete = "INSERT INTO event (category_id,titre,description,nb_persons,price_event,start_date,end_date,place_disponible) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getCategory_id());
            pst.setString(2, t.getTitre());
            pst.setString(3, t.getDescription());
            pst.setInt(4, t.getNb_persons());
            pst.setFloat(5, t.getPrice_event());
            pst.setDate(6, t.getStart_date());
            pst.setDate(7, t.getEnd_date());
            pst.setInt(8, t.getPlace_disponible());
            pst.executeUpdate();
            System.out.println("Event ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    
    }
    

    @Override
    public void supprimer(Event t) {
     try {
            String requete = "DELETE FROM event WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Event supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    @Override
    public void modifier(Event t) {
  try {
            String requete = "UPDATE Event SET category_id=?,titre=?,description=?,nb_persons=?,price_event=?,nb_views=?,start_date=?,end_date=?,place_disponible=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(10, t.getId());
            pst.setInt(1, t.getCategory_id());
            pst.setString(2, t.getTitre());
            pst.setString(3, t.getDescription());
            pst.setInt(4, t.getNb_persons());
            pst.setFloat(5, t.getPrice_event());
            pst.setInt(6, t.getNb_views());
            pst.setDate(7, t.getStart_date());
            pst.setDate(8, t.getEnd_date());
            pst.setInt(9, t.getPlace_disponible());
            pst.executeUpdate();
            System.out.println("Event modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }      }

    @Override
    public List<Event> afficher() {
         List<Event> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM event";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Event(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getFloat(6), rs.getInt(7), rs.getDate(8), rs.getDate(9), rs.getInt(10)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    
    public List<Event> rechercherByCategoryId(int category_id){
          List<Event> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM event WHERE category_id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
                        pst.setInt(1, category_id);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Event(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getFloat(6), rs.getInt(7), rs.getDate(8), rs.getDate(9), rs.getInt(10)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
      
    public List<Event> rechercherByTitre(String titre){
          List<Event> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM event WHERE titre like ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
                        pst.setString(1, "%"+titre+"%");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Event(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getFloat(6), rs.getInt(7), rs.getDate(8), rs.getDate(9), rs.getInt(10)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

  
    }
    

