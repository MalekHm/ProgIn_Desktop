/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Dell
 */
public class user {
    private int id;
    String username,mail,nom,prenom,password,role;

    public user(int id, String username, String mail, String nom, String prenom, String password,String role) {
        this.id = id;
        this.username=username;
        this.mail = mail;
        this.nom = nom;
        this.prenom = prenom;
        this.password=password;
        this.role=role;
      
    }

    public user() {
    }

 

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
      public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

 
  
  
    @Override
    public String toString() {
        return "user{" + "id=" + id + '}';
    }
    

 
    
    
    
}
