/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.services.interfaces;
import edu.esprit.pidev.models.Reclamation;
import java.util.List;

/**
 *
 * @author DIARRA
 */
public interface IReclamationService {
     void add(Reclamation reclamation);

    void update(Reclamation reclamation);

    void delete(int id);

    List<Reclamation> getAll();
}
