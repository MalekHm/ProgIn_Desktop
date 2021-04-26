/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Entity.Activity;
import Service.SActivity;
import java.awt.Event;
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author Oumeyma
 */
public class ActivityUserController implements Initializable {

    @FXML
    private ListView<Activity> listView;
    @FXML
    private Button search_btn;
    @FXML
    private TextField tf_findbyname;
    @FXML
    private ComboBox<String> cbcategory;
    SActivity actServ = new SActivity();
    ObservableList<Activity> activities = FXCollections.observableArrayList();
    int index = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      activities = (ObservableList) actServ.afficher();  
      
      
//*****************************Charge comboBox Categories**********************      
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
        
        
        listView.setItems(activities);
        listView.getSelectionModel().clearSelection();
        listView.setCellFactory(new Callback<ListView<Activity>, ListCell<Activity>>() {

        @Override
        public ListCell<Activity> call(ListView<Activity> param) {
                ListCell<Activity> cell = new ListCell<Activity>() {

                    protected void updateItem(Activity item, boolean empty) {

                        if (item != null) {

                            HBox hbox = new HBox();
                            hbox.setSpacing(5);
                            hbox.setAlignment(Pos.BOTTOM_LEFT);

                            VBox vbox = new VBox();
                            vbox.setSpacing(5);
                            vbox.setAlignment(Pos.BOTTOM_LEFT);

                            VBox Buttons = new VBox();
                            Buttons.setSpacing(5);
                            Buttons.setAlignment(Pos.TOP_LEFT);
                            //**********************************************************

                            Label title = new Label("Titre : " +item.getTitleActivity());
                            Label adress = new Label("Adresse : " +item.getAdressActivity());
                            Label descrip = new Label("Description : " + item.getDescriptionActivity());
                            
                            Label price = new Label(String.valueOf("Prix : " + item.getPriceActivity()) + "  DT");
                           

                            vbox.getChildren().add(title);
                            vbox.getChildren().add(adress);
                            vbox.getChildren().add(descrip);
                            vbox.getChildren().add(price);


                            hbox.getChildren().add(vbox);
                            hbox.getChildren().add(Buttons);

                            setGraphic(hbox);

                     
                        }

                    }
                };

                return cell;
            }
        });
       
                }
                
        // TODO
    

    
    @FXML
    

    private void rechercheByName(ActionEvent event) {
        activities.clear();
        
        activities.addAll((ObservableList) actServ.rechercheByName(tf_findbyname.getText()));
        listView.setItems(activities);
        
        listView.setCellFactory(new Callback<ListView<Activity>, ListCell<Activity>>() {

        @Override
        public ListCell<Activity> call(ListView<Activity> param) {
                ListCell<Activity> cell = new ListCell<Activity>() {

                    protected void updateItem(Activity item, boolean empty) {

                        if (item != null) {

                            HBox hbox = new HBox();
                            hbox.setSpacing(5);
                            hbox.setAlignment(Pos.BOTTOM_LEFT);

                            VBox vbox = new VBox();
                            vbox.setSpacing(5);
                            vbox.setAlignment(Pos.BOTTOM_LEFT);

                            VBox Buttons = new VBox();
                            Buttons.setSpacing(5);
                            Buttons.setAlignment(Pos.TOP_LEFT);
                            //**********************************************************

                            Label title = new Label("Titre : " +item.getTitleActivity());
                            Label adress = new Label("Adresse : " +item.getAdressActivity());
                            Label descrip = new Label("Description : " + item.getDescriptionActivity());
                            
                            Label price = new Label(String.valueOf("Prix : " + item.getPriceActivity()) + "  DT");
                           

                            vbox.getChildren().add(title);
                            vbox.getChildren().add(adress);
                            vbox.getChildren().add(descrip);
                            vbox.getChildren().add(price);


                            hbox.getChildren().add(vbox);
                            hbox.getChildren().add(Buttons);

                            setGraphic(hbox);

                     
                        }

                    }
                };

                return cell;
            }
        });
       
        

    }

    @FXML
    private void findByCategory(ActionEvent event) throws SQLException {
        int id = 0;
        String q = "SELECT id from categoryactivity where name_category_activity='" + cbcategory.getValue() + "'";
        Statement st = DataSource.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery(q);

        while (rs.next()) {
            id = rs.getInt(1);
        }
        activities.clear();
        activities.addAll(actServ.FindByCategory(id));
        listView.setItems(activities);
        listView.setCellFactory(new Callback<ListView<Activity>, ListCell<Activity>>() {

        @Override
        public ListCell<Activity> call(ListView<Activity> param) {
                ListCell<Activity> cell = new ListCell<Activity>() {

                    protected void updateItem(Activity item, boolean empty) {

                        if (item != null) {

                            HBox hbox = new HBox();
                            hbox.setSpacing(5);
                            hbox.setAlignment(Pos.BOTTOM_LEFT);

                            VBox vbox = new VBox();
                            vbox.setSpacing(5);
                            vbox.setAlignment(Pos.BOTTOM_LEFT);

                            VBox Buttons = new VBox();
                            Buttons.setSpacing(5);
                            Buttons.setAlignment(Pos.TOP_LEFT);
                            //**********************************************************

                            Label title = new Label("Titre : " +item.getTitleActivity());
                            Label adress = new Label("Adresse : " +item.getAdressActivity());
                            Label descrip = new Label("Description : " + item.getDescriptionActivity());
                            
                            Label price = new Label(String.valueOf("Prix : " + item.getPriceActivity()) + "  DT");
                           

                            vbox.getChildren().add(title);
                            vbox.getChildren().add(adress);
                            vbox.getChildren().add(descrip);
                            vbox.getChildren().add(price);


                            hbox.getChildren().add(vbox);
                            hbox.getChildren().add(Buttons);

                            setGraphic(hbox);

                     
                        }

                    }
                };

                return cell;
            }
        });
       
    }

    }


