/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controllers;

import com.esprit.models.Category;
import com.esprit.models.Event;
import com.esprit.models.ImageEvent;
import com.esprit.services.impl.EventServiceImpl;
import com.esprit.utils.DataSource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ousse
 */
public class AjouterEventController implements Initializable {
    Connection cnx = DataSource.getInstance().getCnx();
    
    private static Event selectedEvent = new Event();

    @FXML
    private TextField TF_IdEvent;
    @FXML
    private TextField Tf_Title;
    @FXML
    private TextField Tf_Description;
    
    @FXML
    private TextField Tf_nbPerson;
    @FXML
    private TextField Tf_Price;
    @FXML
    private ChoiceBox<String> CBCategory;
    @FXML
    private TableView<Event> Tv_Event;
    @FXML
    private TableColumn<Event,String> Col_Title;
    @FXML
    private TableColumn<Event,String> Col_Description;
    @FXML
    private TableColumn<Event,Date> col_StartDate;
    @FXML
    private TableColumn<Event,Date> Col_EndDate;
    @FXML
    private TableColumn<Event, Integer> Col_nbPerson;
    @FXML
    private TableColumn<Event, Float> Col_Price;
    @FXML
    private TableColumn<Event, Integer> Col_Id;
    @FXML
    private TableColumn<Event, Integer> Col_nbViews;
    @FXML
    private TableColumn<Event, Integer> Col_idCategory;
    @FXML
    private Button Bt_Ajout;
    @FXML
    private Button Bt_Modifier;
    @FXML
    private Button Bt_Supp;
    @FXML
    private DatePicker Dp_datedebut;
    @FXML
    private DatePicker Dp_enddate;
    @FXML
    private TextField Tf_SearchEvent;

    
    ObservableList<Event> list2= FXCollections.observableArrayList();
    @FXML
    private ImageView imageViewTitre;
    @FXML
    private ImageView imageViewDescription;
    @FXML
    private ImageView imageViewNbPerson;
    @FXML
    private ImageView imageViewPrix;
    @FXML
    private ImageView eventImage;
     
    @FXML
    private TextField Imagepath;
    String filename="";
    @FXML
    private void addEvent(ActionEvent event) {
        
         EventServiceImpl sv = new EventServiceImpl();
       Event e =new Event(3, Tf_Title.getText(), Tf_Description.getText(), Integer.valueOf(Tf_nbPerson.getText()), 
               Float.valueOf(Tf_Price.getText()), 0, Date.valueOf(Dp_datedebut.getValue()),Date.valueOf(Dp_enddate.getValue()), Integer.valueOf(Tf_nbPerson.getText()));
       sv.ajouter(e);
              showEvent();
              TF_IdEvent.setText("");
              Tf_Title.setText("");
              Tf_Description.setText("");
              Tf_nbPerson.setText("");
              Tf_Price.setText("");
              Dp_datedebut.setValue(null);
              Dp_enddate.setValue(null);

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       showEvent();
       showCategory();
       
        
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
        Col_Id.setCellValueFactory(new PropertyValueFactory<Event,Integer>("Id"));
        Col_idCategory.setCellValueFactory(new PropertyValueFactory<Event,Integer>("category_id"));
        Col_Title.setCellValueFactory(new PropertyValueFactory<Event,String>("titre"));
        Col_Description.setCellValueFactory(new PropertyValueFactory<Event,String>("description"));
        Col_nbPerson.setCellValueFactory(new PropertyValueFactory<Event,Integer>("nb_persons"));
        Col_Price.setCellValueFactory(new PropertyValueFactory<Event,Float>("price_event"));
        Col_nbViews.setCellValueFactory(new PropertyValueFactory<Event,Integer>("nb_views"));
        col_StartDate.setCellValueFactory(new PropertyValueFactory<Event,Date>("start_date"));
        Col_EndDate.setCellValueFactory(new PropertyValueFactory<Event,Date>("end_date"));
        
        
        ObservableList obList = FXCollections.observableList(list);
       
        Tv_Event.setItems(obList);
        
           

    }
     
     public void showImageEvent(){
        
     }
     
     public List<Category> affichercategory(){
         List<Category> list = new ArrayList<>();
         
          try {
            String requete = "SELECT * FROM category";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Category(rs.getString(1)));
               
                
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
     }
     
    
                 
                 
     
     
     public void showCategory(){
         List<Category> list = affichercategory();
         
         ObservableList obList = FXCollections.observableList(list);

         
         CBCategory.setItems(obList);
     }

     
 
    @FXML
    private void selectLine(MouseEvent event) {
        
               selectedEvent = Tv_Event.getSelectionModel().getSelectedItem();
               Tf_Title.setText(selectedEvent.getTitre());
               Tf_Description.setText(selectedEvent.getDescription());
               Tf_nbPerson.setText(selectedEvent.getNb_persons()+"");
               Tf_Price.setText(selectedEvent.getPrice_event()+"");
               Dp_datedebut.setValue(selectedEvent.getStart_date().toLocalDate());
               Dp_enddate.setValue(selectedEvent.getEnd_date().toLocalDate());
               TF_IdEvent.setText(selectedEvent.getId()+"");
               CBCategory.setValue(selectedEvent.getCategory_id()+"");
               

    }

