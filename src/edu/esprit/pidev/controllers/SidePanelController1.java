package edu.esprit.pidev.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hanen
 */
public class SidePanelController1  {
   

    
    @FXML
    private JFXButton b2;

    @FXML
    private JFXButton b111;

    @FXML
    private JFXButton famille;

    @FXML
    private MenuButton b1;

    @FXML
    private MenuItem parc;

    @FXML
    private MenuItem restaurant;

    @FXML
    private MenuItem cinema;

    @FXML
    private MenuItem cirque;

    @FXML
    private MenuButton b11;

    @FXML
    private MenuItem ajoutEvenement;

    @FXML
    private MenuItem GererEvenement;

    @FXML
    private MenuItem VoirInvt;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        
        Parent register_parent1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/parcFXML.fxml"));
       
       Scene register_scene1 = new Scene(register_parent1);
       
      Stage stage = (Stage) b1.getScene().getWindow() ;
      stage.hide();
      stage.setScene(register_scene1);
      stage.show();
    }
    
    @FXML
    void makeQuizz(ActionEvent event) throws IOException {
          Parent p1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/Quizz.fxml"));
          Scene test1 = new Scene(p1);
          Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
          App1.setScene(test1);
          App1.show();
    }
       @FXML
    void GererEvenementAction(ActionEvent event) throws IOException {
           Parent register_parent1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/MesInvitations.fxml"));
       
       Scene register_scene1 = new Scene(register_parent1);
       
      Stage stage = (Stage) b11.getScene().getWindow() ;
      stage.hide();
      stage.setScene(register_scene1);
      stage.show();

    }

    @FXML
    void VoirInvtAction(ActionEvent event) throws IOException {
           Parent register_parent1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/jesuisInviter.fxml"));
       
       Scene register_scene1 = new Scene(register_parent1);
       
      Stage stage = (Stage) b11.getScene().getWindow() ;
      stage.hide();
      stage.setScene(register_scene1);
      stage.show();

    }

    @FXML
    void ajoutEvenementAction(ActionEvent event) throws IOException {
       Parent register_parent1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/Planing.fxml"));
       
       Scene register_scene1 = new Scene(register_parent1);
       
      Stage stage = (Stage) b11.getScene().getWindow() ;
      stage.hide();
      stage.setScene(register_scene1);
      stage.show();

    }

        @FXML
    void makePromo(ActionEvent event) throws IOException {
          Parent p1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/PromoListFront.fxml"));
          Scene test1 = new Scene(p1);
          Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
          App1.setScene(test1);
          App1.show();
    }
       @FXML
    private void handle1ButtonAction(ActionEvent event) throws IOException {
        
        Parent register_parent1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/restaurant.fxml"));
       
       Scene register_scene1 = new Scene(register_parent1);
       
      Stage stage = (Stage) b1.getScene().getWindow() ;
      stage.hide();
      stage.setScene(register_scene1);
      stage.show();
    }
       
    @FXML
    private void handle2ButtonAction(ActionEvent event) throws IOException {
        
        Parent register_parent1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/cinema.fxml"));
       
       Scene register_scene1 = new Scene(register_parent1);
       
      Stage stage = (Stage) b1.getScene().getWindow() ;
      stage.hide();
      stage.setScene(register_scene1);
      stage.show();
    }
       
 

    @FXML
    private void handle3ButtonAction(ActionEvent event) throws IOException {
        
        Parent register_parent1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/cirque.fxml"));
       
       Scene register_scene1 = new Scene(register_parent1);
       
      Stage stage = (Stage) b1.getScene().getWindow() ;
      stage.hide();
      stage.setScene(register_scene1);
      stage.show();
    }
       
    @FXML
    void RedirigerVersHome(MouseEvent event) throws IOException {
        Parent register_parent1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/AccueilFrontOffice.fxml"));

        Scene register_scene1 = new Scene(register_parent1);

        Stage membresStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        membresStage1.hide();
        membresStage1.setScene(register_scene1);
        membresStage1.show();
    }  
        @FXML
    void familles(ActionEvent event) throws IOException {
          Parent register_parent4 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/familles.fxml"));
       
       Scene register_scene4 = new Scene(register_parent4);
       
       
      Stage membresStage4 = (Stage)((Node)event.getSource()).getScene().getWindow();
      membresStage4.hide();
      membresStage4.setScene(register_scene4);
      membresStage4.show();

    }

}
