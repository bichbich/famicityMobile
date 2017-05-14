/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.services.interfaces;

import java.util.List;
import edu.esprit.pidev.models.Commentaire;


/**
 *
 * @author DIARRA
 */
public interface ICommentaireService {
     void add(Commentaire commentaire);

    void update(Commentaire commentaire);

    void delete(int id);

    List<Commentaire> getAll();
}
