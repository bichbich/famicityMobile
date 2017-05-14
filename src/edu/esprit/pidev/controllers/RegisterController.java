/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.controllers;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import edu.esprit.pidev.models.User;
import edu.esprit.pidev.services.impl.UserService;
import edu.esprit.pidev.services.interfaces.IUserService;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;




/**
 * FXML Controller clas
*/
public class RegisterController  implements Initializable {



    @FXML
    private JFXTextField Jpassword;

    @FXML
    private JFXTextField jEmail;

    @FXML
    private JFXTextField jPrenom;

    @FXML
    private JFXTextField jNom;

    @FXML
    private Label validUsername;

    @FXML
    private Label validPassword;

    @FXML
    private Label validNom;

    @FXML
    private Label validPrenom;

    @FXML
    private Label iv;


    @FXML
    private Label validMail;



    @FXML
    private JFXTextField imagepath;

    @FXML
    private Label imageName;

    @FXML
    private JFXTextField jPasswordconfirm;

    @FXML
    private JFXButton inscription;
     @FXML
    private ComboBox<String> Combobox;
         @FXML
    private JFXTextField jrole;




    @FXML
    private JFXButton upload;
    private static Path destination;
    private static File selectedfile;

    
        @FXML
    void uploadphoto(ActionEvent event) {
        FileChooser fc = new FileChooser();
       selectedfile = fc.showOpenDialog(null);
        
        destination = Paths.get("C:\\Users\\wiem\\Documents\\NetBeansProjects\\JavaFXApplicationIntegration4\\src\\edu\\esprit\\pidev\\images",selectedfile.getName());
       
        imagepath.setText(selectedfile.getAbsolutePath());
    }

     @FXML
   void inscription (ActionEvent event) throws IOException, URISyntaxException {  
             IUserService service = new UserService();
        User user = new User();
        if (service.RechLogin(jNom.getText())== true) {
            System.out.println("login existant");
        }else if (!jEmail.getText().contains("@")) {
            System.out.println("Email non valide");

   }else if ( jEmail.getText().isEmpty()) { 
       System.out.println("Saisir Email");
        
        }else{
       if(selectedfile==null){
        user.setImage(null);
        }else{
            Files.copy(selectedfile.toPath(),destination,StandardCopyOption.REPLACE_EXISTING); 
             user.setImage(destination.toString());
        }
        user.setNom(jNom.getText());
        user.setUsername(jPrenom.getText());
        user.setEmail(jEmail.getText());
        user.setPassword(Jpassword.getText());
       
        user.setPassword(jPasswordconfirm.getText());
       
        user.setRoles("a:0:{}");
        service.add(user);
        System.out.println("Inscription valide");
        iv.setText("Inscription valide");
//         SmsSender ss= new SmsSender();
//                ss.send("Vous êtes maintenant inscris dans l'application Famicity, merci pour votre choix");
        }
    
       
      Parent register_parent1 = FXMLLoader.load(getClass().getResource("/edu/esprit/pidev/gui/login.fxml"));
       
       Scene register_scene1 = new Scene(register_parent1);
       
       
      Stage membresStage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
      membresStage1.hide();
      membresStage1.setScene(register_scene1);
      membresStage1.show();
       

    }
      
    
    public void initialize(URL url, ResourceBundle rb) {
        Combobox.getItems().addAll("Pere","Mere","Soeur","Frere");
    }    
}
//    

