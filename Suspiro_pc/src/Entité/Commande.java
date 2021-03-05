/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entité;

import java.sql.Date;
import Entité.Panier;

/**
 *
 * @author MECHICHI Youssef
 */
public class Commande {
    private int id = 0;
    private int id_user;
    private int id_produit;
    private int quantité = 0;
    private float PrixTot;
    private Date date_achat;
    private String etat;
    public Commande(int id, int id_user,int id_produit,int quantité,float PrixTot,Date date_achat,String etat) {  
        this.id=id;
        this.id_user = id_user;
        this.id_produit=id_produit;
        this.quantité = quantité;
        this.PrixTot=PrixTot;
        this.date_achat=date_achat;
        this.etat=etat;

    }

    public Commande( int id_user,int id_produit,int quantité,float PrixTot,Date date_achat,String etat) {  
        this.id_user = id_user;
        this.id_produit=id_produit;
        this.quantité = quantité;
        this.PrixTot=PrixTot;
        this.date_achat=date_achat;
        this.etat=etat;

    }
    public String get_Etat()
    {
        return etat;
    }
    
    public void set_Etat(String etat)
    {
        this.etat=etat;
    }
     public int getID_Produit() {
        return id_produit;
    }
    public int getId() {
        return id;
    }

    public int getQuantite() {
        return quantité;
    }



    public int getID_User() {
        return id_user;
    }
    
    public float getPrix_Tot() {
        return PrixTot;
    }
    
     public Date getDate_Achat() {
     return date_achat;
    }



    public void setQuantite(int qtt ) {
        this.quantité = qtt;
    }

    public void setID_Produit(int produit) {
        this.id_produit = produit;
    }

    public void setID_User(int user) {
        this.id_user = user;
    }
    
    public void setDate_Achat(Date d)
    {
            this.date_achat=d;
    }
    
    public void setPrix_Tot(float p)
    {
        this.PrixTot=p;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id +",id_produit="+id_produit+ ", quantité=" + quantité + ", ID_User=" + id_user + ",PrixTotal="+PrixTot+",Date d'achat"+date_achat+"}'";
    }
}
