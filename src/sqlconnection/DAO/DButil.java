/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlconnection.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author chris940913
 */
class DButil {
    
    private static final String connectionURL1="jdbc:mysql://localhost:3306/ybbom";

   
        public static Connection getConnection(DBtype dbtype) throws SQLException{
            
            switch(dbtype)
            {
                case MSQLS:
                    return DriverManager.getConnection(connectionURL1,"root","admin");
                default:
                    return null;
            }
            
            
            
        }
        
        
          public static void processException(SQLException e){
              System.err.println("Error msg :"+e.getMessage());
              System.err.println("Error code :"+e.getErrorCode());
              System.err.println("SQL state :"+e.getSQLState());
          }

    
}
