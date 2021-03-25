/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entité;


/**
 *
 * @author kenza
 */
public class Produit {
    private int id = 0;
    private String nom = "";
    private Double prix ;
    private String categorie = "";
    private int id_categorie =0;
    private String photo;
    private String stock;

    public Produit() {
    }

    public Produit(int id, String nom, Double prix, String categorie, String photo, String stock) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.categorie = categorie;
        this.photo = photo;
        this.stock = stock;
    }
    
    public Produit(String nom, Double prix, String categorie, String photo, String stock) {
        this.nom = nom;
        this.prix = prix;
        this.categorie = categorie;
        this.photo = photo;
        this.stock = stock;
    }
    
    public Produit(String nom, Double prix, String categorie, String stock) {
        this.nom = nom;
        this.prix = prix;
        this.categorie = categorie;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Double getPrix() {
        return prix;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getPhoto() {
        return photo;
    }

    public String getStock() {
        return stock;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
    
    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom=" + nom + ", prix=" + prix + ", categorie=" + categorie + ", id_categorie=" + id_categorie + ", photo=" + photo + ", stock=" + stock + '}';
    }



}
