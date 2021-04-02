/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.CategorieEntite;
import tools.connexion;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class CategorieController implements Initializable {

  
    @FXML
    private TableView<CategorieEntite> tableCategorie;
    @FXML
    private TableColumn<CategorieEntite, String> nom;
    @FXML
    private TableColumn<CategorieEntite, String> description;
    @FXML
    private Button buttonSupprimer;
    @FXML
    private Button buttonModifier;
    @FXML
    private Button buttonAjouter;
    @FXML
    private TextField tfNom;
    @FXML
    private TextArea txtDes;
    @FXML
    private Button retour;
    @FXML
       private void handleButtonAction(ActionEvent event) throws IOException {
         if(event.getSource() == buttonAjouter){
            AjouterCategorie();
        }else if (event.getSource() == buttonModifier){
            ModifierCategorie();
        }else if(event.getSource() == buttonSupprimer){
            SupprimerCategorie();
     }
         else if (event.getSource() == retour ){
           retour();
    }}
 ObservableList<CategorieEntite> categorieList = FXCollections.observableArrayList();
 @Override
    public void initialize(URL location, ResourceBundle resources) {
           showCategorie();
    }
        public ObservableList<CategorieEntite> getCategorieList(){
  ObservableList<CategorieEntite> categorieList = FXCollections.observableArrayList();
      Connection cnx = connexion.getInstance().getCnx();
        String query = "SELECT * FROM categorie";
        Statement st;
        ResultSet rs;
        try{
            st = cnx.createStatement();
            rs = st.executeQuery(query);
            CategorieEntite categorie;
          while(rs.next()){
               categorie = new CategorieEntite(rs.getInt("id"),rs.getString("nom"), rs.getString("description"));
                categorieList.add(categorie);
          }
                
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return categorieList;
    }
         private void showCategorie() {
      ObservableList<CategorieEntite> categorielist = getCategorieList();
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
         tableCategorie.setItems(categorielist);
         }

   
    private void AjouterCategorie() {
    Connection cnx = connexion.getInstance().getCnx();
         try {
            String requete = "INSERT INTO categorie(nom,description) VALUES ('" + tfNom.getText() + "','" +txtDes.getText() + "')";
          
            Statement ast = cnx.createStatement();
          
           ast.executeUpdate(requete);
           
        } catch (SQLException var4) {
            System.err.println(var4.getMessage());
        }

        showCategorie();    
    }

    private void ModifierCategorie() {
        String query = "UPDATE categorie SET description ='" + txtDes.getText()+ "' WHERE nom = '" + tfNom.getText()+"'";
        executeQuery(query);
        showCategorie(); 
    
    }

    private void SupprimerCategorie() {
             String query = "DELETE FROM categorie WHERE nom ='" + tfNom.getText() + "'";
        executeQuery(query);
        
      
        showCategorie();
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

    private void retour() throws IOException {
          retour.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("cours.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    
    
    } }
    

