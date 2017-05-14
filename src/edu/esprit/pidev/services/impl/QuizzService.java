/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.services.impl;

import edu.esprit.pidev.models.Quizz;
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
public class QuizzService implements IService<Quizz, Integer>{
      private Connection connection;
    
    public QuizzService(){
            connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void ajouter(Quizz q) {
 try {
            String req = "insert into quizz(quizz,question,reponse1,reponse2,reponse3,reponse,reponse_correcte) values (?,?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, q.getQuizz());
            ps.setString(2, q.getQuestion());
            ps.setString(3, q.getReponse1());
            ps.setString(4, q.getReponse2());
            ps.setString(5, q.getReponse3());
            ps.setInt(6, q.getReponse());
            ps.setString(7, q.getReponse_correcte());
            ps.executeUpdate();
        
        } catch (SQLException ex) {
            ex.printStackTrace();
        }      }

    @Override
    public void modifier(Quizz q) {
   try {
            String req = "update quizz set quizz=?, question=?,reponse1=?,reponse2=?,reponse3=?,reponse=?,reponse_correcte=? where id_Quizz = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, q.getQuizz());
            ps.setString(2, q.getQuestion());
            ps.setString(3, q.getReponse1());
            ps.setString(4, q.getReponse2());
            ps.setString(5, q.getReponse3());
            ps.setInt(6, q.getReponse());
            ps.setString(7, q.getReponse_correcte());
            ps.setInt(8, q.getIdQuizz());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }    }

    @Override
    public void supprimer(Integer idQuizz) {
try {
            String req = "delete from quizz where id_quizz =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, idQuizz);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }      }

    @Override
    public List<Quizz> getAll() {
  List<Quizz> quizzs = new ArrayList<>();
        try {
            String req = "select * from quizz";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Quizz quizz = new Quizz(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getString(8));
                quizzs.add(quizz);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return quizzs;      }

    @Override
    public Quizz findById(Integer id) {
  Quizz quizz=null;
        try {
            String req = "select * from quizz where id_quizz = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                quizz = new Quizz(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getString(8));
                quizz.setIdQuizz(rs.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return quizz;    }
    
      public List<Quizz> getQuizz(Integer niv) {
  List<Quizz> quizzs = new ArrayList<>();
        try {
            String req = "select * from quizz where quizz=" + niv + "";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Quizz quizz = new Quizz(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7));
                quizzs.add(quizz);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return quizzs;      }

      
    public void activer(Quizz q) {
   try {
            String req = "update quizz set (etat) values (?) where id_Quizz = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, "Disponible");
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }    }

    @Override
    public Quizz findPointById(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getReponse(Integer niv) {
        List<String> liste = new ArrayList<>();
        try {
            String query = "select reponse_correcte from quizz where quizz=" + niv + "";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                liste.add(resultSet.getString("reponse_correcte"));

            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return liste;
    }

    @Override
    public void ajoutPoint(Quizz t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Quizz> getAllDispo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void add(Quizz t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Quizz t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Quizz> getAllByName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Quizz> getAllById() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Quizz findByCategorie(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateAccepte(Quizz t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateRefuser(Quizz t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Quizz> getAllP() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
