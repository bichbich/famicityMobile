/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.controllers;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfWriter;
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
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import static javafx.scene.paint.Color.color;
import static javafx.scene.paint.Color.color;
import javafx.scene.text.Text;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author wiem
 */

public class MesInvitationsController implements Initializable {

    @FXML
    private JFXListView List;
    @FXML
    private JFXButton button;
    
    @FXML
    private JFXButton buttonAjout;

    @FXML
    private JFXButton buttonModif;
    
        @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;
           
  @FXML
    private JFXButton buttonSupprime;
    
       @FXML
    private JFXButton modifier;
        
        
    IService serv = new PlaningImp();
     IUserService userservice =new UserService();
        User x=userservice.findById((User.getConnectedUser()));
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
       
           try {
                buttonAjout.setGraphic(new ImageView(new Image(new FileInputStream("C:\\Users\\wiem\\Downloads\\JavaFXApplication9\\JavaFXApplication7\\JavaFXApplication1\\src\\edu\\esprit\\pidev\\images\\add.png"))));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MesInvitationsController.class.getName()).log(Level.SEVERE, null, ex);
            }      
          try {
                buttonSupprime.setGraphic(new ImageView(new Image(new FileInputStream("C:\\Users\\wiem\\Downloads\\JavaFXApplication9\\JavaFXApplication7\\JavaFXApplication1\\src\\edu\\esprit\\pidev\\images\\deletefinal.png"))));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MesInvitationsController.class.getName()).log(Level.SEVERE, null, ex);
            }
           try {
                buttonModif.setGraphic(new ImageView(new Image(new FileInputStream("C:\\Users\\wiem\\Downloads\\JavaFXApplication9\\JavaFXApplication7\\JavaFXApplication1\\src\\edu\\esprit\\pidev\\images\\wrench.png"))));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MesInvitationsController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MesInvitationsController.class.getName()).log(Level.SEVERE, null, ex);
        }  
       
       // System.out.println(x.getNom());
        IService u2= new PlaningImp();        
        List<Planing> listS =u2.getAll();
//        System.out.println("***********");
//        listS.stream().forEach(a->System.out.println(a.getNomdeFamilleid().getNom()));
//        System.out.println("***************");
//        listS=listS.stream().filter(e->e.getNomdeFamilleid().getNom().equals(x.getNom())).collect(Collectors.toList());
//        System.out.println(listS.size());
               // System.out.println("***********");
         listS.stream().forEach(a->System.out.println(a.getNomdeFamilleid().getUsername()));
       // System.out.println("***************");
         listS=listS.stream().filter(e->e.getCreateur().equals(x.getUsername())).collect(Collectors.toList());
        //System.out.println(listS.size());
         final ObservableList<Planing> ListEvenements = FXCollections.observableArrayList(listS);
         
         List.setExpanded(true);
         List.depthProperty().set(1);
         for(Planing p:listS){
             HBox H=new HBox(4);
             Label lbl = new Label();
             Label lbl2 = new Label();
               //buttonSuprim.setStyle("onAction=#SupprimeAction");
          
            if (p.getEtat_event().equals("Accepter")) 
            {
            try {
                lbl2.setGraphic(new ImageView(new Image(new FileInputStream("C:\\Users\\wiem\\Downloads\\JavaFXApplication9\\JavaFXApplication7\\JavaFXApplication1\\src\\edu\\esprit\\pidev\\images\\bookmarkA.png"))));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MesInvitationsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            else if (p.getEtat_event().equals("Refuser")) {
                  try {
                lbl2.setGraphic(new ImageView(new Image(new FileInputStream("C:\\Users\\wiem\\Downloads\\JavaFXApplication9\\JavaFXApplication7\\JavaFXApplication1\\src\\edu\\esprit\\pidev\\images\\bookmarkRe.png"))));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MesInvitationsController.class.getName()).log(Level.SEVERE, null, ex);
            }  
             }
            try {
                lbl.setGraphic(new ImageView(new Image(new FileInputStream("C:\\Users\\wiem\\Downloads\\JavaFXApplication9\\JavaFXApplication7\\JavaFXApplication1\\src\\edu\\esprit\\pidev\\images\\eventf.png"))));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MesInvitationsController.class.getName()).log(Level.SEVERE, null, ex);
            }
             H.setPrefHeight(100); 
             H.setPrefWidth(1000);
             H.getChildren().addAll(lbl,new Label("Nom de l'évènement: "+p.getNom_evenement()+"\n"+"Lieu de l'évènement: "+p.getId_service().getLibelle()+"\n"+"Date de l'évènemenet:  "+ (p.getHoraire_planing()) +"\n" + "Etat de l'évènemenet:  " + p.getEtat_event()),new Label("                        "),lbl2);
             List.getItems().add(H);
         }
