/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.ke.mspace;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
* @author mspace_developer
 */
public class database {
    public static Connection getsqlConnection(){
        Connection connecttosql = null;
        try{
             Class.forName("com.mysql.cj.jdbc.Driver");
            connecttosql = DriverManager.getConnection("jdbc:mysql://localhost:3306/staff", "mysql","mysql123");
        }catch(Exception e){
            e.printStackTrace();
        }
        return connecttosql;
    }
    public static Connection getConnection(){
        Connection conn=null;
            try{
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            conn = DriverManager.getConnection("jdbc:sqlserver://192.168.1.91:1433;databaseName=biometric;encrypt=false", "sa", "Mspace54#");
          
             }catch(Exception e){
            e.printStackTrace();
        }
            return conn;
    }
   
}
