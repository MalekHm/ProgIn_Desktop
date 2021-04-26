/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Entity.Activity;
import Entity.Categoryactivity;
import Service.SActivity;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author Oumeyma
 */
public class AjoutActivityController implements Initializable {
Connection cnx = DataSource.getInstance().getCnx();
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfTitleActivity;
    @FXML
    private TextField tfAdressActivity;
    @FXML
    private TextField tfDescriptionActivity;
    @FXML
    private TextField tfPriceActivity;
    @FXML
    private TableView<Activity> tvActivity;
    @FXML
    private TableColumn<Activity, Integer> colId;
    @FXML
    private TableColumn<Activity, String> colTitleActivity;
    @FXML
    private TableColumn<Activity, String> colAdressActivity;
    @FXML
    private TableColumn<Activity, String> colDescriptionActivity;
    @FXML
    private TableColumn<Activity, Double> colPriceActivity;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;
    @FXML
    private ComboBox<String> cbcategory;

    /**
     * Initializes the controller class.
     */
   public void initialize(URL url, ResourceBundle rb) {
    
          
            String req = "SELECT * FROM categoryactivity";
            Statement st;
            try {
                
                st = DataSource.getInstance().getCnx().createStatement();
                ResultSet rs = st.executeQuery(req);
                
                ObservableList<String> comboCat = FXCollections.observableArrayList();
                
                while (rs.next()) {
                    comboCat.add(rs.getString("name_category_activity"));
                }
                
                cbcategory.setItems(comboCat);
                
            } catch (SQLException ex) {
                Logger.getLogger(AjoutActivityController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    try {
        afficheList();
    } catch (SQLException ex) {
        Logger.getLogger(AjoutActivityController.class.getName()).log(Level.SEVERE, null, ex);
    }
            }

     
            
@FXML
    private void addActivity(ActionEvent event) throws SQLException {
         String requete = "SELECT * FROM categoryactivity WHERE name_category_activity='"
                + cbcategory.getValue() + "'";
        Statement st = DataSource.getInstance().getCnx().createStatement();
        ResultSet result = st.executeQuery(requete);
        Categoryactivity cat = new Categoryactivity();

        while (result.next()) {
            cat.setId(result.getInt(1));
            cat.setNameCategoryActivity(result.getString(2));
         
        }
        
        {
        
            Activity a = new Activity();  
            a.setTitleActivity(tfTitleActivity.getText());
            a.setAdressActivity(tfAdressActivity.getText());
            a.setDescriptionActivity(tfDescriptionActivity.getText());
            a.setPriceActivity(Double.parseDouble(tfPriceActivity.getText()));
            a.setCategoryId(cat);
       
            SActivity prodServ = new SActivity();
            System.out.println("******************************************");
            prodServ.ajouter(a);
            afficheList();
            
             
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info.");
            alert.setHeaderText("Info.");
            alert.setContentText("l'activitée "+a.getTitleActivity()+" a été Ajouté avec succés");
            alert.show();
            
       
    }
    }
    @FXML
    private void deleteActivity(ActionEvent event) throws SQLException {
        String query = "DELETE FROM activity WHERE id = " + tfId.getText() + "";
        executeQuery(query);
          afficheList();  
    }

@FXML
    private void editActivity(ActionEvent event) throws SQLException {
        
        String requete = "SELECT * FROM categoryactivity WHERE name_category_activity='"
                + cbcategory.getValue() + "'";
        Statement st = DataSource.getInstance().getCnx().createStatement();
        ResultSet result = st.executeQuery(requete);
        Categoryactivity cat = new Categoryactivity();
        
        while (result.next()) {
            cat.setId(result.getInt(1));
            cat.setNameCategoryActivity(result.getString(2));
         
        }

        String query = "UPDATE activity SET title_activity= '" + tfTitleActivity.getText() + "' ,adress_activity= '" + tfAdressActivity.getText() + "', description_activity= '" + tfDescriptionActivity.getText() + "', price_activity = " + tfPriceActivity.getText()+ "WHERE id = " + tfId.getText()+ "" ;
        executeQuery(query);
        afficheList();
    } 
    public ObservableList<Activity> getActivitylist() throws SQLException{
        ObservableList<Activity> ActivityList = FXCollections.observableArrayList();
      
        String query ="select * FROM Activity";
       Statement st = DataSource.getInstance().getCnx().createStatement();
      ResultSet rs ;
        try  
        {rs = st.executeQuery(query);
            Activity activity ;
            while (rs.next()) {
              activity =new Activity (rs.getInt("id"), rs.getString("title_activity"), rs.getString("adress_activity"), rs.getString("description_activity"), rs.getDouble("price_activity"));
            ActivityList.add(activity);
            }

        } catch (Exception ex) {
           ex.printStackTrace();
        }
     
        return ActivityList; 
        
        
    }
    public void afficheList() throws SQLException{
        ObservableList<Activity> list =getActivitylist();
        colId.setCellValueFactory(new PropertyValueFactory<Activity,Integer> ("id") );
        colTitleActivity.setCellValueFactory(new PropertyValueFactory<Activity,String> ("titleActivity") );
        colAdressActivity.setCellValueFactory(new PropertyValueFactory<Activity,String> ("adressActivity") ); 
        colDescriptionActivity.setCellValueFactory(new PropertyValueFactory<Activity,String> ("descriptionActivity") );
        colPriceActivity.setCellValueFactory(new PropertyValueFactory<Activity,Double> ("priceActivity") );
        tvActivity.setItems(list);
    }
   private void executeQuery(String query) {
       
        try {
           Statement st = DataSource.getInstance().getCnx().createStatement();
            st.executeUpdate(query);
        }catch (Exception ex){
            
ex.printStackTrace();
        }
        
    }

@FXML
    private void handleMouseAction(MouseEvent event) {
         Activity activity = tvActivity.getSelectionModel().getSelectedItem();
       
        tfId.setText("" + activity.getId());
        tfTitleActivity.setText(activity.getTitleActivity());
         tfAdressActivity.setText(activity.getAdressActivity());
          tfDescriptionActivity.setText(activity.getDescriptionActivity());
          tfPriceActivity.setText("" + activity.getPriceActivity());
        
          
    }

    
}
