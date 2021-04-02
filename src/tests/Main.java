/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;
import service.Categorie;
import service.CoursService;
import tools.connexion;
/**
 *
 * @author Asus
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      connexion cn = connexion.getInstance();
      CoursService ps = new CoursService();
        //CoursService ps = new CoursService();
  //  ps.ajouter(new Cours("darbouka","souha",3));
     Categorie ca = new Categorie();
   //ca.ajouter (new models.Cat(1,"clavier",2));
    ca.supprimer (new models.CategorieEntite(1,"mmm","fghj"));
   // Inscrit in = new Inscrit();
//ps.login(new inscrit("salma", "salah", "123456789", "salma@yahoo.com",9, "client"));
        // TODO code application logic here
   // in.add_users(new inscrit("hadia", "salah", "123456888", "hadia@yahoo.com","client"));
 //ps.supprimer(new Cours("darbouka","souha",2));
 // ps.modifier(new Cours("darbouka","souha",3));
        
    }}
