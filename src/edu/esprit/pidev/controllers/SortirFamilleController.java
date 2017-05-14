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
 * @author DIARRA
 */
public class SortirFamilleController implements Initializable {

    @FXML
    private JFXButton b1;
    @FXML
    private JFXButton b2;
    @FXML
    private JFXButton famille;
    @FXML
    private JFXButton b11;
    @FXML
    private JFXButton cirque;
    @FXML
    private JFXButton parc;
    @FXML
    private JFXButton restaurant;
    @FXML
    private JFXButton cinema;
    
    @FXML
    private JFXButton retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnCirque(ActionEvent event) throws IOException {
        Parent signin_page_parent = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/cirque.fxml"));
        Scene signin_page_scene = new Scene (signin_page_parent);
        Stage app_stage =(Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(signin_page_scene);
        app_stage.show();
       
    }

    @FXML
    private void btnParc(ActionEvent event) throws IOException {
        Parent signin_page_parent = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/parc.fxml"));
        Scene signin_page_scene = new Scene (signin_page_parent);
        Stage app_stage =(Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(signin_page_scene);
        app_stage.show();
    }

    @FXML
    private void btnRestaurant(ActionEvent event) throws IOException {
        Parent signin_page_parent = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/restaurant.fxml"));
        Scene signin_page_scene = new Scene (signin_page_parent);
        Stage app_stage =(Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(signin_page_scene);
        app_stage.show();
    }

    @FXML
    private void btnCinema(ActionEvent event) throws IOException {
        Parent signin_page_parent = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/cinema.fxml"));
        Scene signin_page_scene = new Scene (signin_page_parent);
        Stage app_stage =(Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(signin_page_scene);
        app_stage.show();
    }
     @FXML
    void returnBtn(ActionEvent event) throws IOException {
         Parent signin_page_parent = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/FXMLDocument.fxml"));
        Scene signin_page_scene = new Scene (signin_page_parent);
        Stage app_stage =(Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(signin_page_scene);
        app_stage.show();
    }
}
