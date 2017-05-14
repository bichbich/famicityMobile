/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.controllers;

import edu.esprit.pidev.models.Reservation;
import edu.esprit.pidev.models.Service;
import edu.esprit.pidev.services.impl.ReservationService;
import edu.esprit.pidev.services.impl.ServiceService;
import edu.esprit.pidev.services.interfaces.IServiceService;
import edu.esprit.pidev.services.interfaces.IreservationService;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventDispatcher;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author hanen
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
        private Button Hellobutton;
    @FXML
        private Label ourlabel;
     @FXML
    MenuBar menu = new MenuBar();
     @FXML
        private Menu menu1;
     
     @FXML
        private Menu menu2;
     @FXML
        private Menu menu3;
     @FXML
        private Menu menu4;
     @FXML
        private Image imagef;
      @FXML
    private AnchorPane affiche;
      @FXML
    private AnchorPane affiche2;
      
     MenuItem menui1 = new MenuItem("Parc");
    MenuItem menui2 = new MenuItem("restauration");
    MenuItem menui3 = new MenuItem("cirque");

    MenuItem menui4 = new MenuItem("cinema");
     @FXML
   public ListView< Service> list1;
     IServiceService service = new ServiceService();

    
   public ObservableList<Service> lst = FXCollections.observableArrayList(service.getAll());

    Stage stage; 
    @FXML Parent root;
    @FXML
              IreservationService reservation = new ReservationService(); 
    @FXML
    private BorderPane mytext;
    
    @FXML
    private void handleButtonAction(ActionEvent e) throws IOException {
        
        
      Parent register_parent1 = FXMLLoader.load(getClass().getResource("connexion.fxml"));
       
       Scene register_scene1 = new Scene(register_parent1);
       
      Stage stage = (Stage) menu.getScene().getWindow() ;
      stage.hide();
      stage.setScene(register_scene1);
      stage.show();

//        lst.addAll(service.getAll());
////        TableColumn nomProjet = new TableColumn("Intitulé");
////        nomProjet.setCellValueFactory(new PropertyValueFactory("nomProjet"));
//        ObservableList listNom = FXCollections.observableArrayList();
//        for (int i = 0; i < lst.size(); i++) {
//            listNom.add(lst.get(i));
//        }  
          
        }

     
    
     @FXML
    void sortir(ActionEvent event) {
        System.exit(0);
    }
    
 
  

    
    @FXML
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
    
}
}
