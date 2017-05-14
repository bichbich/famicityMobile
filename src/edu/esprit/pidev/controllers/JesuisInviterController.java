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
import edu.esprit.pidev.services.interfaces.IService;
import edu.esprit.pidev.services.interfaces.IUserService;
import edu.esprit.pidev.models.Planing;
import edu.esprit.pidev.models.User;
import edu.esprit.pidev.services.impl.PlaningImp;
import edu.esprit.pidev.services.impl.UserService;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
 * @author wiem
 */
public class JesuisInviterController implements Initializable {

 
    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXListView List;

    @FXML
    private JFXButton accept;

    @FXML
    private JFXButton refus;

           IUserService userservice =new UserService();
        User x=userservice.findById((User.getConnectedUser()));
        IService u2= new PlaningImp();   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {       
        // TODO
        try {
                accept.setGraphic(new ImageView(new Image(new FileInputStream("C:\\Users\\wiem\\Downloads\\JavaFXApplication9\\JavaFXApplication7\\JavaFXApplication1\\src\\edu\\esprit\\pidev\\images\\checkbox.png"))));
            } catch (FileNotFoundException ex) {
               // Logger.getLogger(MesInvitationsController.class.getName()).log(Level.SEVERE, null, ex);
            }      
          try {
                refus.setGraphic(new ImageView(new Image(new FileInputStream("C:\\Users\\wiem\\Downloads\\JavaFXApplication9\\JavaFXApplication7\\JavaFXApplication1\\src\\edu\\esprit\\pidev\\images\\close.png"))));
            } catch (FileNotFoundException ex) {
                //Logger.getLogger(MesInvitationsController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            //Logger.getLogger(PlaningController.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        System.out.println(x.getNom());
             
        List<Planing> listS =u2.getAll();
        //        System.out.println("***********");
        listS.stream().forEach(a->System.out.println(a.getNomdeFamilleid().getNom()));
//        System.out.println("***************");
        listS=listS.stream().filter(e->e.getNomdeFamilleid().getNom().equals(x.getNom())).collect(Collectors.toList());
//        System.out.println(listS.size());
         List.setExpanded(true);
         List.depthProperty().set(1);
         for(Planing p:listS){
             HBox H=new HBox(4);
             Label lbl = new Label();
             Label lbl2 = new Label();
               if (p.getEtat_event().equals("en attente")) 
            {
            try {
                lbl2.setGraphic(new ImageView(new Image(new FileInputStream("C:\\Users\\wiem\\Downloads\\JavaFXApplication9\\JavaFXApplication7\\JavaFXApplication1\\src\\edu\\esprit\\pidev\\images\\traiterN.png"))));
            } catch (FileNotFoundException ex) {
                //Logger.getLogger(MesInvitationsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            else  {
                  try {
                lbl2.setGraphic(new ImageView(new Image(new FileInputStream("C:\\Users\\wiem\\Downloads\\JavaFXApplication9\\JavaFXApplication7\\JavaFXApplication1\\src\\edu\\esprit\\pidev\\images\\traiter.png"))));
            } catch (FileNotFoundException ex) {
                //Logger.getLogger(MesInvitationsController.class.getName()).log(Level.SEVERE, null, ex);
            }
               }
         try {
                lbl.setGraphic(new ImageView(new Image(new FileInputStream("C:\\Users\\wiem\\Downloads\\JavaFXApplication9\\JavaFXApplication7\\JavaFXApplication1\\src\\edu\\esprit\\pidev\\images\\eventf.png"))));
            } catch (FileNotFoundException ex) {
                //Logger.getLogger(MesInvitationsController.class.getName()).log(Level.SEVERE, null, ex);
            }
             H.setPrefHeight(100); 
             H.setPrefWidth(1000);
             H.getChildren().addAll(lbl,new Label("Nom de l'évènement: "+p.getNom_evenement()+"\n"+"Lieu de l'évènement: "+p.getId_service().getLibelle()+"\n"+"Date de l'évènemenet:  "+ (p.getHoraire_planing()) +"\n" + "Etat de l'évènemenet:  " + p.getEtat_event()),new Label("                        "),lbl2);
             List.getItems().add(H);        
         }
         
    }    
      @FXML
    void acceptAction(ActionEvent event) throws IOException {
        
        List<Planing> listS =u2.getAll();
        //        System.out.println("***********");
        listS.stream().forEach(a->System.out.println(a.getNomdeFamilleid().getNom()));
//        System.out.println("***************");
        listS=listS.stream().filter(e->e.getNomdeFamilleid().getNom().equals(x.getNom())).collect(Collectors.toList());
        Planing selecteds = new Planing();
        Planing selecteds2 = new Planing();
        IService serv = new PlaningImp();
        int index = List.getSelectionModel().getSelectedIndex();
        selecteds = listS.get(index);
        System.out.println(index);
        int p2 = selecteds.getId_planing();
        selecteds2 = (Planing) serv.findByCategorie(p2);
        //selecteds.setId_planing(selecteds2.getId_planing());  
        //selecteds.setEtat_event("Accepter");
        serv.updateAccepte(selecteds2);
       
       // System.out.println("wiwi" + selecteds2.getEtat_event() + "id:fghjklm" +p2);
       List.refresh(); 
       Parent root = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/jesuisInviter.fxml"));       
       Scene register_scene1 = new Scene(root);
       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       stage.hide();
       stage.setScene(register_scene1);
       stage.show();
       stage.setResizable(false);
       stage.centerOnScreen();

    }
  
    @FXML
    void refusAction(ActionEvent event) throws IOException {
          List<Planing> listS =u2.getAll();
        //        System.out.println("***********");
        listS.stream().forEach(a->System.out.println(a.getNomdeFamilleid().getNom()));
//        System.out.println("***************");
        listS=listS.stream().filter(e->e.getNomdeFamilleid().getNom().equals(x.getNom())).collect(Collectors.toList());
        Planing selecteds = new Planing();
        Planing selecteds2 = new Planing();
        IService serv = new PlaningImp();
        int index = List.getSelectionModel().getSelectedIndex();
        selecteds = listS.get(index);
        System.out.println(index);
        int p2 = selecteds.getId_planing();
        selecteds2 = (Planing) serv.findByCategorie(p2);
        //selecteds.setId_planing(selecteds2.getId_planing());  
        //selecteds.setEtat_event("Accepter");
        serv.updateRefuser(selecteds2);
       
       // System.out.println("wiwi" + selecteds2.getEtat_event() + "id:fghjklm" +p2);
       List.refresh(); 
       Parent root = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/jesuisInviter.fxml"));       
       Scene register_scene1 = new Scene(root);
       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       stage.hide();
       stage.setScene(register_scene1);
       stage.show();
       stage.setResizable(false);
       stage.centerOnScreen();

    }
    
}
