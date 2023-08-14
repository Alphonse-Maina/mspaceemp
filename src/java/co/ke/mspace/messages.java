/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.ke.mspace;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import sms.api.client.v1.Client;
import sms.api.client.v1.Response;

/**
 *
 * @author mspace_developer
 */
@ManagedBean
public class messages {
    
   public messages(){} 
   @ManagedProperty(value = "#{employee}", name = "employee")

    public Employee Employee;
   private List<Employee> messages;
   @PostConstruct
   public void init(){
       messages = new ArrayList<>();
        try {
            Connection conn = database.getsqlConnection();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("select * from messages");
            while (result.next()) {
                messages.add(new Employee(result.getString("Recipient"), result.getString("Message"), result.getString("Time")));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
   }
   
    public String sendmessages(){
        String home = "messages.xhtml";
        
        String username="intern";
        String password="intern";
        String senderId="Mspace";
        //String recipient = "0790350480";
        String recipient= Employee.getPAGER();
        String message=Employee.getMessage();
        LocalDateTime sendtime =  LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");  
          String formatDateTime = sendtime.format(format);  
//        Response response = Client.sendTextOne(username, password, senderId, recipient, message);
//        System.out.println(response.getStatus()); 
//        System.out.println(response.getBody());          
//        if(response.getStatus()==200){
//            System.out.println("Message sent successfully at"+sendtime.toString());
//        }
        try{
        Connection conn = database.getsqlConnection();
        Statement stmt = conn.createStatement();
        String sql ="insert into messages values('"+recipient+"','"+message+"','"+sendtime+"')";
        stmt.execute(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
        return home;
    }
    public void print(){
        System.out.println(Employee.getPAGER());
        System.out.println(Employee.getMessage());
    }
    public Employee getEmployee() {
        return Employee;
    }

    public void setEmployee(Employee Employee) {
        this.Employee = Employee;
    }

    public List<Employee> getMessages() {
        return messages;
    }

    public void setMessages(List<Employee> messages) {
        this.messages = messages;
    }
    
}
