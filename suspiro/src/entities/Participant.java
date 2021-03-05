/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 *
 * @author khali
 */
public class Participant {
    private int id_part;
    private int id_ench;
    private int id_user;
    private Boolean resultat;

    public Participant(int id_part, int id_ench, int id_user, Boolean resultat) {
        this.id_part = id_part;
        this.id_ench = id_ench;
        this.id_user = id_user;
        this.resultat = resultat;
    }

    public int getId_part() {
        return id_part;
    }

    public int getId_ench() {
        return id_ench;
    }

    public int getId_user() {
        return id_user;
    }

    public Boolean getResultat() {
        return resultat;
    }

    public void setId_part(int id_part) {
        this.id_part = id_part;
    }

    public void setId_ench(int id_ench) {
        this.id_ench = id_ench;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setResultat(Boolean resultat) {
        this.resultat = resultat;
    }

    @Override
    public String toString() {
        return "Participant{" + "id_part=" + id_part + ", id_inch=" + id_ench + ", id_user=" + id_user + ", resultat=" + resultat + '}'; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
