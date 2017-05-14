/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *package edu.esprit.pidev.models;
/**
 *
 * @author wister
 */
package edu.esprit.pidev.models;
public class User {

    /**
     *
     */
public static int connectedUser;
private int id;
private String username;
private String image;
private String nom;
private String email;
private String password;
private int point;
private int pointTotal;
private int carte;
private String roles;

private static Integer idUser=0;


    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, String username, String image, String nom, String email, String password, int point, int pointTotal, int carte, String roles) {
        this.id = id;
        this.username = username;
        this.image = image;
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.point = point;
        this.pointTotal = pointTotal;
        this.carte = carte;
        this.roles = roles;
    }
    
    

    public User(int id, String username, String nom) {
        this.id = id;
        this.username = username;
        this.nom = nom;
        
    }
    
    
    public User(String username,String email, String image){
    this.username=username;
    this.email=email;
    this.image=image;
    }

       public User(int id, int point, int pointTotal, int carte,String username, String image) {
        this.id = id;
        this.point = point;
        this.pointTotal = pointTotal;
        this.carte = carte;
        this.username = username;
        this.image = image;
    }
       
    

    public String getUsername() {
        return username;
    }

    public User(String password) {
        this.id = id;
        this.password = password;
    }
    

    public void setUsername(String username) {
        this.username = username;
    }
    

    public User(int id, String nom, String email, String password) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.password = password;
    }
    


    public User(String username, String nom, String email, String password) {
        this.username = username;
        this.nom = nom;
        this.email = email;
        this.password = password;
    }

    public User(int id, String username, String email, String password, String nom) {
        this.id = id;
        this.username = username;
        this.nom = nom;
        this.email = email;
        this.password = password;
    }
    
    public User(int id, String username, String email,String password,String image, String nom,String roles) {
        this.id = id;
        this.username = username;
        this.image=image;
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
    
     public User(int id, String username, String email,String password,String image, String nom,String roles,int point) {
        this.id = id;
        this.username = username;
        this.image=image;
        this.nom = nom;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.point = point;
    }
    
    
    
         public User(int id, String username, String email,String password, String nom,String image) {
        this.id = id;
        this.username = username;
        this.image=image;
        this.nom = nom;
        this.email = email;
        this.password = password;
        
    }
  
     

   
    

    public int getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getNom() {
        return nom;
    }

    public int getPoint() {
        return point;
    }

    public int getPointTotal() {
        return pointTotal;
    }

    public int getCarte() {
        return carte;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setPointTotal(int pointTotal) {
        this.pointTotal = pointTotal;
    }

    public void setCarte(int carte) {
        this.carte = carte;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public static int getConnectedUser() {
        return connectedUser;
    }

    public static void setConnectedUser(int connectedUser) {
        User.connectedUser = connectedUser;
    }

   
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + this.id;
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
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    public static Integer getIdUser() {
        return idUser;
    }

    public static void setIdUser(Integer idUser) {
        User.idUser = idUser;
    }

    @Override
    public String toString() {
        return  nom ;
    }

   
          
    
}
