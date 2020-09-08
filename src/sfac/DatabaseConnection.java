/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfac;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Faizun
 */
public class DatabaseConnection {
    Connection connection;
    
    public Connection getConnection(){
        
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sfac", "root", "admin");
            
        } catch (SQLException ex) {
        }
        return connection;
    }
}
