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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


/**
 * FXML Controller class
 *
 * @author wister
 */
public class ProfileController implements Initializable {

    @FXML
    private AnchorPane AnchorPane;

    @FXML
    private Label labelnom;
    @FXML
    private Label labelEmail;
    @FXML
    private ImageView PhotoUser;
    @FXML
    private JFXButton BtnModifMDP;
    @FXML
    private ImageView home;
    VBox nvboxxxxx;
        @FXML
    private JFXButton approuver;
           @FXML
    private JFXButton ignorer;
   @FXML
    private JFXButton affiche;
   
    @FXML
    private JFXListView  List;
    @FXML
    private ImageView logoFamicity;
    @FXML
    private ImageView Sedéconnecter;
    @FXML
    private AnchorPane gauche;
    @FXML
    private JFXButton BtnModifC;
    @FXML
    private JFXButton BtnModifMDP1;

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
    void Modifpass(ActionEvent event) throws IOException {
        Parent register_parent1 = FXMLLoader.load(getClass().getResource("/pidev2/gui/confirmationMotdepasse.fxml"));

        Scene register_scene1 = new Scene(register_parent1);

        Stage membresStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        membresStage1.hide();
        membresStage1.setScene(register_scene1);
        membresStage1.show();
    }

    @FXML
    void Sedéconnecter(MouseEvent event) throws IOException {
        Parent register_parent1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/login.fxml"));

        Scene register_scene1 = new Scene(register_parent1);

        Stage membresStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        membresStage1.hide();
        membresStage1.setScene(register_scene1);
        membresStage1.show();

    }

    @FXML
    void Modifier(ActionEvent event) throws IOException {
        Parent register_parent1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/ModifierUser.fxml"));

        Scene register_scene1 = new Scene(register_parent1);

        Stage membresStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        membresStage1.hide();
        membresStage1.setScene(register_scene1);
        membresStage1.show();

    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
           try {
            
               approuver.setGraphic(new ImageView(new Image(new FileInputStream("C:\\Users\\wister\\Desktop\\pidev2\\src\\pidev2\\images\\téléchargement.jpg"))));
        } catch (FileNotFoundException ex) {
            
        }
                try {
                    ignorer.setMaxHeight(5);
                    ignorer.setMaxWidth(5);
            ignorer.setGraphic(new ImageView(new Image(new FileInputStream("C:\\Users\\wister\\Desktop\\pidev2\\src\\pidev2\\images\\téléchargement (1).jpg"))));
        } catch (FileNotFoundException ex) {
            
        }
  File file = null;

        ImageView imagev1 = null;

        List.depthProperty().set(1);

        }



    @FXML
    private void listerreservation(ActionEvent event) throws IOException {
      Parent register_parent1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/listereservation.fxml"));
       
       Scene register_scene1 = new Scene(register_parent1);
       
      Stage stage = (Stage) affiche.getScene().getWindow() ;
      stage.hide();
      stage.setScene(register_scene1);
      stage.show();
    }

    }

