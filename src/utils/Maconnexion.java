/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




/**
 *
 * @author Dell
 */
public class Maconnexion {
    private static Maconnexion instance;
    private String url = "jdbc:mysql://127.0.0.1:3306/java";
    private String username= "root";
    private String password="";
    private Connection connection;

    private Maconnexion() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("cnx succes");
        } catch (SQLException ex) {
         //  ex.printStackTrace();
            System.out.println("hello  from catch");
            System.out.println(ex.getMessage());
        }
    }

    public static Maconnexion getInstance() {
        if(instance ==null) {
            instance = new Maconnexion();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
    
    
    
}