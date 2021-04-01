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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kenza
 */
public class AjouterProduitController implements Initializable {

    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrix;
    @FXML
    private ComboBox <String> cmb_Categorie;
    @FXML
    private Button btnImporter;
    @FXML
    private Button btnConsulterCat;
    @FXML
    private Text label_photo;
    @FXML
    private CheckBox ckbDispo;
    @FXML
    private CheckBox ckbIndispo;
    @FXML
    private Button btnAjouter;
    
    
    private IServiceProduit serviceproduit = new ServiceProduit();
    private String absolutePathPhotoProduit;
    private IServiceCategorie servicecategorie = new ServiceCategorie();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> combolist = new ArrayList<>();
        combolist= servicecategorie.getall().stream().map(Categorie::getNom).collect(Collectors.toList());
        cmb_Categorie.getItems().addAll(combolist);
        // TODO
    }    
    
    
    
    @FXML
    void ajouterProduit(ActionEvent event) {
        Produit produit = new Produit();
        
        if(!tfNom.getText().equals("") && !label_photo.getText().equals("") && (!ckbDispo.isSelected()==false || !ckbIndispo.isSelected()==false) ) {
       
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
            serviceproduit.add(produit);
            Stage s = (Stage) tfNom.getScene().getWindow();
            s.close();
        } else {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("Erreur: champs vides");
             alert.setHeaderText("Champ(s) manquant(s) ! Veuillez remplir tous les champs. ");
             Optional<ButtonType> result = alert.showAndWait();

        }
    }

    
    @FXML
    void consulterCategorie(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Categorie.fxml"));
            Scene scene;
            try {
                scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                CategorieController controller = fxmlLoader.getController();
            //    controller.initData(categorie);
                stage.setTitle("Catégories ");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    
    @FXML
    void photoProduiteChooser(ActionEvent event) {
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
                //Image imag = new Image(absolutePathPhotoAnnonce);
                //img_annonce.setImage(imag);
            } else {
                System.out.println("Image introuvable");
            }
        });

    }
    
}
