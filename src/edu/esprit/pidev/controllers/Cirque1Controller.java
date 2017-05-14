/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.controllers;

import edu.esprit.pidev.controllers.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import edu.esprit.pidev.models.Commentaire;
import edu.esprit.pidev.models.Reclamation;
import edu.esprit.pidev.services.impl.CommentaireService;
import edu.esprit.pidev.services.impl.ReclamationService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DIARRA
 */
public class Cirque1Controller implements Initializable {

    @FXML
    private JFXTextField txtTitre;
    @FXML
    private JFXTextArea txtContenu;
    @FXML
    private JFXButton send;
    @FXML
    private JFXButton b1;
    @FXML
    private JFXButton b2;
    @FXML
    private JFXButton famille;
    @FXML
    private JFXButton b11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void envoyer(ActionEvent event) {
       Commentaire commentaire = new Commentaire();
         if (commentaire == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
           // alert.initOwner(tableV.getScene().getWindow());
            alert.setTitle("Aucune Sélection");
            alert.setHeaderText("Aucune Réclamation");
            alert.setContentText("Veuillez Sélectionner une réclamation");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
          //  alert.initOwner(tableV.getScene().getWindow());
                    alert.setContentText("Veuillez Confirmer l'ajout");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
 String titre=txtTitre.getText();
        String contenu=txtContenu.getText();
 
        
       CommentaireService com=new CommentaireService();
       Commentaire a=new Commentaire(titre,contenu);
       
       com.add(a);
       
       alert.setTitle("Confirmation");
            alert.setHeaderText("Ajout commentaire");
    
       
                 

              }
           });
    }
       
    }

    @FXML
    private void retourner(ActionEvent event) throws IOException {
         Parent signin_page_parent = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/SortirFamille.fxml"));
        Scene signin_page_scene = new Scene (signin_page_parent);
        Stage app_stage =(Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(signin_page_scene);
        app_stage.show();
    }

    
    
}
