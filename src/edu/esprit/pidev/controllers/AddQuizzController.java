/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import edu.esprit.pidev.models.Quizz;
import edu.esprit.pidev.services.impl.QuizzService;
import edu.esprit.pidev.services.interfaces.IService;
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
 * @author asmouna
 */
public class AddQuizzController implements Initializable {

  @FXML
    private JFXTextField rep21;

    @FXML
    private JFXButton add;

    @FXML
    private JFXTextField rep11;

    @FXML
    private JFXTextField rep31;

    @FXML
    private JFXTextField ques;

    @FXML
    private JFXTextField numquizz;

    @FXML
    private JFXTextField rep;

    Quizz quizz1 = new Quizz();
    IService quizzService = new QuizzService();

    @FXML
    void makeadd(ActionEvent event) {
        
//        String question = ques.getText();
//        String numQuizz = numquizz.getText();
//        String reponse1 = rep1.getText();
//        String reponse2 = rep2.getText();
//        String reponse3 = rep3.getText();
//        int reponse = Integer.parseInt(rep.getText());
       
        quizz1.setQuestion(ques.getText());
        quizz1.setQuizz(numquizz.getText());
        quizz1.setReponse1(rep11.getText());
        quizz1.setReponse2(rep21.getText());
        quizz1.setReponse3(rep31.getText());
        quizz1.setReponse(Integer.parseInt(rep.getText()));
       
        quizzService.ajouter(quizz1);
        System.out.println("Valide");

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
            @FXML
    void makeHome(ActionEvent event) throws IOException {
Parent p1 = FXMLLoader.load(getClass().getResource("/pidev2/gui/Menu.fxml"));
          Scene test1 = new Scene(p1);
          Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
          App1.setScene(test1);
          App1.show();
    }
    
}
