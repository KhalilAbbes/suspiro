/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Suspiro_pdt;

import Entité.Categorie;
import Entité.CodePromo;
import Entité.Produit;
import IService.IServiceCategorie;
import IService.IServiceCodePromo;
import IService.IServiceProduit;
import Service.ServiceCategorie;
import Service.ServiceCodePromo;
import Service.ServiceProduit;
import static Suspiro_pdt.MainController.indice;
import static Suspiro_pdt.MainController.listsearch;
import static Suspiro_pdt.MainController.obsl;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kenza
 */
public class ClientController implements Initializable {

    @FXML
    private TextField tfCodePromo;
    @FXML
    private TextField tfRecherche;
    @FXML
    private ComboBox<String> cmb_Categorie1;
    @FXML
    private TableView<Produit> tvProduit;
    @FXML
    private TableColumn<Produit, String> colNom;
    @FXML
    private TableColumn<Produit, Double> colPrix;
    @FXML
    private TableColumn<Produit, String> colCategorie;
    @FXML
    private TableColumn<Produit, Void> colPhoto;
    @FXML
    private TableColumn<Produit, String> colStock;
    @FXML
    private TableColumn<Produit, Void> colPanier;
    @FXML
    private Button btnRafraichir;
    @FXML
    private Button btnTrieP;
    @FXML
    private Label label;
    @FXML
    private Button btnVerifierC;
    
    boolean test = false;

    private IServiceProduit serviceproduit = new ServiceProduit();
    private String absolutePathPhotoProduit;
    public static List<Produit> listsearch;
    public static ObservableList<Produit> obsl;
    public static int indice;
    private IServiceCategorie servicecategorie = new ServiceCategorie();
    private IServiceCodePromo servicecodepromo = new ServiceCodePromo();
    private ObservableList<Produit> observableList, observableListPromo;
    public static List<CodePromo> listsearchcp;
    public static ObservableList<CodePromo> obslcp;
    public static int indicecp;
    private ArrayList<Produit> arrayList, arrayListPromo;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> combolist = new ArrayList<>();
        combolist = servicecategorie.getall().stream().map(Categorie::getNom).collect(Collectors.toList());
        cmb_Categorie1.getItems().addAll(combolist);

        listsearchcp = servicecodepromo.getall();
        obslcp = FXCollections.observableArrayList(listsearchcp);

