/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.tests;

import com.esprit.models.Category;
import com.esprit.models.Event;
import com.esprit.models.Reservation;
import com.esprit.services.impl.CategoryServiceImpl;
import com.esprit.services.impl.EventServiceImpl;
import com.esprit.services.impl.ReservationServiceImpl;
import java.sql.Date;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 *
 * @author aissa
 */
public class MainProg {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      // EventServiceImpl service = new EventServiceImpl();
        //Event e = new Event(2, "camp", "this is a  test java 3 ", 0, 0, 0, new Date(10), new Date(8), 0);
       // service.ajouter(e);
      //service.rechercherByCategoryId(3).forEach(System.out::println);
      // service.rechercherByTitre("test").forEach(System.out::println);
      // service.afficher().forEach(System.out::println);
       // service.supprimer(e);
       //service.afficher().forEach(System.out::println);
        
     
      // CategoryServiceImpl serviceCat = new CategoryServiceImpl();
       //serviceCat.afficher().forEach(System.out::println);
      // Category categorie = new Category(2, "testtaw2", "testtaw2");
       //serviceCat.modifier(categorie);
       // serviceCat.afficher().forEach(System.out::println);
        
        //on peut pas supp car elle relier avec les event, on peut supprimer les categorie qui ne sont pas atribuer a un evennement
        //serviceCat.supprimer(categorie);
      //  serviceCat.afficher().forEach(System.out::println);
      
      
      ReservationServiceImpl serviceReser = new ReservationServiceImpl();
      //serviceReser.ajouter(new Reservation(50,16, 1, 23, true, new Date(5), 0, 0, "", true));
       //serviceReser.afficher().forEach(System.out::println);
     Reservation reser = new Reservation(30,20, 2, true, new Date(4), 0, 0, "", true);
          
        serviceReser.modifier(reser);
        serviceReser.afficher().forEach(System.out::println);
      serviceReser.annulerReservation(33);
      
      
    }
    
}
