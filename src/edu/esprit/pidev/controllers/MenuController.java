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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hanen
 */
public class MenuController implements Initializable {

    
    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;
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
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
     

    }
    @FXML
    private Button bt;
         
    @FXML
    void profil1Action(MouseEvent event) throws IOException {
       Parent register_parent1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/Profile.fxml"));

        Scene register_scene1 = new Scene(register_parent1);

        Stage membresStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        membresStage1.hide();
        membresStage1.setScene(register_scene1);
        membresStage1.show(); 

    }    
    
}
