/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Suspiro_pdt;

import Utils.DataBase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author kenza
 */
public class Main extends Application {
    
    
    @Override
    public void start(Stage stage) {
        try
        {
            DataBase cnx = DataBase.getInstance();
            Parent root = FXMLLoader.load(getClass().getResource("session.fxml"));
            stage.setTitle("Session");
            stage.setResizable(false);
            stage.setIconified(false);
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (Exception exp){

            System.out.println(exp.getMessage());
            System.exit(0);            
        }
    }

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
