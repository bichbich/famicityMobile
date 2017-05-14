/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.controllers;

import com.jfoenix.controls.JFXButton;
import edu.esprit.pidev.models.Reclamation;
import edu.esprit.pidev.services.impl.ReclamationService;
import edu.esprit.pidev.technique.Mail;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
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
public class EmailRController implements Initializable {

    @FXML
    private TextField emailtf;
    @FXML
    private TextField sujettf;
    @FXML
    private TextArea messagetf;
    @FXML
    private Button btn_Mail;
    @FXML
    TextArea messager;
    @FXML
    private Label affilabel;
    @FXML
    private JFXButton retour;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL location, ResourceBundle rb) {
         /*List<Reclamation> reclam = new ArrayList<Reclamation>();
        ReclamationService reclamationService = new ReclamationService();
        System.out.println(reclamationService.getAlltout().size());
        reclam = reclamationService.getAlltout();
        tableV.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> afficheDeta(newValue));
        eventsData.clear();// tnadhef el liste
        eventsData.addAll(reclam); //t3abi el liste
        tableRec.setItems(eventsData); //t3abi el tableviw

        Image imageOk = new Image(getClass().getResourceAsStream("/Ressources/search-icon-hi.png"));
        ImageView imageView = new ImageView(imageOk);
        imageView.setFitHeight(15);
        imageView.setFitWidth(15);
        rech.setGraphic(imageView);*/
        sujettf.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("[qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç ]")) {
                sujettf.setText(newValue.replaceAll("[^qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNMèéòàùìêîûç]", ""));
            }
        });

        emailtf.addEventFilter(KeyEvent.KEY_TYPED, (Event arg) -> {
            KeyEvent arg0 = (KeyEvent) arg;

            if (!(emailtf.getText().endsWith("@esprit.tn") || (emailtf.getText().endsWith("@gmail.com")) || (emailtf.getText().endsWith("@yahoo.fr")))) {
                emailtf.setStyle("-fx-background-color: red,linear-gradient(to bottom, derive(red,60%) 5%,derive(red,90%) 40%);");
                //     arg0.consume();
                btn_Mail.setDisable(true);
            } else {

                emailtf.setStyle("-fx-background-color: rgb(255, 255, 255, 0.3), linear-gradient(rgb(0, 0, 0, 0.5), rgb(0, 0, 0, 0.😎 50%),rgb(218, 226, 224);");
                btn_Mail.setDisable(false);
            }
        });

//        ObservableList<String> cursors = FXCollections.observableArrayList("Non Archivées", "Archivées", "Tout");
//        selectcb.setItems(cursors);
    }    
                
    @FXML
    private void sendMail(ActionEvent event) {
         
           // System.out.println("maillllll");
            //System.out.println((emailtf.getText()+sujettf.getText()+messagetf.getText()));
            Mail.send(emailtf.getText(), sujettf.getText(), messagetf.getText());
            System.out.println("maillllll2");
            tray.notification.TrayNotification tr = new TrayNotification();
            tr.setTitle("Terminé");
            tr.setMessage("Mail envoyé avec succées");
            tr.setNotificationType(NotificationType.INFORMATION);
            tr.setAnimationType(AnimationType.SLIDE);

            tr.showAndDismiss(Duration.seconds(1));

            //
        
    }
    @FXML
    void returnBtn(ActionEvent event) throws IOException {
Parent signin_page_parent = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/AfficherReclamation.fxml"));
        Scene signin_page_scene = new Scene (signin_page_parent);
        Stage app_stage =(Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(signin_page_scene);
        app_stage.show();
    }
    
//                private void afficheDeta(Reclamation rec) {
//
//        messager.setText("N° réclamation : " + Integer.toString(rec.getId()) + "\nLe client:" + rec.getNom() + " " + rec.getEtat() + "\nLe type de la réclamation : " + rec.getNature() + "\nLa date de la réclamtion : " + String.valueOf(rec.getDate()) + "\nSujet : " + rec.getSujet() + "\nMessage : " + rec.getDescription());
//        emailtf.setText(rec.getEmail());
//        messager.setVisible(true);
//        affilabel.setVisible(true);
//        messager.setEditable(false);
//
//    }
       
}
