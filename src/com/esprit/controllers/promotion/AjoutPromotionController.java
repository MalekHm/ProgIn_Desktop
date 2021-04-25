/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controllers.promotion;

import com.esprit.models.Promotion;
import com.esprit.services.impl.ServicePromotionImpl;
import com.esprit.tests.PIDEVGUI;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Malek
 */
public class AjoutPromotionController implements Initializable {

    @FXML
    private TextArea tfDescription;
    @FXML
    private ComboBox<String> cbproduct;
    @FXML
    private DatePicker datepickerfrom;
    @FXML
    private DatePicker datepickerto;
    @FXML
    private Label ltitre;
    @FXML
    private ComboBox<String> cbevent;
    @FXML
    private TextField tfpourcentage;
    private static Promotion promotion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServicePromotionImpl service = new ServicePromotionImpl();
        cbevent.getItems().addAll(service.getEventNameAndId());
        cbproduct.getItems().addAll(service.getProductNameAndId());

        if (promotion != null) {
            ltitre.setText("Modifier une promotion");
            tfDescription.setText(promotion.getDescriptionpromotion());
            datepickerfrom.setValue(promotion.getStart_date_promotion().toLocalDate());
            datepickerto.setValue(promotion.getEnd_date_promotion().toLocalDate());
            if (promotion.getEvent_id() != 0) {
                cbevent.setValue(promotion.getEvent_id() + "-" + promotion.getEvent());

            }
            if (promotion.getProduct_id() != 0) {
                cbproduct.setValue(promotion.getProduct_id() + "-" + promotion.getProduct());

            }
            tfpourcentage.setText(promotion.getPourcentage() + "");
        }
    }

    @FXML
    private void SavePromotion(ActionEvent event) throws IOException {
        int event_id = 0;
        int product_id = 0;
        if (cbevent.getValue() != null) {
            event_id = Integer.valueOf(cbevent.getValue().substring(0, cbevent.getValue().indexOf("-")));
        }
        if (cbproduct.getValue() != null) {
            product_id = Integer.valueOf(cbproduct.getValue().substring(0, cbproduct.getValue().indexOf("-")));
        }
        if (promotion == null) {
            ServicePromotionImpl service = new ServicePromotionImpl();
            service.ajouter(new Promotion(event_id, product_id, tfDescription.getText(), Date.valueOf(datepickerfrom.getValue()), Date.valueOf(datepickerto.getValue()), Float.valueOf(tfpourcentage.getText())));
            JOptionPane.showMessageDialog(null, "Promotion ajoutée !");
        }
        else{
            ServicePromotionImpl service = new ServicePromotionImpl();
            service.modifier(new Promotion(promotion.getId(),event_id, product_id, tfDescription.getText(), Date.valueOf(datepickerfrom.getValue()), Date.valueOf(datepickerto.getValue()), Float.valueOf(tfpourcentage.getText())));
            JOptionPane.showMessageDialog(null, "Promotion modifiée !");
        }
        Parent root = FXMLLoader.load(getClass().getResource("../../views/promotion/index.fxml"));
        Scene scene = new Scene(root);
        PIDEVGUI.pStage.setScene(scene);
        PIDEVGUI.pStage.show();
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

    public static Promotion getPromotion() {
        return promotion;
    }

    public static void setPromotion(Promotion promotion) {
        AjoutPromotionController.promotion = promotion;
    }

}
