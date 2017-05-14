/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.services.impl;


import edu.esprit.pidev.models.Promotion;
import edu.esprit.pidev.models.Service;
import edu.esprit.pidev.services.interfaces.IService;
import edu.esprit.pidev.technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author asmouna
 */

public class PromotionService implements IService<Promotion, Integer>{
    private Connection connection;
    
    public PromotionService(){
            connection = DataSource.getInstance().getConnection();
    }

    

   
    @Override
    public List<Promotion> getAll() {
    List<Promotion> promotions = new ArrayList<>();
        try {
            String req = "select * from promotion";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Promotion promotion = new Promotion(rs.getInt(1),rs.getString(2),rs.getFloat(3),rs.getString(4),rs.getFloat(5),new Service());
                promotions.add(promotion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return promotions;    }


    @Override
    public void add(Promotion p) {
    try {
            String req = "insert into promotion(description,taux,etat,prix_promo,id_service) values (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, p.getDescription());
            ps.setFloat(2, p.getTaux());
            ps.setString(3, p.getEtat());
            ps.setFloat(4, p.getPrix_promo());
            ps.setInt(5, p.getService().getIdService());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }       }

    @Override
    public void update(Promotion p) {
try {
            String req = "update promotion set (description,taux,etat,prix_Promo,id_service) values (?,?,?,?,?) where idPromo = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(2, p.getDescription());
            ps.setFloat(3, p.getTaux());
            ps.setString(4, p.getEtat());
            ps.setFloat(5, p.getPrix_promo());
            ps.setInt(6, p.getService().getIdService());
            ps.executeUpdate();
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }    }

         public void activer(Promotion p) {
   try {
            String req = "update promotion set `etat`='Disponible' where `id_promo` =  ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, p.getIdPromo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }    }
     
         public void desactiver (Promotion p) {
   try {
            String req = "update promotion set `etat`='Indisponible' where `id_promo` =  ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, p.getIdPromo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }    }
         
    @Override
    public void delete(Integer id) {
try {
            String req = "delete from product where id_Promo =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }       }

    @Override
    public Promotion findById(Integer id) {
 Promotion promo= null;
        try {
            String req = "select * from promotion where id_service =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
           promo= new Promotion(rs.getInt(1),rs.getString(2),rs.getFloat(3),rs.getString(4),rs.getFloat(5),new Service());

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return promo;        }

    @Override
    public Promotion findPointById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public void ajouter(Promotion p) {
        try {
            String req = "insert into promotion(description,taux,etat,id_service,prix_promo) values (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, p.getDescription());
            ps.setFloat(2, p.getTaux());
            ps.setString(3, p.getEtat());
            ps.setInt(4, p.getService().getIdService());
            ps.setFloat(5, p.getPrix_promo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }    }

    @Override
    public void modifier(Promotion p) {
        try {
            String req = "update promotion set description=?, taux=? ,id_service=? , prix_promo=? where id_promo = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, p.getDescription());
            ps.setFloat(2, p.getTaux());
            ps.setInt(3, p.getService().getIdService());
            ps.setFloat(4, p.getPrix_promo());
            ps.setInt(5, p.getIdPromo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }        
    }

 
    @Override
    public void supprimer(Integer idPromo) {
try {
            String req = "delete from promotion where id_promo =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, idPromo);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }    }

    @Override
    public List<String> getReponse(Integer niv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajoutPoint(Promotion t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

        @Override
    public List<Promotion> getAllDispo() {
List<Promotion> promotions = new ArrayList<>();
        try {
            String req = "select * from promotion where etat='Disponible' ";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Promotion promotion = new Promotion(rs.getInt(1),rs.getString(2),rs.getFloat(3),rs.getString(4),new ServiceService().findById(rs.getInt(5)),rs.getFloat(6));
                promotions.add(promotion);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return promotions;    }    

    @Override
    public List<Promotion> getAllByName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Promotion> getAllById() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Promotion findByCategorie(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateAccepte(Promotion t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateRefuser(Promotion t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Promotion> getAllP() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
