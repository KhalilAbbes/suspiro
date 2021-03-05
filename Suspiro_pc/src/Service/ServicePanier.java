/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entité.Panier;
import IService.PanierInterface;
import Util.DB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MECHICHI Youssef
 */
public class ServicePanier implements PanierInterface<Panier>{
    
    private Connection con;
    private Statement ste;

    public ServicePanier() {
        con = DB.getInstance().getConnection();

    }

    @Override
    public void ajouter(Panier p) throws SQLException {
        
        {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `prodal`.`panier` (`id_panier`, `id_produit`, `user`, `quantité`) VALUES ('" 
                + p.getId() + "', '" + p.getID_Produit()+ "', '" + p.getID_User()+ "', '" + p.getQuantite()+"');";
        ste.executeUpdate(requeteInsert);
        }
    }
    
    
    @Override
    public void delete(int Id_Supp) throws SQLException {
        PreparedStatement pre = con.prepareStatement("DELETE FROM `prodal`.`panier` WHERE id_produit=" + Id_Supp +"");
        pre.executeUpdate();
    }
    

    @Override
    public void update_quantite(int qtt,int Id_Modif) throws SQLException {
        PreparedStatement pre = con.prepareStatement("UPDATE `prodal`.`panier` SET quantité=? WHERE id_produit=" + Id_Modif +"");
        pre.setInt(1,qtt);
        pre.executeUpdate();
        

    }
    

    @Override
    public List<Panier> readAll() throws SQLException {
        List<Panier> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("SELECT * FROM panier");
         while (rs.next()) {                
                   int id = rs.getInt("id_panier");
                   int id_prod = rs.getInt("id_produit");
                   int user = rs.getInt("user");
                   int qtt = rs.getInt("quantité");
                   Panier p = new Panier(id,id_prod ,user, qtt);
         arr.add(p);
         }
        return arr;
    }
    
    
    public void deleteALL() throws SQLException {
        PreparedStatement pre = con.prepareStatement("DELETE FROM `prodal`.`panier`");
        pre.executeUpdate();
    }
    
    public void dropTable() throws SQLException {
        PreparedStatement pre = con.prepareStatement("DROP TABLE `prodal`.`panier`");
        pre.executeUpdate();
    }

    
}

    

