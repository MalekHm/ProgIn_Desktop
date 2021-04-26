/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Entity.Blog;
import Service.SBlog;
import Service.SCryptage;
import javafx.scene.image.Image;
import java.io.File;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import utils.DataSource;
import java.awt.Window;
import java.util.Random;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
/**
 * FXML Controller class
 *
 * @author Oumeyma
 */
public class AjoutBlogController implements Initializable {
   

    @FXML
    private TextField tfId;
    @FXML
    private TextField tfTitle;
    @FXML
    private TextField tfDescription;
    @FXML
    private TextField tfDateAjout;
    @FXML
    private TableView<Blog> tvBlog;
        @FXML
    private ImageView imgt;
    @FXML
    private TableColumn<Blog, Integer> colId;
    @FXML
    private TableColumn<Blog, String> colTitle;
    @FXML
    private TableColumn<Blog, String> colDescription;
    @FXML
    private TableColumn<Blog, String> colImage;
    @FXML
    private TableColumn<Blog, String> colDateAjout;
    private Button btnSupprimer;
    private Button btnModifier;
    File file;
    @FXML
    private Label filename;
   

    /**
     * Initializes the controller class.
     */
    @Override 
    public void initialize(URL url, ResourceBundle rb) {
        tfDateAjout.setVisible(false);
       colImage.setPrefWidth(100);
       colImage.setCellValueFactory(new PropertyValueFactory<>("Urlimg"));
        try {
            afficheList();
        } catch (SQLException ex) {
            Logger.getLogger(AjoutBlogController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
@FXML
    private void addBlog(ActionEvent event) throws SQLException {
        
        Blog b = new Blog();
        b.setTitle(tfTitle.getText());
        b.setDescription(tfDescription.getText());
       
        b.setDateAjout(tfDateAjout.getText());
        
         String imgName = SCryptage.cryptWithMD5(filename.getText());
            String imgFullName = imgName + filename.getText().substring(filename.getText().length() - 4, filename.getText().length());
            Random rdm = new Random();

            String finalImgName = rdm.nextInt(999999999) + imgFullName;

            b.setUrlimg(finalImgName);
            
            
        SBlog prodServ = new SBlog();
        System.out.println("++++++++++++++++++++++++++++++++++++++");
            prodServ.saveFile(file, finalImgName);
        System.out.println("******************************************");
        prodServ.ajouter(b);
        afficheList();
        
      System.out.println("Hey you get a notification ");
      Notifications notificationBuilder = Notifications.create()
				.title("Notification")
				.text("vous avez une notification ! Un nouveau blog a ete ajoute ").darkStyle()
                              
                                .hideAfter(Duration.seconds(5))
				.position(Pos.TOP_RIGHT);
                
        notificationBuilder.darkStyle();
       notificationBuilder.show();
       
    }

    @FXML
    private void deleteBlog(ActionEvent event) throws SQLException {
        String query = "DELETE FROM blog WHERE id =" +tfId.getText() + "";
        executeQuery(query);
          afficheList();  
    }

    @FXML
    private void editBlog(ActionEvent event) throws SQLException {
        String query = "UPDATE blog SET title= '" + tfTitle.getText() + "' ,description= '" + tfDescription.getText() + "', dateAjout= '" + tfDateAjout.getText() + "' WHERE id = " + tfId.getText()+ "" ;
           executeQuery(query);
            afficheList();
    }
   
    public ObservableList<Blog> getBloglist() throws SQLException{
        ObservableList<Blog> blogList = FXCollections.observableArrayList();
      
        String query ="SELECT * FROM Blog";
      Statement st = DataSource.getInstance().getCnx().createStatement();
      ResultSet rs ; 
        try {
     
     rs = st.executeQuery(query);
            Blog blog ;
            while (rs.next()) {
              blog =new Blog(rs.getInt("id"), rs.getString("title"), rs.getString("description"), rs.getString("dateAjout"));
            blogList.add(blog);
            }

        } catch (Exception ex) {
        }
     
        return blogList;
        
    }
    public void afficheList() throws SQLException{
         
        ObservableList<Blog> list =getBloglist();
        colId.setCellValueFactory(new PropertyValueFactory<Blog,Integer> ("id") );
        colTitle.setCellValueFactory(new PropertyValueFactory<Blog,String> ("title") );
         colDescription.setCellValueFactory(new PropertyValueFactory<Blog,String> ("description") );
      
   colImage.setCellValueFactory(new PropertyValueFactory<Blog,String> ("urlimg") );
          colDateAjout.setCellValueFactory(new PropertyValueFactory<Blog,String> ("dateAjout") );
    
    tvBlog.setItems(list);
    }
 
private void executeQuery(String query) {
        
        
        try {
           Statement st = DataSource.getInstance().getCnx().createStatement();
            st.executeUpdate(query);
        }catch (Exception ex){
        }
        
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
         Blog blog = tvBlog.getSelectionModel().getSelectedItem();
       
        tfId.setText("" + blog.getId());
        tfTitle.setText(blog.getTitle());
         tfDescription.setText(blog.getDescription());
          tfDateAjout.setText(blog.getDateAjout());
    }

    @FXML
    private void choosefile(ActionEvent event) {
       FileChooser filechooser = new FileChooser();
        filechooser.setTitle("choisir une image ");
        Window stage = null;

        file = filechooser.showOpenDialog(new Stage()); 
        

        filechooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("GIF", "*.gif"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        
        if(file!=null){
        filename.setText(file.getName());
        filename.setVisible(true);
        }
        else{
        filename.setText("Il Faut Choisir Une Photo Du Blog.");
        filename.setVisible(true);
        }

    }

    
    
}
