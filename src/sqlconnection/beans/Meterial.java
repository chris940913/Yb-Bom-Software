package sqlconnection.beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Prakash <Prakash>
 */
public class Meterial 
{
   private String  item_Name ;
   private String  color1;
   private String  color2;
   private String quantity;
   private String  total;
   private String specification;
   private String qty;

   public Meterial(){}
   
    public Meterial(String item_Name, String color1, String color2, String quantity, String total) {
        this.item_Name = item_Name;
        this.color1 = color1;
        this.color2 = color2;
        this.quantity = quantity;
        this.total = total;
    }
    
  
   public String getItem_Name() {
        return item_Name;
    }

    public void setItem_Name(String item_Name) {
        this.item_Name = item_Name;
    }

    public String getColor1() {
        return color1;
    }

    public void setColor1(String color1) {
        this.color1 = color1;
    }

    public String getColor2() {
        return color2;
    }

    public void setColor2(String color2) {
        this.color2 = color2;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
    
    
    
    
}
