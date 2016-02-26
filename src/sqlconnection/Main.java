/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlconnection;

import java.sql.*;
import java.sql.SQLException;
import java.util.Scanner;
import sqlconnection.DAO.OrderDAO;
import sqlconnection.beans.Order;
import sqlconnection.util.Input;
import view.Admin;

/**
 *
 * @author chris940913
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException, Exception {

            Admin admin  = new Admin();
            admin.main(args);
            

            Scanner sc = new Scanner(System.in);
            boolean quit = false;

           
    
    }

}
