/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.controllers;

import com.jfoenix.controls.JFXButton;
import edu.esprit.pidev.models.Famille;
import edu.esprit.pidev.services.impl.FamilleService;
import edu.esprit.pidev.services.interfaces.IFamilleService;
import java.io.IOException;

import java.net.URL;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyStringWrapper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author wister
 */
public class NosFamillesController implements Initializable {

    @FXML
    private TableView<Famille> Familles;

    @FXML
    private TableColumn<Famille, Integer> id;

    @FXML
    private TableColumn<Famille, String> NomdeFamilleid;

    @FXML
    private TableColumn<Famille, String> membres;
    @FXML
    private TableColumn<Famille, String> image;
    @FXML
    private JFXButton rejoindre;
    
        @FXML
    private JFXButton creer;

    @FXML
    void redirectionCréer(ActionEvent event) throws IOException {
        Parent register_parent1 = FXMLLoader.load(getClass().getResource("/pidev2/gui/NewFamily.fxml"));

        Scene register_scene1 = new Scene(register_parent1);

        Stage membresStage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
        membresStage1.hide();
        membresStage1.setScene(register_scene1);
        membresStage1.show();

    }
    


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        IFamilleService familles = new FamilleService();
        Famille f = new Famille();
        List<Famille> ls = new ArrayList<>();
        ls = familles.getAll();

        List<Famille> familles1 = familles.getAll();
        ObservableList<Famille> data = FXCollections.observableArrayList(familles1);
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        NomdeFamilleid.setCellValueFactory(CellData -> new ReadOnlyStringWrapper(CellData.getValue().getNomdeFamilleid().getNom()));
        image.setCellValueFactory(CellData -> new ReadOnlyStringWrapper(CellData.getValue().getNomdeFamilleid().getImage()));
        {final ImageView imageview = new ImageView();
                imageview.setFitHeight(50);
                imageview.setFitWidth(50);}

       
        
        membres.setCellValueFactory(CellData -> new ReadOnlyStringWrapper(CellData.getValue().getMembres()));
        
        
        //CellData.getValue().getMembres()
        Familles.setItems(data);
    }


}
