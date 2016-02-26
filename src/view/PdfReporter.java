/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import sqlconnection.beans.Meterial;



public class PdfReporter {

    Document documento = null;
    FileOutputStream fop = null;
    
    List<String> list = new ArrayList<String>();
    List<Meterial> list1 = new ArrayList<Meterial>();
    List<String> headertitel = new ArrayList<String>();
    List<String> litem = new ArrayList<String>();
    List<String> footer = new ArrayList<String>(); 
    List<String> custOrder = new ArrayList<String>();
    List<String> custOrderhed = new ArrayList<String>();
    List<String> litemname = new ArrayList<String>();
    List<String> productmname = new ArrayList<String>();
    List<String> itemqty = new ArrayList<String>();
    List<String> productspec = new ArrayList<String>();
    List<String> productqty = new ArrayList<String>();
    List<String> totalqty = new ArrayList<String>();
    List<String> ordercolor = new ArrayList<String>();
    Set<String> ordercolor1 = new HashSet<String>();
    Set<String> ordercolor2 = new HashSet<String>();
    List<String> headcolor = new ArrayList<String>();
    List<String> colorname1 = new ArrayList<String>();
    List<String> colorname2 = new ArrayList<String>();
    List<String> rawcat = new ArrayList<String>();
    List<String> productcat = new ArrayList<String>();
    Set<String> litem1 = new HashSet<String>();
    Set<String> productcat1 = new HashSet<String>();
    Set<String> rawcat1 = new HashSet<String>();
    TreeMap<String, Meterial> hashmap = new TreeMap<String, Meterial>();
    
    
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";  // MySQL database connection
        String url = "jdbc:mysql://localhost:3306/ybbom";
        String user = "root";
        String pwd = "admin";
       
