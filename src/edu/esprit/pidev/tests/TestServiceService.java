/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.tests;

import edu.esprit.pidev.models.Service;
import edu.esprit.pidev.services.impl.ServiceService;
import edu.esprit.pidev.services.interfaces.IService;

/**
 *
 * @author Daddati
 */
public class TestServiceService {
    
    public static void main(String[] args) {
        IService serviceSer = new ServiceService();
       // serviceSer.add(service);
       
        serviceSer.delete(42);
       
        serviceSer.getAll().forEach(System.out::println);
    }
}
