/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.services.interfaces;

import edu.esprit.pidev.models.Famille;
import java.util.List;


/**
 *
 * @author wister
 */
public interface IFamilleService {
    void add(Famille famille);

    void update(Famille famille);

    void delete(Integer id);

    List<Famille> getAll();
  

}
