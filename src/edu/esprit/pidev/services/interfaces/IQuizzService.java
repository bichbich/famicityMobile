/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.services.interfaces;
import edu.esprit.pidev.models.Quizz;

/**
 *
 * @author asmouna
 */
public interface IQuizzService extends IService<Quizz, Integer>{
    
        Quizz authentication(String login, String password);
}
