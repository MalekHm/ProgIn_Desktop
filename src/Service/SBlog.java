/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entity.Blog;
import IService.IBlog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author Oumeyma
 */
public class SBlog implements IBlog<Blog> {

    Connection cnx = DataSource.getInstance().getCnx();
    @Override
    public void ajouter(Blog b) {
        try {
            String requete = "INSERT INTO Blog (title,description,urlimg,dateAjout) VALUES (?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, b.getTitle());
            pst.setString(2, b.getDescription());
            pst.setString(3, b.getUrlimg());
            pst.setString(4, b.getDateAjout());
            
            pst.executeUpdate();
            System.out.println("Blog ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Blog b) {
        try {
            String requete = "DELETE FROM Blog WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, b.getId());
            pst.executeUpdate();
            System.out.println("blog supprimée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Blog b) {
        try {
            String requete = "UPDATE blog SET title=?,description=?,urlimg=?,dateAjout=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, b.getId());
            pst.setString(4, b.getTitle());
            pst.setString(5, b.getDescription());
            pst.setString(6, b.getDateAjout());
            pst.executeUpdate();
            System.out.println("Blog modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    @Override
    public List<Blog> afficher() {
        List<Blog> list;
        list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM blog";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               list.add(new Blog(rs.getInt(1), rs.getString("title"), rs.getString("description"),rs.getString("urlimg"), rs.getString("dateAjout")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
     
        return list;
    }
    
      public void saveFile(File f,String name) {
        InputStream inStream = null;
	OutputStream outStream = null;
		
    	try{
    	  File Copyfile =new File("C:\\Users\\Oumeyma\\Documents\\NetBeansProjects\\PROGin\\web\\uploads\\imgblog"+name);
    		
    	    inStream = new FileInputStream(f);
    	    outStream = new FileOutputStream(Copyfile);
    	    byte[] buffer = new byte[1024];
    		
    	    int length;
    	    while ((length = inStream.read(buffer)) > 0){
    	    	outStream.write(buffer, 0, length);
    	    }
    	 
    	    inStream.close();
    	    outStream.close();
    	    
    	    System.out.println("File is copied successful!");
    	    
    	}catch(IOException e){
              }
   
      }
      
      public List<Blog> list() {
    
        ObservableList<Blog> Blog=FXCollections.observableArrayList();
    
        try {
             
            String req="SELECT * FROM Blog";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
             
            while (rs.next()){
            Blog p = new Blog();
                p.setId(rs.getInt(1));
                p.setTitle(rs.getString(4));
                p.setDescription(rs.getString(5));
                p.setUrlimg(rs.getString(6));
                p.setDateAjout(rs.getString(7));
                
                
               System.out.println(rs.getString(4));
                
            Blog.add(p);
             }
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());      
        }
   
    return Blog;
}
}
   

