/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controllers.front;

import com.esprit.models.Event;
import com.esprit.models.Reservation;
import com.esprit.services.impl.EventServiceImpl;
import com.esprit.services.impl.ReservationServiceImpl;
import com.esprit.tests.PIDEVGUI;
import com.esprit.utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ousse
 */
public class EventFrontController implements Initializable {
Connection cnx = DataSource.getInstance().getCnx();
    
    private static Event selectedEvent = new Event();
    private static Reservation selectedReservation = new Reservation();
    @FXML
    private TableColumn<Event, String> Col_Title;
    @FXML
    private TableColumn<Event, String> Col_Description;
    @FXML
    private TableColumn<Event, Date> col_StartDate;
    @FXML
    private TableColumn<Event, Date> Col_EndDate;
    @FXML
    private TableColumn<Event, Integer> Col_nbPerson;
    @FXML
    private TableColumn<Event, Float> Col_Price;
    @FXML
    private TableView<Event> Tv_EventFront;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showEvent();
    }    

    @FXML
    private void AfficherEvent(MouseEvent event) {
         Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../../views/AjouterEvent.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(EventFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
            Scene scene = new Scene(root);
            PIDEVGUI.pStage.setScene(scene);
            PIDEVGUI.pStage.show();
    }

    @FXML
    private void AfficherReservation(MouseEvent event) {
           Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../../views/ReservationFront.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(EventFrontController.class.getName()).log(Level.SEVERE, null, ex);
        }
            Scene scene = new Scene(root);
            PIDEVGUI.pStage.setScene(scene);
            PIDEVGUI.pStage.show();
    }

    @FXML
    private void selectLine(MouseEvent event) {
          int reply = JOptionPane.showConfirmDialog(null, "vous voulez reservez une place dans cet evennement?", "", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            ReservationServiceImpl service = new ReservationServiceImpl();
            service.ajouter(selectedReservation);
            JOptionPane.showMessageDialog(null, "Reservation Effectuer !");

        }
    }
    
        public List<Event> afficher() {
         List<Event> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM event";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Event(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getFloat(6), rs.getInt(7), rs.getDate(8), rs.getDate(9), rs.getInt(10)));

		
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

 
     public void showEvent(){
        List<Event> list = afficher();
       
        Col_Title.setCellValueFactory(new PropertyValueFactory<Event,String>("titre"));
        Col_Description.setCellValueFactory(new PropertyValueFactory<Event,String>("description"));
        col_StartDate.setCellValueFactory(new PropertyValueFactory<Event,Date>("start_date"));
        Col_EndDate.setCellValueFactory(new PropertyValueFactory<Event,Date>("end_date"));
       Col_nbPerson.setCellValueFactory(new PropertyValueFactory<Event,Integer>("nb_persons"));
        Col_Price.setCellValueFactory(new PropertyValueFactory<Event,Float>("price_event"));
        
        ObservableList obList = FXCollections.observableList(list);
       
        Tv_EventFront.setItems(obList);
        
           

    }
     
    
}
