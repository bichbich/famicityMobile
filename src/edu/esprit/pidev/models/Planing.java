/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.models;

import edu.esprit.pidev.models.Service;
import edu.esprit.pidev.models.User;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;

/**
 *
 * @author wiem
 */
public class Planing {
    private int id_planing;
    private Service id_service;
    private String nom_evenement;
    private Date horaire_planing;
    private User NomdeFamilleid;
    private String etat_event;
    private String createur;
    private Date end_planing;

    public Planing(int id_planing, String etat_event) {
        this.id_planing = id_planing;
        this.etat_event = etat_event;
    }

      
    public Planing() {
    }

    public Planing(int id_planing) {
        this.id_planing = id_planing;
    }

    public Planing(int id_planing, Service id_service, String nom_evenement, Date horaire_planing, User NomdeFamilleid, String etat_event, String createur, Date end_planing) {
        this.id_planing = id_planing;
        this.id_service = id_service;
        this.nom_evenement = nom_evenement;
        this.horaire_planing = horaire_planing;
        this.NomdeFamilleid = NomdeFamilleid;
        this.etat_event = etat_event;
        this.createur = createur;
        this.end_planing = end_planing;
    }

    public Planing(Service id_service, String nom_evenement, Date horaire_planing, User NomdeFamilleid, String createur, Date end_planing) {
        this.id_service = id_service;
        this.nom_evenement = nom_evenement;
        this.horaire_planing = horaire_planing;
        this.NomdeFamilleid = NomdeFamilleid;
        this.createur = createur;
        this.end_planing = end_planing;
    }

   

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.id_planing;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Planing other = (Planing) obj;
        if (this.id_planing != other.id_planing) {
            return false;
        }
        if (!Objects.equals(this.createur, other.createur)) {
            return false;
        }
        return true;
    }

    public int getId_planing() {
        return id_planing;
    }

    @Override
    public String toString() {
        return "Nom de l'evenement:" + nom_evenement + " état de l'evenement:" + etat_event + " horaire de l'évenement=" + horaire_planing +"";
    }
    
    public void setId_planing(int id_planing) {
        this.id_planing = id_planing;
    }

    public Service getId_service() {
        return id_service;
    }

    public void setId_service(Service id_service) {
        this.id_service = id_service;
    }

    public String getEtat_event() {
        return etat_event;
    }

    public void setEtat_event(String etat_event) {
        this.etat_event = "en attente";
    }

    public String getCreateur() {
        return createur;
    }

    public void setCreateur(String createur) {
        this.createur = createur;
    }

    public String getNom_evenement() {
        return nom_evenement;
    }

    public void setNom_evenement(String nom_evenement) {
        this.nom_evenement = nom_evenement;
    }

    public Date getHoraire_planing() {
        return horaire_planing;
    }

    public void setHoraire_planing(Date horaire_planing) {
        this.horaire_planing = horaire_planing;
    }

    public Date getEnd_planing() {
        return end_planing;
    }

    public void setEnd_planing(Date end_planing) {
        this.end_planing = end_planing;
    }

    public User getNomdeFamilleid() {
        return NomdeFamilleid;
    }

    public void setNomdeFamilleid(User NomdeFamilleid) {
        this.NomdeFamilleid = NomdeFamilleid;
    }
    
    
}
