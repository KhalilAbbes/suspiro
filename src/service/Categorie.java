/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.CategorieEntite;
import tools.connexion;

/**
 *
 * @author Asus
 */
public class Categorie implements categorieInt<CategorieEntite>{
     Connection cnx = connexion.getInstance().getCnx();
     @Override
    public void ajouter(CategorieEntite var1) {
          try{
        String requete = "INSERT INTO categorie (id,nom,description) VALUES (?,?,?)";

            PreparedStatement ps = cnx.prepareStatement(requete);

            ps.setInt(1, var1.getId());
            ps.setString(2, var1.getNom());
           
            ps.setString(4, var1.getDescription());
            ps.executeUpdate();
            System.out.println(" catégorie ajouter ! ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }

     @Override
    public void supprimer(CategorieEntite var1) {
   try {
            String requete = "DELETE FROM categorie WHERE id= " + var1.getId();
            Statement ast = this.cnx.createStatement();
            ast.executeUpdate(requete);
            System.out.println("Supprimée !! ");
        } catch (SQLException var4) {
            System.err.println(var4.getMessage());
        }
    }

     @Override
    public void modifier(CategorieEntite var1) {
         try {
            String requete = "UPDATE categorie SET nom= ?, cours_id= ? WHERE ( id = ?)";
            PreparedStatement ast = cnx.prepareStatement(requete);
          
            ast.setString(1, var1.getNom());
        
            ast.setInt(3, var1.getId());
            ast.executeUpdate();
            System.out.println(" Modifié ");
        } catch (SQLException ex) {
            System.out.println(" erreur de modification! ");
            System.err.println(ex.getMessage());
        }
    }

     @Override
    public List<CategorieEntite> afficher() {
   ArrayList list = new ArrayList();

        try {
            String requete = "SELECT * FROM categorie ";
            PreparedStatement pst = this.cnx.prepareStatement(requete);
            ResultSet ca = pst.executeQuery();
            while (ca.next()){
            list.add(new CategorieEntite(ca.getInt(1), ca.getString(2),ca.getString(4)));
            
            }

           
        } catch (SQLException var5) {
            System.out.println(var5.getMessage());
        }

        return list;   
    
}
}