//         ,new Label("Nom de l'evenement:  "),new Text(p.getNom_evenement())
//        for(Planing u3:listS) {
//            ListEvenements.addAll(u3);
//        }
//        List.setItems(ListEvenements);
//        List.setCellFactory(e -> {
//        return new EventName();
//        });
            }    
    @FXML
    void AjAction(ActionEvent event) throws IOException {
        
      Parent register_parent1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/Planing.fxml"));      
      Scene register_scene1 = new Scene(register_parent1);
       
      Stage stage = (Stage) buttonAjout.getScene().getWindow() ;
      stage.hide();
      stage.setScene(register_scene1);
      stage.show();

    }

    @FXML
    void ModifAction(ActionEvent event) throws IOException {      
//      Parent register_parent1 = FXMLLoader.load(getClass().getResource("/edu/esprit/piplaningfamicity/gui/ModifierEvent.fxml"));      
//      Scene register_scene1 = new Scene(register_parent1);     
//      Stage stage = (Stage) buttonModif.getScene().getWindow() ;
//      stage.hide();
//      stage.setScene(register_scene1);
//      stage.show();     
        ((Node)(event.getSource())).getScene().getWindow().hide();
        try { 
             Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/esprit/pidev/gui/ModifierPlan.fxml"));
       Parent root = (Parent) fxmlLoader.load();
        ModifierPlanController rcontroller = fxmlLoader.<ModifierPlanController>getController();
        Planing selecteds = new Planing();
        Planing selecteds2 = new Planing();
        //Planing p5 = new Planing();
       
        int index = List.getSelectionModel().getSelectedIndex();
        selecteds = (Planing) serv.getAllById().get(index);      
        int p2 = selecteds.getId_planing();
        selecteds2 = (Planing) serv.findByCategorie(p2);
        rcontroller.setPlanings(selecteds2);      
       //stage.setScene(new Scene(root));                         
                 stage.setScene(new Scene(root));
                     // System.out.println(p2);
                      //System.out.println(selecteds2);
                stage.initModality(Modality.WINDOW_MODAL);
                stage.showAndWait();
                } catch (Exception e) {e.printStackTrace();
        }
    }
     @FXML
    void SupprimeAction(ActionEvent event) throws IOException {

        Planing selecteds = new Planing();
        Planing selecteds2 = new Planing();
        IService serv = new PlaningImp();
        int index = List.getSelectionModel().getSelectedIndex();
        selecteds = (Planing) serv.getAllById().get(index);
        //List.getItems().remove(List.getSelectionModel().getSelectedItem());
        serv.delete(selecteds.getId_planing());
       //List.getItems().remove(List.getSelectionModel().getSelectedItem());
        //serv.getAll();
        List.refresh(); 
          Parent root = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/MesInvitations.fxml"));
       
       Scene register_scene1 = new Scene(root);
       Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
       stage.hide();
       stage.setScene(register_scene1);
       stage.show();
       stage.setResizable(false);
       stage.centerOnScreen();
        
        //System.out.println(selecteds);
    }
     @FXML
    void voirDetailAction(ActionEvent event) throws FileNotFoundException, DocumentException {     
        
                List<Planing> listS =serv.getAll();
//        System.out.println("***********");
//        listS.stream().forEach(a->System.out.println(a.getNomdeFamilleid().getNom()));
//        System.out.println("***************");
//        listS=listS.stream().filter(e->e.getNomdeFamilleid().getNom().equals(x.getNom())).collect(Collectors.toList());
//        System.out.println(listS.size());
               // System.out.println("***********");
        listS.stream().forEach(a->System.out.println(a.getNomdeFamilleid().getUsername()));
       // System.out.println("***************");
        listS=listS.stream().filter(e->e.getCreateur().equals(x.getUsername())).collect(Collectors.toList());
        Document doc = new Document();
         PdfWriter.getInstance(doc, new FileOutputStream("Report.pdf"));
         doc.open();
         //doc.add("55");
         doc.close();
    }    
    }

