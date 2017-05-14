/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.services.impl;

import edu.esprit.pidev.models.Service;
import edu.esprit.pidev.services.interfaces.IServiceService;


import edu.esprit.pidev.technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Daddati
 */
public class ServiceService implements IServiceService{
    
    private Connection connection;
    private PreparedStatement ps;

    public ServiceService() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void add(Service service) {  
        try {
            String req = "insert into service(categorie,libelle,nb_places_dispo,prix,description,image_service)"
                    + " values (?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, service.getCategorie());            
            ps.setString(2, service.getLibelle());
            ps.setInt(3, service.getNbPlacesDispo());
             ps.setDouble(4, service.getPrix());
            ps.setString(5, service.getDescription());
         
            ps.setString(6, service.getImageService());
            ps.executeUpdate();
         
        } catch (SQLException ex) {
                ex.printStackTrace();
        }    
    }

    @Override
    public void update(Service service) {
try {
            String req = "update into service(categorie,libelle,nb_places_dispo,prix,description,image_service)"
                    + " values (?,?,?,?,?,?,?) where id_service = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, service.getCategorie());            
            ps.setString(2, service.getLibelle());
            ps.setInt(3, service.getNbPlacesDispo());
            ps.setDouble(4, service.getPrix());
            ps.setString(5, service.getDescription());
            ps.setString(6, service.getImageService());
            ps.setInt(7, service.getIdService());
            ps.executeUpdate();
         
        } catch (SQLException ex) {
            ex.printStackTrace();
        
        }
    }

    @Override
    public void delete(Integer idService) {
        try {
            String req = "delete from service where id_service =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, idService);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

 @Override
    public List<Service> getAll() {
    List<Service> services = new ArrayList<>();
        try {
            String req = "select * from service";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Service service = new Service(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),
                rs.getDouble(5),rs.getString(6),rs.getString(7));
                services.add(service);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return services;
    }

    @Override
    public Service findById(Integer id) {
        Service service= null;
        try {
            String req = "select * from service where id_service =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               service = new Service(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),
                rs.getDouble(5),rs.getString(6),rs.getString(7));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return service;    
    }

    @Override
    public Service findByLibelle(String libelle) {
       Service service= null;
        try {
            String req = "select * from service where id_service =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, libelle);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                service = new Service(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),
                rs.getDouble(5),rs.getString(6),rs.getString(7));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return service;    
    }
   

    @Override
    public List <Service> findByCategorie(String categorie) {
         String req = "SELECT * FROM `service` WHERE `categorie` = ? ";
        List<Service> c = new ArrayList<>();
        try {
            ps = connection.prepareStatement(req);
            ps.setString(1, categorie);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               Service  service = new Service(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),
                rs.getDouble(5),rs.getString(6),rs.getString(7));
               c.add(service);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public Service findPointById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouter(Service t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Service t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getReponse(Integer niv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajoutPoint(Service t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Service> getAllDispo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Service> getAllByName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Service> getAllById() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Service findByCategorie(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateAccepte(Service t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateRefuser(Service t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Service> getAllP() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
