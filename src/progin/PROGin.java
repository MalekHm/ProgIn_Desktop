/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package progin;
import Entity.Activity;
import Entity.Blog;
import Entity.Categoryactivity;
import Service.SActivity;
import Service.SBlog;
import Service.SCategoryActivity;
import java.sql.SQLException;

/**
 *
 * @author Oumeyma
 */
public class PROGin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        SCategoryActivity sp = new SCategoryActivity();
         SActivity sa = new SActivity();
         SBlog sb = new SBlog ();
         
      //  sp.ajouter(new Categoryactivity(1,"Camping"));
        //sp.afficher().forEach(System.out::println);
        //sb.ajouter(new Blog (1,"Campingblog","cest une sortie en velo " , "12-04-2021"));
        //sb.afficher().forEach(System.out::println);
      //  sa.rechercheByName("Camping");
        //sa.rechercheByName("Velo");
        sp.getTrier();
           } 
    
}
