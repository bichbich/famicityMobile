/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.models;

import java.sql.Date;

/**
 *
 * @author wister
 */
public class notification {

    private int id;
    private int user_id;
    private Date date;
    private String subject;
    private String message;
    private String link;
    private int idemeteur;
    private String nomemeteur;

    public int getId() {
        return id;
    }

    public notification() {
    }

    public int getUser_id() {
        return user_id;
    }

    public Date getDate() {
        return date;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public String getLink() {
        return link;
    }

    public int getIdemeteur() {
        return idemeteur;
    }

    public String getNomemeteur() {
        return nomemeteur;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setIdemeteur(int idemeteur) {
        this.idemeteur = idemeteur;
    }

    public void setNomemeteur(String nomemeteur) {
        this.nomemeteur = nomemeteur;
    }


    public notification(int user_id, String subject, String message, int idemeteur, String nomemeteur) {
        this.user_id = user_id;
        this.subject = subject;
        this.message = message;
        this.idemeteur = idemeteur;
        this.nomemeteur = nomemeteur;
    }
    
    public notification(int id,int user_id, String subject, String message, int idemeteur, String nomemeteur) {
        this.id=id;
        this.user_id = user_id;
        this.subject = subject;
        this.message = message;
        this.idemeteur = idemeteur;
        this.nomemeteur = nomemeteur;
    }
//
//    public notification(int user_id, Date date, String subject, String message, int idemeteur, String nomemeteur) {
//        this.user_id = user_id;
//        this.date = date;
//        this.subject = subject;
//        this.message = message;
//        this.idemeteur = idemeteur;
//        this.nomemeteur = nomemeteur;
//    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.id;
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
        final notification other = (notification) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "notification{" + "id=" + id + ", user_id=" + user_id + ", date=" + date + ", subject=" + subject + ", message=" + message + ", link=" + link + ", idemeteur=" + idemeteur + ", nomemeteur=" + nomemeteur + '}';
    }

}
