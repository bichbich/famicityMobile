/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
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
import javafx.collections.ObservableArray;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import edu.esprit.pidev.models.Promotion;
import edu.esprit.pidev.models.Quizz;
import edu.esprit.pidev.models.Service;
import edu.esprit.pidev.services.impl.PromotionService;
import edu.esprit.pidev.services.impl.QuizzService;
import edu.esprit.pidev.services.interfaces.IService;
import edu.esprit.pidev.technique.DataSource;

/**
 * FXML Controller class
 *
 * @author asmouna
 */
public class ListQuizzController implements Initializable {

    @FXML
    private TableColumn<Quizz, String> rep2;

    @FXML
    private TableColumn<Quizz, String> rep1;

    @FXML
    private TableColumn<Quizz, String> rep3;

    @FXML
    private TableColumn<Quizz, String> Ques;

    @FXML
    private TableColumn<Quizz, String> numQuizz;

    @FXML
    private TableColumn<Quizz, Integer> repCorrecte;
    
    @FXML
    private TableView<Quizz> table;
    
        @FXML
    private JFXButton Recuperer;
        
            @FXML
    private JFXButton Supprimer;
            
                @FXML
    private JFXButton Modifier;


    @FXML
    private JFXButton add;


    @FXML
    private TextField ques;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private TextField rep21;

    @FXML
    private TextField rep11;

    @FXML
    private TextField rep31;


    @FXML
    private TextField numquizz;


    @FXML
    private JFXHamburger hamberger;

    @FXML
    private TextField rep;
    
    @FXML
    private JFXTextField idP;


    Quizz quizz1 = new Quizz();
    IService quizzService = new QuizzService();

    @FXML
    void makeadd(ActionEvent event) {
        
//        String question = ques.getText();
//        String numQuizz = numquizz.getText();
//        String reponse1 = rep1.getText();
//        String reponse2 = rep2.getText();
//        String reponse3 = rep3.getText();
//        int reponse = Integer.parseInt(rep.getText());
       
        quizz1.setQuestion(ques.getText());
        quizz1.setQuizz(numquizz.getText());
        quizz1.setReponse1(rep11.getText());
        quizz1.setReponse2(rep21.getText());
        quizz1.setReponse3(rep31.getText());
        quizz1.setReponse_correcte(rep.getText());
       
        quizzService.ajouter(quizz1);
        System.out.println("Valide");
        
        ObservableList<Quizz> l = FXCollections.observableArrayList();
                    l = FXCollections.observableArrayList(quizzService.getAll());
                    table.setItems(l);
                    table.refresh();

    }

    ObservableList<Quizz> list =FXCollections.observableArrayList();
        ListView<String> view = new ListView<String>();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        Connection connection = DataSource.getInstance().getConnection();
        List<Quizz> Quizzs = new ArrayList<>();
        try {
            String req = "select * from quizz";
            java.sql.PreparedStatement ps = connection.prepareStatement(req);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                list.add(new Quizz (resultSet.getInt("id_quizz"),
                        resultSet.getString("question"), resultSet.getString("reponse1"),
                 resultSet.getString("reponse2"), resultSet.getString("reponse3")
                , resultSet.getInt("reponse"),resultSet.getString("quizz"),resultSet.getString("reponse_correcte"))
                        );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

         
        Ques.setCellValueFactory(new PropertyValueFactory<>("question"));
        rep1.setCellValueFactory(new PropertyValueFactory<>("reponse1"));
        rep2.setCellValueFactory(new PropertyValueFactory<>("reponse2"));
        rep3.setCellValueFactory(new PropertyValueFactory<>("reponse3"));
        repCorrecte.setCellValueFactory(new PropertyValueFactory<>("reponse_correcte"));
        
        table.setItems(list);
        
                     try {
            VBox box = new VBox();
            Parent r = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/SidePanelAD.fxml"));

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
            Logger.getLogger(ListQuizzController.class.getName()).log(Level.SEVERE, null, ex);
        
        
        
        
        
        }
        
        
        
       
    }    
    
     private IntegerProperty index = new SimpleIntegerProperty();
    @FXML
    void makeSupprimer(ActionEvent event) {
        Quizz q = table.getSelectionModel().getSelectedItem();

        if (q == null) {
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
            alert.setHeaderText("Supprimer la question !");
            alert.setContentText("Veuillez Confirmer la Suppression");
            alert.showAndWait().ifPresent(response -> {
                      int i = index.get();
                    System.out.println(i);
                    System.out.println(q);
                if (response == ButtonType.OK) {

                   QuizzService p = new QuizzService();
                   p.supprimer(q.getIdQuizz());
                    ObservableList<Quizz> l = FXCollections.observableArrayList();
                    l = FXCollections.observableArrayList(quizzService.getAll());
                    table.setItems(l);
                    table.refresh();
                }
                
           });
        }
    }
  ObservableList<Object> list2 = FXCollections.observableArrayList();
                @FXML
    void makeRecuperer(ActionEvent event) {
    Quizz selectedEvent=table.getSelectionModel().getSelectedItem();
    
    QuizzService promo =new QuizzService();
    ques.setText(String.valueOf(selectedEvent.getQuestion()));
    numquizz.setText(String.valueOf(selectedEvent.getQuizz()));
    rep11.setText(String.valueOf(selectedEvent.getReponse1()));
    rep21.setText(String.valueOf(selectedEvent.getReponse2()));
    rep31.setText(String.valueOf(selectedEvent.getReponse3()));
    rep.setText(String.valueOf(selectedEvent.getReponse_correcte()));
    int ser = selectedEvent.getIdQuizz();
    list2 = FXCollections.observableArrayList(quizzService.findById(ser));
    idP.setText(String.valueOf(selectedEvent.getIdQuizz()));
    }
   
    @FXML
    void makeModifier(ActionEvent event) throws IOException {
    Quizz selectedEvent=table.getSelectionModel().getSelectedItem();
    QuizzService claimAjout=new QuizzService();
    Quizz claim=new Quizz();
    claim.setIdQuizz(Integer.parseInt(idP.getText()));
    claim.setQuestion(ques.getText());
    claim.setQuizz(numquizz.getText());
    claim.setReponse1(rep11.getText());
    claim.setReponse2(rep21.getText());
    claim.setReponse3(rep31.getText());
    claim.setReponse(0);
    claim.setReponse_correcte(rep.getText());
    claimAjout.modifier(claim);
    System.out.println("modifié");
    ObservableList<Quizz> l = FXCollections.observableArrayList();
    l = FXCollections.observableArrayList(quizzService.getAll());
    table.setItems(l);
    table.refresh();
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
