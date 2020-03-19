/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Admin
 */
public class Company {
    public int id;
    public String name;
    public String password;
    public String Email;
    public boolean isverified;
    
    public Company(String name,String password,String Email){
        this.name=name;
        this.password=password;
        this.Email=Email;
    }
    
    public Company(){
        
    }
    
    public void setIsverified(boolean cond){
        this.isverified=cond;
        String connection="jdbc:mysql://localhost:3306/oose";
        Dbcontext db = new Dbcontext(connection);
        try{
            PreparedStatement stmt=db.con.prepareStatement("inser into company values (?,?,?)");  
            stmt.setString(1,this.name);//1 specifies the first parameter in the query  
            stmt.setString(2,this.password);
            stmt.setString(3, this.Email);

            int i=stmt.executeUpdate();
            ResultSet rs= stmt.getGeneratedKeys();
            if(rs.next()){
                this.id = rs.getInt(1);
            }
            System.out.println(i+" records inserted"); 
            
            System.out.println(this);
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return Email;
    }
    
    public void setEmail(String Email) {
        this.Email = Email;
    }

    @Override
    public String toString() {
        return "Company{" + "id=" + id + ", name=" + name + ", password=" + password + ", document=" + Email + ", isverified=" + isverified + '}';
    }
    
}
