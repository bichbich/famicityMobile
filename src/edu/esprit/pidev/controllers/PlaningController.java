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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PlaningController implements Initializable {

  @FXML
    private AnchorPane AnchorPane;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private ImageView logoFamicity;

    @FXML
    private ImageView Accueil;

    @FXML
    private ImageView Sedéconnecter;

   @FXML
    private JFXDatePicker DateEv;

    @FXML
    private JFXButton ajout;

    @FXML
    private JFXTextField nomEve;

  @FXML
    private JFXComboBox<String> nomService;
  
   
    
       @FXML
    private JFXComboBox<String> nomFamille;
    
    private final ObservableList<Service> Listservices = FXCollections.observableArrayList();
    private final ObservableList<User> ListUser = FXCollections.observableArrayList();
    public static List<Service> service;
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
            Logger.getLogger(PlaningController.class.getName()).log(Level.SEVERE, null, ex);
        }
        DateEv.setPromptText("date evenement");
        nomEve.setPromptText("nom de l'enemement");
        nomFamille.setPromptText("inviter famille");
        nomService.setPromptText("choisir service");
        UserService u1= new UserService();
        List<User> list =u1.getAll();
        for(User u:list) {
            ListUser.addAll(u);  
        }
        for(User up:list) {
        nomFamille.getItems().addAll(up.getNom());           
        }
        IServiceService u2= new ServiceService();
        List<Service> listS =u2.getAll();
        for(Service u3:listS) {
            Listservices.addAll(u3);
        }
        for(Service u4:Listservices) {
        nomService.getItems().addAll(u4.getLibelle());
        }
        
        
    }
    @FXML
     void AjouterAction(ActionEvent event) throws IOException {
        IUserService userservice =new UserService();
        User x=userservice.findById((User.getConnectedUser()));
        System.out.println(User.getConnectedUser());
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/esprit/piplaningfamicity/gui/MesInvitations.fxml"));
       
     
        IService PlaningSer = new PlaningImp();
        Planing evenement = new Planing();
        Service serv = new Service();        
        for(Service u3:Listservices) {
        if (nomService.getSelectionModel().getSelectedItem() == u3.getLibelle())
        {
            evenement.setId_service(u3);
        }}
        evenement.setEtat_event(null);
        evenement.setCreateur(null);
        evenement.setNom_evenement(nomEve.getText());
        evenement.setHoraire_planing(Date.valueOf(DateEv.getValue()));
        evenement.setEnd_planing(Date.valueOf(DateEv.getValue()));
        
         for(User um:ListUser) {
        if (nomFamille.getSelectionModel().getSelectedItem() == um.getNom())
        {
            evenement.setNomdeFamilleid(um);
        }}
         
        PlaningSer.add(evenement);
        System.out.println("Evenement crée avec succée");  
        
        Parent root = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/MesInvitations.fxml"));
       
       Scene register_scene1 = new Scene(root);
       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       stage.hide();
       stage.setScene(register_scene1);
       stage.show();
       stage.setResizable(false);
       stage.centerOnScreen();
        }
    }