/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import edu.esprit.pidev.models.Commentaire;
import edu.esprit.pidev.models.Reclamation;
import edu.esprit.pidev.services.impl.CommentaireService;
import edu.esprit.pidev.services.impl.ReclamationService;
import edu.esprit.pidev.technique.DataSource;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author DIARRA
 */
public class AfficherReclamationController implements Initializable{
    
      private Connection connection;
   
     @FXML
    private JFXButton delete;



    @FXML
    private JFXButton consulter;
    
    @FXML
    private TableView<Reclamation> tableV;
    @FXML
    private TableColumn<Reclamation, String> tabNom;
    @FXML
    private TableColumn<Reclamation, String> tabSujet;
    @FXML
    private TableColumn<Reclamation, String> tabNature;
    @FXML
    private TableColumn<Reclamation, Integer> tabLevel;
    @FXML
    private TableColumn<Reclamation, String> tabEmail;
    @FXML
    private TableColumn<Reclamation, Date> tabDate;
    @FXML
    private TableColumn<Reclamation, String> tabEtat;
    @FXML
    private TableColumn<Reclamation, String> tabDescription;
    private JFXTextField txtNom;
    private JFXTextField txtSujet;
    private JFXTextField txtNature;
    private JFXTextField txtLevel;
    
    private JFXTextField txtEmail;
    private JFXTextField txtEtat;
    private JFXTextArea txtDescription;
    private JFXTextField txtId;
    
    @FXML
    private AnchorPane reclamation;
    @FXML
    private JFXHamburger menuADmin;
    @FXML
    private JFXButton envoyer;
    @FXML
    private JFXButton stat;
     @FXML
    private JFXButton deconnecter;

  

    

    public AfficherReclamationController() {
        connection = DataSource.getInstance().getConnection();
    }
    

     
 

    @FXML
    void voir(ActionEvent event) throws IOException {
         Parent signin_page_parent = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/changerEtat.fxml"));
        Scene signin_page_scene = new Scene (signin_page_parent);
        Stage app_stage =(Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(signin_page_scene);
        app_stage.show();
    
    }

     @FXML
    void logOut(ActionEvent event) throws IOException {
         Parent signin_page_parent = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/login.fxml"));
        Scene signin_page_scene = new Scene (signin_page_parent);
        Stage app_stage =(Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(signin_page_scene);
        app_stage.show();
    }
    
  
    

   
    
    public ObservableList<Reclamation> getReclamation(){
           ReclamationService recl=new ReclamationService();
        ObservableList<Reclamation>MyReclams=FXCollections.observableArrayList();
        recl.getAll();
        
        List<Reclamation> reclamations = new ArrayList<>();
        try {
            String req = "select * from reclamation";
            PreparedStatement ps = connection.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Reclamation reclamation = new Reclamation(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7),rs.getDate(8),rs.getString(9));
                reclamations.add(reclamation);
                MyReclams.add(reclamation);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return MyReclams;
    }
     @FXML
    void supprimer(ActionEvent event) {
Reclamation reclamation = tableV.getSelectionModel().getSelectedItem();

        if (reclamation == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(tableV.getScene().getWindow());
            alert.setTitle("Aucune Sélection");
            alert.setHeaderText("Aucune Réclamation");
            alert.setContentText("Veuillez Sélectionner une réclamation");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(tableV.getScene().getWindow());
            alert.setTitle("Confirmation");
            alert.setHeaderText("Suppression Réclamation");
            alert.setContentText("Veuillez Confirmer la Suppression");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                    ReclamationService service = new ReclamationService();
                    service.delete(reclamation.getId());

                    tableV.getItems().remove(tableV.getSelectionModel().getSelectedItem());
                    tableV.refresh();

              }
           });
    }
        
    
}


    
    
       
        
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //tabUserId.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        tabNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tabSujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        tabNature.setCellValueFactory(new PropertyValueFactory<>("nature"));
        tabLevel.setCellValueFactory(new PropertyValueFactory<>("level"));
        tabEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tabDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tabEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        tabDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        //tabId.setCellValueFactory(new PropertyValueFactory<>("id"));
      
        
        ReclamationService reclAfficher=new ReclamationService();
   
     
       tableV.setItems((reclAfficher.getAllWithObservableList()));
       tableV.getColumns().addAll(tabNom,tabSujet,tabNature,tabLevel,tabEmail,tabDate,tabEtat,tabDescription/*,tabId*/);
       
       ObservableList<String> cuchoice = FXCollections.observableArrayList("plainte","Déception","Protestation","Logiciel","Service");
