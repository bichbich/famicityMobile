/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.services.interfaces;

import java.util.List;
import edu.esprit.pidev.models.notification;


/**
 *
 * @author wister
 */
public interface INotification {
      void add(notification notif);

    void update(notification notif);

    void delete(Integer id);

    List<notification> getAll();
    
    
}
