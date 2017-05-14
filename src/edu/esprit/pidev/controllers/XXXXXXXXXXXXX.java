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
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import edu.esprit.pidev.models.Promotion;
import edu.esprit.pidev.models.Reservation;
import edu.esprit.pidev.models.Service;
import edu.esprit.pidev.models.User;
import edu.esprit.pidev.services.impl.ReservationService;
import edu.esprit.pidev.services.impl.ServiceService;
import edu.esprit.pidev.services.impl.UserService;
import edu.esprit.pidev.services.interfaces.IUserService;
import edu.esprit.pidev.services.interfaces.IreservationService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hanen
 */
public class XXXXXXXXXXXXX implements Initializable {

     @FXML
    private JFXHamburger hamburger;
   
    @FXML
    private JFXDrawer drawer;
   @FXML
    private JFXButton btnDemande;
    IreservationService reservation = new ReservationService();
        @FXML
    private JFXDatePicker datep;

    @FXML
    private JFXTextArea nbplaces;
        ParcFXMLController parc =new ParcFXMLController();
  int param ;
    int param1 ;
    int param2 ;
    
    Service s2 = new  Service();
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
try {
            VBox box = new VBox();
            Parent r = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/SidePanel.fxml"));

            box.getChildren().add(r);
            drawer.setSidePane(box);
            
            System.out.println("edu.esprit.pidev.controllers.Reservation2Controller.initialize()");
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
        }    }    

    void setPromotion(Service S1) {
         s2=S1;
    }
    
       private void ajout2Action(ActionEvent event) throws IOException {
         ((Node)(event.getSource())).getScene().getWindow().hide();
        
        try {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/esprit/pidev/gui/menu.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        ReservationService rservice = new ReservationService();
       
        Reservation r = new Reservation();
       
        IUserService us = new UserService();
         User u1 =us.findById(User.getConnectedUser());
            
          int nbpoint = u1.getPoint();
                
        r.setInformation_user_id(u1);
        r.setId_service(s2);
        int places = Integer.parseInt(nbplaces.getText());
        s2.setNbPlacesDispo(s2.getNbPlacesDispo()-places); 
        r.setNb_places_restant(s2.getNbPlacesDispo() );
        r.setNb_placesreserve(places);
        r.setDate_resrvation(Date.valueOf(datep.getValue()));
        r.setDate_c(Date.valueOf(LocalDate.now()));
        Promotion p1 = new Promotion();
        p1=rservice.getPromotionAscReservation(s2);
     //okok
        int somme=(int) (nbpoint*0.2);
             System.out.println("edu.esprit.pidev.controllers.Reservation2Controller.ajout2Action()");
        if(p1==null)
        {
            int a = (int) s2.getPrix();
            System.out.println("edu.esprit.pidev.controllers.Reservation2Controller.ajout2Action()");
             int t = a*places;
             if(somme >= t){
                  int  aa=somme-t;
                  int  pt=(int) (aa/0.2);
                  r.setPrix_r(0);
                   u1.setPoint((int) pt);
                   
                }else{
                   r.setPrix_r(t-somme);
                    u1.setPoint(0);
                }
             
            rservice.addWithoutPromotion(r);
        Mail.sendMail(r.getInformation_user_id().getEmail(), "Détail Reservation", "vous avez reserver   " +r.getNb_placesreserve()+"places"+ "+" +    "le Montant restant  "+r.getPrix_r());
            System.out.println(r.getInformation_user_id().getEmail());

        }
        else{
        //r.setPrix_r((int) p1.getPrix_promo()*places);
        int p = (int) (p1.getPrix_promo()*places);
        
        int t = p*places;
             if(somme >= t){
                  int  aa=somme-t;
                  int  pt=(int) (aa/0.2);
                  r.setPrix_r(0);
                   u1.setPoint((int) pt);
                   
                }else{
                   r.setPrix_r(t-somme);
                    u1.setPoint(0);
                }
        
        r.setId_promo(p1);
        rservice.add(r);
            System.out.println(r.getInformation_user_id().getEmail());
        Mail.sendMail(r.getInformation_user_id().getEmail(), "Détail Reservation", "vous avez reserver" +r.getNb_placesreserve()+"places"+"le Montant restant  "+r.getPrix_r());
        }
        stage.setScene(new Scene(root));
              //  stage.setTitle(" ");
              
                stage.initModality(Modality.WINDOW_MODAL);
                stage.showAndWait();
                } catch (Exception e) {e.printStackTrace();
        }
        
        
        
        
        
        
        
        
       }
       
       
       
       /*
    @FXML
    private void reserver2Action(ActionEvent event) {
//       (((Node)(event.getSource())).getScene().getWindow().hide();
        
        try {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/esprit/pidev/gui/reservation.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        ReservationController rcontroller = fxmlLoader.<ReservationController>getController();
        Service s1 = new Service();
 int index = listview.getSelectionModel().getSelectedIndex();
        Service selecteds = Service.getAll().get(index);
        System.out.println(selecteds);
       s1.setIdService(selecteds.getIdService());
        s1.setPrix(selecteds.getPrix());
        s1.setNbPlacesDispo(selecteds.getNbPlacesDispo());
        
        rcontroller.setPromotion(s1);
        
       stage.setScene(new Scene(root));
              //  stage.setTitle(" ");
              
                stage.initModality(Modality.WINDOW_MODAL);
                stage.showAndWait();
                } catch (Exception e) {e.printStackTrace();
        }}
//        }
//    
////
*/    
    
        




        
        
    }
    
    

                

