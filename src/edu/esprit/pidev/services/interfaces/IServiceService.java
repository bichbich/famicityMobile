/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.services.interfaces;

import edu.esprit.pidev.models.Service;
import java.util.List;


/**
 *
 * @author Daddati
 */
public interface IServiceService extends IService<Service, Integer>{
    Service findByLibelle(String libelle);
    List<Service> findByCategorie(String categorie);
}
