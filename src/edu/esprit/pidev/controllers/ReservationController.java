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
import java.time.LocalDateTime;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hanen
 */
public class ReservationController implements Initializable {

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
    ParcFXMLController parc = new ParcFXMLController();
    int param;
    int param1;
    int param2;

    Service s2 = new Service();
    User u1 = new User();
    @FXML

    private JFXButton btnconversion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            VBox box = new VBox();
            Parent r = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/SidePanel.fxml"));
            box.getChildren().add(r);
            drawer.setSidePane(box);
            System.err.println("nggh");
            IUserService us = new UserService();
            u1 = us.findById(User.getConnectedUser());
            System.out.println(u1);
            System.out.println(u1.getPoint() + "sqdsdqdsqdqds");
            if(u1.getPoint() == 0)
            {
            btnconversion.setVisible(false);
            }
            else
            {
            btnconversion.setVisible(true);
            }    
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
        
    }

    void setPromotion(Service S1) {
        s2 = S1;
    }

    @FXML
    void ActionAjout(ActionEvent event) throws IOException {
       // ((Node) (event.getSource())).getScene().getWindow().hide();

        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/esprit/pidev/gui/AccueilFrontOffice.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            ReservationService rservice = new ReservationService();
            Reservation r = new Reservation();
            IUserService us = new UserService();
            u1 = us.findById(User.getConnectedUser());
            int nbpoint = u1.getPoint();
            int pd =    s2.getNbPlacesDispo();

            System.out.println(nbpoint);
            r.setInformation_user_id(u1);
            //u1.setPoint(nbpoint+10);
            System.out.println(u1);
            // System.out.println(u.getConnectedUser());
            r.setId_service(s2);
            int places = Integer.parseInt(nbplaces.getText());
            //System.out.println(places);
            s2.setNbPlacesDispo(s2.getNbPlacesDispo() - places);
            r.setNb_places_restant(s2.getNbPlacesDispo());
            //  System.out.println(u.getPoint());
            r.setNb_placesreserve(places);
            if( places > pd ) {  Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(btnDemande.getScene().getWindow());
             alert.initOwner(btnconversion.getScene().getWindow());
             
            alert.setTitle("pas de places disponibles pour ce service");
            alert.setHeaderText(" reservation non valide");
            alert.setContentText("Veuillez reproduire votre reservation , il reste seulement"+pd+"places");
                    alert.showAndWait();
                    System.out.println("dispoooo "+pd);
                    System.out.println(places);

}
        else{
            r.setDate_resrvation(Date.valueOf(datep.getValue()));
            r.setDate_c(Date.valueOf(LocalDate.now()));
            Promotion p1 = new Promotion();
            p1 = rservice.getPromotionAscReservation(s2);
            if (p1 == null) {
                System.out.println(s2.getPrix());
                int a = (int) s2.getPrix();
                r.setPrix_r(places * a);
                rservice.addWithoutPromotion(r);
                Mail.sendMail(r.getInformation_user_id().getEmail(), "Détail Reservation", "vous avez reserver   " + r.getNb_placesreserve() + "places" + "+" + "le Montant restant  " + r.getPrix_r());
                System.out.println(r.getInformation_user_id().getEmail());
            } else {
                // System.out.println(p1.toString());
                r.setPrix_r((int) p1.getPrix_promo());
                r.setId_promo(p1);
                rservice.add(r);
                System.out.println(r.getInformation_user_id().getEmail());
                Mail.sendMail(r.getInformation_user_id().getEmail(), "Détail Reservation", "vous avez reserver" + r.getNb_placesreserve() + "places" + "le Montant restant  " + r.getPrix_r());
            }
            Scene register_scene1 = new Scene(root);

        Stage membresStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        membresStage1.hide();
        membresStage1.setScene(register_scene1);
        membresStage1.show();}
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void covertPoint(ActionEvent event) {
    ((Node)(event.getSource())).getScene().getWindow().hide();
        
        try {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/esprit/pidev/gui/AccueilFrontOffice.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        ReservationService rservice = new ReservationService();
       
        Reservation r = new Reservation();
       
        IUserService us = new UserService();
         User u1 =us.findById(User.getConnectedUser());
            
          int nbpoint = u1.getPoint();
           int pd =    s2.getNbPlacesDispo();
            System.err.println(pd);
        r.setInformation_user_id(u1);
        r.setId_service(s2);
        int places = Integer.parseInt(nbplaces.getText());
        s2.setNbPlacesDispo(s2.getNbPlacesDispo()-places); 
        r.setNb_places_restant(s2.getNbPlacesDispo() );
        r.setNb_placesreserve(places);
        if( places > pd ) {  Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(btnDemande.getScene().getWindow());
             alert.initOwner(btnconversion.getScene().getWindow());
             
            alert.setTitle("pas de places disponibles pour ce service");
            alert.setHeaderText(" reservation non valide");
            alert.setContentText("Veuillez reproduire votre reservation , il reste seulement"+pd+"places");
                    alert.showAndWait();
                    System.out.println("dispoooo "+nbpoint);
                    System.out.println(places);

}
        else{
        r.setDate_resrvation(Date.valueOf(datep.getValue()));
        r.setDate_c(Date.valueOf(LocalDate.now()));
        Promotion p1 = new Promotion();
        p1=rservice.getPromotionAscReservation(s2);
     //okok
        int somme=(int) (nbpoint*0.2);
             //System.out.println("edu.esprit.pidev.controllers.Reservation2Controller.ajout2Action()");
        if(p1==null)
        {
            int a = (int) s2.getPrix();
           // System.out.println("edu.esprit.pidev.controllers.Reservation2Controller.ajout2Action()");
             int t = a*places;
              System.err.println("somme"+somme);
              System.err.println("a"+a);
             if(somme >= t){
                
                  System.err.println("inside if"+t);
                  int  aa=somme-t;
                  int  pt=(int) (aa/0.2);
                  r.setPrix_r(0);
                   u1.setPoint((int) pt);
                   
                }else{
                  System.err.println("else"+t);
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
        Scene register_scene1 = new Scene(root);

        Stage membresStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        membresStage1.hide();
        membresStage1.setScene(register_scene1);
        membresStage1.show();}
                } catch (Exception e) {e.printStackTrace();
        }
    
    }
    @FXML
    private boolean Date2(){
      
       java.sql.Date dDebut= new java.sql.Date(datep.getValue().getYear()-1900, datep.getValue().getMonthValue()-1, datep.getValue().getDayOfMonth());

       java.sql.Date dNow= new java.sql.Date(LocalDateTime.now().getYear()-1900, LocalDateTime.now().getMonthValue()-1,LocalDateTime.now().getDayOfMonth());
    
       
       if(dNow.after(dDebut))
      {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERRUER");
            alert.setHeaderText(null);
            alert.setContentText("Vérifier les dates saisies");
            alert.showAndWait();
                             System.out.println(dNow);
                          System.out.println(dDebut);


            return false ;

   }
    return true;
}

}
