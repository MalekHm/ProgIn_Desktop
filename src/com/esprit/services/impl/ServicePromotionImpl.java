/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services.impl;

import com.esprit.models.Promotion;
import com.esprit.services.IService;
import com.esprit.utils.DataSource;
import com.esprit.utils.Mail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Malek
 */
public class ServicePromotionImpl implements IService<Promotion> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Promotion t) {
        try {

            String requete = "INSERT INTO promotion (event_id,product_id,descriptionpromotion,start_date_promotion,end_date_promotion,pourcentage) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            if (t.getEvent_id() == 0) {
                pst.setString(1, null);
            } else {
                pst.setInt(1, t.getEvent_id());
            }
            if (t.getProduct_id() == 0) {
                pst.setString(2, null);
            } else {
                pst.setInt(2, t.getProduct_id());
            }
            pst.setString(3, t.getDescriptionpromotion());
            pst.setDate(4, t.getStart_date_promotion());
            pst.setDate(5, t.getEnd_date_promotion());
            pst.setFloat(6, t.getPourcentage());
            pst.executeUpdate();
            System.out.println("Promotion ajoutée !");
            Mail test = new Mail();
            for (String email : this.getUserEmail()) {
                test.envoyer(email, "New Promotion", t.getDescriptionpromotion());
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Promotion t) {
        try {
            String requete = "DELETE FROM promotion WHERE id=" + t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Promotion supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Promotion t) {
        try {

            String requete = "UPDATE promotion SET event_id=?,product_id=?,descriptionpromotion=?,start_date_promotion=?,end_date_promotion=?,pourcentage=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            if (t.getEvent_id() == 0) {
                pst.setString(1, null);
            } else {
                pst.setInt(1, t.getEvent_id());
            }
            if (t.getProduct_id() == 0) {
                pst.setString(2, null);
            } else {
                pst.setInt(2, t.getProduct_id());
            }
            pst.setString(3, t.getDescriptionpromotion());
            pst.setDate(4, t.getStart_date_promotion());
            pst.setDate(5, t.getEnd_date_promotion());
            pst.setFloat(6, t.getPourcentage());
            pst.setInt(7, t.getId());
            pst.executeUpdate();

            System.out.println("Promotion modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Promotion> afficher() {
        List<Promotion> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM promotion";
            PreparedStatement pst = cnx.prepareStatement(requete);
            cnx.prepareStatement(requete).executeQuery().first();
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Promotion(rs.getInt(1), rs.getInt(2), this.getEventName(rs.getInt(2)), rs.getInt(3), this.getProductName(rs.getInt(3)), rs.getString(4), rs.getDate(5), rs.getDate(6), rs.getFloat(7)));
                System.out.println();
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    public List<Promotion> recherche(String crt) {
        List<Promotion> list = new ArrayList<>();

        try {
            crt = "'%" + crt + "%'";
            String requete = "select * from promotion WHERE descriptionpromotion LIKE " + crt;
            PreparedStatement pst = cnx.prepareStatement(requete);
            cnx.prepareStatement(requete).executeQuery();
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Promotion(rs.getInt(1), rs.getInt(2), this.getEventName(rs.getInt(2)), rs.getInt(3), this.getProductName(rs.getInt(3)), rs.getString(4), rs.getDate(5), rs.getDate(6), rs.getFloat(7)));
                System.out.println();
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

    public String getEventName(int id) {
        String title = "";
        try {
            String requete = "SELECT titre FROM event where id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                title = rs.getString(1);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return title;

    }

    public String getProductName(int id) {
        String title = "";
        try {
            String requete = "SELECT name FROM product where id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                title = rs.getString(1);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return title;

    }

    public List getEventNameAndId() {
        List<String> res = new ArrayList<>();
        try {
            String requete = "SELECT id,titre FROM event";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                res.add(rs.getString(1) + "-" + rs.getString(2));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return res;

    }

    public List getProductNameAndId() {
        List<String> res = new ArrayList<>();
        try {
            String requete = "SELECT id,name FROM product";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                res.add(rs.getString(1) + "-" + rs.getString(2));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return res;

    }

    public List<String> getUserEmail() {
        List<String> list = new ArrayList<>();

        try {
            String requete = "SELECT email FROM user";
            PreparedStatement pst = cnx.prepareStatement(requete);
            cnx.prepareStatement(requete).executeQuery().first();
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(1));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

}
