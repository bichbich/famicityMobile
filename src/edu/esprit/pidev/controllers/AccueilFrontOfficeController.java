/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import edu.esprit.pidev.models.User;
import edu.esprit.pidev.services.impl.UserService;
import edu.esprit.pidev.services.interfaces.IUserService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author wister
 */
public class AccueilFrontOfficeController implements Initializable {

        @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;
    
        @FXML
    private Label coin;
         @FXML
    private Button bt;
             @FXML
    private ImageView PhotoUser;
             @FXML
    private JFXButton contact;
             @FXML   
      private Label labelnom;
    @FXML
    void profil1Action(MouseEvent event) throws IOException {
       Parent register_parent1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/Profile.fxml"));

        Scene register_scene1 = new Scene(register_parent1);

        Stage membresStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        membresStage1.hide();
        membresStage1.setScene(register_scene1);
        membresStage1.show(); 

    }

    @FXML
    void profilAction(ActionEvent event) throws IOException {
Parent register_parent1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/guiProfile.fxml"));

        Scene register_scene1 = new Scene(register_parent1);

        Stage membresStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        membresStage1.hide();
        membresStage1.setScene(register_scene1);
        membresStage1.show();
    }
  
  @FXML
    void btnOk(ActionEvent event) throws IOException {
       Parent signin_page_parent = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/FXMLDocument1.fxml"));
        Scene signin_page_scene = new Scene (signin_page_parent);
        Stage app_stage =(Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(signin_page_scene);
        app_stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         IUserService userservice = new UserService();
        User x = userservice.findById(User.getConnectedUser());
        System.out.println(User.getConnectedUser());
       
        labelnom.setText(x.getUsername());
File file = null;
        file = new File(x.getImage());

        Image image = new Image(file.toURI().toString());

              PhotoUser.setImage(image);
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
        User a = userservice.findPointById(User.getConnectedUser());
        System.out.println(User.getConnectedUser());
        System.out.println(String.valueOf(a.getPoint()));
        coin.setText(String.valueOf(a.getPoint()));

    }    
    
}
