/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjavaa;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import java.io.IOException;
import java.net.URL;
import service.Operation;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
/**
 * FXML Controller class
 *
 * @author asus
 */
public class ForgetpwdController implements Initializable {

    @FXML
    private TextField txt_code;
    @FXML
    private Button btn_confirm;
    @FXML
    private Label lab_email;
    @FXML
    private Label lab_code;
    @FXML
    private Label lab_msg;
    @FXML
    private Label lab_id;

    private int id;
    private String email;
    private String code;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_confirm.setOnAction(event -> {
             
             Operation op =new Operation();
             String code =op.recCode(Integer.parseInt(lab_id.getText()));
             System.out.println(txt_code.getText().equals(lab_code.getText()));
             if (txt_code.getText().equals(code)){
             try {
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("ResetPassword.fxml"));
        
            Parent parent = (Parent)loader.load();
              ResetPasswordController cont = loader.<ResetPasswordController>getController();
           
            cont.setId(Integer.parseInt(lab_id.getText()));
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Réinitialiser mot de passe");
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(InscriptionController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
        
             }else {
                 lab_msg.setText("Le code inséré est incorrect !");
             }
        });
    
    
    }    
 
    
     public void setId(int a){
        this.id= a;
        lab_id.setText(a+"");
      
        
    }
     public void setEmail(String a){
        this.email= a;
        lab_email.setText(a);
    }
     
     public void setCode(String b){
      
          this.code= b;
          lab_code.setText(b);
      
        
    }
    }    

    

