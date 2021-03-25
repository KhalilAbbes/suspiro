/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Entité.Produit;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author kenza
 */
public interface IServiceProduit {
    
    public void add(Produit p);

    public void delete(Produit p);

    public boolean update(Produit p);
    
    public List<Produit> getall();
    
    public List<Produit> getallPromo();
    
    public List<Produit> trieParPrix( ObservableList<Produit> retour);



}
