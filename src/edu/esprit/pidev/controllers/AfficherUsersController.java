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
import edu.esprit.pidev.models.User;
import edu.esprit.pidev.services.impl.UserService;
import edu.esprit.pidev.services.interfaces.IUserService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import javax.mail.MessagingException;
import static jdk.nashorn.internal.runtime.Debug.id;
import org.controlsfx.control.Notifications;
import static org.jboss.netty.util.internal.SystemPropertyUtil.contains;



/**
 * FXML Controller class
 *
 * @author wister
 */
public class AfficherUsersController implements Initializable {
   
   
   @FXML
    private JFXDrawer drawwer;

    @FXML
    private TextField recherche;

    @FXML
    private TextField nom;

    @FXML
    private TextField prenom;

    @FXML
    private TextField email;

    @FXML
    private TextField password;

    @FXML
    private TextField username;

    @FXML
    private JFXButton delete;

    @FXML
    private ComboBox<String> comboroles;

    @FXML
    private TableView<User> tableV;

    @FXML
    private TableColumn<User,String> tabUsername;

    @FXML
    private TableColumn<User,String> tabNom;

    @FXML
    private TableColumn<User,String> tabroles;

    @FXML
    private JFXHamburger menuADMin;
    UserService s =new UserService();
            

    @FXML
    void ajouterreponsable(ActionEvent event) {
          IUserService service = new UserService();
         User user = new User();
          user.setNom(username.getText());
        user.setUsername(nom.getText());
        user.setEmail(email.getText());
        user.setPassword(password.getText());
        
       user.setRoles(comboroles.getValue());
       // service.addAdmin(user);
        System.out.println("Inscription valide d'un nouveau admin");

    }

    @FXML
    void rechercheUtilisateur(KeyEvent event) {

    }

    @FXML
    void sortirResponsable(ActionEvent event) {
          System.exit(0);
    }

  


    

    @FXML
    void supprimer(ActionEvent event) throws MessagingException {
         User usr1 = tableV.getSelectionModel().getSelectedItem();

        if (usr1 == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(tableV.getScene().getWindow());
            alert.setTitle("Aucune Sélection");
            alert.setHeaderText("Aucun User");
            alert.setContentText("Veuillez Sélectionner un User");
            alert.showAndWait();
        } else {
            System.out.println(usr1.getEmail());
       // EmailAttachmentSender.sendEmailWithAttachments("mohamedaymen.ourabi@esprit.tn","signale","code de récupération de mot de passe ");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(tableV.getScene().getWindow());
            alert.setTitle("Confirmation");
            alert.setHeaderText("Suppression User");
            alert.setContentText("Veuillez Confirmer la Suppression");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {

                   UserService us = new UserService();
                    us.delete(usr1.getId());

                    tableV.getItems().remove(tableV.getSelectionModel().getSelectedItem());
                    tableV.refresh();

              }
           });
    }


    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            IUserService users = new UserService(); 
       
       List<User> users1 = users.getAll();
        ObservableList<User> data = FXCollections.observableArrayList(users1);
        //data.stream().forEach(e->System.out.println(e.getMembres()));
        tableV.setItems(data);
       
        tabUsername.setCellValueFactory(new PropertyValueFactory<>("Username"));
        tabNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
         tabroles.setCellValueFactory(new PropertyValueFactory<>("roles"));
       
                 tableV.setItems(data);
         try {
            VBox box = new VBox();
            Parent r = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/SidePanelAD.fxml"));

            box.getChildren().add(r);
            drawwer.setSidePane(box);
            
            
            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(menuADMin);
            transition.setRate(-1);
            menuADMin.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                transition.setRate(transition.getRate() * -1);
                transition.play();
                
                if (drawwer.isShown()) {
                    drawwer.close();
                } else {
                    drawwer.open();
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
       comboroles.getItems().addAll("a:1:{i:0;s:10:\"ROLE_ADMIN\";}");
       IUserService u3 = new UserService();
       List<User> listSU = u3.getAll();

        ObservableList<User> ListUser = FXCollections.observableArrayList(listSU);
       
        FilteredList<User> listeFiltre = new FilteredList<>(ListUser, e -> true);
            recherche.textProperty().addListener((observableValue,oldValue,newValue)-> {
                listeFiltre.setPredicate((Predicate<? super User>) u -> {
                    
                    if (newValue == null || newValue.isEmpty())
                    { 
                         return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (u.getUsername().contains(newValue))
                    {
                        return true;
                    }
                    return false;
                     });
                    SortedList<User> UserTries = new SortedList<>(listeFiltre);
                    UserTries.comparatorProperty().bind(tableV.comparatorProperty());
                    tableV.setItems(UserTries);  
                 });
       
       
       
       
       
    }    
    

        
  
    

}
