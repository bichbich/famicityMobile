/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.controllers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import static edu.esprit.pidev.controllers.ParcFXMLController.service;
import edu.esprit.pidev.models.Reservation;
import edu.esprit.pidev.models.Service;
import edu.esprit.pidev.models.User;
import edu.esprit.pidev.services.impl.ReservationService;
import edu.esprit.pidev.services.impl.ServiceService;
import edu.esprit.pidev.services.interfaces.IServiceService;
import edu.esprit.pidev.services.interfaces.IreservationService;
import java.io.IOException;
import java.net.URL;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Callback;


/**
 * FXML Controller class
 *
 * @author hanen
 */
public   class ReservationADMINController implements Initializable {

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private TableView<Reservation> tableRecherche;


    @FXML
    private TableColumn<Reservation, String> information_user_id;

    @FXML
    private TableColumn<Reservation, String> id_service;

    @FXML
    private TableColumn<?, ?> nb_placesreserve;

    @FXML
    private TableColumn<?, ?> date_resrvation;

    @FXML
    private TableColumn<?, ?> prix_r;

    @FXML
    private TextField recherche;
        @FXML
    private TextField pr;
    /**
     * Initializes the controller class.
     */
    IreservationService res = new ReservationService();
      ReservationService r = new ReservationService();

    private final ObservableList<Reservation> Listres = FXCollections.observableArrayList();
    
 public static  List<Reservation> reserv;

    ObservableList<Integer> selectedIndexes = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
tableRecherche.setEditable(true);

     
       information_user_id.setCellValueFactory(new Callback<CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Reservation, String> p) {

         return new SimpleStringProperty(p.getValue().getInformation_user_id().getUsername());
     }
  });
        id_service.setCellValueFactory(new Callback<CellDataFeatures<Reservation, String>, ObservableValue<String>>() {
     public ObservableValue<String> call(CellDataFeatures<Reservation, String> p) {

         return new SimpleStringProperty(p.getValue().getId_service().getLibelle());
     }
  });
      

        nb_placesreserve.setCellValueFactory(new PropertyValueFactory<>("nb_placesreserve"));
       date_resrvation.setCellValueFactory(new PropertyValueFactory<>("date_resrvation"));

        prix_r.setCellValueFactory(new PropertyValueFactory<>("prix_r"));
        
        

        reserv = res.getAll();
       // System.out.println(reserv);
        //System.out.println(Service.findByCategorie("parc"));
        for (Reservation r : reserv) {
            Listres.add(r);
        }
        tableRecherche.setItems(Listres);
        try {
            VBox box = new VBox();
            Parent r = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/SidePanelAD.fxml"));

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
         FilteredList<Reservation> filteredData = new FilteredList<>(Listres , p-> true);
       recherche.setOnKeyReleased(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            // 2. Set the filter Predicate whenever the filter changes.
            recherche.textProperty().addListener((observableValue, oldValue, newValue)->{
                filteredData.setPredicate((Predicate<? super Reservation>) r->{
                    if(newValue == null || newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    //System.out.println(r.getInformation_user_id().getNom());
                    if(r.getInformation_user_id().getUsername().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                     else if (r.getDate_resrvation().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                    
                    return false;
                });
            });
                    // 3. Wrap the FilteredList in a SortedList. 

        SortedList<Reservation> sortedData = new SortedList<>(filteredData);
                // 4. Bind the SortedList comparator to the TableView comparator.

           sortedData.comparatorProperty().bind(tableRecherche.comparatorProperty());
                   // 5. Add sorted (and filtered) data to the table.

           tableRecherche.setItems(sortedData);
        }
    });
       // System.out.println("edu");
    }

    private void rechercherreservation(ActionEvent event) {
        reserv = res.getAll();
       // System.out.println(reserv);
        //System.out.println(Service.findByCategorie("parc"));
        for (Reservation r : reserv) {
            Listres.add(r);
        }
        tableRecherche.setItems(Listres);
//        tableRecherche.getItems().clear();
//
//        tableRecherche.setItems((ObservableList<Reservation>) r.findByDate(recherche.getText()));

        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        //System.out.println(Listres);


    }

    @FXML
    private void supprimer(ActionEvent event) {
    Reservation reservation = tableRecherche.getSelectionModel().getSelectedItem();

        if (reservation == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(tableRecherche.getScene().getWindow());
            alert.setTitle("Aucune Sélection");
            alert.setHeaderText("Aucune reservation");
            alert.setContentText("Veuillez Sélectionner une réservation");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(tableRecherche.getScene().getWindow());
            alert.setTitle("Confirmation");
            alert.setHeaderText("Suppression reservation");
            alert.setContentText("Veuillez Confirmer la Suppression");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    ReservationService service = new ReservationService();
                    service.delete(reservation.getId_reservation());

                    tableRecherche.getItems().remove(tableRecherche.getSelectionModel().getSelectedItem());
                    tableRecherche.refresh();
                    
                    
                    
                    

              }
           });


    }}

    @FXML
    private void exit(ActionEvent event) {
                System.exit(0);

    }

    @FXML
    private void modifier(ActionEvent event) {
        Reservation index = tableRecherche.getSelectionModel().getSelectedItem();
        System.out.println(index);
     //   claim.setNomdeFamilleid(NomdeFamilleid.getCellData(NomdeFamilleid));
           IreservationService rs = new ReservationService();

         
       index.setPrix_r(Integer.parseInt(pr.getText()));
        System.out.println(Integer.parseInt(pr.getText()));
       
       rs.update(index);
        System.out.println(index);
              System.out.println("modifié avec succes!!");
        tableRecherche.getItems().addAll(tableRecherche.getSelectionModel().getSelectedItem());
        tableRecherche.refresh();


    

    }

  
}
