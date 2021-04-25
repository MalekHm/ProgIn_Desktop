/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services.impl;

import com.esprit.models.Publicity;
import com.esprit.services.IService;
import com.esprit.utils.DataSource;
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
public class ServicePublicityImpl implements IService<Publicity> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Publicity t) {
        try {
            String requete1 = "UPDATE publicity SET position='NAN' WHERE position=?";
            PreparedStatement pst1 = cnx.prepareStatement(requete1);
            pst1.setString(1, t.getPosition());
            pst1.executeUpdate();

            String requete = "INSERT INTO publicity (start_date_pub,end_date_pub,description_pub,picture_pub,title_pub,position,link) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDate(1, t.getStart_date_pub());
            pst.setDate(2, t.getEnd_date_pub());
            pst.setString(3, t.getDescription_pub());
            pst.setString(4, t.getPicture_pub());
            pst.setString(5, t.getTitle_pub());
            pst.setString(6, t.getPosition());
            pst.setString(7, t.getLink());

            pst.executeUpdate();
            System.out.println("Publicity ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Publicity t) {
        try {
            String requete = "DELETE FROM publicity WHERE id=" + t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Publicity supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Publicity t) {
        try {
            String requete1 = "UPDATE publicity SET position='NAN' WHERE position=?";
            PreparedStatement pst1 = cnx.prepareStatement(requete1);
            pst1.setString(1, t.getPosition());
            pst1.executeUpdate();

            String requete = "UPDATE publicity SET start_date_pub=?,end_date_pub=?,description_pub=?,picture_pub=?,title_pub=?,position=?,link=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setDate(1, t.getStart_date_pub());
            pst.setDate(2, t.getEnd_date_pub());
            pst.setString(3, t.getDescription_pub());
            pst.setString(4, t.getPicture_pub());
            pst.setString(5, t.getTitle_pub());
            pst.setString(6, t.getPosition());
            pst.setString(7, t.getLink());
            pst.setInt(8, t.getId());
            pst.executeUpdate();

            System.out.println("Publicity modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Publicity> afficher() {
        List<Publicity> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM publicity";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Publicity(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
     public List<Publicity> recherche(String crt) {
        List<Publicity> list = new ArrayList<>();

        try {
            crt="'%"+crt+"%'";
            String requete = "select * from publicity WHERE title_pub LIKE " +crt+" OR description_pub LIKE "+crt+" OR link LIKE "+crt+" OR position LIKE "+crt;
            PreparedStatement pst = cnx.prepareStatement(requete);
            cnx.prepareStatement(requete).executeQuery();
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Publicity(rs.getInt(1), rs.getDate(2), rs.getDate(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

}
