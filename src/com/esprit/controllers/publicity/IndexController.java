/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controllers.publicity;

import com.esprit.models.Publicity;
import com.esprit.services.impl.ServicePublicityImpl;
import com.esprit.tests.PIDEVGUI;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Malek
 */
public class IndexController implements Initializable {

    @FXML
    private TableView<Publicity> publicitys;
    @FXML
    private TableColumn<Publicity, String> titre;
    @FXML
    private TableColumn<Publicity, String> description;
    @FXML
    private TableColumn<Publicity, String> position;
    @FXML
    private TableColumn<Publicity, String> link;
    @FXML
    private TableColumn<Publicity, Date> from;
    @FXML
    private TableColumn<Publicity, Date> to;
    static Publicity selectedPub = new Publicity();
    @FXML
    private TextField tfrecherche;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServicePublicityImpl service = new ServicePublicityImpl();
        ObservableList OL = FXCollections.observableArrayList(service.afficher());
        publicitys.setItems(OL);
        titre.setCellValueFactory(new PropertyValueFactory<Publicity, String>("title_pub"));
        description.setCellValueFactory(new PropertyValueFactory<Publicity, String>("description_pub"));
        position.setCellValueFactory(new PropertyValueFactory<Publicity, String>("position"));
        link.setCellValueFactory(new PropertyValueFactory<Publicity, String>("link"));
        from.setCellValueFactory(new PropertyValueFactory<Publicity, Date>("start_date_pub"));
        to.setCellValueFactory(new PropertyValueFactory<Publicity, Date>("end_date_pub"));

    }

    @FXML
    private void redirectTonewPublicictu(ActionEvent event) throws IOException {
        AjoutPublicityController a = new AjoutPublicityController();
        a.setPublicity(null);
        Parent root = FXMLLoader.load(getClass().getResource("../../views/publicity/AjoutPublicity.fxml"));
            Scene scene = new Scene(root);
            PIDEVGUI.pStage.setScene(scene);
            PIDEVGUI.pStage.show();

    }

    @FXML
    private void SelectItem(MouseEvent event) {
        selectedPub = publicitys.getSelectionModel().getSelectedItem();
    }

    @FXML
    private void Delete(ActionEvent event) {
        int reply = JOptionPane.showConfirmDialog(null, "Do you want delete this item?", "", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            ServicePublicityImpl service = new ServicePublicityImpl();
            service.supprimer(selectedPub);
            ObservableList OL = FXCollections.observableArrayList(service.afficher());
            publicitys.setItems(OL);
            titre.setCellValueFactory(new PropertyValueFactory<Publicity, String>("title_pub"));
            description.setCellValueFactory(new PropertyValueFactory<Publicity, String>("description_pub"));
            position.setCellValueFactory(new PropertyValueFactory<Publicity, String>("position"));
            link.setCellValueFactory(new PropertyValueFactory<Publicity, String>("link"));
            from.setCellValueFactory(new PropertyValueFactory<Publicity, Date>("start_date_pub"));
            to.setCellValueFactory(new PropertyValueFactory<Publicity, Date>("end_date_pub"));
            JOptionPane.showMessageDialog(null, "Publicité supprimée !");
        }

    }

    @FXML
    private void Edit(ActionEvent event) throws IOException {
        if (selectedPub.getId() != 0) {
            AjoutPublicityController a = new AjoutPublicityController();
            a.setPublicity(selectedPub);
            Parent root = FXMLLoader.load(getClass().getResource("../../views/publicity/AjoutPublicity.fxml"));
            Scene scene = new Scene(root);
            PIDEVGUI.pStage.setScene(scene);
            PIDEVGUI.pStage.show();
        }

    }

    @FXML
    private void showPublicity(ActionEvent event) throws IOException {
             Parent root = FXMLLoader.load(getClass().getResource("../../views/publicity/index.fxml"));
            Scene scene = new Scene(root);
            PIDEVGUI.pStage.setScene(scene);
            PIDEVGUI.pStage.show();
    }

    @FXML
    private void showPromotion(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../views/promotion/index.fxml"));
            Scene scene = new Scene(root);
            PIDEVGUI.pStage.setScene(scene);
            PIDEVGUI.pStage.show();
    }

    @FXML
    private void Search(KeyEvent event) {
         String rech=tfrecherche.getText();
        
        ServicePublicityImpl service = new ServicePublicityImpl();
        ObservableList OL = FXCollections.observableArrayList(service.recherche(rech));
        publicitys.setItems(OL);
    }

}
