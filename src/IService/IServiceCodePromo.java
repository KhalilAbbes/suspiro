/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;

import Entité.CodePromo;
import java.util.List;

/**
 *
 * @author kenza
 */
public interface IServiceCodePromo {
    
    public void add(CodePromo cp);

    public void delete(CodePromo cp);

    public boolean update(CodePromo cp);
    
    public List<CodePromo> getall();
    
}
