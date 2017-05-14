/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.services.impl;

import edu.esprit.pidev.models.notification;
import edu.esprit.pidev.services.interfaces.INotification;
import edu.esprit.pidev.technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




/**
 *
 * @author wister
 */
public class NotificationService implements INotification {

    private Connection connection;

    public NotificationService() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void add(notification notif) {

        try {
            String req = "insert into notification (user_id,subject,message,idemeteur,nomemeteur) values (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, notif.getUser_id());
            ps.setString(2, notif.getSubject());
            ps.setString(3, notif.getMessage());
            ps.setInt(4, notif.getIdemeteur());
            ps.setString(5, notif.getNomemeteur());

            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(notification notif) {
        try {

            String req = " update notification set user_id=? ,date=? ,subject=? ,message=? ,link=? ,idemeteur=? ,nomemeteur=? where  id= ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, notif.getUser_id());
            ps.setDate(2, notif.getDate());
            ps.setString(3, notif.getSubject());
            ps.setString(4, notif.getMessage());
            ps.setString(5, notif.getLink());
            ps.setInt(6, notif.getIdemeteur());
            ps.setString(7, notif.getNomemeteur());

            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            String req = "delete from notification where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<notification> getAll() {
        List<notification> notifications = new ArrayList<>();
        try {
            String req = "select * from notification ";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                notification notif = new notification(rs.getInt(1), rs.getInt(2), rs.getString(4), rs.getString(5), rs.getInt(8), rs.getString(9));
                notifications.add(notif);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return notifications;

    }

    public List<notification> getAllByUserId(int id) {
        List<notification> notifications = new ArrayList<>();
        try {
            String req = "select * from notification where user_id=? ";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                notification notif = new notification(rs.getInt(1), rs.getInt(2), rs.getString(4), rs.getString(5), rs.getInt(8), rs.getString(9));
                notifications.add(notif);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return notifications;

    }

  
}
