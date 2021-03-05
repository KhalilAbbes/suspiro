/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author MECHICHI Youssef
 */
public class DB {
     String url = "jdbc:mysql://localhost:3306/prodal";
     String login = "root";
     String pwd = "";
    public  static DB db;
    public Connection con;
    
    private DB() {
         try {
             con = DriverManager.getConnection(url, login, pwd);
             System.out.println("connexion etablie");
         } catch (SQLException ex) {
             System.out.println(ex);
             System.out.println("ERREUR");
         }
    }
    
    public Connection  getConnection()
    {
    return con;
    }     
    
    public static DB getInstance()
    {if(db==null)
        db=new DB();
    return db;
    }     
    
}
