/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author khali
 */
public class Enchere {
    private int id_ench;
    private String label_prod;
    private Date deb_inscri;
    private Date fin_inscri;
    private Date deb_vente;
    private Date fin_vente;
    private float prix;
    private Boolean etat;
    private int id_parti;

    public Enchere(int id_ench, String label_prod, Date deb_inscri, Date fin_inscri, Date deb_vente, Date fin_vente, float prix, Boolean etat, int id_parti) {
        this.id_ench = id_ench;
        this.label_prod = label_prod;
        this.deb_inscri = deb_inscri;
        this.fin_inscri = fin_inscri;
        this.deb_vente = deb_vente;
        this.fin_vente = fin_vente;
        this.prix = prix;
        this.etat = etat;
        this.id_parti = id_parti;
    }

    public int getId_ench() {
        return id_ench;
    }

    public String getLabel_prod() {
        return label_prod;
    }

    public Date getDeb_inscri() {
        return deb_inscri;
    }

    public Date getFin_inscri() {
        return fin_inscri;
    }

    public Date getDeb_vente() {
        return deb_vente;
    }

    public Date getFin_vente() {
        return fin_vente;
    }

    public float getPrix() {
        return prix;
    }

    public Boolean getEtat() {
        return etat;
    }

    public int getId_parti() {
        return id_parti;
    }

    public void setId_ench(int id_ench) {
        this.id_ench = id_ench;
    }

    public void setLabel_prod(String label_prod) {
        this.label_prod = label_prod;
    }

    public void setDeb_inscri(Date deb_inscri) {
        this.deb_inscri = deb_inscri;
    }

    public void setFin_inscri(Date fin_inscri) {
        this.fin_inscri = fin_inscri;
    }

    public void setDeb_vente(Date deb_vente) {
        this.deb_vente = deb_vente;
    }

    public void setFin_vente(Date fin_vente) {
        this.fin_vente = fin_vente;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    
    public void setEtat(Boolean etat) {
        this.etat = etat;
    }

    public void setId_parti(int id_parti) {
        this.id_parti = id_parti;
    }

    @Override
    public String toString() {
        return "Enchere{" + "id_ench=" + id_ench + ", label_prod=" + label_prod + ", deb_inscri=" + deb_inscri + ", deb_vente=" + deb_vente + ", fin_vente=" + fin_vente + ", prix=" + prix + ", etat=" + etat + ", id_parti=" + id_parti + '}'; //To change body of generated methods, choose Tools | Templates.
    }

    

    
    
    
}

