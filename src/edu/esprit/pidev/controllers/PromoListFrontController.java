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
import edu.esprit.pidev.models.Promotion;
import edu.esprit.pidev.services.impl.PromotionService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author asmouna
 */
public class PromoListFrontController implements Initializable {

    @FXML
    private JFXListView listview;
    
    @FXML
    private Button MoreDetails;
        
    PromotionService ps= new PromotionService();
    @FXML
    private JFXHamburger hamberger;
    
    @FXML
    private JFXDrawer drawer;


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PromotionService promoService = new PromotionService();
        List<Promotion> listS =promoService.getAllDispo();
       ObservableList<Promotion> Listservices = FXCollections.observableArrayList(listS);
//         final ObservableList<Planing> ListEvenements = FXCollections.observableArrayList(listS);
                        File file = null;
Image image=null;
ImageView imagev1=null;
         listview.setExpanded(true);
         listview.depthProperty().set(1);
                for (Promotion gr : listS)
{

             HBox H=new HBox(2);
             Label lbl = new Label();
               JFXButton buttonreser = new JFXButton();
                           file = new File("C:\\Users\\asmouna\\Desktop\\pidev2\\pidev2\\src\\pidev2\\images\\imageService\\"+gr.getService().getImageService());
            image = new Image(file.toURI().toString());
             System.out.println(gr.getService().getImageService());
        //    imagev = new ImageView( new javafx.scene.image.Image(file.toURI().toString()));
             imagev1 = new ImageView(image);
          
                 imagev1.setFitHeight(90);
                 imagev1.setFitWidth(90);
                
//            try {
//                buttonreser.setGraphic(new ImageView(new Image(new FileInputStream("C:\\Users\\wiem\\Documents\\NetBeansProjects\\PiPlaningFamicity\\src\\edu\\esprit\\piplaningfamicity\\image\\deletefinal.png"))));
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(MesInvitationsController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//              JFXButton buttonModif = new JFXButton();
//            try {
//                buttonModif.setGraphic(new ImageView(new Image(new FileInputStream("C:\\Users\\wiem\\Documents\\NetBeansProjects\\PiPlaningFamicity\\src\\edu\\esprit\\piplaningfamicity\\image\\wrench.png"))));
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(MesInvitationsController.class.getName()).log(Level.SEVERE, null, ex);
//            }

                //Logger.getLogger(MesInvitationsController.class.getName()).log(Level.SEVERE, null, ex);

             H.setPrefHeight(100); 
             H.setPrefWidth(1000);
             H.getChildren().addAll(new Label(gr.getService().getLibelle()+ "\n Prix: " + gr.getService().getNbPlacesDispo()+ "\n Prix promo: " + gr.getPrix_promo()));
             H.getChildren().addAll(imagev1);
             listview.getItems().add(H);
             
}     
             
                try {
            VBox box = new VBox();
            Parent r = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/SidePanel.fxml"));

            box.getChildren().add(r);
            drawer.setSidePane(box);
            
            
            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamberger);
            transition.setRate(-1);
            hamberger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                transition.setRate(transition.getRate() * -1);
                transition.play();
                
                if (drawer.isShown()) {
                    drawer.close();
                } else {
                    drawer.open();
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(ListPromoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        }
//        @FXML
//    void makeMoreDetails(ActionEvent event) {
//        Promotion selecteds = new Promotion();
//            PromotionService serv = new PromotionService();
//        int index = listview.getSelectionModel().getSelectedIndex();
//         selecteds = (Promotion) serv.getAll().get(index);
//        System.out.println(selecteds);
//    }

    @FXML
    public void makeMoreDetails(ActionEvent event) throws Exception {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        
        try {
        Stage stage = new Stage();
       
        Promotion s1 = new Promotion();
 int index = listview.getSelectionModel().getSelectedIndex();
        Promotion selecteds = ps.getAllDispo().get(index);
       // System.out.println(selecteds);
          //  System.out.println(selecteds.getIdPromo());
       s1.setIdPromo(selecteds.getIdPromo());
       s1.setService(selecteds.getService());
       s1.setPrix_promo(selecteds.getPrix_promo());
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/esprit/pidev/gui/Detail.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        DetailController rcontroller = fxmlLoader.<DetailController>getController();
        rcontroller.setPromotion(s1);
            System.out.println(s1);
       stage.setScene(new Scene(root));
              //  stage.setTitle(" ");
              
                stage.initModality(Modality.WINDOW_MODAL);
                stage.showAndWait();
                } catch (Exception e) {e.printStackTrace();
        }
        
  
    }

    }
