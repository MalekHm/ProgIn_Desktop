/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import IServices.ICategoryProduct;
import entity.CategoryProduct;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DataSource;

/**
 *
 * @author hela1
 */
public class SCategoryProduct implements ICategoryProduct<CategoryProduct>{
 
    Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void add(CategoryProduct t) {
        try {
            String requete = "INSERT INTO category_product (name_category) VALUES (?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getNameCategory());
            pst.executeUpdate();
            System.out.println("Categorie ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void delete(CategoryProduct t) {
        try {
            String requete = "DELETE FROM category_product WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Categorie supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
      
    }

    @Override
    public void edit(CategoryProduct t) {
       
        try {
            String requete = "UPDATE category_product SET name_category=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(2, t.getId());
            pst.setString(1, t.getNameCategory());
            pst.executeUpdate();
            System.out.println("Categorie modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<CategoryProduct> show() {
          List<CategoryProduct> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM category_product";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new CategoryProduct(rs.getInt(1), rs.getString("name_category")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    }
    

