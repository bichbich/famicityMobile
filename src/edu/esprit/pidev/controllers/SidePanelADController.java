/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author wister
 */
public class SidePanelADController implements Initializable {

    @FXML
    private JFXButton b1;

    @FXML
    private JFXButton b2;

    @FXML
    private JFXButton b3;

    @FXML
    private JFXButton b4;

    @FXML
    private JFXButton b5;

    @FXML
    private JFXButton b6;

    @FXML
    private JFXButton b7;

    @FXML
    private JFXButton b8;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handlebutton(ActionEvent event) throws IOException {
        
        Parent register_parent1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/reservationADMIN.fxml"));
       
       Scene register_scene1 = new Scene(register_parent1);
       
      Stage stage = (Stage) b4.getScene().getWindow() ;
      stage.hide();
      stage.setScene(register_scene1);
      stage.show();
    }
    
    @FXML
    void makeQuizz(ActionEvent event) throws IOException {
          Parent p1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/ListQuizz.fxml"));
          Scene test1 = new Scene(p1);
          Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
          App1.setScene(test1);
          App1.show();
    }

    
        @FXML
    void makePromo(ActionEvent event) throws IOException {
          Parent p1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/ListPromo.fxml"));
          Scene test1 = new Scene(p1);
          Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
          App1.setScene(test1);
          App1.show();
    }
    
    
       @FXML
    void affichersUsers(ActionEvent event) throws IOException {
         Parent register_parent1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/AfficherUsers.fxml"));
       
       Scene register_scene1 = new Scene(register_parent1);
       
      Stage stage = (Stage) b4.getScene().getWindow() ;
      stage.hide();
      stage.setScene(register_scene1);
      stage.show();

    }
     @FXML
    void btnClaim(ActionEvent event) throws IOException {
   Parent register_parent1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/AfficherReclamation.fxml"));
       
       Scene register_scene1 = new Scene(register_parent1);
       
      Stage stage = (Stage) b4.getScene().getWindow() ;
      stage.hide();
      stage.setScene(register_scene1);
      stage.show();
    }

}
