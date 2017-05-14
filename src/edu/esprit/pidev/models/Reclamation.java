/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.models;
import java.util.Date;
import java.sql.Timestamp;

/**
 *
 * @author DIARRA
 */
public class Reclamation {
    private int id;
    private String nom;
    private String sujet;
    private String etat;
    private String nature;
    private int level;
    private String email;
    private Date date;
    private String description;
     private double stat;

    public Reclamation() {
    }

    public Reclamation(String nature, double stat) {
        this.nature = nature;
        this.stat = stat;
    }

    public Reclamation(String nom, String sujet, String nature, int level, String email, String description) {
        this.nom = nom;
        this.sujet = sujet;
        this.nature = nature;
        this.level = level;
        this.email = email;
        this.description = description;
    }

    public Reclamation(String nom, String sujet, String etat, String nature, int level, String email, Date date, String description) {
        this.nom = nom;
        this.sujet = sujet;
        this.etat = etat;
        this.nature = nature;
        this.level = level;
        this.email = email;
        this.date = date;
        this.description = description;
    }
    
    public Reclamation(int id, String nom, String sujet, String etat, String nature, int level, String email, Date date, String description) {
        this.id = id;
        this.nom = nom;
        this.sujet = sujet;
        this.etat = etat;
        this.nature = nature;
        this.level = level;
        this.email = email;
        this.date = date;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public double getStat() {
        return stat;
    }

    public void setStat(double stat) {
        this.stat = stat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", nom=" + nom + ", sujet=" + sujet + ", etat=" + etat + ", nature=" + nature + ", level=" + level + ", email=" + email + ", date=" + date + ", description=" + description + ", stat=" + stat + '}';
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.id;
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
        final Reclamation other = (Reclamation) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
   
    
    
    
}
