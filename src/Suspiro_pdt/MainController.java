/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Suspiro_pdt;

import Entité.Categorie;
import Service.ServiceCategorie;
import Service.ServiceProduit;
import IService.IServiceProduit;
import Entité.Produit;
import IService.IServiceCategorie;
import Utils.ControlleSaisie;
import Utils.copyImage;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


/**
 *
 * @author kenza
 */
public class MainController implements Initializable {
    
    @FXML
    private ImageView bckgdAdmin;
    @FXML
    private TextField tfRecherche;
    @FXML
    private ComboBox <String> cmb_Categorie1;
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
    private Button btnRafraichir;
    @FXML
    private Button btnTrieP;
    @FXML
    private Button btnpdf;
    @FXML
    private Button btnCodeP;
    @FXML
    private Button btnAjouterP;
    
   
 
//   private IServiceCategorie ServiceCategorie;
    
    private IServiceProduit serviceproduit = new ServiceProduit();
    private String absolutePathPhotoProduit;
    public static List<Produit> listsearch;
    public static ObservableList<Produit> obsl;
    public static int indice;
    private IServiceCategorie servicecategorie = new ServiceCategorie();
    private ObservableList <Produit>  observableList;
    private  ArrayList<Produit>  arrayList ;
    
//    @FXML
//    private void handleButtonAction(ActionEvent event) {
//    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<String> combolist = new ArrayList<>();
        combolist= servicecategorie.getall().stream().map(Categorie::getNom).collect(Collectors.toList());
        cmb_Categorie1.getItems().addAll(combolist);
        
        AfficherAll();
        this.Rechercher();
        this.filtreParCategorie();
        this.filtreParPrix();
     
