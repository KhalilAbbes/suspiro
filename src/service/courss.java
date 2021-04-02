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
public interface courss<E>  {
    void ajouter(E var1);

   void supprimer(E var1);

   void modifier(E var1);

   List<E> afficher();
}
   
