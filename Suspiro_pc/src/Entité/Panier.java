/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entité;

/**
 *
 * @author MECHICHI Youssef
 */
public class Panier {
    private int id = 0;
    private int quantité = 0;
    private int id_produit ;
    private int id_user;

    public Panier(int id,int id_produit, int id_user,int quantité) {
        this.id = id;
        this.id_produit = id_produit;
        this.id_user = id_user;
        this.quantité = quantité;

    }


    public int getId() {
        return id;
    }

    public int getQuantite() {
        return quantité;
    }

    public int getID_Produit() {
        return id_produit;
    }

    public int getID_User() {
        return id_user;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantite(int qtt ) {
        this.quantité = qtt;
    }

    public void setID_Produit(int prod) {
        this.id_produit = prod;
    }

    public void setID_User(int user) {
        this.id_user = user;
    }

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", quantité=" + quantité + ", ID Produit=" + id_produit + ", ID_User=" + id_user + '}';
    }
  
}
