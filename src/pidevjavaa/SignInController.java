/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjavaa;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import entity.user;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.scene.control.Alert;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import service.userservice;
import utils.Maconnexion;
import entity.user;
import java.util.Random;
import static java.util.logging.Level.SEVERE;
import utils.JavaMailUtil;
import utils.Maconnexion;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
/**
 * FXML Controller class
 *
 * @author asus
 */
public class SignInController implements Initializable {

    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btninscription;
    @FXML
    private Button forgetpwd;
    
    Connection connection ;
    PreparedStatement pst ;
    ResultSet rs;
 

    /**
     * Initializes the controller class.
     */
            /*  @Override*/
    public void initialize(URL url, ResourceBundle rb) {
        forgetpwd.setOnAction(event -> {
               service.Operation op= new service.Operation();
               String o = op.recEmail(tfEmail.getText());
               int d= op.recId(tfEmail.getText());
           
               System.out.println(o+"  "+ d);
             try {
                 JavaMailUtil.sendMail(o,d);
             } catch (Exception ex) {
                 Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
             }
               System.out.println(o);
                  try {
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("forgetpwd.fxml"));
        
            Parent parent = (Parent)loader.load();
              ForgetpwdController cont = loader.<ForgetpwdController>getController();
            cont.setEmail(o);
            cont.setCode(op.envoyerCode(d));
            cont.setId(d);
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Mot de passe oublié ?");
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
            }
          
      
        });
    }    
      private void handleButtonAction(ActionEvent event) {
          //Action for btnLogin
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
        	public void handle(ActionEvent event) {
                    String checkUser = tfEmail.getText().toString();
                    String checkPw = tfPassword.getText().toString();
                    Object email = null;
                    Object password = null;
        		if(tfEmail.equals(email) && tfPassword.equals(password)){
        	
        		}
        		else{
        			
        		}
        		tfEmail.setText("");
        		tfPassword.setText("");
        	}
        	});
    }

    @FXML
    private void SignInBouton(ActionEvent event) {
          String uname = tfEmail.getText();
         String pass = tfPassword.getText();
         
         if(uname.equals("") && pass.equals("") )
         {
             JOptionPane.showMessageDialog(null, "Username or password blank");
         }
         else
         { try{
             Class.forName("com.mysql.jdbc.Driver");
             connection = DriverManager.getConnection("jdbc:mysql://localhost/java", "root","");
             
             pst = connection.prepareStatement("select * from user where mail=? and password=? and role='admin' ");
             
             pst.setString(1, uname);
              pst.setString(2, pass);
           
              rs = pst.executeQuery();
              
              if(rs.next()){
                  // JOptionPane.showMessageDialog(null, "Login Success");
                   try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root=loader.load();
            btnLogin.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
              }
              else
              {
                  /* JOptionPane.showMessageDialog(null, "Login Failed");
                   tfEmail.setText("");
                   tfPassword.setText("");*/
                   try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Front.fxml"));
            Parent root=loader.load();
            btnLogin.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
                  
                   
              }
             
         } catch(ClassNotFoundException ex){
             Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
         }   catch (SQLException ex) {
                 Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
             }
             
         }
      
    }

    
        
    
    
    @FXML
    private void InscriptionBouton(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("inscription.fxml"));
            Parent root=loader.load();
            btnLogin.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }


  
    
}
