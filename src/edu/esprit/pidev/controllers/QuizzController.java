/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.controllers;

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
public class QuizzController implements Initializable {

    @FXML
    void makeQuizz1(ActionEvent event) throws IOException {
          Parent p1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/niveau1.fxml"));
          Scene test1 = new Scene(p1);
          Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
          App1.setScene(test1);
          App1.show();
    }

    @FXML
    void makeQuizz2(ActionEvent event) throws IOException {
          Parent p1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/niveau2.fxml"));
          Scene test1 = new Scene(p1);
          Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
          App1.setScene(test1);
          App1.show();
    }

    @FXML
    void makeQuizz3(ActionEvent event) throws IOException {
          Parent p1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/niveau3.fxml"));
          Scene test1 = new Scene(p1);
          Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
          App1.setScene(test1);
          App1.show();
    }

    @FXML
    void makeQuizz4(ActionEvent event) throws IOException {
          Parent p1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/niveau4.fxml"));
          Scene test1 = new Scene(p1);
          Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
          App1.setScene(test1);
          App1.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
