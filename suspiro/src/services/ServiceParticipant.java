/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Enchere;
import entities.Participant;
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
public class ServiceParticipant implements Iservices<Participant>{
        private Connection con;
    private Statement ste;

    public ServiceParticipant() {
        con = DataBase.getInstance().getConnection();

    }
    @Override
    public void ajouter(Participant p) throws SQLException {
        ste = con.createStatement();
        
        String requeteInsert = "INSERT INTO `suspiro`.`participants` (`id_part`, `id_ench`, `id_user`, `resultat`) VALUES ('" 
                + p.getId_part() + "', '" + p.getId_ench() + "', '" + p.getId_user() + "', '" + p.getResultat() +"');";
        ste.executeUpdate(requeteInsert);
    }
    
     @Override
    public void delete(int Id_Supp) throws SQLException {
        PreparedStatement pre = con.prepareStatement("DELETE FROM `suspiro`.`participants` WHERE id_part=" + Id_Supp +"");
        pre.executeUpdate();
    }
    

    @Override
    public void update(int Id_Modif, Participant p) throws SQLException {
        PreparedStatement pre = con.prepareStatement("UPDATE `suspiro`.`participants` SET id_part=?,id_ench=?,id-user=?,resultat=?"
                + "WHERE id_part=" + Id_Modif +"");
        
        
        pre.setInt(1,p.getId_part());
        pre.setInt(2,p.getId_ench());
        pre.setInt(3,p.getId_user());
        pre.setBoolean(4, p.getResultat());

        
        pre.executeUpdate();
       
    }
    

    @Override
    public List<Participant> readAll() throws SQLException {
        List<Participant> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("SELECT * FROM participants");
         while (rs.next()) {                
                   int id_part = rs.getInt("id_part");
                   int id_ench = rs.getInt("id_ench");
                   int id_user = rs.getInt("id_user");
                   Boolean resultat = rs.getBoolean("resultat");
                   Participant p = new Participant(id_part, id_ench, id_user, resultat);
         arr.add(p);
         }
        return arr;
    }
    
 
    @Override
    public void deleteALL() throws SQLException {
        PreparedStatement pre = con.prepareStatement("DELETE FROM `suspiro`.`participants`");
        pre.executeUpdate();
    }
 
    @Override
    public void dropTable() throws SQLException {
        PreparedStatement pre = con.prepareStatement("DROP TABLE `suspiro`.`participants`");
        pre.executeUpdate();
    }
    
}
