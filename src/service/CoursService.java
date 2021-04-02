
package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Cours;
import tools.connexion;


public class CoursService implements courss<Cours> {
     Connection cnx = connexion.getInstance().getCnx();

    @Override
    public void ajouter(Cours var1) {
        try{
        String requete = "INSERT INTO cours (nom,tuteur,id) VALUES (?,?,?)";

            PreparedStatement ps = cnx.prepareStatement(requete);

            ps.setString(1, var1.getNom());
            ps.setString(2, var1.getTuteur());
            ps.setInt(3, var1.getId());
            ps.executeUpdate();
            System.out.println(" cours ajouter ! ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
    }

    @Override
    public void supprimer(Cours var1) {
         try {
            String requete = "DELETE FROM cours WHERE id= " + var1.getId();
            Statement ast = this.cnx.createStatement();
            ast.executeUpdate(requete);
            System.out.println("Cours Supprimée !! ");
        } catch (SQLException var4) {
            System.err.println(var4.getMessage());
        }
    }

    @Override
    public void modifier(Cours var1) {
        try {
            String requete = "UPDATE cours SET nom= ?, tuteur= ? WHERE ( id = ?)";
            PreparedStatement ast = cnx.prepareStatement(requete);
          
            ast.setString(1, var1.getNom());
            ast.setString(2, var1.getTuteur());
            ast.setInt(3, var1.getId());
            ast.executeUpdate();
            System.out.println(" Modifié ");
        } catch (SQLException ex) {
            System.out.println(" erreur de modification! ");
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Cours> afficher() {
         ArrayList list = new ArrayList();

        try {
            String requete = "SELECT * FROM cours ";
            PreparedStatement pst = this.cnx.prepareStatement(requete);
            ResultSet co = pst.executeQuery();

            while (co.next()) {
                list.add(new Cours(co.getString(1), co.getString(2), co.getInt(3)));
            }
        } catch (SQLException var5) {
            System.out.println(var5.getMessage());
        }

        return list;   
    }
       
     public ObservableList<Cours> trier() throws SQLException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
         ObservableList<Cours> ls = FXCollections.observableArrayList();
          Statement st = cnx.createStatement();
        String req = "SELECT * FROM `cours` ORDER BY `cours`.`nom` ASC";
        ResultSet rs = st.executeQuery(req);

        while(rs.next()){
            int id   = rs.getInt("id");
            String nom   = rs.getString("nom");
            String tuteur = rs.getString("tuteur");
            Cours c = new Cours(nom,tuteur,id);
            ls.add(c);
        }
        return ls;
    }
}





     
   

