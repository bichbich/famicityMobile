/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.models;

/**
 *
 * @author asmouna
 */
public class Promotion {
    
    private int idPromo;
    private String description;
    private float taux;
    private String etat;
    private float prix_promo;
    private Service service;

    public Promotion() {
    }
    
       public Promotion(int idPromo, String description, float taux, String etat, Service service, float prix_promo) {
        this.idPromo = idPromo;
        this.description = description;
        this.taux = taux;
        this.etat = etat;
        this.service = service;
        this.prix_promo = prix_promo;
    }

    public Promotion(int idPromo, String description, float taux, String etat, float prix_promo, Service service) {
        this.idPromo = idPromo;
        this.description = description;
        this.taux = taux;
        this.etat = etat;
        this.prix_promo = prix_promo;
        this.service = service;
    }
    
    

    public Promotion(String description, float taux, String etat, float prix_promo, Service service) {
        this.description = description;
        this.taux = taux;
        this.etat = etat;
        this.prix_promo = prix_promo;
        this.service = service;
    }

    public Promotion(int idPromo) {
        this.idPromo = idPromo;
    }

    
    
    public Promotion(String description, float taux, String etat, Service service, float prix_promo) {
        this.description = description;
        this.taux = taux;
        this.etat = etat;
        this.service = service;
        this.prix_promo = prix_promo;
    }
    
      public Promotion(String description, float taux, Service service, float prix_promo) {
        this.description = description;
        this.taux = taux;
        this.service = service;
        this.prix_promo = prix_promo;
    }

    public int getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(int idPromo) {
        this.idPromo = idPromo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getTaux() {
        return taux;
    }

    public void setTaux(float taux) {
        this.taux = taux;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public float getPrix_promo() {
        return prix_promo;
    }

    public void setPrix_promo(float prix_promo) {
        this.prix_promo = prix_promo;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "Promotion{" + "idPromo=" + idPromo + ", description=" + description + ", taux=" + taux + ", etat=" + etat + ", prix_promo=" + prix_promo + ", service=" + service + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.idPromo;
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
        final Promotion other = (Promotion) obj;
        return true;
    }


   
}
