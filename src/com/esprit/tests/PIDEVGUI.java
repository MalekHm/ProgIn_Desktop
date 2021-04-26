/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.tests;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author aissa
 */
public class PIDEVGUI extends Application {
    
   /* @Override
    public void start(Stage primaryStage) throws IOException {
        //Parent  root = FXMLLoader.load(getClass().getResource("../views/CategoryEvent.fxml"));
      /*  Parent  root = FXMLLoader.load(getClass().getResource("../views/EventFront.fxml"));
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("PIDEV");
        primaryStage.setScene(scene);
        primaryStage.show();
      
      
    }*/
  public static Stage pStage=new Stage();
    @Override
    public void start(Stage primaryStage) {
           try {
            Parent root =FXMLLoader.load(getClass().getResource("../views/EventFront.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("PIDEV");
            primaryStage.setScene(scene);
            primaryStage.show();
            pStage=primaryStage;
            
        } catch (IOException ex) {
               System.out.println("ex");
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
