/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.services.impl;

import edu.esprit.pidev.services.interfaces.IService;
import edu.esprit.pidev.services.interfaces.IUserService;
import edu.esprit.pidev.models.Planing;
import edu.esprit.pidev.models.User;
import edu.esprit.pidev.services.impl.ServiceService;
import edu.esprit.pidev.services.impl.UserService;
import edu.esprit.pidev.technique.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wiem
 */
public class PlaningImp implements IService<Planing, Integer>{

    private Connection connection;
    SimpleDateFormat myFormat = new SimpleDateFormat("MM/dd/yyyy");

    public PlaningImp() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void add(Planing planing) {
        try {
            IUserService userservice =new UserService();
            User x=userservice.findById((User.getConnectedUser()));
            String req = "insert into planing(id_planing, id_service, etat_event, createur, nom_evenement, horaire_planing, end_planing, NomdeFamilleid) values (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, planing.getId_planing());
            ps.setInt(2, planing.getId_service().getIdService());
            ps.setString(3, planing.getEtat_event());
            ps.setString(4, x.getUsername());
            ps.setString(5, planing.getNom_evenement());
            ps.setDate(6, planing.getHoraire_planing());
            ps.setDate(7, planing.getEnd_planing());
            ps.setInt(8, planing.getNomdeFamilleid().getId());
            ps.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Planing planing) {
        IUserService userservice =new UserService();
        User x=userservice.findById((User.getConnectedUser()));
        try {
            //update fos_user set username=?,nom=? ,email=?,password=?,image=? where id = ?
            String req = "update planing set id_service=?, nom_evenement=?, horaire_planing=?, NomdeFamilleid=?, etat_event=?, createur=?, end_planing=? where id_planing= ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1,planing.getId_service().getIdService());
            ps.setString(2, planing.getNom_evenement());
            ps.setDate(3,planing.getHoraire_planing() );
            ps.setInt(4, planing.getNomdeFamilleid().getId());
            ps.setString(5,planing.getEtat_event());
            ps.setString(6,x.getUsername());
            ps.setDate(7, planing.getEnd_planing());
            ps.setInt(8,planing.getId_planing());
            ps.executeUpdate();
            System.out.println("chams "+planing);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void delete(Integer id) {
        try {
            String req = "delete from planing where id_planing=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Planing> getAll() {
        List<Planing> planings = new ArrayList<>();
        try {
            String req = "select * from planing";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //new UserService().findById(rs.getInt(2)), new ServiceService().findById(rs.getInt(3))
                //, etat_event, createur, nom_evenement, horaire_planing, end_planing, NomdeFamilleid
                Planing planing = new Planing(rs.getInt(1), new ServiceService().findById(rs.getInt(2)), rs.getString(3), rs.getDate(4), new UserService().findById(rs.getInt(5)), rs.getString(6), rs.getString(7), rs.getDate(8));
                planings.add(planing);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return planings;
    }

    @Override
    public Planing  findById(Integer id) {
        return null;
    
    }

    @Override
    public List<Planing> getAllByName() {
        List<Planing> planings = new ArrayList<>();
        try {
            String req = "select * from planing,fos_user where NomdeFamilleid=id";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //new UserService().findById(rs.getInt(2)), new ServiceService().findById(rs.getInt(3))
                //, etat_event, createur, nom_evenement, horaire_planing, end_planing, NomdeFamilleid
                Planing planing = new Planing(rs.getInt(1), new ServiceService().findById(rs.getInt(2)), rs.getString(3), rs.getDate(4), new UserService().findById(rs.getInt(5)), rs.getString(6), rs.getString(7), rs.getDate(8));
                User user = new User(rs.getInt("id"),rs.getString("username"), rs.getString("nom"));
                user.setNom(rs.getString("nom"));
                planing.setNomdeFamilleid(user);
                planings.add(planing);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return planings;
    }
    
    @Override
    public List<Planing> getAllById() {
      List<Planing> planings = new ArrayList<>();
        try {
            String req = "select * from planing,fos_user where createur=username";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //new UserService().findById(rs.getInt(2)), new ServiceService().findById(rs.getInt(3))
                //, etat_event, createur, nom_evenement, horaire_planing, end_planing, NomdeFamilleid
                Planing planing = new Planing(rs.getInt(1), new ServiceService().findById(rs.getInt(2)), rs.getString(3), rs.getDate(4), new UserService().findById(rs.getInt(5)), rs.getString(6), rs.getString(7), rs.getDate(8));
                User user = new User(rs.getInt("id"),rs.getString("username"), rs.getString("nom"));
                user.setNom(rs.getString("username"));
                planing.setCreateur(user.getUsername());
                planings.add(planing);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return planings;
    }
    @Override
    public Planing findByCategorie(Integer id) {       
        Planing plan = null;
        try {
            String req = "SELECT * FROM `planing` WHERE `id_planing` = ? ";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 plan = new Planing(rs.getInt(1), new ServiceService().findById(rs.getInt(2)), rs.getString(3), rs.getDate(4), new UserService().findById(rs.getInt(5)), rs.getString(6), rs.getString(7), rs.getDate(8));              
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return plan;
    }
    
//    public static void main(String args[]){
//        Planing p = new Planing(4, 18, "test", new Date(""), 22, "en ", "zaweri", new Date());
//    }

    @Override
    public void updateAccepte(Planing planing) {
        IUserService userservice =new UserService();
        User x=userservice.findById((User.getConnectedUser()));
        try {
            String req = "update planing set etat_event=? where id_planing= ?";
            PreparedStatement ps = connection.prepareStatement(req); 
            ps.setString(1, "Accepter");
            ps.setInt(2,planing.getId_planing());
            ps.executeUpdate();
            //System.out.println("chams "+planing);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
    }

    @Override
    public void updateRefuser(Planing planing) {
     IUserService userservice =new UserService();
        User x=userservice.findById((User.getConnectedUser()));
        try {
            String req = "update planing set etat_event=? where id_planing= ?";
            PreparedStatement ps = connection.prepareStatement(req); 
            ps.setString(1, "Refuser");
            ps.setInt(2,planing.getId_planing());
            ps.executeUpdate();
            //System.out.println("chams "+planing);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Planing findPointById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouter(Planing t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Planing t) {
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
    public void ajoutPoint(Planing t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Planing> getAllDispo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Planing> getAllP() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
