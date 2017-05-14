/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import edu.esprit.pidev.models.Famille;
import edu.esprit.pidev.services.impl.FamilleService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author wister
 */
public class DetailsController implements Initializable {
    
   @FXML
    private JFXHamburger humberger;

    @FXML
    private JFXDrawer drawer;

  @FXML
    private JFXListView List;
       @FXML
    private Label membreid;
       @FXML
    private Label membreposition;
    
    Famille s2 = new  Famille();

    FamilleService s = new FamilleService();

 void setFamille(Famille S1) {
         s2=S1;
        // System.out.println(s2);
         membreid.setText(s2.getMembres()+s2.getMembreSimple()+s2.getNomMembres());
        // membreposition.setText(s2.getNomdeFamilleid().getPosition());
         //System.out.println(s2.getNomdeFamilleid().getPosition());
         
    }
  
         @Override
    public void initialize(URL url, ResourceBundle rb) {
           try {
            VBox box = new VBox();
            Parent r = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/SidePanel.fxml"));

            box.getChildren().add(r);
            drawer.setSidePane(box);

            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(humberger);
            transition.setRate(-1);
            humberger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                transition.setRate(transition.getRate() * -1);
                transition.play();

                if (drawer.isShown()) {
                    drawer.close();
                } else {
                    drawer.open();
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(DetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
      

    
    
}
