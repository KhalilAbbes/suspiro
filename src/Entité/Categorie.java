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
public class Categorie {
    
    private int id = 0;
    private String nom = "";

    public Categorie() {
    }
    
    public Categorie(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    
    public Categorie( String nom) {
        this.nom = nom;
    }
    
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", nom=" + nom + '}';
    }
    
}
