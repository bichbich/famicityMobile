/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import edu.esprit.pidev.models.Famille;
import edu.esprit.pidev.models.User;
import edu.esprit.pidev.models.notification;
import edu.esprit.pidev.services.impl.FamilleService;
import edu.esprit.pidev.services.impl.NotificationService;
import edu.esprit.pidev.services.impl.UserService;
import edu.esprit.pidev.services.interfaces.IFamilleService;
import edu.esprit.pidev.services.interfaces.IUserService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

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
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class FamillesController implements Initializable {

    IFamilleService fa = new FamilleService();
 @FXML
    private HBox hbox;
    @FXML
    private JFXButton ajouFamille;

    @FXML
    private JFXListView List;
     @FXML
    private Pane pane;
    @FXML
    private ScrollPane container;
    
    @FXML
    private JFXHamburger humberger;

    @FXML
    private JFXDrawer drawer;
    
    @FXML
    private VBox vb;

    FamilleService Service = new FamilleService();
    Famille famille = new Famille();
    List<Famille> listfamille = Service.getAll();
    private Object grid;
    @FXML
    private JFXButton creer;
    @FXML
    private JFXButton Details;
    @FXML
    void redirectionCréer(ActionEvent event) throws IOException {
        Parent register_parent1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/NewFamily.fxml"));

        Scene register_scene1 = new Scene(register_parent1);

        Stage membresStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        membresStage1.hide();
        membresStage1.setScene(register_scene1);
        membresStage1.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            VBox box = new VBox();
            Parent r = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/SidePanel.fxml"));

            box.getChildren().add(r);
            drawer.setSidePane(box);

            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(humberger);
            transition.setRate(-1);
            humberger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                transition.setRate(transition.getRate() * -1);
                transition.play();

                if (drawer.isShown()) {
                    drawer.close();
                } else {
                    drawer.open();
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(FamillesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            ajouFamille.setGraphic(new ImageView(new Image(new FileInputStream("C:\\wamp\\www\\famicity5\\web\\photo_membre\\addFa.png"))));
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(MesInvitationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
          try {
           Details.setGraphic(new ImageView(new Image(new FileInputStream("C:\\wamp\\www\\famicity5\\web\\photo_membre\\details2.jpg"))));
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(MesInvitationsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        File file = null;

        ImageView imagev1 = null;
        // TODO   
        IUserService u3 = new UserService();
        IFamilleService u2 = new FamilleService();
        List<Famille> listS = u2.getAll();
        List<User> listSU = u3.getAll();

        ObservableList<User> ListUser = FXCollections.observableArrayList(listSU);
        ObservableList<Famille> ListFamille = FXCollections.observableArrayList(listS);
        List.setExpanded(true);
        List.depthProperty().set(1);
        for (Famille p : listS) {
            HBox H = new HBox();
            Label lbl = new Label();
            JFXButton button = new JFXButton();
            button.setText("Rejoindre");
            button.setStyle("-fx-background-color:#2196F3");
            Image image = null;
            file = new File("C:\\wamp\\www\\famicity5\\web\\photo_membre" + p.getNomdeFamilleid().getImage());
            image = new Image(file.toURI().toString());
            //System.out.println(f.getNomdeFamilleid().getImage());

            imagev1 = new ImageView(image);
            imagev1.setFitHeight(80);
            imagev1.setFitWidth(90);

            H.setPrefHeight(100);
            H.setPrefWidth(1000);
            H.getChildren().addAll(imagev1, new Label("" + p.getNomdeFamilleid().getNom()));
            List.getItems().add(H);
        }

    }

    @FXML
    void AjoutFamilleAction(ActionEvent event) {
        Famille selecteds = new Famille();
        IFamilleService serv = new FamilleService();
        int index = List.getSelectionModel().getSelectedIndex();
  selecteds = (Famille) serv.getAll().get(index);
        NosFamillesController nfc = new NosFamillesController();
        sendNotification(selecteds.getNomdeFamilleid().getId());
        System.out.println(selecteds.getNomdeFamilleid().getId());
         

      

    }

    void sendNotification(int id) {
        NotificationService ns = new NotificationService();
        Date d = new Date();
        java.sql.Date date = new java.sql.Date(d.getTime());
        String nomemeteur = null;
        notification n = new notification(id, "Je veut rejoindre votre famille", "Je veut rejoindre votre famille", new UserService().findById(User.getConnectedUser()).getId(), new UserService().findById(User.getConnectedUser()).getNom());
       //notification n=new notification("Je veux rejoindre votre Famille !", id,new UserService().findById(User.getConnectedUser()).getNom() );
       ns.add(n);
    }
    
     @FXML
    void DetailsAction(ActionEvent event) throws IOException {
     ((Node)(event.getSource())).getScene().getWindow().hide();
        
        try {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/esprit/pidev/gui/Details.fxml"));
        Parent root = (Parent) fxmlLoader.load();
       DetailsController rcontroller = fxmlLoader.<DetailsController>getController();
        Famille s1 = new Famille();
 int index = List.getSelectionModel().getSelectedIndex();
        Famille selecteds = Service.getAll().get(index);
        // List<Famille> lst = Service.getAlln(selecteds.getNomdeFamilleid().getPosition());

       System.out.println(selecteds);
       s1.setMembres(selecteds.getMembres());
       s1.setNomdeFamilleid(selecteds.getNomdeFamilleid());
          
        rcontroller.setFamille(s1);
        
       stage.setScene(new Scene(root));
              //  stage.setTitle(" ");
              
                stage.initModality(Modality.WINDOW_MODAL);
                stage.showAndWait();
                } catch (Exception e) {e.printStackTrace();
        }
        

           

             

    }

}
