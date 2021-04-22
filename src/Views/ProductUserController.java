/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Services.SProduct;
import entity.Product;
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
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author hela1
 */
public class ProductUserController implements Initializable {

    @FXML
    private TextField prix_min;
    @FXML
    private TextField prix_max;
    @FXML
    private ComboBox<String> categ_btn;
    @FXML
    private RadioButton triAsc_btn;
    @FXML
    private RadioButton triDesc_btn;
    @FXML
    private ListView<Product> listView;
    @FXML
    private TextField tf_findByName;
    
    
    
    SProduct prodServ = new SProduct();
    ObservableList<Product> products = FXCollections.observableArrayList();
    int index = 0;
    @FXML
    private Button search_btn;
    @FXML
    private AnchorPane Dialog;
    @FXML
    private Hyperlink close_btn;
    @FXML
    private ImageView ImgV;
    @FXML
    private Label idProd;
    @FXML
    private Label detail_nom;
    @FXML
    private Label detail_prix;
    @FXML
    private Label detail_descrip;
    @FXML
    private Label detail_categorie;
    @FXML
    private TextField prixr_max;
    @FXML
    private TextField prixr_min;
    @FXML
    private Label detail_prixr;
    @FXML
    private Button Acheter;
    @FXML
    private Button louer;
    @FXML
    private Label lab_Qte;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      products = (ObservableList) prodServ.list();
      idProd.setVisible(false);
      
//*****************************Charge comboBox Categories**********************      
        String req = "SELECT * FROM category_product";
        Statement st;
        try {

            st = DataSource.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);

            ObservableList<String> comboCat = FXCollections.observableArrayList();

            while (rs.next()) {
                comboCat.add(rs.getString("name_category"));
            }

