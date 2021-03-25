/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entité.CodePromo;
import IService.IServiceCodePromo;
import Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kenza
 */
public class ServiceCodePromo implements IServiceCodePromo{
    
    Connection cn = DataBase.getInstance().getConnection();
    PreparedStatement pt;
    ResultSet rs;
    ArrayList<CodePromo> cp = new ArrayList<>();
    ArrayList<CodePromo> retour = new ArrayList<CodePromo>();
    ArrayList<Integer> Stat = new ArrayList<Integer>();
    

    @Override
    public void add(CodePromo cp) {
        String req = "INSERT INTO `magasin`.`codepromo` (`code`) "
                + "VALUES (?)";
        try {
            pt = cn.prepareStatement(req);
            pt.setString(1, cp.getCode());
            pt.executeUpdate();
            System.out.println("Ajout  établie.");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    public void delete(CodePromo cp) {
        try {
            String req = "DELETE FROM codepromo WHERE id=?";
            pt = cn.prepareStatement(req);
            pt.setInt(1, cp.getId());
            pt.executeUpdate();
            System.out.println("Code Promo  supprimé.");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean update(CodePromo cp) {
        try {
            String req = "UPDATE codepromo SET code=? WHERE id=" + cp.getId();
            pt = cn.prepareStatement(req);
            pt.setString(1, cp.getCode());
            if (pt.executeUpdate() > 0) {
                System.out.println("update avec sucées.");
                return true;
            }

            System.out.println("Code Promo  modifié.");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }

    @Override
    public List<CodePromo> getall() {
        String req = "SELECT id,Code FROM codepromo";
        try {
            pt = cn.prepareStatement(req);
            rs = pt.executeQuery();
            while (rs.next()) {
                CodePromo c = new CodePromo();
                c.setCode(rs.getString(2));
                c.setId(rs.getInt(1));
                cp.add(c);
            }
            System.out.println("Affichage  établie.");
            return cp;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
}