    @FXML
    private void modifierEvent(ActionEvent event) {
           EventServiceImpl sv = new EventServiceImpl();
       Event e =new Event(Integer.valueOf(TF_IdEvent.getText()), Tf_Title.getText(), Tf_Description.getText(), Integer.valueOf(Tf_nbPerson.getText()), Float.valueOf(Tf_Price.getText()), 0, Date.valueOf(Dp_datedebut.getValue()),Date.valueOf(Dp_enddate.getValue()), Integer.valueOf(Tf_nbPerson.getText()));
       sv.modifier(e);
              showEvent();
              Tf_Title.setText("");
              Tf_Description.setText("");
              Tf_nbPerson.setText("");
              Tf_Price.setText("");
    
              Dp_datedebut.setValue(null);
              Dp_enddate.setValue(null);
              
        
        
    }

    @FXML
    private void supprimerEvent(ActionEvent event) {
        
          EventServiceImpl sv = new EventServiceImpl();
          
          sv.supprimer(selectedEvent);
         
          showEvent();
    }

    
    @FXML
    private void searchEvent(KeyEvent event) {
        FilteredList<Event> filtredData = new FilteredList<>(list2,b -> true);
        Event ev = new Event();
        Tf_SearchEvent.textProperty().addListener((observable, oldValue, newValue)->{
        filtredData.setPredicate(Event ->{
            if(newValue == null||newValue.isEmpty()){
                return true;
            }
            String lowerCaseFilter = newValue.toLowerCase();
            if(ev.getTitre().toLowerCase().indexOf(lowerCaseFilter) != -1){
                return true;
            }else if(ev.getDescription().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                return true;
            }
            else return false;
            
        });
    });
        SortedList<Event> sortedData = new SortedList<>(filtredData);
        sortedData.comparatorProperty().bind(Tv_Event.comparatorProperty());
        Tv_Event.setItems(sortedData);
        
        
        
        
    }

    @FXML
    private boolean testTitle(){
        
        int nbNonChar = 0;
        for (int i = 1; i < Tf_Title.getText().trim().length(); i++) {
            char ch = Tf_Title.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && Tf_Title.getText().trim().length() >= 3) {
          imageViewTitre.setImage(new Image("Image/checkmark.png"));
            return true;
        } else {
           imageViewTitre.setImage(new Image("Image/alertemark.png"));
               
            return false;

        }
    }
    
    @FXML
        private boolean testDescription(){
             int nbNonChar = 0;
        for (int i = 1; i < Tf_Description.getText().trim().length(); i++) {
            char ch = Tf_Description.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && Tf_Description.getText().trim().length() >= 3) {
          imageViewDescription.setImage(new Image("Image/checkmark.png"));
            return true;
        } else {
           imageViewDescription.setImage(new Image("Image/alertemark.png"));
               
            return false;

        }
            
        }
        
         @FXML
        private boolean testNbPerson(){
             

        if (Tf_nbPerson.getText().isEmpty()==false) {
          imageViewNbPerson.setImage(new Image("Image/checkmark.png"));
            return true;
        } else {
           imageViewNbPerson.setImage(new Image("Image/alertemark.png"));
               
            return false;

        }
            
        }
        
           @FXML
        private boolean testPriceEvent(){
           

        if ( Tf_Price.getText().isEmpty()==false) {
          imageViewPrix.setImage(new Image("Image/checkmark.png"));
            return true;
        } else {
           imageViewPrix.setImage(new Image("Image/alertemark.png"));
               
            return false;

        }
            
        }

    private Boolean testSaisie() {
        String erreur = "";
        if (!testTitle()) {
            erreur = erreur + ("Veuillez verifier le titre: seulement des caractères et de nombre >= 3 \n");
        }
        if (!testDescription()) {
            erreur = erreur + ("Veuillez verifier la description: seulement des caractères et de nombre >= 3");
        }
        if (!testNbPerson()) {
            erreur = erreur + ("Veuillez verifier le nombre de personne \n");
        }
        if(!testPriceEvent()){
             erreur = erreur + ("Veuillez verifier le prix de l'évennement \n");
        }
        

        if ( (!testTitle()) || (!testDescription()) || !testNbPerson() || !testPriceEvent()) {
           
           
           
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Erreur de format");
        alert.setHeaderText("Vérifier les champs");
        alert.setContentText(erreur);
       
       
        }

        return  testTitle()&& testDescription()&& testNbPerson()&&testPriceEvent();
    }

    private void testTitle(KeyEvent event) {
        testTitle();
    }

    private void testDescription(KeyEvent event) {
        testDescription();
    }

    private void testNbPerson(KeyEvent event) {
        testNbPerson();
    }

    private void testPriceEvent(KeyEvent event) {
        testPriceEvent();
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
            filename = "Event-" + UUID.randomUUID().toString() + ".png";

            Imagepath.setText(file.getAbsolutePath());
            Path source = Paths.get(Imagepath.getText());
            Path dest = Paths.get("C:\\wamp64\\www\\Prog-in-master\\public\\publicity\\" + filename);
            Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);
            Image im = new Image("http://localhost/Prog-in-master/public/Event/" + filename);
            eventImage.setImage(im);
        }
    }

  


     
    
    
}
