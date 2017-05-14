/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import java.awt.MenuBar;
import java.net.URL;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
public class javaFXApplication extends Application {
@FXML
public void start(Stage stage) throws Exception {
        
        
      Parent root = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/login.fxml"));

        
       stage.setResizable(false);
       stage.centerOnScreen();
        
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
            stage.show();
            
            

        
    }
   


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
}


