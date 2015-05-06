/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package docsys.inventory;

/**
 *
 * @author Ninad
 */
public class InventoryBean {
    
    private String prodName;
    private String prodQty;
    private String prodType;

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public InventoryBean(String p, String q, String t) 
    {
        this.prodName = p;
        this.prodQty = q;
        this.prodType = t;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdQty() {
        return prodQty;
    }

    public void setProdQty(String prodQty) {
        this.prodQty = prodQty;
    }

    
    
    
}
