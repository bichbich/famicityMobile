/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import edu.esprit.pidev.models.Promotion;
import edu.esprit.pidev.models.User;
import edu.esprit.pidev.services.impl.PromotionService;
import edu.esprit.pidev.services.impl.UserService;
import edu.esprit.pidev.services.interfaces.IUserService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import com.restfb.BinaryAttachment;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.FacebookType;


/**
 * FXML Controller class
 *
 * @author asmouna
 */
public class DetailController implements Initializable {
    @FXML
    private Label idSer;
    
       @FXML
    private JFXButton Partager;
     
    int a = 0;
    
    @FXML
    private TextArea Descriptiion;

    
    @FXML
    private ImageView imagePromo;
    @FXML
    private JFXHamburger hamberger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private Label idSer2;

    @FXML
    private Label idSer1;

    @FXML
    private Label nom;

    @FXML
    private Label coin;

    @FXML
    void makePartager(ActionEvent event) throws FileNotFoundException {
        PromotionService promotionservice = new PromotionService();
        Promotion x = promotionservice.findById(a);
        
        
        String nomphoto = x.getService().getImageService();
        String accessToken="EAACEdEose0cBAMVwvCCrHKAQzaZCRCZCELnSHkPjPl8Su5bgOJJci5ftaZCPYI0HLR1cIhfN85WZB9UVQm8wfOd33dZCkfnDgAlvzFo5RZBBc8lUSwv1ZCrVzzxD2O7nieFJSsfhn6kAHzl306GHrgZA4jBmOUr5ag8xkUf2OFXqyHiEn0Bkbk6ZCvmJkrBXwu5QZD";
        FacebookClient fbclient = new DefaultFacebookClient(accessToken, Version.LATEST);
        
        
//        FileInputStream fis = new FileInputStream(new File("C:\\Users\\asmouna\\Desktop\\pidev2\\pidev2\\src\\pidev2\\images\\imageService"));
        
        User me = fbclient.fetchObject("me", User.class);
              FacebookType response;
//            response = fbclient.publish("me/photos",FacebookType.class,
                   
       //             Parameter.with("message", x.getDescription()));
              
              response = fbclient.publish("me/feed", FacebookType.class, Parameter.with("message", x.getDescription()));
   //     System.out.println(me.getName());
              
    }
    public static Promotion promo ;
    
   public void setPromotion(Promotion s1){
       
                               File file = null;
Image image=null;
ImageView imagev1=null;
         promo = s1;
         System.out.println("AAA"+promo);
         idSer.setText(promo.getService().getLibelle());
         idSer1.setText(String.valueOf(promo.getService().getNbPlacesDispo()));
         idSer2.setText(String.valueOf(promo.getPrix_promo()));
                           file = new File("C:\\Users\\asmouna\\Desktop\\pidev2\\pidev2\\src\\pidev2\\images\\imageService\\"+promo.getService().getImageService());
            image = new Image(file.toURI().toString());
           //  System.out.println(gr.getService().getImageService());
        //    imagev = new ImageView( new javafx.scene.image.Image(file.toURI().toString()));
             imagePromo = new ImageView(image);
          
//                 imagev1.setFitHeight(170);
//                 imagev1.setFitWidth(170);
         a = promo.getIdPromo();
    } 
   public void promoss (){
      
       System.out.println(promo);
   }    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
Promotion p1 = new Promotion();
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
            Logger.getLogger(AccueilFrontOfficeController.class.getName()).log(Level.SEVERE, null, ex);
        }
         IUserService userservice = new UserService();
        User x = userservice.findPointById(User.getConnectedUser());
        System.out.println(User.getConnectedUser());
        System.out.println(String.valueOf(x.getPoint()+x.getUsername()));
        nom.setText(x.getUsername());
        coin.setText(String.valueOf(x.getPoint()));
    }    
    
}
