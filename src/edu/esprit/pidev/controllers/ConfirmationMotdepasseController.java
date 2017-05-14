/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.controllers;


import edu.esprit.pidev.services.impl.UserService;
import edu.esprit.pidev.technique.Mailer;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class ConfirmationMotdepasseController implements Initializable {

    /**
     * Initializes the controller class.
     */
   @FXML
    private TextField code;

    @FXML
    private TextField email;

    @FXML
    private Button confirmer;

    @FXML
    private Button renvoyerCode;
      @FXML
    private Label erreur;
    
    private UserService userservice = new UserService();
    
   
    @FXML
    void confirmer(ActionEvent event) throws IOException {
 if (code.getText().equals(Mailer.getCode().toString()))
 { 
        Parent register_parent1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/ModifierUser.fxml"));
       
       Scene register_scene1 = new Scene(register_parent1);
       
       
      Stage membresStage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
      membresStage1.hide();
      membresStage1.setScene(register_scene1);
      membresStage1.show();}
 else 
            System.out.println("Erreur");
            erreur.setText("Erreur");
    }

    

  
    


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    

    @FXML
    private void SendCode(ActionEvent event) {
        
            Random random = new Random();
            Mailer.setCode(random.nextInt(9000)+1000);
            Mailer.send("rajhi.hiba.sticc@gmail.com","bonbonchocolat",email.getText(),"Rénitialisation de mot de passe","Code de vérification :"+Mailer.getCode());
            
    }
    
}
