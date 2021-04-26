/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services.impl;

import com.esprit.models.Category;

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
 * @author ousse
 */
public class CategoryServiceImpl implements IService<Category>{
    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Category t) {
        try {
            String requete = "INSERT INTO category (name,description) VALUES (?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getName());
            pst.setString(2, t.getDescription());
            pst.executeUpdate();
            System.out.println("Category ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Category t) {
  try {
            String requete = "DELETE FROM category WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Category supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }     }

    @Override
    public void modifier(Category t) {
    try {
            String requete = "UPDATE category SET name=?,description=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(3, t.getId());
            pst.setString(1, t.getName());
            pst.setString(2, t.getDescription());
            pst.executeUpdate();
            System.out.println("category modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    @Override
    public List<Category> afficher() {
       List<Category> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM category";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
      public List<Category> afficherParid(int id){
           List<Category> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM category WHERE user_id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
                        pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
             list.add(new Category(rs.getInt(1),rs.getString(2), rs.getString(3)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
      public List<Category> rechercherByName(String name){
          List<Category> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM category WHERE name like ?";
            PreparedStatement pst = cnx.prepareStatement(requete);
                        pst.setString(1, "%"+name+"%");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1),rs.getString(2), rs.getString(3)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
}
