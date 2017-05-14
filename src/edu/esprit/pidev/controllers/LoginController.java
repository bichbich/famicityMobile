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
import edu.esprit.pidev.services.interfaces.IUserService;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;


/**
 * FXML Controller class
 *
 * @author wister
 */
public class LoginController  implements Initializable {
    
     
  public static final String ACCOUNT_SID = "ACe848124277c5272fa7731689c59b32b6";
    public static final String AUTH_TOKEN = "c12ec03c9857ec634c0d59606f4cb05c";


    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton login;

    @FXML
    private JFXButton register;

    @FXML
    private ImageView userlogin;
  @FXML
    private Label passoublie;
  @FXML
    private Button btnoubli;
  

    @FXML
    void makelogin(ActionEvent event) throws IOException {
                    

        Parent register_parent1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/register.fxml"));

        Scene register_scene1 = new Scene(register_parent1);

        Stage membresStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        membresStage1.hide();
        membresStage1.setScene(register_scene1);
        membresStage1.show();

    }
        @FXML
    void oublierAction(ActionEvent event) throws IOException {
         Parent register_parent1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/confirmationMotdepasse.fxml"));

        Scene register_scene1 = new Scene(register_parent1);

        Stage membresStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        membresStage1.hide();
        membresStage1.setScene(register_scene1);
        membresStage1.show();

    }

    @FXML

    void Bienvenue(ActionEvent event) throws IOException, URISyntaxException {

//               SmsSender ss= new SmsSender();
//                ss.send("Vous êtes maintenant inscris dans l'application Famicity, merci pour votre choix");

        IUserService userservice = new UserService();
        if (username.getText().isEmpty() || password.getText().isEmpty()) {
            Notifications NotificationBuilder = Notifications.create()
                    .title("error")
                    .text("veuiller remplir tt les champs svp !! ")
                    .graphic(null)
                    .hideAfter(Duration.seconds(8))
                    .position(Pos.TOP_LEFT);
            NotificationBuilder.showError();

        } else {
            System.out.println(username.getText());
            if (userservice.authentication(username.getText(), password.getText()) == true) {
                System.out.println("connected ");
                if (userservice.findById(User.getConnectedUser()).getRoles().equals("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {
                    Parent register_parent1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/menuADMIN.fxml"));

                    Scene register_scene1 = new Scene(register_parent1);

                    Stage membresStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    membresStage1.hide();
                    membresStage1.setScene(register_scene1);
                    membresStage1.show();

                } else if (userservice.authentication(username.getText(), password.getText()) == true) {
                    if (userservice.findById(User.getConnectedUser()).getRoles().equals("a:0:{}")) {
                    Parent register_parent1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/AccueilFrontOffice.fxml"));

                    Scene register_scene1 = new Scene(register_parent1);

                    Stage membresStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    membresStage1.hide();
                    membresStage1.setScene(register_scene1);
                    membresStage1.show();
                    }   

                }
    //                Parent register_parent1 = FXMLLoader.load(getClass().getResource("/pidev2/gui/Profile.fxml"));
  
            } else {
                Notifications NotificationBuilder = Notifications.create()
                        .title("error")
                        .text("login et mot de passe incorrects ")
                        .graphic(null)
                        .hideAfter(Duration.seconds(8))
                        .position(Pos.TOP_LEFT);
                NotificationBuilder.showError();
            }

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
