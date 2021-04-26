/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controllers;

import com.esprit.models.Personne;
import com.esprit.services.ServicePersonne;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author aissa
 */
public class AjoutPersonneController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private Button btn;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AjouterPersonne(ActionEvent event) throws IOException {
        ServicePersonne sp = new ServicePersonne();
        sp.ajouter(new Personne(tfNom.getText(), tfPrenom.getText()));
        
        JOptionPane.showMessageDialog(null, "Personne ajoutée !");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/DetailsPersonne.fxml"));
        
        Parent root = loader.load();
        tfNom.getScene().setRoot(root);
        
        DetailsPersonneController dpc = loader.getController();
        dpc.setLbNom(tfNom.getText());
        dpc.setLbPrenom(tfPrenom.getText());
    }

}
