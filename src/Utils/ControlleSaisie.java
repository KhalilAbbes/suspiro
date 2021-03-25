/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Duration;
import tray.notification.TrayNotification;
import static tray.notification.NotificationType.ERROR;


/**
 *
 * @author kenza
 */
public class ControlleSaisie {
    
    public static boolean estVide(TextField txtField, String nomField){
        if(txtField.getText().equals("")){
            txtField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ; -fx-border-radius: 4;");
            TrayNotification tray = new TrayNotification("Erreur", "Précisez votre "+ nomField , ERROR);
            tray.showAndDismiss(Duration.millis(2000));
            return true; 
        } else {
            txtField.setStyle("-fx-border-color: none ; -fx-border-width: 0px ; -fx-border-radius: 4;");
            return false; 
        }
    }
    
    
    public static boolean estVidePhoto(Text txtField, String nomField){
        if(txtField.getText().equals("")){
            txtField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ; -fx-border-radius: 4;");
            TrayNotification tray = new TrayNotification("Erreur", "Précisez votre "+ nomField , ERROR);
            tray.showAndDismiss(Duration.millis(2000));
            return true; 
        } else {
            txtField.setStyle("-fx-border-color: none ; -fx-border-width: 0px ; -fx-border-radius: 4;");
            return false; 
        }
    }
        
        
//    public static boolean estVideCombo(ComboBox comboField, String nomField){
//        if(comboField.getSelectionModel().getSelectedItem()==null  ){
//            comboField.setStyle("-fx-border-color: red ; -fx-border-width: 2px ; -fx-border-radius: 4;");
//            TrayNotification tray = new TrayNotification("Erreur", "Précisez votre "+ nomField , ERROR);
//            tray.showAndDismiss(Duration.millis(2000));
//            return true; 
//        } else {
//            comboField.setStyle("-fx-border-color: none ; -fx-border-width: 0px ; -fx-border-radius: 4;");
//            return false; 
//        }
//    }
    
}
