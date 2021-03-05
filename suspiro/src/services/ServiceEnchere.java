/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Enchere;
import iservices.Iservices;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DataBase;

/**
 *
 * @author khali
 */
public class ServiceEnchere implements Iservices<Enchere>{
    
    private Connection con;
    private Statement ste;

    public ServiceEnchere() {
        con = DataBase.getInstance().getConnection();

    }
    @Override
    public void ajouter(Enchere e) throws SQLException {
        ste = con.createStatement();
        
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d1 = sdf.format(e.getDeb_inscri());
        String d2 = sdf.format(e.getFin_inscri());
        String d3 = sdf.format(e.getDeb_vente());
        String d4 = sdf.format(e.getFin_vente());
        String requeteInsert = "INSERT INTO `suspiro`.`encheres` (`id_ench`, `label_prod`, `deb_inscri`, `fin_inscri`, `deb_vente`, `fin_vente`, `prix`, `etat`, `id_part`) VALUES ('" 
                + e.getId_ench() + "', '" + e.getLabel_prod() + "', '" + d1 + "', '" + d2 +"', '" + d3 +"', '" + d4 +"', '" + e.getPrix() +"', '" + e.getEtat() +"', '" + e.getId_parti() +"');";
        ste.executeUpdate(requeteInsert);
    }
    
     @Override
    public void delete(int Id_Supp) throws SQLException {
        PreparedStatement pre = con.prepareStatement("DELETE FROM `suspiro`.`encheres` WHERE id_ench=" + Id_Supp +"");
        pre.executeUpdate();
    }
    

    @Override
    public void update(int Id_Modif, Enchere e) throws SQLException {
        PreparedStatement pre = con.prepareStatement("UPDATE `suspiro`.`encheres` SET id_ench=?,label_prod=?,deb_inscri=?,fin_inscri=?,deb_vente=?,fin_vente=?,prix=?,etat=?,id_part=? "
                + "WHERE id_ench=" + Id_Modif +"");
        
        java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d1 = sdf.format(e.getDeb_inscri());
        String d2 = sdf.format(e.getFin_inscri());
        String d3 = sdf.format(e.getDeb_vente());
        String d4 = sdf.format(e.getFin_vente());
        
        pre.setInt(1,e.getId_ench());
        pre.setString(2, e.getLabel_prod());
        pre.setString(3, d1);
        pre.setString(4, d2);
        pre.setString(5, d3);
        pre.setString(6, d4);
        pre.setFloat(7, e.getPrix());
        pre.setBoolean(8, e.getEtat());
        pre.setInt(9, e.getId_parti());
        
        pre.executeUpdate();
       
    }
    

    @Override
    public List<Enchere> readAll() throws SQLException {
        List<Enchere> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("SELECT * FROM encheres");
         while (rs.next()) {                
                   int id_ench = rs.getInt("id_ench");
                   String label_prod = rs.getString("label_prod");
                   Date deb_inscri = rs.getDate("deb_inscri");
                   Date fin_inscri = rs.getDate("fin_inscri");
                   Date deb_vente = rs.getDate("deb_vente");
                   Date fin_vente = rs.getDate("fin_vente");
                   Float prix = rs.getFloat("prix");
                   Boolean etat = rs.getBoolean("etat");
                   int id_part = rs.getInt("id_part");
                   Enchere e = new Enchere(id_ench, label_prod, deb_inscri, fin_inscri, deb_vente, fin_vente, prix, etat, id_part);
         arr.add(e);
         }
        return arr;
    }
    
 
    @Override
    public void deleteALL() throws SQLException {
        PreparedStatement pre = con.prepareStatement("DELETE FROM `suspiro`.`encheres`");
        pre.executeUpdate();
    }
 
    @Override
    public void dropTable() throws SQLException {
        PreparedStatement pre = con.prepareStatement("DROP TABLE `suspiro`.`encheres`");
        pre.executeUpdate();
    }
 
}
