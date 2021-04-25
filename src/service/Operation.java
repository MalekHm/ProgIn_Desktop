/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Maconnexion;

/**
 *
 * @author asus
 */
public class Operation {
    private static Operation instance;
    private Statement st;
    private ResultSet rs;
    
    public Operation() {
        Maconnexion cs=Maconnexion.getInstance();
        try {
            st=cs.getConnection().createStatement();
        } catch (SQLException ex) {
           
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static Operation getInstance(){
        if(instance==null) 
            instance=new Operation();
        return instance;
    }
    
    public String recEmail (String user) {
        String req="select mail from user where mail ='"+user+"'";
        String res="null";
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
               res=rs.getString("mail");
              return res ;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res; 
    }
    
     public String recEmailUser (int id) {
        String req="select mail from user where id ="+id;
        String res="null";
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
               res=rs.getString("mail");
              return res ;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res; 
    }
      public String recNom (int id) {
        String req="select nom from user where id ="+id;
        String res="null";
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
               res=rs.getString("nom");
              return res ;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res; 
    }
      
      public String recPrenom (int id) {
        String req="select prenom from user where id ="+id;
        String res="null";
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
               res=rs.getString("prenom");
              return res ;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res; 
    }
      
      public String recRole (String user) {
        String req="select role from user where username ='"+user+"'";
        String res="null";
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
               res=rs.getString("role");
              return res ;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res; 
    }
      
      public int recId (String user) {
        String req="select id from user where mail ='"+user+"'";
      int res=0;
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
               res=rs.getInt("id");
              return res ;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res; 
    }
      
      public String envoyerCode(int id){
         Random r = new Random();
         return ""+r.nextInt(100)+id+r.nextInt(100);
         
       //return ;
     }
      
      
      public String recCode (int id) {
        String req="select code from user where id ='"+id+"'";
        String res="null";
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
               res=rs.getString("code");
              return res ;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res; 
    }
      
     public boolean update(String pwd,int id) {
         
        String qry = "UPDATE user SET password = '"+pwd+"' WHERE id = "+id;
         
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
     
     public boolean updateInfo(String nom,String prenom,String mail,String username,int id) {
         
        String qry = "UPDATE user SET  nom = '"+nom+"', prenom = '"+prenom+"', mail = '"+mail+"', username = '"+username+"' WHERE id = "+id;
         
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
     
     public boolean updateCode(String code,int id) {
         
        String qry = "UPDATE user SET code = '"+code+"' WHERE id = "+id;
         
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Operation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
