/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Suspiro_pdt;

import Entité.Categorie;
import IService.IServiceCategorie;
import Service.ServiceCategorie;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;



public class CategorieController implements Initializable {
    
    @FXML
    private TableView<Categorie> tvCategorie;
    
    @FXML
    private TableColumn<Categorie, String> colCategorie;

    @FXML
    private TextField tfNom;

    @FXML
    private Button btnAjouter;
    
    @FXML
    private Button btnSupprimer;
    
    @FXML
    private Button btnRafraichir;
    
    
    private IServiceCategorie servicecategorie = new ServiceCategorie();
    private  ArrayList<Categorie>  arrayList ;
    public static ObservableList<Categorie> obsl;
    private ObservableList <Categorie>  observableList;
    private Categorie categorie;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AfficherAll();
        // TODO
    }    
    
    
    private void AfficherAll() {
        arrayList = (ArrayList) servicecategorie.getall();
        observableList = FXCollections.observableArrayList(arrayList);
        tvCategorie.setItems(observableList);
        colCategorie.setCellValueFactory(new PropertyValueFactory<Categorie, String>("Nom"));
    }
    
          
    
   @FXML
   private void ajouterCategorie(ActionEvent event) throws IOException
   {
        Categorie categorie = new Categorie();
 
        categorie.setNom(tfNom.getText());
        System.out.println(categorie.toString());
 //       observableList.add(categorie);
//        observableList.notifyAll();
        servicecategorie.add(categorie);
        
   }
   
   @FXML
    private void SupprimerCategorie(ActionEvent event) throws SQLException {
        
        Categorie c = tvCategorie.getSelectionModel().getSelectedItem();
        if (c==null) {
        JOptionPane.showMessageDialog(null,"There is nothing selected !");
        }
        else{
            servicecategorie.delete(c);
        ;}
    }
    
    @FXML
    public void Rafraichir()  {
//         //observableList.remove(0,observableList.size());
         arrayList.clear();
         AfficherAll();
    } 
     
    
//     @FXML
//   private void ModifierCategorie(ActionEvent event) throws IOException
//   {
//       
//        Categorie c = tvCategorie.getSelectionModel().getSelectedItem();
//        if (c==null) {
//        JOptionPane.showMessageDialog(null,"There is nothing selected !");
//        }
//        else{
//            servicecategorie.update(c)
//        ;}
//   }
   
    
    
   }

