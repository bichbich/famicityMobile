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
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.sun.javafx.scene.control.skin.TableColumnHeader;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.fxml.FXMLLoader;
import edu.esprit.pidev.models.Promotion;
import edu.esprit.pidev.models.Reservation;
import edu.esprit.pidev.models.Service;
import edu.esprit.pidev.models.User;
import edu.esprit.pidev.services.impl.ReservationService;
import edu.esprit.pidev.services.impl.ServiceService;
import edu.esprit.pidev.services.interfaces.IServiceService;
import edu.esprit.pidev.services.interfaces.IreservationService;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hanen
 */
public class ParcFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXHamburger hamburger;
//    @FXML
//    public TableView<Service> tableservice;

    @FXML
    private JFXDrawer drawer;
        

//    @FXML
//    private TableColumn<?, ?> lib;
//
//    @FXML
//    private TableColumn<?, ?> prix;
//
//    @FXML
//    private TableColumn<?, ?> description;
//    @FXML
//    private Button cp;
//
//    @FXML
//    private Button fd;
    IreservationService reservation = new ReservationService();
    private JFXDatePicker datep;

//    @FXML
//    private TableColumn<?, ?> image;
//    @FXML
//    private TableColumn<?, ?> reserver;
       @FXML
    private JFXListView  listview;
    IServiceService Service = new ServiceService();
   
    
 public static List<Service> service;

    ObservableList<Integer> selectedIndexes = FXCollections.observableArrayList();

    @Override
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
            Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        File file = null;
Image image=null;
ImageView imagev1=null;

//  IService u2= new PlaningImp();        
       List<Service> listS =Service.findByCategorie("parc");
       ObservableList<Service> Listservices = FXCollections.observableArrayList(listS);
//         final ObservableList<Planing> ListEvenements = FXCollections.observableArrayList(listS);
         
         listview.setExpanded(true);
         listview.depthProperty().set(1);
                for (Service gr : listS) {
                    file = new File("C:\\Users\\hanen\\Documents\\NetBeansProjects\\playWithJava\\JavaFXApplication1\\src\\edu\\esprit\\pidev\\images\\"+gr.getImageService());
                    image = new Image(file.toURI().toString());
             imagev1 = new ImageView(image);
          
              imagev1.setFitHeight(50);
                 imagev1.setFitWidth(50);

             HBox H=new HBox(2);
             Label lbl = new Label();
               JFXButton buttonreser = new JFXButton();
//            
//            try {
//                lbl.setGraphic(new ImageView(new Image(new FileInputStream(gr.getImageService()))));
//            } catch (FileNotFoundException ex) {
                //Logger.getLogger(MesInvitationsController.class.getName()).log(Level.SEVERE, null, ex);
//            }
              imagev1.setFitHeight(150);
                 imagev1.setFitWidth(150);
             H.getChildren().addAll(new Label("Nom Service:  " + gr.getLibelle()+ "\n catégorie: " + gr.getCategorie() + "\n Description: " + gr.getDescription() + "\n prix:  " + gr.getPrix(),imagev1));

             listview.getItems().add(H);
             
             
             
             
             
         

    }
    }

    @FXML
    public void reserverAction(ActionEvent event) throws Exception {
        ((Node)(event.getSource())).getScene().getWindow().hide();
        
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
        }

    }
    

}
