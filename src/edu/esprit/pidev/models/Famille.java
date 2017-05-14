/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.models;

import edu.esprit.pidev.services.impl.UserService;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author wister
 */
public class Famille {

    private int id;
    private String membres;
    private User NomdeFamilleid;
    // private String NomdeFamilleid;

    public Famille(int id, User NomdeFamilleid) {
        this.id = id;
        this.NomdeFamilleid = NomdeFamilleid;
    }

    public Famille(int id, User NomdeFamilleid, String membres) {
        this.id = id;
        this.NomdeFamilleid = NomdeFamilleid;
        this.membres = membres;
    }

    public int getId() {
        return id;
    }
    
    public String getMembreSimple(){
        return membres;
    }
    
    public String getMembres() {
        String listDesNoms = "";
        Pattern p = Pattern.compile("\".+?\"");
        Matcher m = p.matcher(membres);
        while (m.find()) {
            String ss = m.group().toString().replace("\"", "");
            int i = Integer.parseInt(ss);
            UserService us = new UserService();
            User u = us.getUser(i);
            String nom = u.getUsername();
            listDesNoms = listDesNoms + " " + nom;
        }
        return listDesNoms;
    }

    public void setMembres(String membres) {
        this.membres = membres;
    }

    public Famille(int id) {
        this.id = id;
    }

    public Famille() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getNomdeFamilleid() {
        return NomdeFamilleid;
    }

    public void setNomdeFamilleid(User NomdeFamilleid) {
        this.NomdeFamilleid = NomdeFamilleid;
    }

    public String getNomMembres() {
        String listDesNoms = "";
        Pattern p = Pattern.compile("\".+?\"");
        Matcher m = p.matcher(membres);
        List<String> ls = new ArrayList<>();
        while (m.find()) {
            String ss = m.group().toString();
            listDesNoms.concat("," + m.group().toString());
        }
        return listDesNoms;
    }

    @Override
    public String toString() {
        return "Famille{" + "id=" + id + ", membres=" + membres + ", NomdeFamilleid=" + NomdeFamilleid + '}';
    }

}
