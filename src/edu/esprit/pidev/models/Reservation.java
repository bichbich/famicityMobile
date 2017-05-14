/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.models;

import java.sql.Date;

/**
 *
 * @author hanen
 */
public class Reservation {
    private int id_reservation;
    private User information_user_id;
    private Service id_service;
    private int nb_placesreserve;
    private int nb_places_restant;
    private Date date_resrvation;
    private Promotion id_promo;
    private Date date_c;
    private int prix_r;

//    public Reservation( int nb_placesreserve, Date date_resrvation, Date date_c, int prix_r) {
//        
//        this.nb_placesreserve = nb_placesreserve;
//        this.date_resrvation = date_resrvation;
//        this.date_c = date_c;
//        this.prix_r = prix_r;
//    }
    

    public Reservation(int id_reservation, User information_user_id, Service id_service, int nb_placesreserve, int nb_places_restant, Date date_resrvation, Promotion id_promo, Date date_c, int prix_r) {
        this.id_reservation = id_reservation;
        this.information_user_id = information_user_id;
        this.id_service = id_service;
        this.nb_placesreserve = nb_placesreserve;
        this.nb_places_restant = nb_places_restant;
        this.date_resrvation = date_resrvation;
        this.id_promo = id_promo;
        this.date_c = date_c;
        this.prix_r = prix_r;
    }

//    public Reservation(User information_user_id, Service id_service, int nb_placesreserve, int nb_places_restant, Date date_resrvation, Promotion id_promo, Date date_c, int prix_r) {
//        this.information_user_id = information_user_id;
//        this.id_service = id_service;
//        this.nb_placesreserve = nb_placesreserve;
//        this.nb_places_restant = nb_places_restant;
//        this.date_resrvation = date_resrvation;
//        this.id_promo = id_promo;
//        this.date_c = date_c;
//        this.prix_r = prix_r;
//    }

    public Reservation() {
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.id_reservation;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reservation other = (Reservation) obj;
        if (this.id_reservation != other.id_reservation) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "reservation{" + "id_reservation=" + id_reservation + ", information_user_id=" + information_user_id + ", id_service=" + id_service + ", nb_placesreserve=" + nb_placesreserve + ", nb_places_restant=" + nb_places_restant + ", date_resrvation=" + date_resrvation + ", id_promo=" + id_promo + ", date_c=" + date_c + ", prix_r=" + prix_r + '}';
    }
    

    /**
     * @return the id_reservation
     */
    public int getId_reservation() {
        return id_reservation;
    }

    /**
     * @param id_reservation the id_reservation to set
     */
    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    /**
     * @return the information_user_id
     */
    public User getInformation_user_id() {
        return information_user_id;
    }

    /**
     * @param information_user_id the information_user_id to set
     */
    public void setInformation_user_id(User information_user_id) {
        this.information_user_id = information_user_id;
    }

    /**
     * @return the id_service
     */
    public Service getId_service() {
        return id_service;
    }

    /**
     * @param id_service the id_service to set
     */
    public void setId_service(Service id_service) {
        this.id_service = id_service;
    }

    /**
     * @return the nb_placesreserve
     */
    public int getNb_placesreserve() {
        return nb_placesreserve;
    }

    /**
     * @param nb_placesreserve the nb_placesreserve to set
     */
    public void setNb_placesreserve(int nb_placesreserve) {
        this.nb_placesreserve = nb_placesreserve;
    }

    /**
     * @return the nb_places_restant
     */
    public int getNb_places_restant() {
        return nb_places_restant;
    }

    /**
     * @param nb_places_restant the nb_places_restant to set
     */
    public void setNb_places_restant(int nb_places_restant) {
        this.nb_places_restant = nb_places_restant;
    }

    /**
     * @return the date_resrvation
     */
    public Date getDate_resrvation() {
        return date_resrvation;
    }

    /**
     * @param date_resrvation the date_resrvation to set
     */
    public void setDate_resrvation(Date date_resrvation) {
        this.date_resrvation = date_resrvation;
    }

    /**
     * @return the id_promo
     */
    public Promotion getId_promo() {
        return id_promo;
    }

    /**
     * @param id_promo the id_promo to set
     */
    public void setId_promo(Promotion id_promo) {
        this.id_promo = id_promo;
    }

    /**
     * @return the date_c
     */
    public Date getDate_c() {
        return date_c;
    }

    /**
     * @param date_c the date_c to set
     */
    public void setDate_c(Date date_c) {
        this.date_c = date_c;
    }

    /**
     * @return the prix_r
     */
    public int getPrix_r() {
        return prix_r;
    }

    /**
     * @param prix_r the prix_r to set
     */
    public void setPrix_r(int prix_r) {
        this.prix_r = prix_r;
    }

 
  
}
