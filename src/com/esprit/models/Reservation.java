/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.models;

import java.sql.Date;

/**
 *
 * @author ousse
 */
public class Reservation {
    private int id;
    private int event_id;
    private int user_id;
    private int nb_participants;
    private boolean status;
    private Date reservation_date;
    private float resteapayer;
    private float reduction;
    private String token;
    private boolean paid;

    public Reservation(int id, int event_id, int user_id, int nb_participants, boolean status, Date reservation_date, float resteapayer, float reduction, String token, boolean paid) {
        this.id = id;
        this.event_id = event_id;
        this.user_id = user_id;
        this.nb_participants = nb_participants;
        this.status = status;
        this.reservation_date = reservation_date;
        this.resteapayer = resteapayer;
        this.reduction = reduction;
        this.token = token;
        this.paid = paid;
    }

    public Reservation(int event_id, int user_id, int nb_participants, boolean status, Date reservation_date, float resteapayer, float reduction, String token, boolean paid) {
        this.event_id = event_id;
        this.user_id = user_id;
        this.nb_participants = nb_participants;
        this.status = status;
        this.reservation_date = reservation_date;
        this.resteapayer = resteapayer;
        this.reduction = reduction;
        this.token = token;
        this.paid = paid;
    }

    public Reservation() {
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getNb_participants() {
        return nb_participants;
    }

    public void setNb_participants(int nb_participants) {
        this.nb_participants = nb_participants;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getReservation_date() {
        return reservation_date;
    }

    public void setReservation_date(Date reservation_date) {
        this.reservation_date = reservation_date;
    }

    public float getResteapayer() {
        return resteapayer;
    }

    public void setResteapayer(float resteapayer) {
        this.resteapayer = resteapayer;
    }

    public float getReduction() {
        return reduction;
    }

    public void setReduction(float reduction) {
        this.reduction = reduction;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", event_id=" + event_id + ", user_id=" + user_id + ", nb_participants=" + nb_participants + ", status=" + status + ", reservation_date=" + reservation_date + ", resteapayer=" + resteapayer + ", reduction=" + reduction + ", token=" + token + ", paid=" + paid + '}';
    }
    
    
    
    
}
