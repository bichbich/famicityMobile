/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.services.impl;

import edu.esprit.pidev.models.User;
import edu.esprit.pidev.services.interfaces.IUserService;
import edu.esprit.pidev.technique.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.esprit.pidev.models.BCrypt;

/**
 *
 * @author wister
 */
public class UserService implements IUserService {

    private Connection connection;

    public UserService() {
        connection = DataSource.getInstance().getConnection();
    }

    @Override
    public void add(User user) {
        try {
            String req = "insert into fos_user(username,nom,email,password,image,roles) values (?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getNom());
            ps.setString(3, user.getEmail());
            String pw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(13));
            ps.setString(4, pw);
            ps.setString(5, user.getImage());
          
            ps.setString(6, user.getRoles()
                    
          
                    
            );

            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try {
            String req = "update fos_user set username=?,nom=? ,email=?,password=? where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getNom());
            ps.setString(3, user.getEmail());
            String pw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(13));
            ps.setString(4, pw);
            ps.setInt(5, user.getId()
            );
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            String req = "delete from fos_user  where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try {
            String req = "select * from fos_user";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               
                User user = new User(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getString(8),rs.getString(14),rs.getString(13));
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;
    }

    @Override
    public User findById(Integer id) {
        User user = null;
        try {
            String req = "select * from fos_user where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getString(8), rs.getString(13), rs.getString(14),rs.getString(12),rs.getInt(15));
                //  user = new User(rs.getInt(1),rs.getString(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    public User findByUsername(String s) {
        User user = null;
        try {
            String req = "select * from fos_user where username =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, s);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getString(8), rs.getString(13), rs.getString(14),rs.getString(12));
                //  user = new User(rs.getInt(1),rs.getString(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }
    
    
    @Override
    public Boolean authentication(String username, String password) {
        Boolean exist = false;
        try {
            String query = "select * from fos_user  where username = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
                i++;
                if (i == 1) {
                    if (BCrypt.checkpw(password, rs.getString(8)) == true) {
                        User.setConnectedUser(rs.getInt(1));
                        exist = true;

                    } else {
                        exist = false;
                    }
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(IUserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exist;
    }

    @Override
    public boolean RechLogin(String text) {
        boolean b = false;
        try {
            String query = "SELECT * FROM  fos_user WHERE fos_user.username ='" + text + "'";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery(query);

            while (rs.next()) {
                b = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;
    }

    @Override
    public User getUser(int id) {
        User u = new User();
        try {
            String req = "select * from fos_user where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setNom(rs.getString(3));

                u.setImage(rs.getString(4));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return u;
    }

//    @Override
//    public void addAdmin(User user) {
//          try {
//            String req = "insert into fos_user(username,nom,email,password,roles) values (?,?,?,?,?)";
//            PreparedStatement ps = connection.prepareStatement(req);
//            ps.setString(1, user.getUsername());
//            ps.setString(2, user.getNom());
//            ps.setString(3, user.getEmail());
//            String pw = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(13));
//            ps.setString(4, pw);
//           
//            ps.setString(5, user.getRoles()
//                    
//            );
//
//            ps.executeUpdate();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
    
    public boolean validEmail(String email) {
        boolean exist = false;
        try {
            String req = "select id, email from fos_user where email = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString (1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                User.setIdUser(rs.getInt("id"));
                exist=true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return exist;
    }  

    @Override
    public boolean RechNom(String text) {
       boolean b = false;
        try {
            String query = "SELECT * FROM  fos_user WHERE fos_user.nom ='" + text + "'";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery(query);

            while (rs.next()) {
                b = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return b;
    }

    @Override
    public User findPointById(Integer id) {
    User user = null;
        try {
            String req = "select * from fos_user where id =?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt(1),rs.getInt(15),rs.getInt(16),rs.getInt(17), rs.getString(2), rs.getString(4));
                //  user = new User(rs.getInt(1),rs.getString(2));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;    }

    @Override
    public void ajouter(User t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modifier(User t) {
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
        public void ajoutPoint(User user ) {
 try {
            String req = "update fos_user set point=? where id = ?";
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setInt(1, user.getPoint());
            ps.setInt(2, user.getId()
            );
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }     
    }

    @Override
    public List<User> getAllDispo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getAllByName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getAllById() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public User findByCategorie(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateAccepte(User t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateRefuser(User t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> getAllP() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

}
