/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjavaa;

import entity.user;
import java.io.File;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

import service.userservice;
import utils.Maconnexion;

/**
 *
 * @author Dell
 */
public class FXMLDocumentController implements Initializable {
    
    
    @FXML
    private TextField username;
    
    @FXML
    private TextField nom;
    
    @FXML
    private TextField prenom;
    
    @FXML
    private TextField mail;
    
    @FXML
    private TextField password;
    
    
    @FXML
    private Button ajouter;
    
    @FXML
    private TableView<user> ListeUser;
    
    
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private TableColumn<user, String> colUsername;
    @FXML
    private TableColumn<user, String> colNom;
    @FXML
    private TableColumn<user, String> colPrenom;
    @FXML
    private TableColumn<user, String> colEmail;
     ObservableList<user> userliste= FXCollections.observableArrayList();
     ObservableList<user> liste2= FXCollections.observableArrayList();
    @FXML
    private TextField recherche;
    @FXML
    private ImageView nomcheck;
    @FXML
    private ImageView prenomcheck;
    @FXML
    private ImageView emailcheck;
    @FXML
    private TextField modifid;
    @FXML
    private TableColumn<user, String> colRole;
    @FXML
    private TextField role;
    @FXML
    private Button retour;
   
    
    
    private void handleButtonAction(ActionEvent event) {
        
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
afficher();
        // TODO
        
    }    

 
    @FXML
    private void BoutonAjouter(ActionEvent event) {
        if (testSaisie()) {
        userservice sr= new userservice();
        user r=new user();
        
        r.setUsername(username.getText());
        r.setNom(nom.getText());
        r.setPrenom(prenom.getText());
        r.setMail(mail.getText());
        r.setPassword(password.getText());
        r.setRole(role.getText());
       
        sr.ajouterUser(r);
        afficher1();
        username.clear();
        nom.clear();
        prenom.clear();
        password.clear();
                mail.clear();
                role.clear();
        }
        
    }
    
 
 public void afficher1()
{
    try{

            Connection cnx =Maconnexion.getInstance().getConnection();
            
          String query = "SELECT * FROM user";
          Statement st;
          ResultSet rs;
              st = cnx.createStatement();
              rs = st.executeQuery(query);
              user u;
              while(rs.next()){
            
            u = new user (rs.getInt("id"),rs.getString("username"),rs.getString("mail"),rs.getString("nom"),rs.getString("prenom"),rs.getString("password"),rs.getString("role"));

        userliste.add(u);

              }
              
          }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Error on Building Data");
          }
      colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
      colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));   
 colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
  colEmail.setCellValueFactory(new PropertyValueFactory<>("mail"));
   colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
      



      ListeUser.setItems(userliste);
    
}
   
   
   
   
public void afficher()
{
    try{

            Connection cnx =Maconnexion.getInstance().getConnection();
            
          String query = "SELECT * FROM user";
          Statement st;
          ResultSet rs;
              st = cnx.createStatement();
              rs = st.executeQuery(query);
              user u;
              while(rs.next()){
            
            u = new user (rs.getInt("id"),rs.getString("username"),rs.getString("mail"),rs.getString("nom"),rs.getString("prenom"),rs.getString("password"),rs.getString("role"));

        liste2.add(u);

              }
              
          }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Error on Building Data");
          }
      colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
      colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));   
 colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
  colEmail.setCellValueFactory(new PropertyValueFactory<>("mail"));
  colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
 



      ListeUser.setItems(liste2);
}
    @FXML
    private void BoutonSupprimer(ActionEvent event) {
        Alert alert =new Alert (Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation") ;
                alert.setHeaderText("Voulez vous supprimer l'utilisateur ?") ;//problem
         userservice pr=new userservice();
         user f=new user() ;
         
        


         userliste=ListeUser.getSelectionModel().getSelectedItems();
          Connection cnx = Maconnexion.getInstance().getConnection();
            int id;
            id=userliste.get(0).getId();
            System.out.println(id);
             
        try {
            
           String query = "delete from user where id = ?";
      PreparedStatement preparedStmt = cnx.prepareStatement(query);
      preparedStmt.setInt(1, id);

      // execute the preparedstatement
        Optional <ButtonType> result=alert.showAndWait();
                if (result.get()==ButtonType.OK)
                {
                    Stage stage =(Stage) supprimer.getScene().getWindow() ;
                     preparedStmt.execute();
       pr.supprimerUser(f);
       afficher();

      
     
                    JOptionPane.showMessageDialog(null, "Utilisateur supprimé");
                    
                }
      
      
     
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean testNom() {
     
        int nbNonChar = 0;
        for (int i = 1; i < nom.getText().trim().length(); i++) {
            char ch = nom.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && nom.getText().trim().length() >= 3) {
          nomcheck.setImage(new Image("Image/checkmark.png"));
            return true;
        } else {
           nomcheck.setImage(new Image("Image/alertemark.png"));
               
            return false;

        }
    }
    private boolean testPrenom() {
        int nbNonChar = 0;
        for (int i = 1; i < prenom.getText().trim().length(); i++) {
            char ch = prenom.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && prenom.getText().trim().length() >= 3) {
            prenomcheck.setImage(new Image("Image/checkmark.png"));
            return true;
        } else {
            prenomcheck.setImage(new Image("Image/alertemark.png"));
            return false;

        }
    }
    private boolean testemail() {
                
         String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (mail.getText() == null) {
            return false;
        }

        if (pat.matcher(mail.getText()).matches() == false) {
           emailcheck.setImage(new Image("Image/alertemark.png"));
          //   String erreur = erreur + ("Veuillez verifier votre adresse email\n");
            return false;
//            

        } else {
           emailcheck.setImage(new Image("Image/checkmark.png"));
        }
        return true;
    }
private Boolean testSaisie() {
        String erreur = "";
        if (!testNom()) {
            erreur = erreur + ("Veuillez verifier votre Nom: seulement des caractères et de nombre >= 3 \n");
        }
        if (!testPrenom()) {
            erreur = erreur + ("Veuillez verifier votre Prenom: seulement des caractères et de nombre >= 3");
        }
        if (!testemail()) {
            erreur = erreur + ("Veuillez verifier que votre adresse email est de la forme : **@**.** \n");
        }
        

        if ( (!testNom()) || (!testPrenom()) || !testemail()  ) {
           
           
           
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Erreur de format");
        alert.setHeaderText("Vérifier les champs");
        alert.setContentText(erreur);
       
       
        }

        return  testNom() && testPrenom() && testemail() ;
    }
    

    @FXML
    private void BoutonModifier(ActionEvent event) {
        Alert alert =new Alert (Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation") ;
                alert.setHeaderText("Voulez-vous modifier un user?") ;
        userservice sr= new userservice();
        user r=new user();
      
            
        r.setUsername(username.getText());
        r.setNom(nom.getText());
        r.setPrenom(prenom.getText());
        r.setMail(mail.getText());
        r.setPassword(password.getText());
        r.setId(Integer.parseInt(modifid.getText()));
          r.setRole(role.getText());

        try {
            
             Optional <ButtonType> result=alert.showAndWait();
                if (result.get()==ButtonType.OK)
                {
sr.modifierUser(r);


                    Stage stage =(Stage) modifier.getScene().getWindow() ;
                     JOptionPane.showMessageDialog(null, "User Modifié");
                }
        } catch (Exception e) {
            System.out.println(e.getMessage());
       }
        
    }

    private void rechercher(KeyEvent event) {
         

    }

    @FXML
    private void recherche(KeyEvent event) {
    }

    @FXML
    private void userrecherche(KeyEvent event) {
          userservice re= new userservice();
     List<user>results = new ArrayList<>();
     results = re.listuser();
     FilteredList<user> filteredData = new FilteredList<>(liste2 , b -> true);
     user r = new user();
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(user -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
 
				  if (user.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                                                 
                                else if (user.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} 
                                  if (user.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                                                 
                                else if (user.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				} 
				
                                
                                 
				     else  
				    	 return false; // Does not match
                                  
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<user> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(ListeUser.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		ListeUser.setItems(sortedData);
    }

    @FXML
    private void nomcheck(KeyEvent event) {
        testNom();
    }

    @FXML
    private void prenomcheck(KeyEvent event) {
        testPrenom();
    }

    @FXML
    private void emailcheck(KeyEvent event) {
        testemail();
    }

    @FXML
    private void show(ActionEvent event) {
        user r=ListeUser.getSelectionModel().getSelectedItem();
                   userservice sr = new userservice();

    nom.setText(r.getNom());
    prenom.setText(r.getPrenom());
     username.setText(r.getNom());
    mail.setText(r.getMail());
     password.setText(r.getPassword());
     role.setText(r.getRole());
     modifid.setText(Integer.toHexString(r.getId()));
    
   
    }

    @FXML
    private void BoutonRetour(ActionEvent event) {
           try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
            Parent root=loader.load();
            retour.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    }


  
    
    

