/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services;

import com.esprit.models.Personne;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aissa
 */
public class ServicePersonne2 implements IService<Personne> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Personne t) {
        try {
            String requete = "INSERT INTO personne (nom,prenom) VALUES ('" + t.getNom() + "','" + t.getPrenom() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Personne ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Personne t) {
        try {
            String requete = "DELETE FROM personne WHERE id=" + t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Personne supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Personne t) {
        try {
            String requete = "UPDATE personne SET nom='" + t.getNom() + "',prenom='" + t.getPrenom() + "' WHERE id=" + t.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(requete);
            System.out.println("Personne modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Personne> afficher() {
        List<Personne> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM personne";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                list.add(new Personne(rs.getInt(1), rs.getString(2), rs.getString("prenom")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
}