        AfficherAll();
        this.Rechercher();
        this.filtreParCategorie();
        this.filtreParPrix();

// TODO
    }


    private void AfficherAll() {
        arrayList = (ArrayList) serviceproduit.getall();
        if (test) //If there is a promo code
        {
            arrayList.clear();
            arrayListPromo = (ArrayList) serviceproduit.getallPromo();
            observableListPromo = FXCollections.observableArrayList(arrayListPromo);
            tvProduit.setItems(observableListPromo);
            colNom.setCellValueFactory(new PropertyValueFactory<Produit, String>("Nom"));
            colPrix.setCellValueFactory(new PropertyValueFactory<Produit, Double>("Prix"));
            colCategorie.setCellValueFactory(new PropertyValueFactory<Produit, String>("Categorie"));
            colPhoto.setCellFactory(col -> new TableCell<Produit, Void>() {
                private final Button button;
                {
                    button = new Button("Photo ");
                    button.setOnAction(evt -> {
                        Produit item = (Produit) getTableRow().getItem();
                        detailsProduit(item);
                    });
                }
                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    setGraphic(empty ? null : button);
                }
            });
            colStock.setCellValueFactory(new PropertyValueFactory<Produit, String>("Stock"));
            colPanier.setCellFactory(col -> new TableCell<Produit, Void>() {
                private final Button button;
                { 
                    button = new Button("Panier ");
                }
                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    setGraphic(empty ? null : button);
                }
            });
            
        } else {  //If there isn't a promo code
            
            observableList = FXCollections.observableArrayList(arrayList);
            tvProduit.setItems(observableList);
            colNom.setCellValueFactory(new PropertyValueFactory<Produit, String>("Nom"));
            colPrix.setCellValueFactory(new PropertyValueFactory<Produit, Double>("Prix"));
            colCategorie.setCellValueFactory(new PropertyValueFactory<Produit, String>("Categorie"));
            colPhoto.setCellFactory(col -> new TableCell<Produit, Void>() {
                private final Button button;
                {
                    button = new Button("Photo ");
                    button.setOnAction(evt -> {
                        Produit item = (Produit) getTableRow().getItem();
                        detailsProduit(item);
                    });
                }
                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    setGraphic(empty ? null : button);
                }
            });
            colStock.setCellValueFactory(new PropertyValueFactory<Produit, String>("Stock"));
            colPanier.setCellFactory(col -> new TableCell<Produit, Void>() {
                private final Button button;
                {
                    button = new Button("panier");
                }
                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    setGraphic(empty ? null : button);
                }
            });
        }
    }

    
    private void detailsProduit(Produit produit) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("PhotoPdtClient.fxml"));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            PhotoPdtClientController controller = fxmlLoader.getController();
            controller.initData(produit);
            stage.setTitle("Photo ");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(PhotoPdtClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private void Rechercher() {
        FilteredList<Produit> filteredData = new FilteredList<>(observableList, b -> true);
        tfRecherche.setOnKeyReleased(e -> {
            tfRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Produit>) Produit -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (Produit.getNom().toLowerCase().contains(lowerCaseFilter)) {
                        return true; 
                    } else if (Produit.getCategorie().contains(newValue)) {
                        return true;
                    } else if (Produit.getStock().contains(newValue)) {
                        return true;
                    }
                    return false; // Does not match.
                });
            });
            SortedList<Produit> soretedData = new SortedList<>(filteredData);
            soretedData.comparatorProperty().bind(tvProduit.comparatorProperty());
            tvProduit.setItems(soretedData);
        });
    }

    
    private void filtreParCategorie() {
        FilteredList<Produit> filteredData = new FilteredList<>(observableList, b -> true);
        cmb_Categorie1.setOnAction(e -> {
            String nom = cmb_Categorie1.getSelectionModel().getSelectedItem();
            filteredData.setPredicate((Predicate<? super Produit>) Produit -> {
                if (nom == null || nom.isEmpty()) {
                    return true;
                }
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = nom.toLowerCase();
                if (Produit.getCategorie().equals(nom)) {
                    return true; // Filter matches first name.
                }
                return false; // Does not match.
            });
            SortedList<Produit> soretedData = new SortedList<>(filteredData);
            soretedData.comparatorProperty().bind(tvProduit.comparatorProperty());
            tvProduit.setItems(soretedData);
        });
    }

    
    private void filtreParPrix() {
        FilteredList<Produit> filteredData = new FilteredList<>(observableList, b -> true);
        btnTrieP.setOnAction(e -> {
            observableList = FXCollections.observableArrayList(arrayList.stream().sorted((a, b)
                    -> a.getPrix().compareTo(b.getPrix())).collect(Collectors.toList()));
            tvProduit.setItems(observableList);
        });
    }

    
    @FXML
    public void Rafraichir() {
        //observableList.remove(0,observableList.size());
        label.setText("");  
        test = false;
        arrayList.clear();
        AfficherAll();
    }

    
    @FXML
    private void testcode() {
        listsearchcp = obslcp.stream().filter(e -> e.getCode().equals(tfCodePromo.getText()))
                .collect(Collectors.toList());
        if(listsearchcp.isEmpty()){
            System.out.println("Code Promo Invalide !");
            label.setText("Code Promo Invalide !");    
            test = false;
            arrayList.clear();
            AfficherAll();
        } else {
            label.setText("Vous avez obtenu 20% sur tous nos produits !");
            System.out.println("Code Promo Valide !");
            test = true;
            AfficherAll();
        }
    }
    


}
