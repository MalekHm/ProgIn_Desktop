/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.models;

import java.sql.Date;

/**
 *
 * @author asus
 */
public class command {
    private int id;
    private int user_id ;
    private Date order_date;

    public command(int id, int user_id, Date order_date) {
        this.id = id;
        this.user_id = user_id;
        this.order_date = order_date;
    }

    public command(int user_id, Date order_date) {
        this.user_id = user_id;
        this.order_date = order_date;
    }

    public command(Date order_date) {
        this.order_date = order_date;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    @Override
    public String toString() {
        return "command{" + "id=" + id + ", user_id=" + user_id + ", order_date=" + order_date + '}';
    }
    
  
 
    
   
}
