/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.services.impl;

import com.esprit.models.ImageEvent;
import com.esprit.services.IService;
import java.util.List;

/**
 *
 * @author ousse
 */
public class ImageEventServiceImpl implements IService<ImageEvent>{

    @Override
    public void ajouter(ImageEvent t) {
       
    }

    @Override
    public void supprimer(ImageEvent t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(ImageEvent t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ImageEvent> afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   //mehouch kemil nekes fazet el id mtaa el event mnin bch njibha
    /* public List<ImageEvent> AfficherImage(Event e) throws SQLException{
         List<ImageEvent> listImage = new ArrayList<>();
         
         try{
             String requete ="SELECT * from image where event_id=?";
             PreparedStatement pst = cnx.prepareStatement(requete);
             pst.setInt(1, e.getId());
             ResultSet rs = pst.executeQuery();
             while(rs.next()){
             listImage.add(new ImageEvent(rs.getInt(1),rs.getString(2)));}
         }catch (SQLException ex) {
            System.err.println(ex.getMessage());
                 }
         return listImage;
         }*/
}
