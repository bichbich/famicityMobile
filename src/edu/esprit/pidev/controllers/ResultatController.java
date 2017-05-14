/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.controllers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import edu.esprit.pidev.models.User;
import edu.esprit.pidev.services.impl.UserService;
import edu.esprit.pidev.services.interfaces.IUserService;

/**
 * FXML Controller class
 *
 * @author asmouna
 */

public class ResultatController implements Initializable {
    

@FXML
    private ImageView profil;

    @FXML
    private Label l1;
    
    @FXML
    private Label l3;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private Label nom;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private Label coin;

    @FXML
    void profil1Action(ActionEvent event) {}
int R = 0;
    /**
     * Initializes the controller class.
     */
 void setPromotion(Integer R1, Integer R2) {
         l1.setText(R1.toString());
         l3.setText(R2.toString());
         
    }
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                 try {
            VBox box = new VBox();
            Parent r = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/SidePanel.fxml"));

            box.getChildren().add(r);
            drawer.setSidePane(box);

            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
            transition.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                transition.setRate(transition.getRate() * -1);
                transition.play();

                if (drawer.isShown()) {
                    drawer.close();
                } else {
                    drawer.open();
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(AccueilFrontOfficeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        IUserService userservice = new UserService();
        User x = userservice.findPointById(User.getConnectedUser());
        System.out.println(User.getConnectedUser());
        System.out.println(String.valueOf(x.getPoint()+x.getUsername()));
        nom.setText(x.getUsername());
        coin.setText(String.valueOf(x.getPoint()));
    }    
    
}
