/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controllers.front;

import com.esprit.models.Event;
import com.esprit.models.Reservation;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.print.DocFlavor;

/**
 * FXML Controller class
 *
 * @author ousse
 */
public class ReservationFrontController implements Initializable {
    Connection cnx = DataSource.getInstance().getCnx();

    @FXML
    private TableColumn<Reservation, Date> Col_DateDebut;
    @FXML
    private TableColumn<Reservation, Float> Col_RestePayemnt;
    @FXML
    private TableColumn<Reservation, Float> Col_Reduction;
    @FXML
    private TableColumn<Reservation, Integer> Col_EventReservation;
    @FXML
    private TableView<?> tv_ReservartionFront;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
showReservation();
    }    
    
      public List<Reservation> afficher() {
         List<Reservation> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM reservation";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Reservation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getBoolean(5), rs.getDate(6), rs.getFloat(7), rs.getFloat(8), rs.getString(9), rs.getBoolean(10)));

		
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }

 
     public void showReservation(){
        List<Reservation> list = afficher();
        Col_EventReservation.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("event_id"));
         Col_DateDebut.setCellValueFactory(new PropertyValueFactory<Reservation,Date>("reservation_date"));
          Col_Reduction.setCellValueFactory(new PropertyValueFactory<Reservation,Float>("reduction"));
           Col_RestePayemnt.setCellValueFactory(new PropertyValueFactory<Reservation,Float>("resteapayer"));
            
     
        
        
        ObservableList obList = FXCollections.observableList(list);
       
        tv_ReservartionFront.setItems(obList);
        
           

    }

    @FXML
    private void AfficherEvent(MouseEvent event) {
        
    }

    @FXML
    private void AfficherReservation(MouseEvent event) {
    }
    
}
