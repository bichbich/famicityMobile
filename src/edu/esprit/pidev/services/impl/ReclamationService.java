/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.services.impl;


import edu.esprit.pidev.models.Reclamation;
import edu.esprit.pidev.services.interfaces.IService;
import edu.esprit.pidev.technique.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author DIARRA
 */
public class ReclamationService implements IService<Reclamation, Integer>{
    

    private Connection connection;

    public ReclamationService() {
        connection = DataSource.getInstance().getConnection();
    }

   
  

   
   // private String validerEmail;
 @Override
    public void add(Reclamation reclamation) {
       
        //if(validerNom() & validerSujet() & validerNature() & validerLevel() & validerEmail()){
        try {
            String req = "insert into reclamation(nom,sujet,etat,nature,level,email,description) values (?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, reclamation.getNom());
            ps.setString(2, reclamation.getSujet());
            ps.setString(3, reclamation.getEtat());
            ps.setString(4, reclamation.getNature());
            ps.setInt(5, reclamation.getLevel());
            ps.setString(6, reclamation.getEmail());
            // ps.setDate(7, reclamation.getDate());
            ps.setString(7, reclamation.getDescription());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
    }
     @Override
    public void update(Reclamation reclamation) {
        try {
           // String req = "update reclamation set (nom,sujet,etat,nature,level,email,description) values (?,?,?,?,?,?,?) where id = ?";
           String req = "update reclamation set nom=?, sujet=?,etat=?,nature=?,level=?,email=?,description=? where id=? ";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, reclamation.getNom());
            ps.setString(2, reclamation.getSujet());
            ps.setString(3, reclamation.getEtat());
            ps.setString(4, reclamation.getNature());
            ps.setInt(5, reclamation.getLevel());
            ps.setString(6, reclamation.getEmail());
          // ps.setDate(7, reclamation.getDateR());
            ps.setString(7, reclamation.getDescription());
            ps.setInt(8, reclamation.getId());
            ps.executeUpdate();
            
            System.out.println("modifié avec succes !!!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     @Override
    public void delete(Integer id) {
        try {
            String req = "delete from reclamation where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     @Override
    public List<Reclamation> getAll() {
        List<Reclamation> reclamations = new ArrayList<>();
        try {
            String req = "select * from reclamation";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reclamation reclamation = new Reclamation(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getDate(8),rs.getString(9));
                reclamations.add(reclamation);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return reclamations;
    }
    public ObservableList<Reclamation> getAllWithObservableList() {
       ObservableList<Reclamation> reclamations =FXCollections.observableArrayList();
        try {
            String req = "select * from reclamation";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reclamation reclamation = new Reclamation(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getDate(8),rs.getString(9));
                reclamations.add(reclamation);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return reclamations;
    }
    
 @Override
    public Reclamation findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
/*public void afficher(int id) {
       Reclamation reclamations= new Reclamation();
        try {
            String req = "select * from reclamation where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
          ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reclamation reclamation = new Reclamation(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getDate(8),rs.getString(9));
                reclamations.equals(id);
               // reclamations.getLibelle();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       //return reclamations;
        
    }*/
    

public ObservableList<Reclamation> rechercher(String id) {
          ObservableList<Reclamation> ServiceEventList =FXCollections.observableArrayList() ;
        try {
          
            String req="SELECT * FROM reclamation where nom LIKE '%"+id+"%' or sujet LIKE '%"+id+"%' or level LIKE '%"+id+"%' or email LIKE '%"+id+"%' or date LIKE '%"+id+"%' or description LIKE '%"+id+"%'  " ;
            
            Statement stm=connection.createStatement();
            ResultSet resultat= stm.executeQuery(req);
            while(resultat.next()){
                
                Reclamation MyClaim = new Reclamation();
                MyClaim.setId(resultat.getInt(1));
                MyClaim.setNom(resultat.getString(2));
                MyClaim.setSujet(resultat.getString(3));
                MyClaim.setEtat(resultat.getString(4));
                MyClaim.setNature(resultat.getString(5));
                MyClaim.setLevel(resultat.getInt(6));
                MyClaim.setEmail(resultat.getString(7));
                MyClaim.setDate(resultat.getDate(8));
                MyClaim.setDescription(resultat.getString(9));
                 ServiceEventList.add(MyClaim);
                
                
            }
            
          
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
          return  ServiceEventList;
    }
 //@Override
 public List<Reclamation> getStatCat() {
        String req = "select count(*) as nb,nature from reclamation group by nature";
        List<Reclamation> liste = new ArrayList<Reclamation>();
        try {
           PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reclamation re = new Reclamation(rs.getString("nature"),rs.getDouble("nb"));
                liste.add(re);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }
 

   public List<Reclamation> getAlluserreclamations(int i) {
         String req = "select nom,sujet,etat,nature from reclamation where idR=?";
        List<Reclamation> reclamations = new ArrayList<>();
        try {
           PreparedStatement  ps = connection.prepareStatement(req);
            ps.setInt(1, i);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Reclamation r= new  Reclamation(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getDate(8),rs.getString(9));
                reclamations.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reclamations;
    }

    @Override
    public List<Reclamation> getAllP() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reclamation findPointById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouter(Reclamation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Reclamation t) {
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
    public void ajoutPoint(Reclamation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reclamation> getAllDispo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reclamation> getAllByName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reclamation> getAllById() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reclamation findByCategorie(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateAccepte(Reclamation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateRefuser(Reclamation t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
