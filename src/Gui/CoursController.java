

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Cours;
import tools.connexion;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class CoursController implements Initializable {

    @FXML
    private Text TG;
    @FXML
    private Label lbNom;
    @FXML
    private Label lblTuteur;
    private TextField tfId;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfTuteur;
    @FXML
    private TableView<Cours> tvCours;
    
    @FXML
    private TableColumn<Cours, String> colNom;
    @FXML
    private TableColumn<Cours, String> colTuteur;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField txchercher;
 
    @FXML
    private ImageView imageactu;
    @FXML
    private Button imagepub;
     final FileChooser fileChooser = new FileChooser();
    String imagepath = "";
    String imageuser1path = "";
    String fichierpath = "";
    @FXML
    private Button fichier;
 
    @FXML
    private Label namefich;
    @FXML
    private Button suivant;
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
         if(event.getSource() == btnInsert){
            insertRecord();
        }else if (event.getSource() == btnUpdate){
            updateRecord();
        }else if(event.getSource() == btnDelete){
            deleteButton();
     }
        else if (event.getSource()== suivant){
        suivantButton();}
    }
    ObservableList<Cours>dataList ;
     ObservableList<Cours> coursList = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      search_Cours();
      showCours();
     
    } 
      public ObservableList<Cours> getCoursList(){
        ObservableList<Cours> coursList = FXCollections.observableArrayList();
      Connection cnx = connexion.getInstance().getCnx();
        String query = "SELECT * FROM cours";
        Statement st;
        ResultSet rs;
        try{
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            Cours cours;
          while(rs.next()){
             cours = new Cours(rs.getString("nom"), rs.getString("tuteur"),rs.getInt("id"));
             coursList.add(cours);
          }
                
        }catch(Exception ex){
        }
        return coursList;
    }

    private void showCours() {
         ObservableList<Cours> list = getCoursList();
       
        colTuteur.setCellValueFactory(new PropertyValueFactory<>("tuteur"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        
        tvCours.setItems(list);
 
    }

    private void insertRecord() {
       Connection cnx = connexion.getInstance().getCnx();
         try {
            String requete = "INSERT INTO cours(nom,tuteur) VALUES ('" + tfNom.getText() + "','" + tfTuteur.getText() + "')";
          
            Statement ast = cnx.createStatement();
          
           ast.executeUpdate(requete);
           
        } catch (SQLException var4) {
            System.err.println(var4.getMessage());
        }
 search_Cours();
 showCours();
               
    }

    private void updateRecord() {
        String query = "UPDATE  cours SET tuteur ='" + tfTuteur.getText()+ "' WHERE nom = '" + tfNom.getText()+"'";
        executeQuery(query);
        showCours();
    }

    private void deleteButton() {
      String query = "DELETE FROM cours WHERE nom ='" + tfNom.getText() + "'";
        executeQuery(query);
        
       search_Cours();
        showCours();

    }
    
    private void executeQuery(String query) {
        Connection cnx = connexion.getInstance().getCnx();
        Statement st;
        try{
            st = cnx.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            
}
    }
    @FXML
       void search_Cours() {          

        colNom.setCellValueFactory(new PropertyValueFactory<Cours,String>("nom"));
        colTuteur.setCellValueFactory(new PropertyValueFactory<Cours,String>("tuteur"));
         dataList = connexion.getDataList();
        tvCours.setItems(dataList);
        FilteredList<Cours> filteredData = new FilteredList<>(dataList, b -> true);  
 txchercher.textProperty().addListener((observable, oldValue, newValue) -> {
 filteredData.setPredicate(cours -> {
    if (newValue == null || newValue.isEmpty()) {
     return true;
    }    
    String lowerCaseFilter = newValue.toLowerCase();
    
    if (cours.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
     return true; // Filter matches username
    } else if (cours.getTuteur().toLowerCase().indexOf(lowerCaseFilter) != -1) {
     return true; // Filter matches password
   
    }
    else if (String.valueOf(cours.getId()).indexOf(lowerCaseFilter)!=-1)
         return true;// Filter matches email
                                
         else  
          return false; // Does not match.
   });
  });  
  SortedList<Cours> sortedData = new SortedList<>(filteredData);  
  sortedData.comparatorProperty().bind(tvCours.comparatorProperty());  
  tvCours.setItems(sortedData);      
    }
    
  
    // Code Source in description

    @FXML
    private void Openphoto(ActionEvent event) throws MalformedURLException {
               File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            String location = (file.getAbsoluteFile().toURI().toString());
            imagepath = file.getPath();
                 Image image = new Image(file.toURI().toString());
             imageactu.setImage(image); 
    }
        
    }

    @FXML
    private void Openfichier(ActionEvent event) {
  File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            String location = (file.getAbsoluteFile().toURI().toString());
            fichierpath = file.getPath();
            namefich.setText(file.getName()); 
    }
    }

    private void suivantButton() throws IOException {
         suivant.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("Categorie.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show(); }
}


 