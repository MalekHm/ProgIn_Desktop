/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevjavaa;

import java.sql.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.Maconnexion;


/**
 *
 * @author Dell
 */
public class Pidevjavaa extends Application {
        Connection cnx;

    @Override
    public void start(Stage stage) throws Exception {
                                cnx=Maconnexion.getInstance().getConnection();

        Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
