/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import edu.esprit.pidev.services.interfaces.IService;
import edu.esprit.pidev.services.interfaces.IServiceService;
import edu.esprit.pidev.services.interfaces.IUserService;
import edu.esprit.pidev.models.Planing;
import edu.esprit.pidev.models.Service;
import edu.esprit.pidev.models.User;
import edu.esprit.pidev.services.impl.PlaningImp;
import edu.esprit.pidev.services.impl.ServiceService;
import edu.esprit.pidev.services.impl.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author wiem
 */
public class ModifierPlanController implements Initializable {

    
     @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXDatePicker datemodif;

    @FXML
    private JFXComboBox<String> servmodif;

    @FXML
    private JFXComboBox<String> famillemodif;

    @FXML
    private JFXTextField nommodif;

    @FXML
    private JFXButton buttonmodif;

  
    Planing p = new Planing() ;
    Planing p2 = new Planing() ;
      private final ObservableList<Service> Listservices = FXCollections.observableArrayList();
    private final ObservableList<User> ListUser = FXCollections.observableArrayList();
    public static List<Service> service;
    
    public void setPlanings( Planing p11)
     {
         p=p11;
     }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
            Logger.getLogger(ModifierPlanController.class.getName()).log(Level.SEVERE, null, ex);
        }  
        datemodif.setPromptText("date evenement");
        nommodif.setPromptText("nom de l'enemement");
        famillemodif.setPromptText("inviter famille");
        servmodif.setPromptText("choisir service");
        UserService u1= new UserService();
        List<User> list =u1.getAll();
        for(User u:list) {
            ListUser.addAll(u);  
        }
        for(User up:list) {
        famillemodif.getItems().addAll(up.getNom());           
        }
        IServiceService u2= new ServiceService();
        List<Service> listS =u2.getAll();
        for(Service u3:listS) {
            Listservices.addAll(u3);
        }
        for(Service u4:Listservices) {
        servmodif.getItems().addAll(u4.getLibelle());
        }
         
    }    
        @FXML
    void modifAction(ActionEvent event) throws IOException {
        System.out.println(p.getId_planing());
        IUserService userservice =new UserService();
        User x=userservice.findById((User.getConnectedUser()));
        //System.out.println(User.getConnectedUser());
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/esprit/piplaningfamicity/gui/MesInvitations.fxml"));
     
     
        IService PlaningSer = new PlaningImp();        
        Service serv = new Service();        
        for(Service u3:Listservices) {
        if (servmodif.getSelectionModel().getSelectedItem() == u3.getLibelle())
        {
            p.setId_service(u3);
        }}
        p.setId_planing(p.getId_planing());
        p.setEtat_event(p.getEtat_event());
        p.setCreateur(x.getUsername());
        p.setNom_evenement(nommodif.getText());
        p.setHoraire_planing(Date.valueOf(datemodif.getValue()));
        p.setEnd_planing(Date.valueOf(datemodif.getValue()));
        
         for(User um:ListUser) {
        if (famillemodif.getSelectionModel().getSelectedItem() == um.getNom())
        {
            p.setNomdeFamilleid(um);
        }}
         
        PlaningSer.update(p);
        System.out.println("Evenement crée avec succée" + p); 
          Parent root = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/MesInvitations.fxml"));       
       Scene register_scene1 = new Scene(root);
       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       stage.hide();
       stage.setScene(register_scene1);
       stage.show();
       stage.setResizable(false);
       stage.centerOnScreen();
        //System.out.println(p);
    }

}