// txtNature.getItems().clear();
//    txtNature.setItems(cuchoice);
    }

    private void modifier(ActionEvent event) throws IOException {
          ReclamationService claimAjout=new ReclamationService ();
        Reclamation claim=new Reclamation();
        //int a=user.getId();
        //int b=Integer.parseInt(txtUser.getText());
        //b=a;
        claim.setId(Integer.parseInt(txtId.getText()));
        //evt.setUser_id(Integer.parseInt(txtUser.getText()));
        claim.setNom(txtNom.getText());
        claim.setSujet(txtSujet.getText());
        claim.setNature(txtNature.getText());
        claim.setLevel(Integer.parseInt(txtLevel.getText()));
        claim.setEmail(txtEmail.getText());
        claim.setEtat(txtEtat.getText());
        //claim.setDate(txtDate.getText());
        
        claim.setDescription(txtDescription.getText());
        
      
       claimAjout.update(claim);
        System.out.println("modifié avec succes oumou!!");
       
        Parent signin_page_parent = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/AfficherReclamation.fxml"));
        Scene signin_page_scene = new Scene (signin_page_parent);
        Stage app_stage =(Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(signin_page_scene);
        app_stage.show();
        
    }

    private void recuperer(ActionEvent event) {
        
        Reclamation selectedEvent=tableV.getSelectionModel().getSelectedItem();
    
    ReclamationService claims=new ReclamationService();
    txtId.setText(String.valueOf(selectedEvent.getId()));
    //txtUser.setText(String.valueOf(selectedEvent.getUser_id()));
    txtNom.setText(selectedEvent.getNom());
    txtSujet.setText(selectedEvent.getSujet());
         txtNature.setText(selectedEvent.getNature());
         txtLevel.setText(String.valueOf(selectedEvent.getLevel()));
         txtEmail.setText(selectedEvent.getEmail());
         //txtDate.setValue(selectedEvent.getDate.toLocalDate());
         txtEtat.setText(selectedEvent.getEtat());
         txtDescription.setText(selectedEvent.getDescription());
    
    }

    private void stat(ActionEvent event) throws IOException {
        Parent signin_page_parent = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/Statistique.fxml"));
        Scene signin_page_scene = new Scene (signin_page_parent);
        Stage app_stage =(Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(signin_page_scene);
        app_stage.show();
    }

//    private void sendMessageCode(ActionEvent event) {
//        
//        
//        Commentaire commentaire = new Commentaire();
//         if (commentaire == null) {
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//           // alert.initOwner(tableV.getScene().getWindow());
//            alert.setTitle("Aucune Sélection");
//            alert.setHeaderText("Aucune Réclamation");
//            alert.setContentText("Veuillez Sélectionner une réclamation");
//            alert.showAndWait();
//        } else {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//          //  alert.initOwner(tableV.getScene().getWindow());
//                    alert.setContentText("Veuillez Confirmer l'envoi!");
//            alert.showAndWait().ifPresent(response -> {
//                if (response == ButtonType.OK) {
// 
//       SendMessage ms = new SendMessage();
//       
//                ms.sendMessageCode("56311710", "");
//      
//       alert.setTitle("Confirmation");
//            alert.setHeaderText("Ajout commentaire");
//    
//       
//                 
//
//              }
//           });
//    }
//       
//               
//     }              
               
    

    @FXML
    private void send(ActionEvent event) throws IOException {
         Parent signin_page_parent = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/emailR.fxml"));
        Scene signin_page_scene = new Scene (signin_page_parent);
        Stage app_stage =(Stage)((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(signin_page_scene);
        app_stage.show();
    }

    @FXML
    private void afficherStatistique(ActionEvent event) {
        Scene scene = new Scene(new Group());
        Stage mainStage = new Stage();
        mainStage.setWidth(500);
        mainStage.setHeight(500);
        

        List<Reclamation> liste = new ArrayList<Reclamation>();
        liste = new ReclamationService().getStatCat();
        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList();
        for (int i = 0; i < liste.size(); i++) {
            pieChartData.add(new PieChart.Data(liste.get(i).getNature(), liste.get(i).getStat()));

        }
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Reclamation Par type");

        ((Group) scene.getRoot()).getChildren().add(chart);
        mainStage.setScene(scene);
        mainStage.show();
    }

    


    
}


