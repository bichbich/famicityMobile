/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.models;


/**
 *
 * @author Daddati
 */
public class Service {
    private int idService;
    private int nbPlacesDispo;
    private String categorie;
    private String libelle;
    private double prix;
    private String description;
    private String imageService;

    public Service() {
    }

    public Service(int idService) {
        this.idService = idService;
    }
  public Service(int idService, String libelle) {
        this.idService = idService;
        this.libelle = libelle;
    }

    public Service(int idService, String categorie, int nbPlacesDispo, String libelle, double prix, String description, String imageService) {
        this.idService = idService;
        this.nbPlacesDispo = nbPlacesDispo;
        this.categorie = categorie;
        this.libelle = libelle;
        this.prix = prix;
        this.description = description;
        this.imageService = imageService;
    }
  
     public Service(int idService, String categorie, String libelle, int nbPlacesDispo, double prix, String description,  String imageService) {
        this.idService = idService;
        this.categorie = categorie;
        this.libelle = libelle;
        this.description = description;
        this.prix = prix;
        this.nbPlacesDispo = nbPlacesDispo;
        this.imageService = imageService;
    }
        public Service( String categorie, String libelle, String description, double prix, int nbPlacesDispo, String imageService) {
        this.nbPlacesDispo = nbPlacesDispo;
        this.libelle = libelle;
        this.prix = prix;
        this.description = description;
        this.imageService = imageService;
    }

    
    public Service(int idService, String categorie, String libelle, double prix, int nbPlacesDispo, String description, String imageService) {
        this.idService = idService;
        this.nbPlacesDispo = nbPlacesDispo;
        this.categorie = categorie;
        this.libelle = libelle;
        this.prix = prix;
        this.description = description;
        this.imageService = imageService;
    }


    
    public Service( String categorie, String libelle,int nbPlacesDispo , double prix ,String description, String imageService) {
        this.nbPlacesDispo = nbPlacesDispo;
        this.libelle = libelle;
        this.prix = prix;
        this.description = description;
        this.imageService = imageService;
    }

  
    public int getIdService() {
        return idService;
    }

    public void setIdService(int idService) {
        this.idService = idService;
    }

    public int getNbPlacesDispo() {
        return nbPlacesDispo;
    }

    public void setNbPlacesDispo(int nbPlacesDispo) {
        this.nbPlacesDispo = nbPlacesDispo;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageService() {
        return imageService;
    }

    public void setImageService(String imageService) {
        this.imageService = imageService;
    }

    @Override
    public String toString() {
        return libelle ;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.idService;
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
        final Service other = (Service) obj;
        if (this.idService != other.idService) {
            return false;
        }
        return true;
    }
    
    
    
}
