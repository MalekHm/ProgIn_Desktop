/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Services.SCryptage;
import Services.SProduct;
import entity.CategoryProduct;
import entity.Product;
import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author hela1
 */
public class AddProductController implements Initializable {

    @FXML
    private TextField tfname;
    @FXML
    private TextField tfdescription;
    @FXML
    private TextField tfprice;
    @FXML
    private TextField tfpricer;
    @FXML
    private TextField tfquantity;
    @FXML
    private ComboBox<String> cbcategory;
    @FXML
    private TableColumn<Product, Integer> colid;
    @FXML
    private TableColumn<Product, String> colname;
    @FXML
    private TableColumn<Product, String> coldescription;
    @FXML
    private TableColumn<Product, Double> colprice;
    @FXML
    private TableColumn<Product, Double> colpricer;
    @FXML
    private TableColumn<Product, Integer> colquantity;
    @FXML
    private TableView<Product> tvproduct;
    @FXML
    private TextField tfid;
    @FXML
    private TextField tfsearch;
    @FXML
    private TableColumn<Product, String> colurlimg;
    @FXML
    private Label filename;
    File file;
    
    @FXML
    private Label lab_cat;
    @FXML
    private Label lab_prix;
    @FXML
    private Label lab_prixr;
    @FXML
    private Label lab_Qte;
    @FXML
    private Label Nullable_warn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            tfid.setVisible(false);
            try {
                showProduct();
            } catch (SQLException ex) {
                Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
            String req = "SELECT * FROM category_product";
            Statement st;
            try {
                
                st = DataSource.getInstance().getCnx().createStatement();
                ResultSet rs = st.executeQuery(req);
                
                ObservableList<String> comboCat = FXCollections.observableArrayList();
                
                while (rs.next()) {
                    comboCat.add(rs.getString("name_category"));
                }
                
                cbcategory.setItems(comboCat);
                
            } catch (SQLException ex) {
                Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            FilteredList<Product> filteredData = new FilteredList<>(getProductList(), b -> true);
            
            
            // 2. Set the filter Predicate whenever the filter changes.
            tfsearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(employee -> {
                    // If filter text is empty, display all persons.
                    
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    
                    // Compare first name and last name of every person with filter text.
                    String lowerCaseFilter = newValue.toLowerCase();
                    
                    if (employee.getName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true; // Filter matches first name.
                    } else if (employee.getDescription().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                    }
                    else if (String.valueOf(employee.getPrice()).indexOf(lowerCaseFilter)!=-1)
                        return true;
                    else
                        return false; // Does not match.
                });
            });
            
            // 3. Wrap the FilteredList in a SortedList.
            SortedList<Product> sortedData = new SortedList<>(filteredData);
            
            // 4. Bind the SortedList comparator to the TableView comparator.
            // 	  Otherwise, sorting the TableView would have no effect.
            sortedData.comparatorProperty().bind(tvproduct.comparatorProperty());
            
