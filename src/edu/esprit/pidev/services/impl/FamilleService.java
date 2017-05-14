/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.services.impl;

import edu.esprit.pidev.models.Famille;
import edu.esprit.pidev.services.interfaces.IFamilleService;
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
public class FamilleService implements IFamilleService {

    private Connection connection;

    public FamilleService() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void add(Famille famille) {
        try {

            String req = "insert into famille(NomdeFamilleid,membres) values (?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, famille.getNomdeFamilleid().getId());
            ps.setString(2,famille.getNomMembres());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Famille famille) {
        try {
            String req = "update Famille set NomdeFamilleid =? WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, famille.getNomdeFamilleid().getId());
            ps.setInt(2, famille.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void updateMembres(Famille famille) {
        try {
            String req = "update Famille set membres=? WHERE id=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, famille.getMembreSimple());
            ps.setInt(2, famille.getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void delete(Integer id) {
        try {
            String req = "delete from famille where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Famille> getAll() {
        List<Famille> familles = new ArrayList<>();
        try {
            String req = "select * from Famille";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Famille famille = new Famille(rs.getInt(1), new UserService().findById(rs.getInt(2)), rs.getString(3));
            

                familles.add(famille);
                

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return familles;
    }
    
    public Famille findByNomDeFamilleId(int id) {
        Famille f = null;
        try {
            String req = "select * from famille where NomdeFamilleid=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                f = new Famille(rs.getInt(1), new UserService().findById(rs.getInt(2)), rs.getString(3));            
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return f;
    }
    public Famille findById(int id) {
        Famille f = null;
        try {
            String req = "select membres from famille where id=?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                f = new Famille(rs.getInt(1), new UserService().findById(rs.getInt(2)), rs.getString(3));            
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return f;
    }
     public List<Famille> getAlln(String position) {
        List<Famille> familles = new ArrayList<>();
        try {
            String req = "select * from Famille where NomdeFamilleid=?";
            PreparedStatement ps = connection.prepareStatement(req);
           ps.setString(1, position);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Famille famille = new Famille(rs.getInt(1), new UserService().findById(rs.getInt(2)), rs.getString(3));
            

                familles.add(famille);
                

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return familles;
 
}


}
