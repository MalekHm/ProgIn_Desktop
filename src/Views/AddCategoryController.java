/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Services.SCategoryProduct;
import entity.CategoryProduct;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author hela1
 */
public class AddCategoryController implements Initializable {

    @FXML
    private TextField tfid;
    @FXML
    private TextField tfname;
    @FXML
    private TableView<CategoryProduct> tvCategory;
    @FXML
    private TableColumn<CategoryProduct, Integer> colId;
    @FXML
    private TableColumn<CategoryProduct, String> colName;
    @FXML
    private Button btnadd;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
    @FXML
    private Label Nullable_warn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            tfid.setVisible(false);
            showCategory();
        } catch (SQLException ex) {
            Logger.getLogger(AddCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
 
    @FXML
    private void addcategory(ActionEvent event) throws SQLException {
        
       
         if (controle_AntiNull()==true 
               
                ) 
        {
        
       CategoryProduct c = new CategoryProduct();  
            c.setNameCategory(tfname.getText());
            SCategoryProduct prodServ = new SCategoryProduct();
            System.out.println("******************************************");
            prodServ.add(c);
            showCategory();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info.");
            alert.setHeaderText("Info.");
            alert.setContentText("la Catégorie "+c.getNameCategory()+" a été Ajoutée avec succés");
            alert.show();
            
        }  else {

            Nullable_warn.setVisible(true);
        }
}
    @FXML
    private void updatecategory(ActionEvent event) throws SQLException {
        CategoryProduct c = new CategoryProduct();
        
        c.setId(Integer.parseInt( tfid.getText()));
        c.setNameCategory(tfname.getText());
        
        
        SCategoryProduct serv =new SCategoryProduct();
        serv.edit(c);
                    //***********************************
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info.");
            alert.setHeaderText("Info.");
            alert.setContentText("la Catégorie "+c.getNameCategory()+" a été Modifiée avec succés");
            alert.show();
            
            showCategory();
    }

    @FXML
    private void deletecategory(ActionEvent event) throws SQLException {
        CategoryProduct c = new CategoryProduct();
        c.setId(Integer.parseInt( tfid.getText()));
        SCategoryProduct serv =new SCategoryProduct();
        serv.delete(c);
                    //***********************************
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info.");
            alert.setHeaderText("Info.");
            alert.setContentText("la Catégorie a été supprimé avec succés");
            alert.show();
            
            showCategory();
    }
    
    
    public ObservableList<CategoryProduct> getCategoryProductList() throws SQLException{
        ObservableList<CategoryProduct> categoryList = FXCollections.observableArrayList();  
        String query = "SELECT * FROM  category_product ";
        Statement st = DataSource.getInstance().getCnx().createStatement();
        ResultSet rs;
        
        try{
          
            rs = st.executeQuery(query);
            CategoryProduct categoryProduct ;
            while(rs.next()){
                categoryProduct = new CategoryProduct(rs.getInt("id"), rs.getString("name_category"));
                categoryList.add(categoryProduct);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return categoryList;
    }
    
    
    public void showCategory() throws SQLException{
        ObservableList<CategoryProduct> list = getCategoryProductList();
        colId.setCellValueFactory(new PropertyValueFactory<CategoryProduct, Integer> ("id"));
        colName.setCellValueFactory(new PropertyValueFactory<CategoryProduct, String> ("nameCategory"));
        tvCategory.setItems(list);
    }
    
    

    private void executeQuery(String query) {
        try{
        Statement st = DataSource.getInstance().getCnx().createStatement();
        st.executeUpdate(query);
        
    }catch(Exception ex){
        ex.printStackTrace();
        
    }
}

    
    @FXML
    private void handleMouseAction(MouseEvent event) {
        CategoryProduct category = tvCategory.getSelectionModel().getSelectedItem();
        //System.out.println("id" + category.getId());
        //System.out.println("name_category" + category.getNameCategory());
        tfid.setText("" + category.getId());
        tfname.setText(category.getNameCategory());
        
    }
    
    private Boolean controle_AntiNull(){
        
                if(tfname.getText()!=null
                ){
            return true;
        }
        
        return false;
    }
    
    
     public TextField  getId() {
        return tfid;
    }

    public void setId(TextField id) {
        this.tfid = id;
    }

    public TextField getNameCategory() {
        return tfname;
    }

    public void setNameCategory(TextField name) {
        this.tfname = name;
    }
}
