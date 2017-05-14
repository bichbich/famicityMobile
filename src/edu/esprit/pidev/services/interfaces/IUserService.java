/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.services.interfaces;


import java.util.List;
import edu.esprit.pidev.models.User;

/**
 *
 * @author wister
 */
public interface IUserService extends IService<User,Integer>{
    
  Boolean authentication(String username , String password);
    public boolean RechLogin(String text);
    
 public User getUser(int id);
  public boolean RechNom(String text);

    
    
}
