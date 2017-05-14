/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import edu.esprit.pidev.models.Promotion;
import edu.esprit.pidev.models.Service;
import edu.esprit.pidev.services.impl.PromotionService;
import edu.esprit.pidev.services.impl.ServiceService;
import edu.esprit.pidev.services.interfaces.IService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author asmouna
 */
public class ListPromoController implements Initializable {

   
    @FXML
    private TableColumn<Promotion, Float> p;

    @FXML
    private TableColumn<Promotion, Integer> Ser;

    @FXML
    private TableColumn<? , ?> a;

    @FXML
    private TableColumn<Promotion, String> des;

    @FXML
    private TableColumn<? , ?> s;

    @FXML
    private TableColumn<Promotion, Integer> iid;

    @FXML
    private TableColumn<Promotion, Float> t;

    @FXML
    private TableColumn<Promotion, String> e;

    @FXML
    private TableView<Promotion> table;
    
    @FXML
    private JFXTextField idP;
    
    @FXML
    private TextField t1;

    @FXML
    private JFXButton Modifier;

    @FXML
    private TextField desc;

    @FXML
    private ComboBox<Service> cb;
    
    @FXML
    private JFXHamburger hamberger;
    
    @FXML
    private JFXDrawer drawer;
    
        @FXML
    private JFXButton Acc;

    
    PromotionService promoService = new PromotionService();
         List<Promotion> promotions = new ArrayList<>();
    
      IService pService = new PromotionService();
        ObservableList<Promotion> list =FXCollections.observableArrayList();
                ObservableList<Promotion> listPromo =FXCollections.observableArrayList();

        ListView<String> view = new ListView<String>();
    
    
    @FXML
    private JFXButton AddPromo;
    
    Promotion promotion1 = new Promotion();
    private Object selectedEvent;
    
    @FXML
    void makeAddPromo(ActionEvent event) {
          
        Service choix = cb.getValue();
        promotion1.setDescription(desc.getText());
        promotion1.setEtat("Disponible");
        promotion1.setService(choix);
        promotion1.setTaux(Integer.valueOf(t1.getText()));
        promotion1.setPrix_promo((float)choix.getPrix()-(((float)choix.getPrix()*((Float.valueOf(t1.getText()))/100))));
       
        promoService.ajouter(promotion1);
        System.out.println("Valide");
        System.out.println(choix.getIdService());
        ObservableList<Promotion> l = FXCollections.observableArrayList();
        l = FXCollections.observableArrayList(promoService.getAll());
        table.setItems(l);
        table.refresh();
    }
   


            
        Service service = new Service();
        IService serviceService = new ServiceService();
        ObservableList<Service> list1 = FXCollections.observableArrayList();
         ObservableList<Object> list2 = FXCollections.observableArrayList();
    
        
       
            @FXML
    void makeRecuperer(ActionEvent event) {
    Promotion selectedEvent=table.getSelectionModel().getSelectedItem();
    
    PromotionService promo =new PromotionService();
    desc.setText(String.valueOf(selectedEvent.getDescription()));
    t1.setText(String.valueOf(selectedEvent.getTaux()));
    int ser = selectedEvent.getService().getIdService();
    list2 = FXCollections.observableArrayList(serviceService.findById(ser));
    idP.setText(String.valueOf(selectedEvent.getIdPromo()));
    }
    
        @FXML
         void acc(ActionEvent event) throws IOException {
          Parent p1 = FXMLLoader.load(getClass().getResource("/pidev2/gui/Menu.fxml"));
          Scene test1 = new Scene(p1);
          Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
          App1.setScene(test1);
          App1.show();
    }

    @FXML
    void makeSupprimer(ActionEvent event) {
        Promotion promo = table.getSelectionModel().getSelectedItem();

        if (promo == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(table.getScene().getWindow());
            alert.setTitle("Aucune Sélection");
            alert.setHeaderText("Aucune Famille");
            alert.setContentText("Veuillez Sélectionner une Famille");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(table.getScene().getWindow());
            alert.setTitle("Confirmation");
            alert.setHeaderText("Suppression famille");
            alert.setContentText("Veuillez Confirmer la Suppression");
            alert.showAndWait().ifPresent(response -> {
                      int i = index.get();
                    System.out.println(i);
                    System.out.println(promo);
                if (response == ButtonType.OK) {

                   PromotionService p = new PromotionService();
                   p.supprimer(promo.getIdPromo());
                    ObservableList<Promotion> l = FXCollections.observableArrayList();
                    l = FXCollections.observableArrayList(promoService.getAll());
                    table.setItems(l);
                    table.refresh();
                    
                }
           });
    }

    }
 private IntegerProperty index = new SimpleIntegerProperty();
    @FXML
    void makeActiver(ActionEvent event) {
        Promotion promo = table.getSelectionModel().getSelectedItem();
        promoService.activer(promo);
table.getItems().removeAll();
ObservableList<Promotion> l = FXCollections.observableArrayList();
l = FXCollections.observableArrayList(promoService.getAll());
table.setItems(l);
table.refresh();
    }

    @FXML
    void makeDesactiver(ActionEvent event) {
        Promotion promo = table.getSelectionModel().getSelectedItem();
        promoService.desactiver(promo);
table.getItems().removeAll();
ObservableList<Promotion> l = FXCollections.observableArrayList();
l = FXCollections.observableArrayList(promoService.getAll());
table.setItems(l);
table.refresh();
    }

    @FXML
    void makeModifier(ActionEvent event) throws IOException {
        Promotion selectedEvent=table.getSelectionModel().getSelectedItem();
        PromotionService claimAjout=new PromotionService();
        Promotion claim=new Promotion();
        Service choix1 = cb.getValue();
        claim.setIdPromo(Integer.parseInt(idP.getText()));
        claim.setDescription(desc.getText());
        claim.setTaux((Float.valueOf(t1.getText())));
        claim.setService((choix1));
        claim.setPrix_promo((float)choix1.getPrix()-(((float)choix1.getPrix()*((Float.valueOf(t1.getText()))/100))));
        claimAjout.modifier(claim);
        System.out.println("modifié");
        ObservableList<Promotion> l = FXCollections.observableArrayList();
        l = FXCollections.observableArrayList(promoService.getAll());
        table.setItems(l);
        table.refresh();
//       
//        Parent signin_page_parent = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/ListPromo.fxml"));
//        Scene signin_page_scene = new Scene (signin_page_parent);
//        Stage app_stage =(Stage)((Node) event.getSource()).getScene().getWindow();
//        app_stage.setScene(signin_page_scene);
//        app_stage.show();
        
    }
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    index.set(-1);
        list1 = FXCollections.observableArrayList(serviceService.getAll());
        listPromo = FXCollections.observableArrayList(promoService.getAll());
            cb.setItems((list1));
         
            //        iid.setCellValueFactory(new PropertyValueFactory<>("idPromo"));
      //  Ser.setCellValueFactory(new PropertyValueFactory<>("id_service"));
        des.setCellValueFactory(new PropertyValueFactory<>("description"));
        t.setCellValueFactory(new PropertyValueFactory<>("taux"));
        e.setCellValueFactory(new PropertyValueFactory<>("etat"));
        p.setCellValueFactory(new PropertyValueFactory<>("prix_promo"));
        
        table.setItems(listPromo);
        
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
    
            @FXML
    void makeHome(ActionEvent event) throws IOException {
Parent p1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/Menu.fxml"));
          Scene test1 = new Scene(p1);
          Stage App1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
          App1.setScene(test1);
          App1.show();
    }
    
}
