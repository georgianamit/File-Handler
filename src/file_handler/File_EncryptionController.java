/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package file_handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;

/**
 * FXML Controller class
 *
 * @author spider
 */
public class File_EncryptionController implements Initializable {
    File selectedFile;
    
    @FXML
    private TextField filePathTf;
    
    @FXML
    private Label errorlbl;
    
    @FXML
    private AnchorPane file_encryption_id;
        
    @FXML
    private void fileBrowseBtn(ActionEvent evt){
        FileChooser fc = new FileChooser();
        fc.setTitle("Choose Folder");
        Stage stage = (Stage)file_encryption_id.getScene().getWindow();
        selectedFile = fc.showOpenDialog(stage);


         if(selectedFile != null){
             filePathTf.setText(selectedFile.getAbsolutePath());
         }else{
             errorlbl.setText("Try Again!");
         }
    }
    
    @FXML
    private void encryptBtnAction(ActionEvent evt){
        try{
            FileInputStream inputFile = new FileInputStream(selectedFile.getAbsolutePath());
            FileOutputStream outputFile = new FileOutputStream(selectedFile.getParent()+"\\"+"encrypted"+getExtension(selectedFile));
            byte k[] = "$p!d3rLa8W38Am1t".getBytes();
            SecretKeySpec key = new SecretKeySpec(k,"AES");
            Cipher enc = Cipher.getInstance("AES");
            enc.init(Cipher.ENCRYPT_MODE,key);
            CipherOutputStream cos = new CipherOutputStream(outputFile,enc);
            byte[] buf = new byte[1024];
            int read;
            while((read = inputFile.read(buf)) != -1){
                cos.write(buf,0,read);
            }
            inputFile.close();
            outputFile.flush();
            cos.close();
            
        }catch(Exception e){
            
        }
    }
    
    @FXML
    private void decryptBtnAction(ActionEvent evt){
        try{
            FileInputStream inputFile = new FileInputStream(selectedFile.getAbsolutePath());
            FileOutputStream outputFile = new FileOutputStream(selectedFile.getParent()+"\\decrypted"+getExtension(selectedFile));
            byte k[] = "$p!d3rLa8W38Am1t".getBytes();
            SecretKeySpec key = new SecretKeySpec(k,"AES");
            Cipher enc = Cipher.getInstance("AES");
            enc.init(Cipher.DECRYPT_MODE,key);
            CipherOutputStream cos = new CipherOutputStream(outputFile,enc);
            byte[] buf = new byte[1024];
            int read;
            while((read = inputFile.read(buf)) != -1){
                cos.write(buf,0,read);
            }
            inputFile.close();
            outputFile.flush();
            cos.close();
        }catch(Exception e){
            
        }
    }
    
    String getExtension(File file){
        String fileExt=file.getName();
        return fileExt.substring(fileExt.lastIndexOf('.'));
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        file_encryption_id.setOpacity(0);
        fadeInTransition();
    }    

    private void fadeInTransition() {
       FadeTransition fadein = new FadeTransition();
       fadein.setDuration(Duration.millis(1000));
       fadein.setNode(file_encryption_id);
       fadein.setFromValue(0);
       fadein.setToValue(1);
       fadein.play();
    }
    
}
