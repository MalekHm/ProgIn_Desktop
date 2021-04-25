/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controllers.publicity;

import com.esprit.models.Publicity;
import com.esprit.services.impl.ServicePublicityImpl;
import com.esprit.tests.PIDEVGUI;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.UUID;
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
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Malek
 */
public class AjoutPublicityController implements Initializable {

    @FXML
    private TextArea tfDescription;
    @FXML
    private TextField tfTitre;
    @FXML
    private TextField tfLink;
    @FXML
    private ComboBox<String> cbposition;
    @FXML
    private DatePicker datepickerfrom;
    @FXML
    private DatePicker datepickerto;

    private static Publicity publicity;
    @FXML
    private Label ltitre;
    @FXML
    private ImageView ImagePub;
    @FXML
    private TextField Imagepath;
    String filename="";
    @FXML
    private Label titreerror;
    @FXML
    private Label positionerror;
    @FXML
    private Label descriptionerror;
    @FXML
    private Label linkerror;
    @FXML
    private Label imageerror;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ltitre.setText("Ajouter une nouvelle publicité");

        cbposition.getItems().addAll(
                "TOP",
                "BOTTOM",
                "NAN"
        );
        titreerror.setVisible(false);
        positionerror.setVisible(false);
        descriptionerror.setVisible(false);
        linkerror.setVisible(false);
        imageerror.setVisible(false);

        if (publicity != null) {
            filename=publicity.getPicture_pub();
            ltitre.setText("Modifier une publicité");
            tfTitre.setText(publicity.getTitle_pub());
            tfDescription.setText(publicity.getDescription_pub());
            tfLink.setText(publicity.getLink());
            datepickerfrom.setValue(publicity.getStart_date_pub().toLocalDate());
            datepickerto.setValue(publicity.getEnd_date_pub().toLocalDate());
            cbposition.setValue(publicity.getPosition());
            Image im = new Image("http://localhost/Prog-in-master/public/publicity/" + publicity.getPicture_pub());
            ImagePub.setImage(im);
        }

    }

    @FXML
    private void SavePublicity(ActionEvent event) throws IOException {
        if (this.checkFields()) {
            if (publicity != null) {
                ServicePublicityImpl sp = new ServicePublicityImpl();
                sp.modifier(new Publicity(publicity.getId(), Date.valueOf(datepickerfrom.getValue()), Date.valueOf(datepickerto.getValue()), tfDescription.getText(), filename, tfTitre.getText(), cbposition.getValue(), tfLink.getText()));
                JOptionPane.showMessageDialog(null, "Publicité modifiée !");
            } else {
                ServicePublicityImpl sp = new ServicePublicityImpl();
                sp.ajouter(new Publicity(Date.valueOf(datepickerfrom.getValue()), Date.valueOf(datepickerto.getValue()), tfDescription.getText(), filename, tfTitre.getText(), cbposition.getValue(), tfLink.getText()));
                JOptionPane.showMessageDialog(null, "Publicité ajoutée !");
            }

            Parent root = FXMLLoader.load(getClass().getResource("../../views/publicity/index.fxml"));
            Scene scene = new Scene(root);
            PIDEVGUI.pStage.setScene(scene);
            PIDEVGUI.pStage.show();
        }

    }

    public static Publicity getPublicity() {
        return publicity;
    }

    public static void setPublicity(Publicity publicity) {
        AjoutPublicityController.publicity = publicity;
    }

    @FXML
    private void OpenFileMan(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            filename = "pub-" + UUID.randomUUID().toString() + ".png";

            Imagepath.setText(file.getAbsolutePath());
            Path source = Paths.get(Imagepath.getText());
            Path dest = Paths.get("C:\\wamp64\\www\\Prog-in-master\\public\\publicity\\" + filename);
            Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);
            Image im = new Image("http://localhost/Prog-in-master/public/publicity/" + filename);
            ImagePub.setImage(im);
        }
    }

    public boolean checkFields() {
        titreerror.setVisible(false);
        positionerror.setVisible(false);
        descriptionerror.setVisible(false);
        linkerror.setVisible(false);
        imageerror.setVisible(false);
        Boolean isvalid = true;
        if (tfTitre.getText().isEmpty()) {
            titreerror.setVisible(true);
            isvalid = false;
        }
        if (tfDescription.getText().isEmpty()) {
            descriptionerror.setVisible(true);
            isvalid = false;
        }
        if (tfLink.getText().isEmpty()) {
            linkerror.setVisible(true);
            isvalid = false;
        }
        if (cbposition.getValue() == null) {
            positionerror.setVisible(true);
            isvalid = false;
        }
        if (filename.isEmpty()  ) {
            imageerror.setVisible(true);
            isvalid = false;

        }

        return isvalid;
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

}
