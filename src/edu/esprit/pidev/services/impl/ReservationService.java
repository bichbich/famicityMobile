/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.services.impl;

import edu.esprit.pidev.models.Promotion;
import edu.esprit.pidev.technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import edu.esprit.pidev.models.Reservation;
import edu.esprit.pidev.models.Service;
import edu.esprit.pidev.models.User;
import edu.esprit.pidev.services.interfaces.IreservationService;
import java.sql.Date;
import java.sql.ResultSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author hanen
 */
public class ReservationService implements IreservationService<Reservation, Integer> {
    private ResultSet rs;

    private Connection connection;
    SimpleDateFormat myFormat = new SimpleDateFormat("MM/dd/yyyy");

    public ReservationService() {
        connection = DataSource.getInstance().getConnection();
    }

    public Promotion getPromotionAscReservation(Service s1) {
        try {

            String req = "select * from promotion where id_service = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, s1.getIdService());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Promotion p1 = new Promotion();
                p1.setIdPromo(rs.getInt(1));
                p1.setPrix_promo(rs.getInt(6));
                return p1;
            }
            return null;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    public void add(Reservation reservation) {
        try {

            String req = "insert into reservation(nb_placesreserve , nb_places_restant , date_reservation ,date_c ,prix_r ,id_promo,Information_user_id,Id_service) values (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, reservation.getNb_placesreserve());
            ps.setInt(2, reservation.getNb_places_restant());
            ps.setDate(3, (Date) reservation.getDate_resrvation());
            ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
            ps.setInt(6, reservation.getId_promo().getIdPromo());
            ps.setInt(5, reservation.getPrix_r());
            ps.setInt(7, reservation.getInformation_user_id().getId());
            ps.setInt(8, reservation.getId_service().getIdService());

            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
         try {
             String req1 = "UPDATE `service` SET `nb_places_dispo`= ? where id_service=?";
             System.out.println("edu.esprit.pidev.services.impl.ReservationService.addWithoutPromotion()");
            PreparedStatement ps1 = connection.prepareStatement(req1);
            ps1.setInt(1, reservation.getNb_places_restant());
            ps1.setInt(2, reservation.getId_service().getIdService());
            ps1.executeUpdate();
        } catch (Exception e) {
        e.printStackTrace();
        }
         try {
             String req2 = "UPDATE `fos_user` SET point=? where id =?";
             System.out.println("edu.esprit.pidev.services.impl.ReservationService.addWithoutPromotion()");
            PreparedStatement ps2 = connection.prepareStatement(req2);
            ps2.setInt(1, reservation.getInformation_user_id().getPoint()+10);
            ps2.setInt(2, reservation.getInformation_user_id().getId());
            ps2.executeUpdate();
        } catch (Exception e) {
        e.printStackTrace();
        }
    }

    public void addWithoutPromotion(Reservation reservation) {
        try {

            String req = "insert into reservation(nb_placesreserve , nb_places_restant , date_reservation ,date_c ,prix_r , Information_user_id,Id_service) values (?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, reservation.getNb_placesreserve());
            ps.setInt(2, reservation.getNb_places_restant());
            ps.setDate(3, (Date) reservation.getDate_resrvation());
            ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));

            ps.setInt(5, reservation.getPrix_r());
            ps.setInt(6, reservation.getInformation_user_id().getId());
            ps.setInt(7, reservation.getId_service().getIdService());

            ps.executeUpdate();

           

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
         try {
             String req1 = "UPDATE `service` SET `nb_places_dispo`= ? where id_service=?";
             System.out.println("edu.esprit.pidev.services.impl.ReservationService.addWithoutPromotion()");
            PreparedStatement ps1 = connection.prepareStatement(req1);
            ps1.setInt(1, reservation.getNb_places_restant());
            ps1.setInt(2, reservation.getId_service().getIdService());
            ps1.executeUpdate();
        } catch (Exception e) {
        e.printStackTrace();
        }
         try {
             String req2 = "UPDATE `fos_user` SET point=? where id =?";
             System.out.println("edu.esprit.pidev.services.impl.ReservationService.addWithoutPromotion()");
            PreparedStatement ps2 = connection.prepareStatement(req2);
            //ps2.setInt(1, reservation.getInformation_user_id().getPoint());
            ps2.setInt(1, reservation.getInformation_user_id().getPoint()+10);
            ps2.setInt(2, reservation.getInformation_user_id().getId());
            
            ps2.executeUpdate();
        } catch (Exception e) {
        e.printStackTrace();
        }
       
        
    }

    public void delete(Integer id) {
        try {
            String req = "delete from reservation where id_reservation =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Reservation> getAll() {
        List<Reservation> resrvations = new ArrayList<>();
        try {
            String req = "select * from reservation";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Reservation reservation = new Reservation(rs.getInt(1), new UserService().findById(rs.getInt(2)), new ServiceService().findById(rs.getInt(3)), rs.getInt(4), rs.getInt(5), rs.getDate(6), new PromotionService().findById(rs.getInt(7)), rs.getDate(8), rs.getInt(9));

                resrvations.add(reservation);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resrvations;
    }

    @Override
    public void update(Reservation t) {
  
        String resq = "UPDATE `reservation` SET prix_r=?  WHERE id_reservation=?";
        try {
            PreparedStatement ps = connection.prepareStatement(resq);
           
            ps.setInt(1, t.getPrix_r());
            ps.setInt(2, t.getId_reservation());
            
             ps.executeUpdate();
             //System.out.println(t.getPrix_r());
        } catch (SQLException ex) {
            ex.printStackTrace();
        
        }
    }    

    
    public List<Reservation> findById(int id) {
List<Reservation> r = new ArrayList<>();      
try {
            String req = "select * from reservation where Information_user_id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
     Reservation  reservation =  new Reservation(rs.getInt(1), new UserService().findById(rs.getInt(2)), new ServiceService().findById(rs.getInt(3)), rs.getInt(4), rs.getInt(5), rs.getDate(6), new PromotionService().findById(rs.getInt(7)), rs.getDate(8), rs.getInt(9));
r.add(reservation);
               // System.out.println(r);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return r;    
    }

    

    
    

}
