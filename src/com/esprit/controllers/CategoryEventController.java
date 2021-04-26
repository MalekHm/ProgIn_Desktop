/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controllers;

import com.esprit.models.Category;
import com.esprit.models.Event;
import com.esprit.services.IService;
import com.esprit.services.impl.CategoryServiceImpl;
import com.esprit.services.impl.EventServiceImpl;
import com.esprit.utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ousse
 */
public class CategoryEventController implements Initializable {
    Connection cnx = DataSource.getInstance().getCnx();
    
    private static Category selectedCategory = new Category();


    @FXML
    private TextField TF_IDCatEvent;
    @FXML
    private TextField TF_IDCatNameEvent;
    @FXML
    private TextField TF_IDCatDescEvent;
    @FXML
    private TableView<Category> tv_Cat;
    @FXML
    private TableColumn<Category, Integer> Col_idCat;
    @FXML
    private TableColumn<Category, String> Col_NameCat;
    @FXML
    private TableColumn<Category, String> Col_DescCat;
    @FXML
    private Button bt_Ajout;
    @FXML
    private Button bt_Modifier;
    @FXML
    private Button bt_Supprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showCategory();
    }    

    @FXML
    private void selectedCategory(MouseEvent event) {
        
          selectedCategory = tv_Cat.getSelectionModel().getSelectedItem();
               TF_IDCatEvent.setText(selectedCategory.getId()+"");
              TF_IDCatNameEvent.setText(selectedCategory.getName()+"");
              TF_IDCatDescEvent.setText(selectedCategory.getDescription()+"");
              
        
    }

    @FXML
    private void ajouterCategory(ActionEvent event) {
         CategoryServiceImpl sv = new CategoryServiceImpl();
       Category c =new Category( TF_IDCatNameEvent.getText(), TF_IDCatDescEvent.getText());
       sv.ajouter(c);
              showCategory();
              TF_IDCatNameEvent.setText("");
              TF_IDCatDescEvent.setText("");
            
    }

    @FXML
    private void modifierCategory(ActionEvent event) {
         CategoryServiceImpl sv = new CategoryServiceImpl();
       Category c =new Category(Integer.valueOf(TF_IDCatEvent.getText()),TF_IDCatNameEvent.getText(), TF_IDCatDescEvent.getText());
       sv.modifier(c);
              showCategory();
              TF_IDCatEvent.setText("");
              TF_IDCatNameEvent.setText("");
              TF_IDCatDescEvent.setText("");
            
    }

    @FXML
    private void supprimerCategory(ActionEvent event) {
        
        CategoryServiceImpl sc = new CategoryServiceImpl();
        
        sc.supprimer(selectedCategory);
        
    }

  

    
    public List<Category> afficher() {
        List<Category> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM category";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getInt(1),rs.getString(2), rs.getString(3)));
               
                
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
       public void showCategory(){
           
        List<Category> list = afficher();
        Col_idCat.setCellValueFactory(new PropertyValueFactory<Category,Integer>("id"));
        Col_NameCat.setCellValueFactory(new PropertyValueFactory<Category,String>("name"));
        Col_DescCat.setCellValueFactory(new PropertyValueFactory<Category,String>("description"));
        
        ObservableList obList = FXCollections.observableList(list);
       
        tv_Cat.setItems(obList);
    }
    
}
