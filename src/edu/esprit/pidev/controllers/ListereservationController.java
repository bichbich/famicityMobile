/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import edu.esprit.pidev.models.Reservation;
import edu.esprit.pidev.models.Service;
import edu.esprit.pidev.models.User;
import edu.esprit.pidev.services.impl.ReservationService;
import edu.esprit.pidev.services.impl.ServiceService;
import edu.esprit.pidev.services.impl.UserService;
import edu.esprit.pidev.services.interfaces.IServiceService;
import edu.esprit.pidev.services.interfaces.IUserService;
import edu.esprit.pidev.services.interfaces.IreservationService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hanen
 */
public class ListereservationController implements Initializable {

    @FXML
    private JFXHamburger hamburger;
//    @FXML
//    public TableView<Service> tableservice;

    @FXML
    private JFXDrawer drawer;
        

//    @FXML
//    private TableColumn<?, ?> lib;
//
//    @FXML
//    private TableColumn<?, ?> prix;
//
//    @FXML
//    private TableColumn<?, ?> description;
//    @FXML
//    private Button cp;
//
//    @FXML
//    private Button fd;
    IreservationService reservation = new ReservationService();
        @FXML
    private Button supp;

//    @FXML
//    private TableColumn<?, ?> image;
//    @FXML
//    private TableColumn<?, ?> reserver;
       @FXML
    private JFXListView  listview;
    ReservationService RService = new ReservationService();
   
    User u1 = new User();
 public static List<Reservation> service;

    ObservableList<Integer> selectedIndexes = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          try {
            VBox box = new VBox();
            Parent r = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/SidePanel.fxml"));

            box.getChildren().add(r);
            drawer.setSidePane(box);

            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
            transition.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                transition.setRate(transition.getRate() * -1);
                transition.play();

                if (drawer.isShown()) {
                    drawer.close();
                } else {
                    drawer.open();
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
       IUserService us = new UserService();
            u1 = us.findById(User.getConnectedUser());
       List<Reservation> listS =RService.findById(u1.getId());
       // System.err.println(RService.findById(u1.getId()));
       ObservableList<Reservation> Listres = FXCollections.observableArrayList(listS);
         System.out.println(listS);
         listview.setExpanded(true);
         listview.depthProperty().set(1);
                for (Reservation r : listS) {
                   
             HBox H=new HBox(2);
             Label lbl = new Label();
               JFXButton buttonreser = new JFXButton();
//            
//            try {
//                lbl.setGraphic(new ImageView(new Image(new FileInputStream(gr.getImageService()))));
//            } catch (FileNotFoundException ex) {
                //Logger.getLogger(MesInvitationsController.class.getName()).log(Level.SEVERE, null, ex);
//            }
             
             H.getChildren().addAll(new Label("Nom Service:  " + r.getId_service().getLibelle()+ "\n nombre de places reservés: " + r.getNb_placesreserve() + "\n prix restant: " + r.getPrix_r() + "\n prix:  " + r.getDate_resrvation()));

             listview.getItems().add(H);
             
             
             
             
             
         

        // TODO
    }    
    
}
    

    @FXML
    private void supprime(ActionEvent event) throws IOException {
        Reservation select= new Reservation();
        IreservationService serv = new ReservationService();
        int index = listview.getSelectionModel().getSelectedIndex();
        select = (Reservation) serv.getAll().get(index);
        //List.getItems().remove(List.getSelectionModel().getSelectedItem());
        serv.delete(select.getId_reservation());
       listview.getItems().remove(listview.getSelectionModel().getSelectedItem());
        //serv.getAll();
        listview.refresh(); 
         // Parent root = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/listereservation.fxml"));
       
//       Scene register_scene1 = new Scene(root);
//       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//       stage.hide();
//       stage.setScene(register_scene1);
//       stage.show();
//       stage.setResizable(false);
//       stage.centerOnScreen();
        
       // System.out.println(select);
    }
}
