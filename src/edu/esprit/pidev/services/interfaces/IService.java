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
public interface IService<T, R> {

    void add(T t);

    void update(T t);

    void delete(R id);

    List<T> getAll();
    List<T> getAllP();
    T findById(R id);
    
    T findPointById(Integer id);
        void ajouter(T t);
    void modifier (T t);
    void supprimer (R id);

    List<String> getReponse(Integer niv);
    void ajoutPoint(T t);
    List<T> getAllDispo();
    List<T> getAllByName();
    
    List<T> getAllById();

    T findByCategorie(R id);
    
    void updateAccepte(T t);
    
    void updateRefuser(T t);
    
    
   

}
