/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.esprit.pidev.models.User;
import edu.esprit.pidev.services.impl.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author wister
 */
public class ModifierUserController implements Initializable {

   @FXML
    private AnchorPane AnchorPane;

    @FXML
    private JFXTextField Jpassword;

    @FXML
    private JFXTextField jEmail;

    @FXML
    private JFXTextField jPrenom;

    @FXML
    private JFXTextField jNom;
  @FXML
    private JFXButton retour;

    

 @FXML
    void retourAction(ActionEvent event) throws IOException {
 Parent register_parent1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/Profile.fxml"));

        Scene register_scene1 = new Scene(register_parent1);

        Stage membresStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        membresStage1.hide();
        membresStage1.setScene(register_scene1);
        membresStage1.show();
    }


    @FXML
    void modifierAction(ActionEvent event) throws IOException {
            UserService usermodif =new UserService();
        User user =new User(jNom.getText(), jPrenom.getText(),jEmail.getText(),Jpassword.getText());
        user.setId(User.getConnectedUser());
usermodif.update(user);
        System.out.println("Modifié avec succés");
        
           Parent register_parent1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/Profile.fxml"));

        Scene register_scene1 = new Scene(register_parent1);

        Stage membresStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        membresStage1.hide();
        membresStage1.setScene(register_scene1);
        membresStage1.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
}
