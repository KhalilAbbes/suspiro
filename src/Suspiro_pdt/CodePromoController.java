/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Suspiro_pdt;

import Entité.CodePromo;
import IService.IServiceCodePromo;
import Service.ServiceCodePromo;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author kenza
 */
public class CodePromoController implements Initializable {
    
     @FXML
    private TableView<CodePromo> tvCodePromo;

    @FXML
    private TableColumn<CodePromo, String> colCodePromo;

    @FXML
    private TextField tfCode;

    @FXML
    private Button btnAjouter;

    @FXML
    private Button btnSupprimer;

    @FXML
    private Button btnRafraichir;
    
    
    private IServiceCodePromo servicecodepromo = new ServiceCodePromo();
    private  List<CodePromo>  arrayList ;
    public static ObservableList<CodePromo> obsl;
    private ObservableList <CodePromo>  observableList;
    private CodePromo codepromo;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      

        AfficherAll();
        // TODO
    }    
    
    private void AfficherAll() {
        arrayList = (ArrayList) servicecodepromo.getall();
        observableList = FXCollections.observableArrayList(arrayList);
        tvCodePromo.setItems(observableList);
        colCodePromo.setCellValueFactory(new PropertyValueFactory<CodePromo, String>("Code"));
    }
    
     @FXML
    void Rafraichir(ActionEvent event) {
        //         //observableList.remove(0,observableList.size());
         arrayList.clear();
         AfficherAll();

    }

    @FXML
    void SupprimerCodeP(ActionEvent event) {
        CodePromo c = tvCodePromo.getSelectionModel().getSelectedItem();
        if (c==null) {
        JOptionPane.showMessageDialog(null,"There is nothing selected !");
        }
        else{
            servicecodepromo.delete(c);
        ;}

    }

    @FXML
    void ajouterCode(ActionEvent event) { 
        CodePromo codepromo = new CodePromo();
 
        codepromo.setCode(tfCode.getText());
        System.out.println(codepromo.toString());
 //       observableList.add(categorie);
//        observableList.notifyAll();
        servicecodepromo.add(codepromo);

    }
    
    //     @FXML
//   private void ModifierCodePromo(ActionEvent event) throws IOException
//   {
//       
//        CodePromo c = tvCodePromo.getSelectionModel().getSelectedItem();
//        if (c==null) {
//        JOptionPane.showMessageDialog(null,"There is nothing selected !");
//        }
//        else{
//            servicecodepromo.update(c)
//        ;}
//   }
    
//    
}
