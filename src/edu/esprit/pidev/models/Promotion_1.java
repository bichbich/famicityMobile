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
public class Promotion_1 {
    
    private int idPromo;
    private String description;
    private float taux;
    private String etat;
    private Service service;
    private float prix_promo;

    public Promotion_1() {
    }

    public Promotion_1(int idPromo, String description, float taux, String etat, Service service, float prix_promo) {
        this.idPromo = idPromo;
        this.description = description;
        this.taux = taux;
        this.etat = etat;
        this.service = service;
        this.prix_promo = prix_promo;
    }

    
    
    public Promotion_1(String description, float taux, String etat, Service service, float prix_promo) {
        this.description = description;
        this.taux = taux;
        this.etat = etat;
        this.service = service;
        this.prix_promo = prix_promo;
    }
    
      public Promotion_1(String description, float taux, Service service, float prix_promo) {
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

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public float getPrix_promo() {
        return prix_promo;
    }

    public void setPrix_promo(float prix_promo) {
        this.prix_promo = prix_promo;
    }

    @Override
    public String toString() {
        return "Promotion{" + "idPromo=" + idPromo + ", description=" + description + ", taux=" + taux + ", etat=" + etat + ", service=" + service + ", prix_promo=" + prix_promo + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.idPromo;
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
        final Promotion_1 other = (Promotion_1) obj;
        return true;
    }

   
   
}
