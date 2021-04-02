/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Asus
 */
public class cours extends Application {
        @Override
        public void start(Stage primaryStage) throws Exception {
try {
            Parent root = FXMLLoader.load(getClass().getResource("cours.fxml"));
           // primaryStage.setScene(FXMLLoader.load(getClass().getResource("cours.fxml")));
            
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Gestion cours en ligne");
            primaryStage.setScene(scene);
            primaryStage.show();
            //en cas d'exception null pointer exception
            System.out.println(null == root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}