            // 5. Add sorted (and filtered) data to the table.
            tvproduct.setItems(sortedData);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
               
        
    }
        
   
    
    public ObservableList<Product> getProductList() throws SQLException{
        ObservableList<Product> ProductList = FXCollections.observableArrayList();
       
        String requete = "SELECT * FROM category_product WHERE name_category='"
                + cbcategory.getValue() + "'";
        String query = "SELECT * FROM  product ";
        Statement st = DataSource.getInstance().getCnx().createStatement();
        ResultSet rs;
        ResultSet result = st.executeQuery(requete);
        CategoryProduct cat = new CategoryProduct();

        while (result.next()) {
            cat.setId(result.getInt(1));
            cat.setNameCategory(result.getString(2));  
        }
        try{
            rs = st.executeQuery(query);
            Product Product ;
            while(rs.next()){
                Product = new Product(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getDouble("price"),rs.getDouble("pricer"),rs.getInt("quantity"),rs.getString("urlimg"));
                ProductList.add(Product);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return ProductList;
    }
    
    public void showProduct() throws SQLException{
        ObservableList<Product> list = getProductList();
        colid.setCellValueFactory(new PropertyValueFactory<Product, Integer> ("id"));
        colname.setCellValueFactory(new PropertyValueFactory<Product, String> ("name"));
        coldescription.setCellValueFactory(new PropertyValueFactory<Product, String> ("description"));
        colprice.setCellValueFactory(new PropertyValueFactory<Product, Double> ("price"));
        colpricer.setCellValueFactory(new PropertyValueFactory<Product, Double> ("pricer"));
        colquantity.setCellValueFactory(new PropertyValueFactory<Product, Integer> ("quantity"));
        
       
        colurlimg.setCellValueFactory(new PropertyValueFactory<Product, String> ("urlimg"));
        tvproduct.setItems(list);
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
    private void addProduct(ActionEvent event) throws SQLException {
       
        String requete = "SELECT * FROM category_product WHERE name_category='"
                + cbcategory.getValue() + "'";
        Statement st = DataSource.getInstance().getCnx().createStatement();
        ResultSet result = st.executeQuery(requete);
        CategoryProduct cat = new CategoryProduct();

        while (result.next()) {
            cat.setId(result.getInt(1));
            cat.setNameCategory(result.getString(2));
         
        }
        if (controle_AntiNull()==true 
               && Controle_Cat() == true 
                && Controle_prix() == true
                && Controle_prixr() == true 
                && Controle_quantite() == true 
                ) 
        {
            Product p = new Product();
          
            p.setName(tfname.getText());

            p.setDescription(tfdescription.getText());

            p.setPrice(Double.parseDouble(tfprice.getText()));

            p.setPricer(Double.parseDouble(tfpricer.getText()));

            p.setQuantity(Integer.parseInt(tfquantity.getText()));

            p.setCategoryProductId(cat);
            
            
            String imgName = SCryptage.cryptWithMD5(filename.getText());
            String imgFullName = imgName + filename.getText().substring(filename.getText().length() - 4, filename.getText().length());
            Random rdm = new Random();

            String finalImgName = rdm.nextInt(999999999) + imgFullName;

            p.setUrlimg(finalImgName);

            SProduct prodServ = new SProduct();
            System.out.println("++++++++++++++++++++++++++++++++++++++");
            prodServ.saveFile(file, finalImgName);
            System.out.println("******************************************");
          
            prodServ.add(p);
            showProduct();
            
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info.");
            alert.setHeaderText("Info.");
            alert.setContentText("le produit "+p.getName()+" a été Ajouté avec succés");
            alert.show();
            
            
        }
        
        else {

            Nullable_warn.setVisible(true);
        }
}
    

    @FXML
    private void editproduct(ActionEvent event) throws SQLException  {
        
        try { String requete = "SELECT * FROM category_product WHERE name_category='"
                + cbcategory.getValue() + "'";
        Statement st = DataSource.getInstance().getCnx().createStatement();
        ResultSet result = st.executeQuery(requete);
        CategoryProduct cat = new CategoryProduct();

        while (result.next()) {
            cat.setId(result.getInt(1));
            cat.setNameCategory(result.getString(2));
        }
        String query = "UPDATE product SET name = '" + tfname.getText() +" ' , description = '" + tfdescription.getText() +" ', price = " + tfprice.getText() + " , pricer = " + tfpricer.getText() + " , quantity =  " + tfquantity.getText() +  " WHERE id = " + tfid.getText()+ "";
        executeQuery(query);
        }catch(Exception ex){
        ex.printStackTrace();
        
        showProduct();
        
        
    }
        
        try {
            showProduct();
        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         
    } 
    
           @FXML
    private void deleteProduct(ActionEvent event) throws SQLException {
        String query = "DELETE FROM product WHERE id = "  + tfid.getText()+ "";
        executeQuery(query);
        showProduct();
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
        Product product = tvproduct.getSelectionModel().getSelectedItem();
        tfid.setText("" +product.getId());
        tfname.setText(product.getName());
        tfdescription.setText(product.getDescription());
        tfprice.setText("" + product.getPrice());
        tfpricer.setText("" + product.getPricer());
        tfquantity.setText("" + product.getQuantity());
     
    }

    @FXML
    private void chooseFile(ActionEvent event) {
        
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Open Resource File");
        Window stage = null;
        file = filechooser.showOpenDialog(stage);

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
        filename.setText("Il Faut Choisir Une Photo Du Produit.");
        filename.setVisible(true);
        }

    }
    
    
    private boolean Controle_Cat() {

        if (cbcategory.getValue() != null) {
            lab_cat.setVisible(false);
            return true;
        } else {
            lab_cat.setText("Il faut Choisir une Catégorie.");
            lab_cat.setVisible(true);
            return false;
        }
    }
    
    
    private boolean Controle_prix() {

        lab_prix.setVisible(false);

        for (int i = 0; i < tfprice.getText().length(); i++) {
            if (!Character.isDigit(tfprice.getText().charAt(i))) {
                lab_prix.setText("Il faut Préciser une Valeur Numérique.");
                lab_prix.setVisible(true);
                return false;
            }
        }
        return true;
    }
    
     private boolean Controle_prixr() {

        lab_prixr.setVisible(false);

        for (int i = 0; i < tfpricer.getText().length(); i++) {
            if (!Character.isDigit(tfpricer.getText().charAt(i))) {
                lab_prixr.setText("Il faut Préciser une Valeur Numérique.");
                lab_prixr.setVisible(true);
                return false;
            }
        }
        return true;
    }
     
    private boolean Controle_quantite() {

        lab_Qte.setVisible(false);

        for (int i = 0; i < tfquantity.getText().length(); i++) {
            if (!Character.isDigit(tfquantity.getText().charAt(i))) {
                lab_Qte.setText("Il faut Préciser une Valeur Numérique.");
                lab_Qte.setVisible(true);
                return false;
            }
        }

        return true;
    }
    
    
     private Boolean controle_AntiNull(){
        
                if(tfname.getText()!=null
                && tfpricer.getText()!=null
                && tfprice.getText()!=null
                && tfquantity.getText()!=null
                && cbcategory.getValue()!=null
                && tfdescription.getText()!=null
                && filename.getText()!=null){
            return true;
        }
        
        return false;
    }
      
}
