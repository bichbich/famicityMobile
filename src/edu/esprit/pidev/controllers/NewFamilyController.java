/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import edu.esprit.pidev.models.Famille;
import edu.esprit.pidev.models.User;
import edu.esprit.pidev.services.impl.FamilleService;
import edu.esprit.pidev.services.impl.UserService;
import edu.esprit.pidev.services.interfaces.IFamilleService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author wister
 */
public class NewFamilyController implements Initializable {

        @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private ImageView img;

    @FXML
    private JFXButton créer;
      @FXML
    private Label id;
      
    @FXML
    private Label erreur;
          @FXML
    private JFXTextField membres;
UserService userservice = new UserService();
    @FXML
    void creerFamille(ActionEvent event) throws IOException {
         
         IFamilleService f = new FamilleService();
         Famille fa =new Famille();
//   if (userservice.RechNom(c.getValue().getNom()) == false) {
         fa.setNomdeFamilleid(c.getValue());
          fa.setMembres("a:0:{}");
         
         f.add(fa);
         f.add(fa);
         System.out.println("Famille Créee");
 
           }
     @FXML
    private ComboBox<User> c;
     
         ObservableList<User> list =FXCollections.observableArrayList ();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              UserService u1=new UserService();
       list =FXCollections.observableArrayList (u1.getAll());
       
        c.setItems(list);
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
      
    }    
    
}
