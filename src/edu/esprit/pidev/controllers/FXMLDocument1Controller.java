/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import edu.esprit.pidev.models.Commentaire;
import edu.esprit.pidev.models.Reclamation;
import edu.esprit.pidev.services.impl.ReclamationService;
import edu.esprit.pidev.services.interfaces.IReclamationService;
import edu.esprit.pidev.technique.statiqaccount;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author DIARRA
 */
public class FXMLDocument1Controller implements Initializable {
    
    private ObservableList<Reclamation> eventsData = FXCollections.observableArrayList();

    @FXML
    private JFXButton b1;
    @FXML
    private JFXButton b2;
    @FXML
    private JFXButton famille;
    @FXML
    private JFXButton b11;
    @FXML
    private JFXButton contact;
    @FXML
    private JFXTextField txtNom;
    @FXML
    private JFXTextField txtSujet;
    @FXML
    private JFXTextField txtLevel;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextArea txtDescription;
    @FXML
    private JFXButton envoyer;
   @FXML
    private ChoiceBox<String> txtNature;
     @FXML
    private JFXButton deconnexion;
     private Reclamation a;
    
    Stage mainStage;
    
    public Stage getMainStage() {
        return mainStage;
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle rb) {
        
        ObservableList<String> cuchoice = FXCollections.observableArrayList("plainte","Déception","Protestation","Logiciel","Service");
 txtNature.getItems().clear();
    txtNature.setItems(cuchoice);
        
    } 
    
     @FXML
    void logout(ActionEvent event) throws IOException {
     Parent signin_page_parent = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/login.fxml"));
        Scene signin_page_scene = new Scene (signin_page_parent);
        Stage app_stage =(Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(signin_page_scene);
        app_stage.show();
    }

    @FXML
    private void sortir(ActionEvent event) throws IOException {
        
        
        Parent signin_page_parent = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/SortirFamille.fxml"));
        Scene signin_page_scene = new Scene (signin_page_parent);
        Stage app_stage =(Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(signin_page_scene);
        app_stage.show();
    }
 
    @FXML
    private void contacter(ActionEvent event) throws IOException {
        Parent signin_page_parent = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/MapApi.fxml"));
        Scene signin_page_scene = new Scene (signin_page_parent);
        Stage app_stage =(Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(signin_page_scene);
        app_stage.show();
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
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
                    alert.setContentText("Ajouté avec succès");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
      
       
         
        String nom=txtNom.getText();
        String sujet=txtSujet.getText();
        String etat="En cours";
        String nature=txtNature.getValue();
        

        
        int level=Integer.parseInt(txtLevel.getText());
        
        String email=txtEmail.getText();
        Date date = null ;
        
        String description=txtDescription.getText();
        
       ReclamationService recl=new ReclamationService();
       Reclamation a=new Reclamation(nom, sujet, etat, nature, level, email, date,description);
       
       recl.add(a);
        alert.setTitle("Confirmation");
            alert.setHeaderText("Ajout commentaire");
    
       
                 

              }
           });
    }
       
//       
//        Parent signin_page_parent = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/AfficherReclamation.fxml"));
//        Scene signin_page_scene = new Scene (signin_page_parent);
//        Stage app_stage =(Stage)((Node) event.getSource()).getScene().getWindow();
//        app_stage.setScene(signin_page_scene);
//        app_stage.show();
            
        
    }
    
  

//    @FXML
//    private void choosetype(ActionEvent event) {
//        List<Reclamation> liste = new ArrayList<>();
//        ReclamationService re = new ReclamationService();
//        if(txtNature.getSelectionModel().getSelectedItem().toLowerCase().equals("plainte".toLowerCase()))
//        liste = re.getAlluserreclamations(statiqaccount.user.getId()).stream().filter(x->x.getNature().toLowerCase().equals("plainte".toLowerCase())).collect(Collectors.toList());
//        else if(txtNature.getSelectionModel().getSelectedItem().toLowerCase().equals("Déception".toLowerCase()))
//        liste = re.getAlluserreclamations(statiqaccount.user.getId()).stream().filter(x->x.getNature().toLowerCase().equals("Déception".toLowerCase())).collect(Collectors.toList());
//        else if(txtNature.getSelectionModel().getSelectedItem().toLowerCase().equals("Protestation".toLowerCase()))
//        liste = re.getAlluserreclamations(statiqaccount.user.getId()).stream().filter(x->x.getNature().toLowerCase().equals("Protestation".toLowerCase())).collect(Collectors.toList());
//        else if(txtNature.getSelectionModel().getSelectedItem().toLowerCase().equals("Logiciel".toLowerCase()))
//        liste = re.getAlluserreclamations(statiqaccount.user.getId()).stream().filter(x->x.getNature().toLowerCase().equals("Logiciel".toLowerCase())).collect(Collectors.toList());
//        else if(txtNature.getSelectionModel().getSelectedItem().toLowerCase().equals("Service".toLowerCase()))
//        liste = re.getAlluserreclamations(statiqaccount.user.getId());
//        else{
//        liste = re.getAlluserreclamations(statiqaccount.user.getId());
//        }
//        eventsData.clear();
//        eventsData.addAll(liste);
//        tableRec.setItems(eventsData);
//    }
    
}
