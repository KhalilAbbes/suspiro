/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suspiro;

import entities.Enchere;
import entities.Participant;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import services.ServiceEnchere;
import services.ServiceParticipant;

/**
 *
 * @author khali
 */
public class Suspiro {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ServiceEnchere ser = new ServiceEnchere();
        ServiceParticipant ser1 = new ServiceParticipant();
        
java.util.Date d = new java.util.Date(120,7,11);
       
        
        
        Enchere e1 = new Enchere(8831, "3oud", d, d, d, d, (float) 10.5,(boolean) true, 5);
        Enchere e2 = new Enchere(11, "Jrana", d, d, d, d, (float) 10.5,(boolean) false, 12);
        Enchere e3 = new Enchere(81, "darbouka", d, d, d, d, (float) 10.5,(boolean) true, 69);
        Enchere e4 = new Enchere(91, "biano", d, d, d, d, (float) 10.5,(boolean) true, 101);
        
        Participant p1 = new Participant(999, 14, 77, (boolean) true);
        Participant p2 = new Participant(54, 30, 8, (boolean) false);
        
        try {

                    //ser.ajouter(e1);
                    //ser.ajouter(e2);
                    //ser.ajouter(e3);
                    
                        //ser1.delete(999);
                    //ser1.ajouter(p1);
                    //ser1.update(999, p2);
                
            //List<Participant> list = ser1.readAll();
            //System.out.println(list);
                    
                                //Affichage
            List<Enchere> list = ser.readAll();
            System.out.println(list);

            //Modification
            ser.update(8831, e4);
            
           List<Enchere> list1 = ser.readAll();
            System.out.println(list1);
            
           //Suppression
            ser.delete(1);
            List<Enchere> list3 = ser.readAll();
            System.out.println(list3);

            //ser.deleteALL();
           
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    }

