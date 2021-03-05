/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suspiro_pc;

import Entité.Commande;
import Entité.Panier;
import Service.ServiceCommande;
import Service.ServicePanier;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author MECHICHI Youssef
 */
public class Suspiro_pc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        ServicePanier ser = new ServicePanier();
        ServiceCommande serc=new ServiceCommande();
        //(int id, int id_user,int id_produit,int quantité,float PrixTot,Date date_achat,boolean etat)
        Date d=new Date(120, 4, 17);
        Commande c1=new Commande(1717,11111,5, (float) 24.2, d,"En cours");
      //    Panier p1 = new Panier(1, 12 , 1717, 3);
       // Panier p2 = new Panier(2, 13 , 1717, 5);
        
          // Ajout
          //serc.ajouter(c1);
         //   ser.ajouter(p1);
           // ser.ajouter(p2);
//            ser.ajouter1(p4);
//
//            //Affichage
          //    List<Commande> list=serc.readAll();
            //  System.out.println(list);
           // List<Panier> list = ser.readAll();
           // System.out.println(list);
//            //Modification
       //   serc.update_Etat(1);
           // ser.update(2,13);
      serc.delete(1);
//            List<Produit> list1 = ser.readAll();
//            System.out.println(list1);
//            //Suppression
             //ser.delete(13);
//            List<Produit> list3 = ser.readAll();
//            System.out.println(list3);
//            ser.deleteALL();
//            ser.dropTable();
        
    }
    
}
