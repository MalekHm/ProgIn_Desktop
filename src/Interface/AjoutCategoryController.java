/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Entity.Activity;
import Entity.Categoryactivity;
import Service.SActivity;
import Service.SCategoryActivity;
import static java.awt.AWTEventMulticaster.add;
import static java.awt.AWTEventMulticaster.add;
import java.io.IOException;
import java.net.URL;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
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
public class AjoutCategoryController implements Initializable {

    @FXML
    private TextField tfId;
    @FXML
    private TextField tfCategory;
    @FXML
    private TableView<Categoryactivity> tvCategoryActivity;
    @FXML
    private TableColumn<Categoryactivity, Integer> colId;
    @FXML
    private TableColumn<Categoryactivity, String> colCategory;
    @FXML
    private Button btnAjouter;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnSupprimer;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
    try {
        afficheList();
    } catch (SQLException ex) {
        Logger.getLogger(AjoutActivityController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
@FXML
    private void addCategoryactivity(ActionEvent event) throws SQLException {
            Categoryactivity a = new Categoryactivity();  
            a.setNameCategoryActivity(tfCategory.getText());
            SCategoryActivity prodServ = new SCategoryActivity();
            System.out.println("******************************************");
            prodServ.ajouter(a);
            afficheList();
       
    }
    
    @FXML
    private void deleteCategoryactivity(ActionEvent event) throws SQLException {
        String query = "DELETE FROM categoryactivity WHERE id = "  + tfId.getText()+ "";
        executeQuery(query);
        afficheList();  
    }

    @FXML
    private void editCategoryactivity(ActionEvent event) throws SQLException {
         String query = "UPDATE categoryactivity SET name_category_activity = '" + tfCategory.getText()+ "'WHERE id = " + tfId.getText()+ "" ;
         executeQuery(query);
         afficheList();
    }
    public ObservableList<Categoryactivity> getCategoryactivitylist() throws SQLException{
        ObservableList<Categoryactivity> CategoryactivityList = FXCollections.observableArrayList();
      
        String query ="select * FROM categoryactivity";
       Statement st = DataSource.getInstance().getCnx().createStatement();
      ResultSet rs ;
        try  
        {rs = st.executeQuery(query);
            Categoryactivity categoryactivity ;
            while (rs.next()) {
              categoryactivity =new Categoryactivity (rs.getInt("id"), rs.getString("name_category_activity"));
            CategoryactivityList.add(categoryactivity);
            }

        } catch (Exception ex) {
           ex.printStackTrace();
        }
     
        return CategoryactivityList;
        
        
    }
    
    
    public void afficheList() throws SQLException{
        ObservableList<Categoryactivity> list =getCategoryactivitylist();
        colId.setCellValueFactory(new PropertyValueFactory<Categoryactivity,Integer> ("id") );
        colCategory.setCellValueFactory(new PropertyValueFactory<Categoryactivity,String> ("nameCategoryActivity") );
        tvCategoryActivity.setItems(list);
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
        Categoryactivity categoryactivity= tvCategoryActivity.getSelectionModel().getSelectedItem();
       
        tfId.setText("" + categoryactivity.getId());
        tfCategory.setText(categoryactivity.getNameCategoryActivity());
        
          
    }

}
