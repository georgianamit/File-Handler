/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file_handler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author spider
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private AnchorPane file_handler_id;
    

    
    @FXML
    private void fileClassifierBtnAction(ActionEvent evt){
        fadeOutTransitionForClassifier();
    }
    
    @FXML
    private void fileEncryptionBtnAction(ActionEvent evt){
        fadeOutTransitionForEncryption();
    }
    
    private void fadeOutTransitionForEncryption(){
       FadeTransition fadeout = new FadeTransition();
       fadeout.setDuration(Duration.millis(1000));
       fadeout.setNode(file_handler_id);
       fadeout.setFromValue(1);
       fadeout.setToValue(0);
       fadeout.setOnFinished(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent evt){
               loadFileEncryption();
           }
       });
       fadeout.play();
   } 
    
    private void fadeOutTransitionForClassifier(){
       FadeTransition fadeout = new FadeTransition();
       fadeout.setDuration(Duration.millis(1000));
       fadeout.setNode(file_handler_id);
       fadeout.setFromValue(1);
       fadeout.setToValue(0);
       fadeout.setOnFinished(new EventHandler<ActionEvent>(){
           @Override
           public void handle(ActionEvent evt){
               loadFileClassifier();
           }
       });
       fadeout.play();
   } 
   
   private void loadFileClassifier(){
        try {
            Parent fileClassifier = (AnchorPane)FXMLLoader.load(getClass().getResource("File_Classifier.fxml"));
            Scene newScene = new Scene(fileClassifier);
            Stage curStage = (Stage)file_handler_id.getScene().getWindow();
            curStage.setScene(newScene);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
      private void loadFileEncryption(){
        try {
            Parent fileClassifier = (AnchorPane)FXMLLoader.load(getClass().getResource("File_Encryption.fxml"));
            Scene newScene = new Scene(fileClassifier);
            Stage curStage = (Stage)file_handler_id.getScene().getWindow();
            curStage.setScene(newScene);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
