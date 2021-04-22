/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Pane;
import utils.DataSource;

/**
 * FXML Controller class
 *
 * @author hela1
 */
public class PieChartController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Pane paneview;
    
    
        public void initialize(URL url, ResourceBundle rb) {
            
         paneview.getChildren().clear();
         
            Connection c;
            ObservableList<PieChart.Data> data = FXCollections.observableArrayList();
          try{
            c = DataSource.getInstance().getCnx();
            String SQL = "SELECT COUNT(category_product_id), "
                    + "name_category FROM product c, category_product na"
                    + " WHERE na.id=c.category_product_id GROUP BY name_category";
            ResultSet rs = c.createStatement().executeQuery(SQL);
             
            while(rs.next()){
                //adding data on piechart data
                data.add(new PieChart.Data(rs.getString(2),rs.getInt(1)));
            }
          }catch(Exception e){
              System.out.println("Error on DB connection");
              return;
          }
       
       PieChart pieChart = new PieChart();
       pieChart.getData().addAll(data);
       paneview.getChildren().add(pieChart);
       
      
      // pieChart.getData().addAll(data);
        
      
    }    
private void executeQuery(String query) {
        try{
        Statement st = DataSource.getInstance().getCnx().createStatement();
        st.executeUpdate(query);
        
    }catch(Exception ex){
        ex.printStackTrace();
        
    }
}
    
}
