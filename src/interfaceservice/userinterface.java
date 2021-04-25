/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaceservice;

import entity.user;
import java.util.List;

/**
 *
 * @author Dell
 */
public interface userinterface {
   void  ajouterUser(user u);
   public List<user> listuser() ;
   void supprimerUser(user u);
   void modifierUser(user u);
    
}
