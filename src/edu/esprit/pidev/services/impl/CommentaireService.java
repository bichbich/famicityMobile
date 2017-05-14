/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.services.impl;
import edu.esprit.pidev.models.Reclamation;
import edu.esprit.pidev.models.Commentaire;
import edu.esprit.pidev.services.interfaces.ICommentaireService;
import edu.esprit.pidev.services.interfaces.IService;
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
 * @author DIARRA
 */
public class CommentaireService  implements IService<Commentaire, Integer>{
   

    private Connection connection;

    public CommentaireService() {
        connection = DataSource.getInstance().getConnection();
    }
     @Override
     public void add(Commentaire commentaire) {
        try {
            String req = "insert into commentaire(titre,contenu) values (?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, commentaire.getTitre());
            ps.setString(2, commentaire.getContenu());
           
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     @Override
    public void update(Commentaire commentaire) {
        try {
            String req = "update commentaire set (titre,contenu) values (?,?) where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, commentaire.getTitre());
            ps.setString(2, commentaire.getContenu());
            ps.setInt(3, commentaire.getId());
            ps.executeUpdate();
          
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
     @Override
     public void delete(Integer id) {
        try {
            String req = "delete from commentaire where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, 2);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
     @Override
    public  List<Commentaire> getAll() {
        List<Commentaire> commentaires = new ArrayList<>();
        try {
            String req = "select * from commentaire";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Commentaire commentaire = new Commentaire(rs.getInt(1),rs.getString(2),rs.getString(3));
                commentaires.add(commentaire);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return commentaires;
    }
     

    public void update(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }   
 @Override
    public Commentaire findById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Commentaire> getAllP() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Commentaire findPointById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void ajouter(Commentaire t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(Commentaire t) {
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
    public void ajoutPoint(Commentaire t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Commentaire> getAllDispo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Commentaire> getAllByName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Commentaire> getAllById() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Commentaire findByCategorie(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateAccepte(Commentaire t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateRefuser(Commentaire t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
     

}