        int cid = 0;
        String name ="";
        int addcolor1=0;
        int addcolor2=0;
        int addtotal =0;
        int qty =0;
        int prodqty =0;
        int qty1=0;
        int i=0;
        
public void createPDF(File file, String id,String category)  {
        
         Connection conn=null;
         ResultSet resultSet = null;
         String pcode="";

         headertitel.add("Item Name");
         headertitel.add("Color1");
         headertitel.add("Color2");
         headertitel.add("Item Quantity");
        
         custOrderhed.add("Product Code");
         custOrderhed.add("Color1");
         custOrderhed.add("Color2");
         custOrderhed.add("Color3");
         custOrderhed.add("Qty");
   try{
      conn = DriverManager.getConnection(url,user,pwd); 
      String query ="";
      if(category!=null && category.equalsIgnoreCase("All"))
      {
      query="select  multiple_order.ProdCode,multiple_order.Qty,multiple_order.customerId,multiple_order.color1,multiple_order.color2,multiple_order.color3,product_raw.ItemCode,product_raw.quantity,product_raw.Category ,product_raw.Specification ,rawmaterial.ColorD1,rawmaterial.ColorD2 from multiple_order INNER JOIN product_raw ON multiple_order.ProdCode = product_raw.ProdCode Inner join rawmaterial on product_raw.ItemCode = rawmaterial.ItemCode where multiple_order.OrderNo  IN ('"+id+"')";
       }
      else
      {
      query="select  multiple_order.ProdCode,multiple_order.Qty,multiple_order.customerId,multiple_order.color1,multiple_order.color2,multiple_order.color3,product_raw.ItemCode,product_raw.quantity,product_raw.Category ,product_raw.Specification ,rawmaterial.ColorD1,rawmaterial.ColorD2 from multiple_order INNER JOIN product_raw ON multiple_order.ProdCode = product_raw.ProdCode Inner join rawmaterial on product_raw.ItemCode = rawmaterial.ItemCode where multiple_order.OrderNo  IN ('"+id+"') and  product_raw.Category ='"+category+"' ";
       
      }
      Statement sqlStmt = conn.createStatement();
      resultSet = sqlStmt.executeQuery(query);
      
      while(resultSet != null && resultSet.next())
                {
                    
                   String procode= resultSet.getString("ProdCode");
                   String color1= resultSet.getString("color1");
                   String color2= resultSet.getString("color2");
                   String color3= resultSet.getString("color3");
                   String ColorD1= resultSet.getString("ColorD1");
                   String ColorD2= resultSet.getString("ColorD2");
                   String Spec= resultSet.getString("Specification");
                   String tid = resultSet.getString("ItemCode");
                   String prorawcat = resultSet.getString("Category");
                   prodqty =Integer.parseInt(resultSet.getString("Qty"));
                  
                   litem.add(tid);
                   rawcat.add(prorawcat);
                   productcat.add(procode);
   
                   productqty.add(resultSet.getString("Qty"));
                   String itemqty =resultSet.getString("quantity");
                   int tqty=prodqty*Integer.parseInt(itemqty);
                   addtotal +=tqty;
                   
                   String tqty1 = String.valueOf(tqty);
                   totalqty.add(tqty1);
                   cid =  resultSet.getInt("customerId");
                   
                 
                 String cl  =ColorD1;
                 String cl2  =ColorD2;
                                     
                     if(cl.equalsIgnoreCase("1"))
                {
                   cl = color1;
                }
                else if(cl.equalsIgnoreCase("2"))
                {
                    cl = color2;
                }
                else if(cl.equalsIgnoreCase("3"))
                {
                    cl =color3;
                }
                else if(cl.equalsIgnoreCase("0"))
                {
                    cl = "No Color";
                }
                 if(cl2.equalsIgnoreCase("1") )
                {
                   cl2 = color1;
                }
                else if(cl2.equalsIgnoreCase("2"))
                {
                    cl2 = color2;
                }
                else if(cl2.equalsIgnoreCase("3"))
                {
                    cl2 = color3;
                }
                else if(cl2.equalsIgnoreCase("0"))
                {
                   cl2 = "No Color";
                }
                     
                 Meterial mat = new Meterial();
                
                 mat.setItem_Name(tid);
                 mat.setColor1(cl);
                 mat.setColor2(cl2);
                 mat.setQuantity(String.valueOf(tqty));
                 list1.add(mat);
                }
      
for (Meterial mat : list1) {
                         String key=mat.getItem_Name()+mat.getColor1()+mat.getColor2();
                         Meterial newMeterial;
                        
                      if(hashmap.containsKey(key))
                        {
                         newMeterial=hashmap.get(key);
                            
                         mat.setQuantity(String.valueOf(Integer.parseInt(mat.getQuantity())+Integer.parseInt(newMeterial.getQuantity())));
                         mat.setTotal(mat.getQuantity());
                        }
                       
                        hashmap.put(key, mat);
    }

      resultSet = sqlStmt.executeQuery("select * from multiple_order where OrderNo='"+id+"'");
      
      while(resultSet != null && resultSet.next())
                {
                    custOrder.add(resultSet.getString("ProdCode"));
                    custOrder.add(resultSet.getString("Color1"));
                    custOrder.add(resultSet.getString("Color2"));
                    custOrder.add(resultSet.getString("Color3"));
                    custOrder.add(resultSet.getString("Qty"));
                    
                    i +=Integer.parseInt(resultSet.getString("Qty"));
                     
                    ordercolor.add(resultSet.getString("Color1"));
                    ordercolor.add(resultSet.getString("Color2"));
                    ordercolor.add(resultSet.getString("Color3"));
                }
      
      resultSet = sqlStmt.executeQuery("Select Name from customerinfo where id ='"+cid+"'");
      while(resultSet != null && resultSet.next())
                {
                   name = resultSet.getString("Name");
                }

}catch(Exception e)
        {
            e.printStackTrace();
        }
       
    try {
            documento = new Document();
            file.createNewFile();
            fop = new FileOutputStream(file);
            PdfWriter.getInstance(documento, fop);
            documento.open();
            
//Fonts
            Font fontHeader = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            Font fontBody = new Font(Font.FontFamily.COURIER, 12, Font.NORMAL);
            Font title1 = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
    
//Product Report ----------------------------------------  
        
           Paragraph ordate = new Paragraph();
           ordate.add(String.valueOf(new Date()));
           ordate.setSpacingAfter(15);
           documento.add(ordate);
           
           Paragraph orheader = new Paragraph();
           orheader.setFont(title1);
           orheader.add("ORDER REPORT");
           orheader.setAlignment(Element.ALIGN_CENTER);
           orheader.setSpacingAfter(10);
           documento.add(orheader);
           
           Paragraph orcname = new Paragraph();
           orcname.add("Customer Name :");
           orcname.add(name);
           orcname.setSpacingAfter(8);
           documento.add(orcname);
           
           Paragraph orhardware = new Paragraph();
           orhardware.add("Order No :");
           orhardware.add(id);
           orhardware.setSpacingAfter(8);
           documento.add(orhardware);
           
           Paragraph orunderline = new Paragraph();
           orunderline.add("______________________________________________________________________________");
           orunderline.setSpacingAfter(10);
           documento.add(orunderline);
          
           PdfPTable prodorder = new PdfPTable(custOrderhed.size());
           prodorder.setWidthPercentage(100);
            
            for (int j = 0; j < custOrderhed.size(); j++)
            {
                Phrase frase = new Phrase(custOrderhed.get(j), fontHeader);
                PdfPCell cell = new PdfPCell(frase);
                cell.setBackgroundColor(new BaseColor(Color.lightGray.getRGB()));
                prodorder.addCell(cell);
            }
      documento.add(prodorder);
            
            PdfPTable products= new PdfPTable(5);
            products.setWidthPercentage(100);
             
             for (int i = 0; i < custOrder.size(); i++) 
            {
            products.addCell(new Phrase(custOrder.get(i).toString(), fontBody));
            }
            documento.add(products);
            PdfPTable total1 = new PdfPTable(5);
            total1.setWidthPercentage(100);
            for( int m=0;m<1;m++)
            {
            total1.addCell("Total");
            total1.addCell("");
            total1.addCell("");
            total1.addCell("");
            total1.addCell(String.valueOf(i));
        }
            documento.add(total1);
            
            Paragraph orunderline1 = new Paragraph();
            orunderline1.add("______________________________________________________________________________");
            orunderline1.setSpacingBefore(75);
            documento.add(orunderline1);
       
//----------------------Order BY Meterial---------------------------        
            PdfPTable cabetabla = new PdfPTable(headertitel.size());
            cabetabla.setWidthPercentage(100);

            Paragraph date = new Paragraph();
            date.add(String.valueOf(new Date()));
            date.setSpacingAfter(15);
            date.setSpacingBefore(10);
            documento.add(date);
           
           
            Paragraph header = new Paragraph();
            header.setFont(title1);
            header.add("ORDER BY METERIAL REPORT");
            header.setAlignment(Element.ALIGN_CENTER);
            header.setSpacingAfter(10);
            documento.add(header);
           
            Paragraph cname = new Paragraph();
            cname.add("Customer Name :");
            cname.add(name);
            cname.setSpacingAfter(8);
            documento.add(cname);
           
            Paragraph omorderid = new Paragraph();
            omorderid.add("Order No :");
            omorderid.add(id);
            omorderid.setSpacingAfter(8);
            documento.add(omorderid);

            Paragraph underline = new Paragraph();
            underline.add("______________________________________________________________________________");
            underline.setSpacingAfter(10);
            documento.add(underline);
           
            for(String rawcat : rawcat1)
            {
            Paragraph hardware = new Paragraph();
            hardware.add(rawcat);
            hardware.setSpacingAfter(15);
            documento.add(hardware);
            }
          
 //------------order by meterial top two row-----
            
           PdfPTable orderheader = new PdfPTable(4);
           orderheader.setWidthPercentage(100);
            
            for (int j = 0; j < headertitel.size(); j++)
            {
                Phrase frase = new Phrase(headertitel.get(j), fontHeader);
                PdfPCell cell = new PdfPCell(frase);
                cell.setBackgroundColor(new BaseColor(Color.lightGray.getRGB()));
                orderheader.addCell(cell);
            }
        documento.add(orderheader);
      
       
//-------------------------------------------------        
//Tabla for body
        PdfPTable pdfTable= new PdfPTable(4);
        pdfTable.setWidthPercentage(100);
 
        for (String key :hashmap.keySet()) 
            {
                    pdfTable.addCell(hashmap.get(key).getItem_Name());
                    pdfTable.addCell(hashmap.get(key).getColor1());
                    pdfTable.addCell(hashmap.get(key).getColor2());
                    pdfTable.addCell(hashmap.get(key).getQuantity());
            }
        documento.add(pdfTable);
 
         footer.add("Total");
         footer.add("");
         footer.add("");
         footer.add(String.valueOf(addtotal));
         
         PdfPTable total = new PdfPTable(footer.size());
         total.setWidthPercentage(100);
         for (int j = 0; j < footer.size(); j++)
            {
                Phrase frase = new Phrase(footer.get(j), fontBody);
                PdfPCell cell = new PdfPCell(frase);
                total.addCell(cell);
            }
          documento.add(total);
    } catch (Exception e) {
            
        } finally {
            try {
                if(documento!=null && fop!=null){
                documento.close();
                fop.flush();
                fop.close();
                }
            } catch (Exception e) 
            {
                
            }
        }
    }
    
    
  //--------------------------------Curring report method-------------------------------------
public void showCuttingReport(File file, String id,String category)  
        {
         Connection conn=null;
         ResultSet resultSet = null;
        
         headertitel.add("Quantity");
         headertitel.add("Specification");
         headertitel.add("Color1");
         headertitel.add("Color2");
         headertitel.add("Item Quantity");
        
        int increment=0;
        
        String rset = "";
        
   try{
      conn = DriverManager.getConnection(url,user,pwd);
      String query="";
      if(category!=null && category.equalsIgnoreCase("All"))
      {
        query="select multiple_order.ProdCode,multiple_order.Qty,multiple_order.customerId, product_raw.ItemCode,product_raw.quantity, product_raw.Category from multiple_order INNER JOIN product_raw ON multiple_order.ProdCode = product_raw.ProdCode Inner join rawmaterial on product_raw.ItemCode = rawmaterial.ItemCode where multiple_order.OrderNo IN ('"+id+"')";
      }
      else
      {
       query="select multiple_order.ProdCode,multiple_order.Qty,multiple_order.customerId, product_raw.ItemCode,product_raw.quantity, product_raw.Category from multiple_order INNER JOIN product_raw ON multiple_order.ProdCode = product_raw.ProdCode Inner join rawmaterial on product_raw.ItemCode = rawmaterial.ItemCode where multiple_order.OrderNo IN ('"+id+"') and product_raw.Category ='"+category+"'";
      }
      Statement sqlStmt = conn.createStatement();
      resultSet = sqlStmt.executeQuery(query);
      
      while(resultSet != null && resultSet.next())
                {
                   String procode= resultSet.getString("ProdCode");
                   String tid = resultSet.getString("ItemCode");
                   String prorawcat = resultSet.getString("Category");
                   prodqty =Integer.parseInt(resultSet.getString("Qty"));
                  
                   litem.add(tid);
                   rawcat.add(prorawcat);
                   productcat.add(procode);
                   rawcat1.add(prorawcat);
                   productcat1.add(procode);
                   productqty.add(resultSet.getString("Qty"));
                   String itemqty =resultSet.getString(5);
                   int tqty=prodqty*Integer.parseInt(itemqty);
                   String tqty1 = String.valueOf(tqty);
                   totalqty.add(tqty1);
                   cid =  resultSet.getInt("customerId");
            }
    resultSet = sqlStmt.executeQuery("select * from multiple_order where OrderNo='"+id+"'");
      
      while(resultSet != null && resultSet.next())
                {
                    custOrder.add(resultSet.getString("ProdCode"));
                    custOrder.add(resultSet.getString("Color1"));
                    custOrder.add(resultSet.getString("Color2"));
                    custOrder.add(resultSet.getString("Color3"));
                    custOrder.add(resultSet.getString("Qty"));
                    
                    ordercolor.add(resultSet.getString("Color1"));
                    ordercolor.add(resultSet.getString("Color2"));
                    ordercolor.add(resultSet.getString("Color3"));
                }
      
      resultSet = sqlStmt.executeQuery("Select Name from customerinfo where id ='"+cid+"'");
      while(resultSet != null && resultSet.next())
                {
                   name = resultSet.getString("Name");
                }
      
//-------get rawmaterial all data----------------------------------------
       for (int j = 0; j < litem.size(); j++)
                {
 resultSet=sqlStmt.executeQuery("select  multiple_order.Qty, rawmaterial.ItemCode, rawmaterial.ColorD1, rawmaterial.ColorD2, sum(product_raw.quantity), product_raw.ProdCode, product_raw.specification from rawmaterial INNER JOIN product_raw ON rawmaterial.ItemCode = product_raw.ItemCode INNER JOIN multiple_order ON multiple_order.ProdCode = product_raw.ProdCode where rawmaterial.ItemCode IN ('"+litem.get(j)+"') and product_raw.ProdCode in('"+productcat.get(j)+"')and product_raw.Category in('"+rawcat.get(j)+"') group by product_raw.specification,rawmaterial.ColorD1"); 
                    while(resultSet != null && resultSet.next())
                    {
                      litem1.add(resultSet.getString("ItemCode"));
                    }
                  }
                
        //-----------------------------------------------------------------
        }catch(Exception e)
        {
            e.printStackTrace();
        }
       
    try {
            conn = DriverManager.getConnection(url,user,pwd); 

            documento = new Document();
            file.createNewFile();
            fop = new FileOutputStream(file);
            PdfWriter.getInstance(documento, fop);
            documento.open();
            
//Fonts
            Font fontHeader = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
            Font fontBody = new Font(Font.FontFamily.COURIER, 12, Font.NORMAL);
            
            Font title1 = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
            Font title = new Font(Font.FontFamily.TIMES_ROMAN, 14,Font.BOLD );
    
//----------------------Order BY Meterial---------------------------        
           PdfPTable cabetabla = new PdfPTable(headertitel.size());
           cabetabla.setWidthPercentage(100);
            
           Paragraph date = new Paragraph();
           date.add(String.valueOf(new Date()));
           date.setSpacingAfter(15);
           date.setSpacingBefore(10);
           documento.add(date);
           
           
           Paragraph header = new Paragraph();
           header.setFont(title1);
           header.add("CUTTING REPORT");
           header.setAlignment(Element.ALIGN_CENTER);
           header.setSpacingAfter(10);
           documento.add(header);
           
           Paragraph cname = new Paragraph();
           cname.add("Customer Name :");
           cname.add(name);
           cname.setSpacingAfter(8);
           documento.add(cname);
           
           Paragraph omorderid = new Paragraph();
           omorderid.add("Order No :");
           omorderid.add(id);
           omorderid.setSpacingAfter(8);
           documento.add(omorderid);
           
           Paragraph underline = new Paragraph();
           underline.add("______________________________________________________________________________");
           underline.setSpacingAfter(10);
           documento.add(underline);
           for(String cat : rawcat1)
           {
           Paragraph hardware = new Paragraph();
           hardware.setFont(title1);
           hardware.add(cat);
           hardware.setSpacingBefore(15);
           hardware.setSpacingAfter(4);
           
           documento.add(hardware);
  
 //------------order by meterial top two row-----
            for (String iname : litem1)
            {
                Paragraph itemname = new Paragraph();
                itemname.setFont(title);
                itemname.add(iname);
                itemname.setSpacingBefore(8);
                itemname.setSpacingAfter(10);
                
                for (String proname : productcat1)
            {
                String query="";
                         if(category!=null && category.equalsIgnoreCase("All"))
                     { 
//       query="select  multiple_order.Qty,multiple_order.Color1,multiple_order.Color2,multiple_order.Color3,rawmaterial.ItemCode, rawmaterial.ColorD1, rawmaterial.ColorD2, sum(product_raw.quantity), product_raw.ProdCode, product_raw.specification from rawmaterial INNER JOIN product_raw ON rawmaterial.ItemCode = product_raw.ItemCode INNER JOIN multiple_order ON multiple_order.ProdCode = product_raw.ProdCode where rawmaterial.ItemCode IN ('"+iname+"') and product_raw.ProdCode in('"+proname+"')and product_raw.Category in('"+cat+"')and multiple_order.OrderNo='"+id+"'group by multiple_order.Qty,multiple_order.Color1,multiple_order.Color2,multiple_order.Color3, \n" +
//"rawmaterial.ColorD1, rawmaterial.ColorD2, product_raw.specification "; 
         
          query="select  multiple_order.Qty,multiple_order.Color1,multiple_order.Color2,multiple_order.Color3,rawmaterial.ItemCode, rawmaterial.ColorD1, rawmaterial.ColorD2, product_raw.quantity, product_raw.ProdCode, product_raw.specification from rawmaterial INNER JOIN product_raw ON rawmaterial.ItemCode = product_raw.ItemCode INNER JOIN multiple_order ON multiple_order.ProdCode = product_raw.ProdCode where rawmaterial.ItemCode IN ('"+iname+"') and product_raw.ProdCode in('"+proname+"')and product_raw.Category in('"+cat+"')and multiple_order.OrderNo='"+id+"' ";                 
                         
                         
      }
      else
      {
//         System.out.print("'"+proname+"'"+",");
//         System.out.println("");
//         System.out.print(iname+" "+proname);
         
//        query="select  multiple_order.Qty,multiple_order.Color1,multiple_order.Color2,multiple_order.Color3, rawmaterial.ItemCode, rawmaterial.ColorD1, rawmaterial.ColorD2, sum(product_raw.quantity), product_raw.ProdCode, product_raw.specification from rawmaterial INNER JOIN product_raw ON rawmaterial.ItemCode = product_raw.ItemCode INNER JOIN multiple_order ON multiple_order.ProdCode = product_raw.ProdCode where rawmaterial.ItemCode IN ('"+iname+"') and product_raw.ProdCode in('"+proname+"')and product_raw.Category in('"+rawcat.get(0)+"') and multiple_order.OrderNo='"+id+"'group by multiple_order.Color1,multiple_order.Color2,multiple_order.Color3, \n" +
//"rawmaterial.ColorD1, rawmaterial.ColorD2, product_raw.specification "; 
     query="select  multiple_order.Qty,multiple_order.Color1,multiple_order.Color2,multiple_order.Color3, rawmaterial.ItemCode, rawmaterial.ColorD1, rawmaterial.ColorD2, product_raw.quantity, product_raw.ProdCode, product_raw.specification from rawmaterial INNER JOIN product_raw ON rawmaterial.ItemCode = product_raw.ItemCode INNER JOIN multiple_order ON multiple_order.ProdCode = product_raw.ProdCode where rawmaterial.ItemCode IN ('"+iname+"') and product_raw.ProdCode in('"+proname+"')and product_raw.Category in('"+rawcat.get(0)+"') and multiple_order.OrderNo='"+id+"'"; 
      }
      Statement sqlStmt = conn.createStatement();
      resultSet = sqlStmt.executeQuery(query);
      
      rset = resultSet.toString();
      
      while(resultSet != null && resultSet.next())
                {
                    increment++;
                String qty =resultSet.getString(8);
                String Qty = resultSet.getString("Qty");
                String Color1 = resultSet.getString("Color1");
                String Color2 = resultSet.getString("Color2");
                String Color3 = resultSet.getString("Color3");
                
                 qty1 = Integer.parseInt(qty)*Integer.parseInt(Qty);
                 
                 addtotal+= qty1;
                 
                 litemname.add(resultSet.getString("ItemCode"));
                 String cl  ="";
                 String cl2  ="";
                 cl = resultSet.getString("ColorD1");
                 cl2 = resultSet.getString("ColorD2");
     
                 if(cl.equalsIgnoreCase("1"))
                {
                   cl = Color1;
                }
                else if(cl.equalsIgnoreCase("2"))
                {
                    cl = Color2;
                }
                else if(cl.equalsIgnoreCase("3"))
                {
                    cl =Color3;
                }
                else if(cl.equalsIgnoreCase("0"))
                {
                    cl = "No Color";
                }
                 if(cl2.equalsIgnoreCase("1") )
                {
                   cl2 = Color1;
                }
                else if(cl2.equalsIgnoreCase("2"))
                {
                    cl2 = Color2;
                }
                else if(cl2.equalsIgnoreCase("3"))
                {
                    cl2 = Color3;
                }
                else if(cl2.equalsIgnoreCase("0"))
                {
                   cl2 = "No Color";
                }
                
                 Meterial mat = new Meterial();
                
                 mat.setQty(qty);
                 mat.setSpecification(resultSet.getString("specification"));
                 mat.setColor1(cl);
                 mat.setColor2(cl2);
                 mat.setQuantity(String.valueOf(qty1));
                
                 list1.add(mat);
                 
    //                System.out.println("within while ------->"+list1.size());
            }
   }
               
         if(increment>0)
                       {
           documento.add(itemname);   
           PdfPTable orderheader = new PdfPTable(5);
           orderheader.setWidthPercentage(100);
           for (int j = 0; j < headertitel.size(); j++)
            {
                Phrase frase = new Phrase(headertitel.get(j), fontHeader);
                PdfPCell cell = new PdfPCell(frase);
                cell.setBackgroundColor(new BaseColor(Color.lightGray.getRGB()));
                orderheader.addCell(cell);
                documento.add(orderheader);
            }
//-------------------------------------------------        
//Tabla for body
          hashmap.clear();
    //      System.out.println("list size   ----->"+list1.size()+"   "+hashmap.size());
           
      for (Meterial mat : list1) {
          
                        String key=mat.getItem_Name()+mat.getColor1()+mat.getColor2()+mat.getSpecification();
                        Meterial newMeterial ;
                        if(hashmap.containsKey(key))
                        {
                            newMeterial=hashmap.get(key);
                            mat.setQuantity(String.valueOf(Integer.parseInt(mat.getQuantity())+Integer.parseInt(newMeterial.getQuantity())));
                            mat.setQty(String.valueOf(Integer.parseInt(mat.getQty())+Integer.parseInt(newMeterial.getQty())));
                       
                        }
    //                  System.out.println("hashmap A------>"+hashmap.size());
                       
                       hashmap.put(key, mat);
                      
    //                  System.out.println("hashmap B------>"+hashmap.size());  
    }   
           
        PdfPTable pdfTable= new PdfPTable(5);
        pdfTable.setWidthPercentage(100);
       
    //     System.out.println("hashmap --------->>>>>"+hashmap.size());
        
         for (String key :hashmap.keySet()) 
            {
                    pdfTable.addCell(hashmap.get(key).getQty());
                    pdfTable.addCell(hashmap.get(key).getSpecification());
                    pdfTable.addCell(hashmap.get(key).getColor1());
                    pdfTable.addCell(hashmap.get(key).getColor2());
                    pdfTable.addCell(hashmap.get(key).getQuantity());
            }
        documento.add(pdfTable);
    //     System.out.println("footer --------->>>>>"+footer.size());
         footer.add("Total");
         footer.add("");
         footer.add("");
         footer.add("");
         footer.add(String.valueOf(addtotal));
         
         PdfPTable total = new PdfPTable(5);
         total.setWidthPercentage(100);
         
         
         for (int j = 0; j < footer.size(); j++)
            {
                Phrase frase = new Phrase(footer.get(j), fontBody);
                PdfPCell cell = new PdfPCell(frase);
                total.addCell(cell);
            }
         documento.add(total);
         
         Paragraph tm2 = new Paragraph();
         tm2.setFont(title);
         tm2.add("total m2 :");
         tm2.setSpacingAfter(2);
         documento.add(tm2);
         
         
         
         List<String> total_m2 = new ArrayList<String>();
         total_m2.add(" ");
         
         PdfPTable totalm2 = new PdfPTable(1);
         totalm2.setWidthPercentage(100);
         
          for (int j = 0; j < total_m2.size(); j++)
            {
                Phrase frase = new Phrase(total_m2.get(j), fontBody);
                PdfPCell cell = new PdfPCell(frase);
                totalm2.addCell(cell);
            }
         documento.add(totalm2);
           
         list.clear();
         list1.clear();
         footer.clear();
         addtotal = 0;
         increment=0;
        }
      }
   }
 } catch (Exception e) {
            
        } finally {
            try {
                if(documento!=null && fop!=null){
                documento.close();
                fop.flush();
                fop.close();
                }
            } catch (Exception e) 
            {
                
            }
        }

     }  

}