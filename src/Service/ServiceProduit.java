/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entité.Produit;
import IService.IServiceProduit;
import java.util.List;
import java.util.ArrayList;
import Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.ObservableList;

/**
 *
 * @author kenza
 */
public class ServiceProduit implements IServiceProduit{
    
    Connection cn = DataBase.getInstance().getConnection();
    PreparedStatement pt;
    ResultSet rs;
    ArrayList<Produit> pdt = new ArrayList<Produit>();
    ArrayList<Produit> retour = new ArrayList<Produit>();
    ArrayList<Integer> Stat = new ArrayList<Integer>();

    
    @Override
    public void add(Produit p) {
        String req = "INSERT INTO `magasin`.`produit` (`nom`, `prix`, `categorie_id`, `photo`, `stock`) "
                + "VALUES ( ?, ?, ?, ?, ?)";
        try {
            pt = cn.prepareStatement(req);
            pt.setString(1, p.getNom());
            pt.setDouble(2, p.getPrix());
            pt.setInt(3, p.getId_categorie());
            pt.setString(4, p.getPhoto());
            pt.setString(5, p.getStock());
            pt.executeUpdate();
            System.out.println("Ajout  établie.");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public void delete(Produit produit) {
        try {
            String req = "DELETE FROM produit WHERE id=?";
            pt = cn.prepareStatement(req);
            pt.setInt(1, produit.getId());
            pt.executeUpdate();
            System.out.println("Instrument  supprimé.");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean update(Produit p) {
        try {
            String req = "UPDATE produit SET nom=?, prix=?, categorie_id=?, photo=?, stock=? WHERE id=" + p.getId();
            pt = cn.prepareStatement(req);
            pt.setString(1, p.getNom());
            pt.setDouble(2, p.getPrix());
             pt.setInt(3, p.getId_categorie());
            pt.setString(4, p.getPhoto());
            pt.setString(5, p.getStock());
            if (pt.executeUpdate() > 0) {
                System.out.println("update avec sucées.");
                return true;
            }

            System.out.println("Instrument  modifié.");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Produit> getall() {

        String req = "SELECT p.nom, p.prix, p.photo, p.stock, c.nom ,p.id FROM produit p INNER JOIN categorie c ON p.categorie_id = c.id";
        try {
            pt = cn.prepareStatement(req);
            rs = pt.executeQuery();
            while (rs.next()) {
                Produit p = new Produit();
                p.setNom(rs.getString(1));
                p.setPrix(rs.getDouble(2));
               // p.setId_categorie(rs.getInt(3));
                p.setCategorie(rs.getString(5));
                p.setPhoto(rs.getString(3));
                p.setStock(rs.getString(4));
                p.setId(rs.getInt(6));
                pdt.add(p);
            }
            System.out.println("Affichage  établie.");
            return pdt;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
        @Override
    public List<Produit> getallPromo() {

        String req = "SELECT p.nom, p.prix-(p.prix*0.2), p.photo, p.stock, c.nom ,p.id FROM produit p INNER JOIN categorie c ON p.categorie_id = c.id";
        try {
            pt = cn.prepareStatement(req);
            rs = pt.executeQuery();
            while (rs.next()) {
                Produit p = new Produit();
                p.setNom(rs.getString(1));
                p.setPrix(rs.getDouble(2));
               // p.setId_categorie(rs.getInt(3));
                p.setCategorie(rs.getString(5));
                p.setPhoto(rs.getString(3));
                p.setStock(rs.getString(4));
                p.setId(rs.getInt(6));
                pdt.add(p);
            }
            System.out.println("Affichage  établie.");
            return pdt;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    

    @Override
    public List<Produit> trieParPrix(ObservableList<Produit> retour) {
//        retour.removeAll(retour);
//        retour = (ArrayList<Produit>) getall();
        return retour.stream().sorted((a, b) -> a.getPrix().compareTo(b.getPrix())).collect(Collectors.toList());
    }

   
 
    
    
}
