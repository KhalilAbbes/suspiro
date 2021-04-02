/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Asus
 */
public class Cours {
    private int id;
    private String tuteur;
    private String nom ;

    public Cours( String nom ,String tuteur, int id ) {
        this.id = id;
        this.tuteur = tuteur;
        this.nom = nom;
    }


    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTuteur() {
        return tuteur;
    }

    public void setTuteur(String tuteur) {
        this.tuteur = tuteur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "cours{" + "id=" + id + ", tuteur=" + tuteur + ", nom=" + nom + '}';
    }
    
    
}
