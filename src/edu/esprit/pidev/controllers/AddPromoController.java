/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.controllers;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import edu.esprit.pidev.models.Promotion;
import edu.esprit.pidev.models.Service;
import edu.esprit.pidev.services.impl.PromotionService;
import edu.esprit.pidev.services.impl.ServiceService;
import edu.esprit.pidev.services.interfaces.IService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author asmouna
 */
public class AddPromoController implements Initializable {

    @FXML
    private JFXTextField t;

    @FXML
    private JFXButton addPromo;

    @FXML
    private JFXTextField desc;

    @FXML
    private JFXComboBox<Service> cb;
    
    @FXML
    private JFXButton AddPromo;
    
    Promotion promotion1 = new Promotion();
    IService promoService = new PromotionService();
    
    @FXML
    void makeAddPromo(ActionEvent event) {
          
        Service choix = cb.getValue();
        promotion1.setDescription(desc.getText());
        promotion1.setEtat("Disponible");
        promotion1.setService(choix);
        promotion1.setTaux(Float.valueOf(t.getText()));
        promotion1.setPrix_promo(77);
       
        promoService.ajouter(promotion1);
        System.out.println("Valide");
        System.out.println(choix.getIdService());
    }


            
        Service service = new Service();
        IService serviceService = new ServiceService();
        ObservableList<Service> list = FXCollections.observableArrayList();
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        list = FXCollections.observableArrayList(serviceService.getAll());
            cb.setItems((list));

    }    
    
        @FXML
    void makeHome(ActionEvent event) throws IOException {
Parent p1 = FXMLLoader.load(getClass().getResource("/pidev2/gui/Menu.fxml"));
          Scene test1 = new Scene(p1);
          Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
          App1.setScene(test1);
          App1.show();
    }

}