            categ_btn.setItems(comboCat);

        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Dialog.setVisible(false);
        listView.setItems(products);
        listView.getSelectionModel().clearSelection();
        listView.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {

        @Override
        public ListCell<Product> call(ListView<Product> param) {
                ListCell<Product> cell = new ListCell<Product>() {

                    protected void updateItem(Product item, boolean empty) {

                        if (item != null) {

                            HBox hbox = new HBox();
                            hbox.setSpacing(100);
                            hbox.setAlignment(Pos.CENTER);

                            VBox vbox = new VBox();
                            vbox.setSpacing(2.5);
                            vbox.setAlignment(Pos.CENTER);

                            VBox Buttons = new VBox();
                            Buttons.setSpacing(2.5);
                            Buttons.setAlignment(Pos.TOP_LEFT);
                            
                            Image img;
                                img = new Image("file:C:\\Users\\hela1\\Documents\\NetBeansProjects\\Progin\\web\\uploads\\ImgProduit" + item.getUrlimg());
                            ImageView imgView = new ImageView(img);
                            
                            imgView.setFitWidth(100);
                            imgView.setFitHeight(100);
                            imgView.setPreserveRatio(false);
                            imgView.setSmooth(true);
                            imgView.setCache(true);

                            Button detail = new Button("Details.");

                            //*****************************Details_button_OnClick******************************
                            detail.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<Event>() {
                               
                                @Override
                                public void handle(Event event) {

                                    Dialog.setVisible(true);
                                    System.out.println("++++++++++++++++++++++++++++++++");
                                    ImgV.setImage(img);
                                    
                                    detail_nom.setText(item.getName());
                                    detail_descrip.setText(item.getDescription().toString());
                                    detail_prix.setText(String.valueOf(item.getPrice()) + " DT" );
                                    detail_prixr.setText(String.valueOf(item.getPricer()) + " DT" );
                                    lab_Qte.setText(String.valueOf(item.getQuantity()));
                                    detail_categorie.setText(item.getCategoryProductId().getNameCategory());
                                    idProd.setText(item.getId().toString());
                               
                                }
                            });

                           
                            //***********************Button size************************************
                            detail.setMaxWidth(120);
                           
                            //***********************************************************

                            Label nomProd = new Label(item.getName());
                            Label CategProd = new Label(item.getCategoryProductId().getNameCategory());
                            Label prixProd = new Label(String.valueOf("Prix de vente: " + item.getPrice()) + "  DT");
                            Label prixrProd = new Label(String.valueOf("Prix de location: " + item.getPricer()) + "  DT");

                            vbox.getChildren().add(nomProd);
                            vbox.getChildren().add(CategProd);
                            vbox.getChildren().add(prixProd);
                            vbox.getChildren().add(prixrProd);

                            Buttons.getChildren().add(detail);
                           
                            hbox.getChildren().add(imgView);

                            hbox.getChildren().add(vbox);
                            hbox.getChildren().add(Buttons);

                            setGraphic(hbox);

                     
                        }

                    }
                };

                return cell;
            }
        });
       
                }
                
    
                

    @FXML
    private void findByPrice(ActionEvent event) {
        products.clear();
        products.addAll(prodServ.FindByPrice(Double.parseDouble(prix_min.getText()), Double.parseDouble(prix_max.getText())));
        listView.setItems(products);
        //-------------------------------------------------------------------------------
        listView.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {

            @Override
            public ListCell<Product> call(ListView<Product> param) {
                ListCell<Product> cell = new ListCell<Product>() {

                    protected void updateItem(Product item, boolean empty) {

                        if (item != null) {

                            HBox hbox = new HBox();
                            hbox.setSpacing(100);
                            hbox.setAlignment(Pos.CENTER);

                            VBox vbox = new VBox();
                            vbox.setSpacing(2.5);
                            vbox.setAlignment(Pos.CENTER);

                            VBox Buttons = new VBox();
                            Buttons.setSpacing(2.5);
                            Buttons.setAlignment(Pos.TOP_LEFT);

                            Image img;
                            img = new Image("file:C:\\Users\\hela1\\Documents\\NetBeansProjects\\Progin\\web\\uploads\\ImgProduit" + item.getUrlimg());
                            ImageView imgView = new ImageView(img);
//              
//            
//            //**************************ImgView Resize*****************
                            imgView.setFitWidth(100);
                            imgView.setFitHeight(100);
                            imgView.setPreserveRatio(true);
                            imgView.setSmooth(true);
                            imgView.setCache(true);
                            //***********************************************************

                            Button detail = new Button("Details.");

                            //*****************************Details_button_OnClick******************************
                            detail.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<Event>() {
                                @Override
                                public void handle(Event event) {

                                    Dialog.setVisible(true);
                                    System.out.println("++++++++++++++++++++++++++++++++");
                                    ImgV.setImage(img);

                                    detail_nom.setText(item.getName());
                                    detail_descrip.setText(item.getDescription().toString());
                                    detail_prix.setText(String.valueOf(item.getPrice()) + " DT" );
                                    detail_prixr.setText(String.valueOf(item.getPricer()) + " DT" );
                                    lab_Qte.setText(String.valueOf(item.getQuantity()));
                                    detail_categorie.setText(item.getCategoryProductId().getNameCategory());
                                    idProd.setText(item.getId().toString());

                                }
                            });

                            //***********************************************************************
                          
                            

                            //***********************Button size************************************
                            detail.setMaxWidth(120);
                            
                            
                            //***********************************************************

                            Label nomProd = new Label(item.getName());
                            Label CategProd = new Label(item.getCategoryProductId().getNameCategory());
                            Label prixProd = new Label("Prix de vente: " +String.valueOf(item.getPrice()) + "  DT");
                            Label prixrProd = new Label("Prix de location: " +String.valueOf(item.getPricer()) + "  DT");
                            
                            vbox.getChildren().add(nomProd);
                            vbox.getChildren().add(CategProd);
                            vbox.getChildren().add(prixProd);
                            vbox.getChildren().add(prixrProd);

                            Buttons.getChildren().add(detail);
                            
                            

                            hbox.getChildren().add(imgView);
                            hbox.getChildren().add(vbox);
                            hbox.getChildren().add(Buttons);

                            setGraphic(hbox);

                        }

                    }
                };

                return cell;
            }
        });
        
        
        //--------------------------------------------------------------------------------

        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println(products.size());
        System.out.println("+++++++++++++++++++++++++++++++++++");

    }
        
        
        
           
    

    @FXML
    private void findByCategory(ActionEvent event) throws SQLException {
    int id = 0;
        String q = "SELECT id from category_product where name_category='" + categ_btn.getValue() + "'";
        Statement st = DataSource.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery(q);

        while (rs.next()) {
            id = rs.getInt(1);
        }
        products.clear();
        products.addAll(prodServ.FindByCategory(id));
        listView.setItems(products);
        listView.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {

            @Override
            public ListCell<Product> call(ListView<Product> param) {
                ListCell<Product> cell = new ListCell<Product>() {

                    protected void updateItem(Product item, boolean empty) {

                        if (item != null) {

                            HBox hbox = new HBox();
                            hbox.setSpacing(100);
                            hbox.setAlignment(Pos.CENTER);

                            VBox vbox = new VBox();
                            vbox.setSpacing(2.5);
                            vbox.setAlignment(Pos.CENTER);

                            VBox Buttons = new VBox();
                            Buttons.setSpacing(2.5);
                            Buttons.setAlignment(Pos.TOP_LEFT);

                            Image img;
                            img = new Image("file:C:\\Users\\hela1\\Documents\\NetBeansProjects\\Progin\\web\\uploads\\ImgProduit" + item.getUrlimg());
                            ImageView imgView = new ImageView(img);
//              
//            
//            //**************************ImgView Resize*****************
                            imgView.setFitWidth(100);
                            imgView.setFitHeight(100);
                            imgView.setPreserveRatio(true);
                            imgView.setSmooth(true);
                            imgView.setCache(true);
                            //***********************************************************

                            Button detail = new Button("Details.");

                            //*****************************Details_button_OnClick******************************
                            detail.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<Event>() {
                                @Override
                                public void handle(Event event) {

                                    Dialog.setVisible(true);
                                    System.out.println("++++++++++++++++++++++++++++++++");
                                    ImgV.setImage(img);

                                    detail_nom.setText(item.getName());
                                    detail_descrip.setText(item.getDescription().toString());
                                    detail_prix.setText(String.valueOf(item.getPrice()) + " DT" );
                                    detail_prixr.setText(String.valueOf(item.getPricer()) + " DT" );
                                    lab_Qte.setText(String.valueOf(item.getQuantity()));
                                    detail_categorie.setText(item.getCategoryProductId().getNameCategory());
                                    idProd.setText(item.getId().toString());

                                }
                            });

                            //***********************************************************************
                           
                            

                            //***********************Button size************************************
                            detail.setMaxWidth(120);
                           
                            
                            //***********************************************************

                            Label nomProd = new Label(item.getName());
                            Label CategProd = new Label(item.getCategoryProductId().getNameCategory());
                            Label prixProd = new Label("Prix de vente: " +String.valueOf(item.getPrice()) + "  DT");
                            Label prixrProd = new Label("Prix de location: " +String.valueOf(item.getPricer()) + "  DT");
                            
                            vbox.getChildren().add(nomProd);
                            vbox.getChildren().add(CategProd);
                            vbox.getChildren().add(prixProd);
                            vbox.getChildren().add(prixrProd);

                            Buttons.getChildren().add(detail);
                            
                            

                            hbox.getChildren().add(imgView);
                            hbox.getChildren().add(vbox);
                            hbox.getChildren().add(Buttons);

                            setGraphic(hbox);

                        }

                    }
                };

                return cell;
            }
        });
        
        
        //--------------------------------------------------------------------------------

        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println(products.size());
        System.out.println("+++++++++++++++++++++++++++++++++++");

    
    }
    

    @FXML
    private void OrderByAsc(ActionEvent event) {
        products.clear();
        products.addAll(prodServ.OrderByAsc());
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println(products);
        System.out.println("+++++++++++++++++++++++++++++++++++");
        
        
            System.out.println("///////////");
            triDesc_btn.setSelected(false);
    }

    @FXML
    private void OrderByDesc(ActionEvent event) {
        products.clear();
        products.addAll(prodServ.OrderByDesc());
        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println(products);
        System.out.println("+++++++++++++++++++++++++++++++++++");
        
        
            System.out.println("///////////");
            triAsc_btn.setSelected(false);
    }

    @FXML
    private void findByName(ActionEvent event) {
        products.clear();
        products.addAll((ObservableList) prodServ.searchByName(tf_findByName.getText()));
        listView.setItems(products);
        //-------------------------------------------------------------------------------
        listView.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {

            @Override
            public ListCell<Product> call(ListView<Product> param) {
                ListCell<Product> cell = new ListCell<Product>() {

                    protected void updateItem(Product item, boolean empty) {

                        if (item != null) {

                            HBox hbox = new HBox();
                            hbox.setSpacing(100);
                            hbox.setAlignment(Pos.CENTER);

                            VBox vbox = new VBox();
                            vbox.setSpacing(2.5);
                            vbox.setAlignment(Pos.CENTER);

                            VBox Buttons = new VBox();
                            Buttons.setSpacing(2.5);
                            Buttons.setAlignment(Pos.TOP_LEFT);

                            Image img;
                            img = new Image("file:C:\\Users\\hela1\\Documents\\NetBeansProjects\\Progin\\web\\uploads\\ImgProduit" + item.getUrlimg());
                            ImageView imgView = new ImageView(img);
//              
//            
//            //**************************ImgView Resize*****************
                            imgView.setFitWidth(100);
                            imgView.setFitHeight(100);
                            imgView.setPreserveRatio(true);
                            imgView.setSmooth(true);
                            imgView.setCache(true);
                            //***********************************************************

                            Button detail = new Button("Details.");

                            //*****************************Details_button_OnClick******************************
                            detail.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<Event>() {
                                @Override
                                public void handle(Event event) {

                                    Dialog.setVisible(true);
                                    System.out.println("++++++++++++++++++++++++++++++++");
                                    ImgV.setImage(img);

                                    detail_nom.setText(item.getName());
                                    detail_descrip.setText(item.getDescription().toString());
                                    detail_prix.setText(String.valueOf(item.getPrice()) + " DT" );
                                    detail_prixr.setText(String.valueOf(item.getPricer()) + " DT" );
                                    lab_Qte.setText(String.valueOf(item.getQuantity()));
                                    detail_categorie.setText(item.getCategoryProductId().getNameCategory());
                                    idProd.setText(item.getId().toString());

                                }
                            });

                            //***********************************************************************
                            
                            

                            //***********************Button size************************************
                            detail.setMaxWidth(120);
                           
                            //***********************************************************

                            Label nomProd = new Label(item.getName());
                            Label CategProd = new Label(item.getCategoryProductId().getNameCategory());
                             Label prixProd = new Label("Prix de vente: " +String.valueOf(item.getPrice()) + "  DT");
                            Label prixrProd = new Label("Prix de location: " +String.valueOf(item.getPricer()) + "  DT");
                            
                            vbox.getChildren().add(nomProd);
                            vbox.getChildren().add(CategProd);
                            vbox.getChildren().add(prixProd);
                            vbox.getChildren().add(prixrProd);

                            Buttons.getChildren().add(detail);
                            
                            

                            hbox.getChildren().add(imgView);
                            hbox.getChildren().add(vbox);
                            hbox.getChildren().add(Buttons);

                            setGraphic(hbox);

                        }

                    }
                };

                return cell;
            }
        });
        
        
        //--------------------------------------------------------------------------------

        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println(products.size());
        System.out.println("+++++++++++++++++++++++++++++++++++");

    }

    @FXML
    private void findByPricer(ActionEvent event) {
        products.clear();
        products.addAll(prodServ.FindByPricer(Double.parseDouble(prixr_min.getText()), Double.parseDouble(prixr_max.getText())));
        listView.setItems(products);
        //-------------------------------------------------------------------------------
        listView.setCellFactory(new Callback<ListView<Product>, ListCell<Product>>() {

            @Override
            public ListCell<Product> call(ListView<Product> param) {
                ListCell<Product> cell = new ListCell<Product>() {

                    protected void updateItem(Product item, boolean empty) {

                        if (item != null) {

                            HBox hbox = new HBox();
                            hbox.setSpacing(100);
                            hbox.setAlignment(Pos.CENTER);

                            VBox vbox = new VBox();
                            vbox.setSpacing(2.5);
                            vbox.setAlignment(Pos.CENTER);

                            VBox Buttons = new VBox();
                            Buttons.setSpacing(2.5);
                            Buttons.setAlignment(Pos.TOP_LEFT);

                            Image img;
                            img = new Image("file:C:\\Users\\hela1\\Documents\\NetBeansProjects\\Progin\\web\\uploads\\ImgProduit" + item.getUrlimg());
                            ImageView imgView = new ImageView(img);
//              
//            
//            //**************************ImgView Resize*****************
                            imgView.setFitWidth(100);
                            imgView.setFitHeight(100);
                            imgView.setPreserveRatio(true);
                            imgView.setSmooth(true);
                            imgView.setCache(true);
                            //***********************************************************

                            Button detail = new Button("Details.");

                            //*****************************Details_button_OnClick******************************
                            detail.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<Event>() {
                                @Override
                                public void handle(Event event) {

                                    Dialog.setVisible(true);
                                    System.out.println("++++++++++++++++++++++++++++++++");
                                    ImgV.setImage(img);

                                    detail_nom.setText(item.getName());
                                    detail_descrip.setText(item.getDescription().toString());
                                    detail_prix.setText(String.valueOf(item.getPrice()) + " DT" );
                                    detail_prixr.setText(String.valueOf(item.getPricer()) + " DT" );
                                    lab_Qte.setText(String.valueOf(item.getQuantity()));
                                    detail_categorie.setText(item.getCategoryProductId().getNameCategory());
                                    idProd.setText(item.getId().toString());

                                }
                            });

                            //***********************************************************************
                           
                            

                            //***********************Button size************************************
                            detail.setMaxWidth(120);
                            
                           
                            //***********************************************************

                            Label nomProd = new Label(item.getName());
                            Label CategProd = new Label(item.getCategoryProductId().getNameCategory());
                            Label prixProd = new Label("Prix de vente: " +String.valueOf(item.getPrice()) + "  DT");
                            Label prixrProd = new Label("Prix de location: " +String.valueOf(item.getPricer()) + "  DT");
                            
                            vbox.getChildren().add(nomProd);
                            vbox.getChildren().add(CategProd);
                            vbox.getChildren().add(prixProd);
                            vbox.getChildren().add(prixrProd);

                            Buttons.getChildren().add(detail);
                            
                            hbox.getChildren().add(imgView);
                            hbox.getChildren().add(vbox);
                            hbox.getChildren().add(Buttons);

                            setGraphic(hbox);

                        }

                    }
                };

                return cell;
            }
        });
        
        
        //--------------------------------------------------------------------------------

        System.out.println("++++++++++++++++++++++++++++++++++");
        System.out.println(products.size());
        System.out.println("+++++++++++++++++++++++++++++++++++");

    }

    @FXML
    private void CloseDialog(ActionEvent event) {
        Dialog.setVisible(false);
    }

    @FXML
    private void Acheter(ActionEvent event) {
    }

    @FXML
    private void Louer(ActionEvent event) {
    }
        
        
    }
    

    
    

