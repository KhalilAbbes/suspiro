/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entité.Commande;
import Entité.Panier;
import IService.CommandeInterface;
import Util.DB;
import java.sql.Connection;
import java.sql.Date;
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
public class ServiceCommande implements CommandeInterface<Commande> {
     private Connection con;
    private Statement ste;

    public ServiceCommande() {
        con = DB.getInstance().getConnection();

    }     
        
    @Override
    public void ajouter(Commande c) throws SQLException {
        
        {
            java.util.Date dt = new java.util.Date();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d1 = sdf.format(c.getDate_Achat());
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `prodal`.`commande` (`id_commande`, `id_user`, `id_produit`, `quantité`,`prix_total`,`date_achat`,`état`) VALUES ('" 
                + c.getId() + "', '" + c.getID_User()+ "', '" + c.getID_Produit()+ "', '" + c.getQuantite()+"', '" + c.getPrix_Tot()+"', '" + c.getDate_Achat()+"', '" + c.get_Etat()+"');";
        ste.executeUpdate(requeteInsert);
        }
    }

    
    @Override
    public void delete(int Id_Supp) throws SQLException {
        PreparedStatement pre = con.prepareStatement("DELETE FROM `prodal`.`commande` WHERE id_commande=" + Id_Supp +"");
        pre.executeUpdate();
    }
    

    @Override
    public void update_Etat(int Id_Modif) throws SQLException {
        PreparedStatement pre = con.prepareStatement("UPDATE `prodal`.`commande` SET état=? WHERE id_commande=" + Id_Modif +"");
        pre.setString(1,"Validée");
        pre.executeUpdate();
        

    }

    @Override
    public List<Commande> readAll() throws SQLException {
        List<Commande> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("SELECT * FROM commande");
         while (rs.next()) {                
                   int id = rs.getInt("id_commande");
                   int id_user = rs.getInt("id_user");
                   int id_produit = rs.getInt("id_produit");
                   float prix_total = rs.getFloat("prix_total");
                   int qtt = rs.getInt("quantité");
                   Date date=rs.getDate("date_achat");
                   String bool=rs.getString("état");
                   Commande c= new Commande(id,id_user,id_produit,qtt,prix_total,date,bool );
         arr.add(c);
         }
        return arr;
    }
    public void deleteALL() throws SQLException {
        PreparedStatement pre = con.prepareStatement("DELETE FROM `prodal`.`commande`");
        pre.executeUpdate();
    }
    
    public void dropTable() throws SQLException {
        PreparedStatement pre = con.prepareStatement("DROP TABLE `prodal`.`commande`");
        pre.executeUpdate();
    
}
}