        // TODO
    }    
    
   
   private void AfficherAll() {
        arrayList = (ArrayList) serviceproduit.getall();
        observableList = FXCollections.observableArrayList(arrayList);
        tvProduit.setItems(observableList);
        colNom.setCellValueFactory(new PropertyValueFactory<Produit, String>("Nom"));
        colPrix.setCellValueFactory(new PropertyValueFactory<Produit, Double>("Prix"));
        colCategorie.setCellValueFactory(new PropertyValueFactory<Produit, String>("Categorie"));
        colPhoto.setCellFactory(col -> new TableCell<Produit, Void>() {
            private final Button button;
            {
                button = new Button("Details");
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
    }
   
   
   @FXML
   private void AjouterP(ActionEvent event) throws IOException
   {
    FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("AjouterProduit.fxml"));
            Scene scene;
            try {
                scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                AjouterProduitController controller = fxmlLoader.getController();
            //    controller.initData(categorie);
                stage.setTitle("Ajouter un Produit ");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);
            }
   }
   
   
   private void detailsProduit(Produit produit) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("PhotoProduit.fxml"));
            /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
             */
            Scene scene;
            try {
                scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                PhotoProduitController controller = fxmlLoader.getController();
                controller.initData(produit);
                stage.setTitle("Details ");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(PhotoProduitController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
    // TODO: implement
}
    
    
    @FXML
    private void tfRecherche(ActionEvent event) throws IOException {
        listsearch = obsl.stream().filter(e -> e.getNom().contains(tfRecherche.getText())).collect(Collectors.toList());
        indice = 2;
        Parent root = FXMLLoader.load(getClass().getResource("/Suspiro_pdt/Main.fxml"));
//        container.setCenter(root);
    }
    
    
     private void Rechercher(){
         FilteredList<Produit>filteredData=new FilteredList<>(observableList,b -> true);
         tfRecherche.setOnKeyReleased(e->{
                   tfRecherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate((Predicate<? super Produit>)Produit -> {
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (Produit.getNom().toLowerCase().contains(lowerCaseFilter) ) {
                                    return true; 
				} 
				else if (Produit.getCategorie().contains(newValue)){
				    return true;
                                }
                                else if (Produit.getStock().contains(newValue)){
				    return true;
                                }
                                return false; // Does not match.
			});
		});
        SortedList<Produit>soretedData=new SortedList<>(filteredData);
        soretedData.comparatorProperty().bind(tvProduit.comparatorProperty());
        tvProduit.setItems(soretedData);
            });
     }
     
     
     private void filtreParCategorie(){
         FilteredList<Produit>filteredData=new FilteredList<>(observableList,b -> true);
            cmb_Categorie1.setOnAction(e->{
                  String nom = cmb_Categorie1.getSelectionModel().getSelectedItem();
			filteredData.setPredicate((Predicate<? super Produit>)Produit -> {				
				if (nom == null || nom.isEmpty()) {
					return true;
				}
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = nom.toLowerCase();
				if (Produit.getCategorie().equals(nom) ) {
					return true; // Filter matches first name.
				} 
			    	 return false; // Does not match.
			});
        SortedList<Produit>soretedData=new SortedList<>(filteredData);
        soretedData.comparatorProperty().bind(tvProduit.comparatorProperty());
        tvProduit.setItems(soretedData);
            });
     }
     
     
     private void filtreParPrix(){
         FilteredList<Produit>filteredData=new FilteredList<>(observableList,b -> true);
         btnTrieP.setOnAction(e->{
                   observableList = FXCollections.observableArrayList(arrayList.stream().sorted((a, b) -> 
                        a.getPrix().compareTo(b.getPrix())).collect(Collectors.toList()));
         tvProduit.setItems(observableList);
            });
     }
     
     @FXML
      public void Rafraichir()  {
         //observableList.remove(0,observableList.size());
         arrayList.clear();
         AfficherAll();
   }       
      
      
      
    @FXML
    private void genererPDF(ActionEvent event) throws IOException,FileNotFoundException, DocumentException  {
        
            String file_name="C:\\Users\\kenza\\Desktop\\PIDEV\\Suspiro_pdt\\src\\res\\Produit_photo\\listeProduits.pdf";
            Document document = new Document();
            
            PdfWriter.getInstance(document, new FileOutputStream(file_name));
            
            document.open();
            PdfPTable t = new PdfPTable(5);
            PdfPCell c1 = new PdfPCell(new Phrase("Photo"));
            c1.setBorderColor(BaseColor.RED);
            t.addCell(c1);
            PdfPCell c2 = new PdfPCell(new Phrase("Nom"));
            c2.setBorderColor(BaseColor.RED);
            t.addCell(c2);
            PdfPCell c3 = new PdfPCell(new Phrase("Prix"));
            c3.setBorderColor(BaseColor.RED);
            t.addCell(c3);
            PdfPCell c4 = new PdfPCell(new Phrase("Catégorie"));
            c4.setBorderColor(BaseColor.RED);
            t.addCell(c4);
            PdfPCell c5 = new PdfPCell(new Phrase("Stock"));
            c5.setBorderColor(BaseColor.RED);
            t.addCell(c5);
            t.setHeaderRows(arrayList.size());
          try {
            for(Produit p : arrayList)
            {
                com.itextpdf.text.Image x = com.itextpdf.text.Image.getInstance("C:\\Users\\kenza\\Desktop\\PIDEV\\Suspiro_pdt\\src\\res\\Produit_photo\\" + p.getPhoto());
                x.scaleToFit(100, 100);
                t.addCell(x);
                t.addCell(p.getNom());
                t.addCell(Double.toString(p.getPrix()));
                t.addCell(p.getCategorie());
                t.addCell(p.getStock());
                document.add(t);
            }
            document.close();
            System.out.println("*** PDF créé dans le fichier Produit_photo ***");
        } catch (DocumentException ex) {
            System.out.println(ex); 
        }
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("PDF ");
        alert.setHeaderText(null);
        alert.setContentText("PDF générer avec succès !");
        alert.showAndWait();

  
    }
    
    
    @FXML
   private void consulterCodePromo(ActionEvent event) throws IOException
   {
    FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("CodePromo.fxml"));
            /* 
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
             */
            Scene scene;
            try {
                scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                CodePromoController controller = fxmlLoader.getController();
            //    controller.initData(categorie);
                stage.setTitle("Code Promo ");
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);
            }
   }
         
   }   
   


    

