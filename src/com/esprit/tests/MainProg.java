/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.tests;

import com.esprit.models.Promotion;
import com.esprit.models.Publicity;
import com.esprit.services.impl.ServicePromotionImpl;
import com.esprit.services.impl.ServicePublicityImpl;
import java.sql.Date;

/**
 *
 * @author aissa
 */
public class MainProg {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ServicePublicityImpl sp = new ServicePublicityImpl();
        //ajout d'une publicité
        //Publicity pub=new Publicity(new Date(24), new Date(25), "aa", "bb", "aa", "TOP", "ahah");
        //sp.ajouter(pub);
      
        
        //suppression
      //Publicity pub1=new Publicity(3, null, null, null, null, null, null, null);
      // sp.supprimer(pub1);
        
        //modification
        //Publicity pub2=new Publicity(6,new Date(24), new Date(25), "aa", "bbzz", "aaaaddd", "TOP", "ahaha");
        //sp.modifier(pub2);
        
        
        ServicePromotionImpl s=new ServicePromotionImpl();
        
        s.afficher().forEach(System.out::println);
    }
    
}
