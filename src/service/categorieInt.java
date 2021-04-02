/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;

/**
 *
 * @author Asus
 */
public interface categorieInt <M> {
    void ajouter(M var1);

   void supprimer(M var1);

   void modifier(M var1);

   List<M> afficher();
}
