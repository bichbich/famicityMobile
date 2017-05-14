/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.pidev.controllers;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import edu.esprit.pidev.models.Promotion;
import edu.esprit.pidev.models.Quizz;
import edu.esprit.pidev.models.User;
import edu.esprit.pidev.services.impl.QuizzService;
import edu.esprit.pidev.services.impl.UserService;
import edu.esprit.pidev.services.interfaces.IQuizzService;
import edu.esprit.pidev.services.interfaces.IUserService;
import edu.esprit.pidev.technique.DataSource;

/**
 * FXML Controller class
 *
 * @author asmouna
 */
public class Niveau3Controller implements Initializable {
    
    @FXML
    private ScrollPane container;
    
    @FXML
    private JFXHamburger hamberger;
    
    @FXML
    private JFXDrawer drawer;
       
       List<String> response = new ArrayList<String>();

    public static int x;
 ObservableList<Quizz> listRe;

      QuizzService quizz = new QuizzService();
      

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    Connection connection = DataSource.getInstance().getConnection();
        QuizzService quizTest = new QuizzService();
        List<Quizz> listQuiz = quizTest.getQuizz(3);
        
        VBox vb = new VBox();
        int i =0;
      
      
for(Quizz quiz : listQuiz){
    GridPane grid = new GridPane();
    VBox Vtest= new VBox();
        
        
        Label l1=new Label(quiz.getQuestion());
        VBox V = new VBox();
        String choix1 = quiz.getReponse1();
        String choix2 = quiz.getReponse2();
        String choix3 = quiz.getReponse3();
        RadioButton r1;
        RadioButton r2;
        RadioButton r3;
        ToggleGroup g1;
            g1 = new ToggleGroup();
            r1 = new RadioButton(choix1);
            r2 = new RadioButton(choix2);
            r3 = new RadioButton(choix3);
            r1.setToggleGroup(g1);
            r2.setToggleGroup(g1);
            r3.setToggleGroup(g1);
            V.getChildren().addAll(r1,r2,r3);
        
          Vtest.getChildren().addAll(l1,V);    
        vb.getChildren().addAll(Vtest);
            
        g1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

        @Override
        public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
             if(r1.isSelected())
            {
                response.add(r1.getText());
            }
            else if(r2.isSelected())
            {
                response.add(r2.getText());
            }
             else if(r3.isSelected())
            {
                response.add(r3.getText());
            }
        }
    });
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
        AnchorPane pane = new AnchorPane();
        pane.getChildren().addAll(vb);
        
  container.setContent(pane);

    }
     
    @FXML
    void valider(ActionEvent event) {
    int repSuccniv1 = 0 ;
    int repEchniv1 = 0;
  QuizzService testservice = new QuizzService();
  List<String> responsebase = testservice.getReponse(3);
  Iterator<String> iter1 = responsebase.iterator();
  Iterator<String> iter2 = response.iterator();
        while (iter1.hasNext()&& iter2.hasNext()) {
            if(iter1.next().toString().equals(iter2.next().toString())){
                repSuccniv1++;
            }
            else
            {   
                repEchniv1++;
            }
        }
        System.out.println("reponses correctes = "+repSuccniv1);
        System.out.println("reponses fausses = "+repEchniv1);
        response.forEach(System.out::println);
        responsebase.forEach(System.out::println);
                
        IUserService userservice = new UserService();
        User u = userservice.findPointById(User.getConnectedUser());
        u.setPoint(u.getPoint()+(repSuccniv1*10));
        userservice.ajoutPoint(u);
        
         try {
        Stage stage = new Stage();
       
        Promotion s1 = new Promotion();
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/edu/esprit/pidev/gui/Resultat.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        ResultatController rcontroller = fxmlLoader.<ResultatController>getController();
        rcontroller.setPromotion(repSuccniv1,repEchniv1);
            //System.out.println(s1);
       stage.setScene(new Scene(root));
              //  stage.setTitle(" ");     
                stage.initModality(Modality.WINDOW_MODAL);
                stage.showAndWait();
                } catch (Exception e) {e.printStackTrace();
        }
        
    }
    
    
}
