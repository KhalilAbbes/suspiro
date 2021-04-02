/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Cours;

/**
 *
 * @author Asus
 */
public class connexion {
    public String url = "jdbc:mysql://localhost:3306/suspiro";
   public String login = "root";
   public String pwd = "";
   public Connection cn;
   public static connexion instance;

   public connexion() {
      try {
         this.cn = DriverManager.getConnection(this.url, this.login, this.pwd);
         System.out.println("Connection etablit");
      } catch (SQLException var2) {
         System.out.println("Erreur de connection");
         System.out.println(var2.getMessage());
      }

   }

   public static connexion getInstance() {
      if (instance == null) {
         instance = new connexion();
      }

      return instance;
   }

   public Connection getCnx() {
      return this.cn;
   }
      public static ObservableList<Cours> getDataList(){
        Connection cnx =  connexion.getInstance().getCnx();
        ObservableList<Cours> list = FXCollections.observableArrayList();
        try {
            PreparedStatement ps = cnx.prepareStatement("select * from cours");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){ 
                  list.add(new Cours(rs.getString("nom"), rs.getString("tuteur"),Integer.parseInt(rs.getString("id"))));               
            
                         
            }
        } catch (Exception e) {
        }
        return list;
    }
    
}





