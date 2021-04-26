/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;


import Entity.Blog;
import Service.SBlog;
import java.awt.Dialog;
import java.awt.Event;
import java.io.InputStream;
import javafx.scene.image.Image ;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import org.controlsfx.control.Rating;



/**
 * FXML Controller class
 *
 * @author Oumeyma
 */
public class BlogUserController implements Initializable {
    @FXML
    ImageView imgt;

    @FXML
    private ListView<Blog> listView;
    SBlog blogServ = new SBlog();
    ObservableList<Blog> blogs = FXCollections.observableArrayList();
    int index = 0;
    @FXML
    private Rating rating;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        blogs = (ObservableList) blogServ.list();
        

     
        listView.setItems(blogs);
        listView.getSelectionModel().clearSelection();
        listView.setCellFactory(new Callback<ListView<Blog>, ListCell<Blog>>() {

        @Override
        public ListCell<Blog> call(ListView<Blog> param) {
                ListCell<Blog> cell = new ListCell<Blog>() {

                    protected void updateItem(Blog item, boolean empty) {

                        if (item != null) {

                            HBox hbox = new HBox();
                            hbox.setSpacing(50);
                            hbox.setAlignment(Pos.BOTTOM_LEFT);

                            VBox vbox = new VBox();
                            vbox.setSpacing(15);
                            vbox.setAlignment(Pos.BOTTOM_LEFT);

                            VBox Buttons = new VBox();
                            Buttons.setSpacing(20);
                            Buttons.setAlignment(Pos.TOP_LEFT);
                            
                       
                            
                      /*  Rating rating = new Rating();
                        rating.setUpdateOnHover(false);
                        rating.setPartialRating(false);
                        rating.setMax(5);
                        */
                        
                        
                             Image img;
                              img = new Image("file:C:\\Users\\Oumeyma\\Documents\\NetBeansProjects\\PROGin\\web\\uploads\\imgblog" + item.getUrlimg());
                            ImageView imgView = new ImageView(img);
                            
                            imgView.setFitWidth(300);
                            imgView.setFitHeight(300);
                            imgView.setPreserveRatio(false);
                            imgView.setSmooth(true);
                            imgView.setCache(true);
                    
                            Label nomblog = new Label("Titre du blog : " +item.getTitle());
                            Label descblog = new Label("Description du blog : "  + item.getDescription());
                            
                            Label dateblog = new Label("Date d' ajout du blog est le : " +String.valueOf( item.getDateAjout()) );
                           
                            vbox.getChildren().add(nomblog);
                            vbox.getChildren().add(descblog);
                            vbox.getChildren().add(dateblog);
                            //vbox.getChildren().add(image);
                          //  vbox.getChildren().add(rating);
                      
                           
                            hbox.getChildren().add(imgView);
                            
                            hbox.getChildren().add(vbox);
                           // hbox.getChildren().add(submit);

                            setGraphic(hbox);

                     
                        }

                    }
                };

                return cell;
            }
        });
        
    } 

   /* @FXML
    private void handleButtonAction(ActionEvent event) {
        
        
        System.out.println("Rating given by user :" + rating.getRating());
    }*/

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("Rating given by user :" + rating.getRating());
    }
}

