/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjavaa;

import entity.user;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import service.userservice;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField tfusername;
    @FXML
    private TextField tfnom;
    @FXML
    private TextField tfprenom;
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfpassword;
    @FXML
    private Button btnValider;
    @FXML
    private Button Retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void BoutonValider(ActionEvent event) {
        userservice sr= new userservice();
        
        user r=new user();
      

        r.setUsername(tfusername.getText());
        r.setNom(tfnom.getText());
        r.setPrenom(tfprenom.getText());
        r.setMail(tfemail.getText());
        r.setPassword(tfpassword.getText());
       
       
      
        r.setRole("client");
        String fileName = null;
        
       

     
        sr.ajouterUser(r);
      
        }

    @FXML
    private void BoutonRetour(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
            Parent root=loader.load();
            Retour.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    

