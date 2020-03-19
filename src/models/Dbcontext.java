/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Admin
 */
public class Dbcontext {
    
    String connection;
    public Connection con;
    
    public Dbcontext(String connection){
        this.connection=connection;
        try{
            Class.forName("com.mysql.jdbc.Driver");  
            Connection con=(Connection) DriverManager.getConnection(connection,"root","");  
            this.con=con;
        }
        catch(Exception e){
            System.out.println(e);
        }
         
    }
    
}
