/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.models;

import java.sql.Date;

/**
 *
 * @author aissa
 */
public class Event {

   private int id;
   private int category_id;
   private String titre;
   private String description;
   private int nb_persons;
   private float price_event;
   private int nb_views;
   private Date start_date;
   private Date end_date;
   private int place_disponible;

    public Event(int id, int category_id, String titre, String description, int nb_persons, float price_event, int nb_views, Date start_date, Date end_date, int place_disponible) {
        this.id = id;
        this.category_id = category_id;
        this.titre = titre;
        this.description = description;
        this.nb_persons = nb_persons;
        this.price_event = price_event;
        this.nb_views = nb_views;
        this.start_date = start_date;
        this.end_date = end_date;
        this.place_disponible = place_disponible;
    }

    public Event(int category_id, String titre, String description, int nb_persons, float price_event, int nb_views, Date start_date, Date end_date, int place_disponible) {
        this.category_id = category_id;
        this.titre = titre;
        this.description = description;
        this.nb_persons = nb_persons;
        this.price_event = price_event;
        this.nb_views = nb_views;
        this.start_date = start_date;
        this.end_date = end_date;
        this.place_disponible = place_disponible;
    }

    public Event(int category_id, String titre, String description, int nb_persons, float price_event, Date start_date, Date end_date, int place_disponible) {
        this.category_id = category_id;
        this.titre = titre;
        this.description = description;
        this.nb_persons = nb_persons;
        this.price_event = price_event;
        this.start_date = start_date;
        this.end_date = end_date;
        this.place_disponible = place_disponible;
    }
    
    

    public float getPrice_event() {
        return price_event;
    }

    public void setPrice_event(float price_event) {
        this.price_event = price_event;
    }
   
   

  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNb_persons() {
        return nb_persons;
    }

    public void setNb_persons(int nb_persons) {
        this.nb_persons = nb_persons;
    }

    public int getNb_views() {
        return nb_views;
    }

    public void setNb_views(int nb_views) {
        this.nb_views = nb_views;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public int getPlace_disponible() {
        return place_disponible;
    }

    public void setPlace_disponible(int place_disponible) {
        this.place_disponible = place_disponible;
    }

    public Event() {
    }

    @Override
    public String toString() {
        return "Event{" + "id=" + id + ", category_id=" + category_id + ", titre=" + titre + ", description=" + description + ", nb_persons=" + nb_persons + ", price_event=" + price_event + ", nb_views=" + nb_views + ", start_date=" + start_date + ", end_date=" + end_date + ", place_disponible=" + place_disponible + '}';
    }
   
   
}
