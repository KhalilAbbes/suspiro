/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author MECHICHI Youssef
 */
public interface PanierInterface<P> {
    void ajouter(P p) throws SQLException;
    void update_quantite(int qtt,int Id_Modif ) throws SQLException;
    void delete(int Id_Supp) throws SQLException;
    List<P> readAll() throws SQLException;
    public void deleteALL() throws SQLException;
    public void dropTable() throws SQLException;
}
