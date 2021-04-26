/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services.impl;

import com.esprit.models.Event;
import com.esprit.models.Reservation;
import com.esprit.services.IService;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ousse
 */
public class ReservationServiceImpl implements IService<Reservation> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Reservation t) {

        try {

            String requete = "INSERT INTO reservation (event_id,user_id,nb_participants,status,reservation_date,resteapayer,reduction,token,paid) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getEvent_id());
            pst.setInt(2, t.getUser_id());
            pst.setInt(3, t.getNb_participants());
            pst.setBoolean(4, t.isStatus());
            pst.setDate(5, t.getReservation_date());
            pst.setFloat(6, t.getResteapayer());
            pst.setFloat(7, t.getReduction());
            pst.setString(8, t.getToken());
            pst.setBoolean(9, t.isPaid());
            pst.executeUpdate();
            System.out.println("reservation ajoutée !");

            String requete1 = "UPDATE event SET place_disponible=place_disponible-? WHERE id=?";
            PreparedStatement pst1 = cnx.prepareStatement(requete1);
            pst1.setInt(2, t.getEvent_id());
            pst1.setInt(1, t.getNb_participants());
            pst1.executeUpdate();
            System.out.println("Event modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Reservation t) {
try {
            String requete = "DELETE FROM reservation WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Event supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }      }

    @Override
    public void modifier(Reservation t) {
        try {
            String requete = "UPDATE reservation SET event_id=?,user_id=?,nb_participants=?,status=?,reservation_date=?,resteapayer=?,reduction=?,token=?,paid=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(10, t.getId());
            pst.setInt(1, t.getEvent_id());
            pst.setInt(2, t.getUser_id());
            pst.setInt(3, t.getNb_participants());
            pst.setBoolean(4, t.isStatus());
            pst.setDate(5, t.getReservation_date());
            pst.setFloat(6, t.getResteapayer());
            pst.setFloat(7, t.getReduction());
            pst.setString(8, t.getToken());
            pst.setBoolean(9, t.isPaid());
            pst.executeUpdate();
            System.out.println("Reservation modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }  
    }

    @Override
    public List<Reservation> afficher() {
List<Reservation> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM Reservation";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Reservation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getBoolean(5), rs.getDate(6), rs.getFloat(7), rs.getFloat(8), rs.getString(9), rs.getBoolean(10)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;   
    }

    public void annulerReservation(int id_reservation) {

        try {
            String requete = "UPDATE reservation SET status=? WHERE id=?";
            PreparedStatement pst1 = cnx.prepareStatement(requete);
            pst1.setBoolean(1, false);
            pst1.setInt(2, id_reservation);
            pst1.executeUpdate();
            System.out.println("Reservation annulé !");
        } catch (SQLException ex) {
            Logger.getLogger(ReservationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public List<Reservation> afficherParUserId(int user_id){
           List<Reservation> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM reservation WHERE user_id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
                        pst.setInt(1, user_id);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Reservation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getBoolean(5), rs.getDate(6), rs.getFloat(7), rs.getFloat(8), rs.getString(9), rs.getBoolean(10)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
     public List<Reservation> afficherParEventId(int event_id){
           List<Reservation> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM reservation WHERE event_id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
                        pst.setInt(1, event_id);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Reservation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getBoolean(5), rs.getDate(6), rs.getFloat(7), rs.getFloat(8), rs.getString(9), rs.getBoolean(10)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
  
}
