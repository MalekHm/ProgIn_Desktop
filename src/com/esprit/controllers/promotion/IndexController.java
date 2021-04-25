/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controllers.promotion;

import com.esprit.controllers.publicity.AjoutPublicityController;
import com.esprit.models.Promotion;
import com.esprit.services.impl.ServicePromotionImpl;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javax.swing.JOptionPane;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author Malek
 */
public class IndexController implements Initializable {

    @FXML
    private TableView<Promotion> promotions;
    @FXML
    private TableColumn<Promotion, String> event;
    @FXML
    private TableColumn<Promotion, String> product;
    @FXML
    private TableColumn<Promotion, String> description;
    @FXML
    private TableColumn<Promotion, Date> from;
    @FXML
    private TableColumn<Promotion, Date> to;
    @FXML
    private TableColumn<Promotion, Float> pourcentage;

    static Promotion selectedPromotion = new Promotion();
    @FXML
    private TextField tfrecherche;
    @FXML
    private ImageView img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServicePromotionImpl service = new ServicePromotionImpl();
        ObservableList OL = FXCollections.observableArrayList(service.afficher());
        promotions.setItems(OL);
        event.setCellValueFactory(new PropertyValueFactory<Promotion, String>("event"));
        product.setCellValueFactory(new PropertyValueFactory<Promotion, String>("product"));
        description.setCellValueFactory(new PropertyValueFactory<Promotion, String>("descriptionpromotion"));
        from.setCellValueFactory(new PropertyValueFactory<Promotion, Date>("start_date_promotion"));
        to.setCellValueFactory(new PropertyValueFactory<Promotion, Date>("end_date_promotion"));
        pourcentage.setCellValueFactory(new PropertyValueFactory<Promotion, Float>("pourcentage"));
    }

    @FXML
    private void redirectTonewPromotion(ActionEvent event) throws IOException {
        AjoutPromotionController a=new AjoutPromotionController();
        a.setPromotion(null);
        Parent root = FXMLLoader.load(getClass().getResource("../../views/promotion/AjoutPromotion.fxml"));
            Scene scene = new Scene(root);
            PIDEVGUI.pStage.setScene(scene);
            PIDEVGUI.pStage.show();
    }

    @FXML
    private void Delete(ActionEvent event) {
        int reply = JOptionPane.showConfirmDialog(null, "Do you want delete this item?", "", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            ServicePromotionImpl service = new ServicePromotionImpl();
            service.supprimer(selectedPromotion);
            this.initialize(null, null);
            JOptionPane.showMessageDialog(null, "Promotion supprimée !");

        }
    }

    @FXML
    private void Edit(ActionEvent event) throws IOException {
            if (selectedPromotion.getId() != 0) {
            AjoutPromotionController a = new AjoutPromotionController();
            a.setPromotion(selectedPromotion);
            Parent root = FXMLLoader.load(getClass().getResource("../../views/promotion/AjoutPromotion.fxml"));
            Scene scene = new Scene(root);
            PIDEVGUI.pStage.setScene(scene);
            PIDEVGUI.pStage.show();
        }
    }

    @FXML
    private void SelectItem(MouseEvent event) {
        selectedPromotion = promotions.getSelectionModel().getSelectedItem();
         ByteArrayOutputStream out = QRCode.from("Promotion de "+selectedPromotion.getPourcentage()+"% pour"+selectedPromotion.getEvent() +"/ "+selectedPromotion.getProduct()).to(ImageType.PNG).withSize(200, 200).stream();
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());

        // SHOW QR CODE
        Image image = new Image(in);
        img.setImage(image);
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
        
        ServicePromotionImpl service = new ServicePromotionImpl();
        ObservableList OL = FXCollections.observableArrayList(service.recherche(rech));
        promotions.setItems(OL);
        
        
    }

    
}
