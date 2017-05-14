/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.tests;

import edu.esprit.pidev.models.Promotion;
import edu.esprit.pidev.models.User;
import edu.esprit.pidev.models.Reservation;
import edu.esprit.pidev.models.Service;
import edu.esprit.pidev.services.impl.ReservationService;
import java.sql.Date;
import java.text.SimpleDateFormat;


/**
 *
 * @author hanen
 */
public class TestReservation {
    public static void main(String[] args) {
      String date = "2016-11-03";
    java.sql.Date javaSqlDate = java.sql.Date.valueOf(date);
       
             User user = new User(4);
             Service service = new Service(2);
             Promotion promo = new Promotion();
           
             //Reservation res = new Reservation(user, service, 2, 3 ,javaSqlDate, promo, new Date(System.currentTimeMillis()), 1);
        ReservationService resservice = new ReservationService();
   
       // resservice.add(res);
        resservice.delete(255);
       resservice.getAll().forEach(System.out::println);
    }
}
