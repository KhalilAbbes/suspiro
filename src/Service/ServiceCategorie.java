/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entité.Categorie;
import IService.IServiceCategorie;
import Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kenza
 */
public class ServiceCategorie implements IServiceCategorie{
    
    
    Connection cn = DataBase.getInstance().getConnection();
    PreparedStatement pt;
    ResultSet rs;
    ArrayList<Categorie> cat = new ArrayList<>();
    ArrayList<Categorie> retour = new ArrayList<Categorie>();
    ArrayList<Integer> Stat = new ArrayList<Integer>();

    
    @Override
    public void add(Categorie cat) {
        String req = "INSERT INTO `magasin`.`categorie` (`Nom`) "
                + "VALUES (?)";
        try {
            pt = cn.prepareStatement(req);
            pt.setString(1, cat.getNom());
            pt.executeUpdate();
            System.out.println("Ajout  établie.");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @Override
    public void delete(Categorie categorie) {
        try {
            String req = "DELETE FROM categorie WHERE id=?";
            pt = cn.prepareStatement(req);
            pt.setInt(1, categorie.getId());
            pt.executeUpdate();
            System.out.println("Catégorie  supprimée.");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public boolean update(Categorie c) {
        try {
            String req = "UPDATE categorie SET nom=? WHERE id=" + c.getId();
            pt = cn.prepareStatement(req);
            pt.setString(1, c.getNom());
            if (pt.executeUpdate() > 0) {
                System.out.println("update avec sucées.");
                return true;
            }

            System.out.println("Catégorie  modifiée.");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Categorie> getall() {
        String req = "SELECT id,Nom FROM categorie";
        try {
            pt = cn.prepareStatement(req);
            rs = pt.executeQuery();
            while (rs.next()) {
                Categorie c = new Categorie();
                c.setNom(rs.getString(2));
                c.setId(rs.getInt(1));
                cat.add(c);
            }
            System.out.println("Affichage  établie.");
            return cat;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
