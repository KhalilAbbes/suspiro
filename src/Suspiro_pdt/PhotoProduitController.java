/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Suspiro_pdt;

import Entité.Categorie;
import Entité.Produit;
import IService.IServiceCategorie;
import IService.IServiceProduit;
import Service.ServiceCategorie;
import Service.ServiceProduit;
import Utils.copyImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kenza
 */
public class PhotoProduitController implements Initializable {

    @FXML
    private ImageView ImageView;

    @FXML
    private Button btnModifier;

    @FXML
    private Button btnSupprimer;

    @FXML
    private TextField tfPrix;

    @FXML
    private TextField tfNom;

    @FXML
    private ComboBox<String> cmb_Categorie;

    @FXML
    private Button btnImporter;
    
    @FXML
    private Text label_photo;
    
    @FXML
    private CheckBox ckbDispo;

    @FXML
    private CheckBox ckbIndispo;
    
    private IServiceProduit serviceproduit = new ServiceProduit();
    private IServiceCategorie servicecategorie = new ServiceCategorie();
    private Produit produit;
    private String absolutePathPhotoProduit;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        List<String> combolist = new ArrayList<>();
        combolist= servicecategorie.getall().stream().map(Categorie::getNom).collect(Collectors.toList());
        cmb_Categorie.getItems().addAll(combolist);
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
            tfNom.setText(produit.getNom());
            tfPrix.setText(String.valueOf(produit.getPrix()));
            label_photo.setText(produit.getPhoto());
    }
     
     
     @FXML
    private void photoProduitChooser(ActionEvent event) 
    {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        btnImporter.setOnAction(e -> {
            File choix = fileChooser.showOpenDialog(null);
            if (choix != null) {
                System.out.println(choix.getAbsolutePath());
                absolutePathPhotoProduit = choix.getAbsolutePath();
                System.out.println("TEST" + absolutePathPhotoProduit);
                label_photo.setText(choix.getName());
            } else {
                System.out.println("Image introuvable");
            }
        });
    }
    
     
     @FXML
   private void ModifierProduit(ActionEvent event) throws IOException
   {
        produit.setNom(tfNom.getText());
        produit.setPrix(Double.parseDouble(tfPrix.getText()));
        String nom = cmb_Categorie.getSelectionModel().getSelectedItem();
        produit.setCategorie(nom);
        produit.setPhoto(label_photo.getText());
        produit.setId_categorie(servicecategorie.getall().stream().filter(e -> e.getNom().equals(nom)).map(Categorie::getId).collect(Collectors.toList()).get(0));
        boolean Dispo = ckbDispo.isSelected();
        boolean Indispo = ckbIndispo.isSelected();
        if (Dispo) {
            produit.setStock("Disponible");
        } else if (Indispo) {
            produit.setStock("Indisponible");
        }
        System.out.println(produit.toString());
        copyImage.deplacerVers(label_photo, absolutePathPhotoProduit, "C:\\Users\\kenza\\Desktop\\PIDEV\\Suspiro_pdt\\src\\res\\Produit_photo\\");
        serviceproduit.update(produit);
        Stage s = (Stage) tfNom.getScene().getWindow();
        s.close();
   }
   
   
    @FXML
   private void SupprimerProduit(ActionEvent event) throws IOException
   {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("suppression");
        alert.setHeaderText("Etes-vous sur de vouloir supprimer  '"
                + tfNom.getText() + "'  ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            serviceproduit.delete(produit);
            Stage s = (Stage) tfNom.getScene().getWindow();       
            s.close();
        }
   }

}
