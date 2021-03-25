/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entité;

/**
 *
 * @author kenza
 */
public class CodePromo {
    
    private int id = 0;
    private String code = "";

    public CodePromo() {
    }
    
    public CodePromo(int id, String code) {
        this.id = id;
        this.code = code;
    }
    
    public CodePromo( String code) {
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
}
