/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Suspiro_pdt;

import Entité.Produit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author kenza
 */
public class PhotoPdtClientController implements Initializable {
    
    @FXML
    private ImageView ImageView;

    @FXML
    private Text titre;
    
    private Produit produit;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void initData(Produit produit) {
         System.out.println(produit.toString());
         this.produit = produit;
         
            Image image = null;
            try {
                   System.out.println("C:\\Users\\kenza\\Desktop\\PIDEV\\Suspiro_pdt\\src\\res\\Produit_photo\\" + produit.getPhoto());
                image = new Image(new FileInputStream("C:\\Users\\kenza\\Desktop\\PIDEV\\Suspiro_pdt\\src\\res\\Produit_photo\\" + produit.getPhoto()));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(PhotoProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ImageView.setImage(image);
            titre.setText(produit.getNom());
    }
    
}
