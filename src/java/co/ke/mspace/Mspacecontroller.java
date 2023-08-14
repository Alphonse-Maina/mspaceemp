/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.ke.mspace;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
//import org.primefaces.event.RowEditEvent;

/**
 *
 * @author mspace_developer
 */
@RequestScoped
@ManagedBean
public class Mspacecontroller {
    
    public Mspacecontroller(){}
    private List<Employee> employees;
    private List<Employee> employeess;
    private List<Employee> staffs;
    @PostConstruct
    public void postconstruct(){
        init();
        init1();
        init3();
        init4();
        
    }
    public void init() {
        employees = new ArrayList<>();
        try {
            Connection conn = database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery("select * from USERINFO WHERE PAGER <> ''");
            while (result.next()) {
                employees.add(new Employee( result.getString("Badgenumber"),result.getString("Name"),result.getString("Gender"),result.getString("PAGER")));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void init1(){
    employeess = new ArrayList<>();
        try {
            String name = null;
            Connection conn = database.getConnection();
             Connection connect = database.getConnection();
            Statement stmt = conn.createStatement();
            Statement stm = connect.createStatement();
            ResultSet rs = stmt.executeQuery("select * from CHECKINOUT ORDER BY CHECKTIME DESC");
            while (rs.next()) {
//                ResultSet nme = stm.executeQuery("select Name from USERINFO where USERID = "+rs.getString("USERID"));
//                while (nme.next()){
//                    name = nme.getString("Name");
                 employeess.add(new Employee(rs.getInt("USERID"),name, rs.getString("CHECKTIME"), rs.getInt("LOGID"), rs.getString("sn")));
//                
//                }
//                
               }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void init3(){
        staffs = new ArrayList<>();
        try {
            String userType = null;
            Connection connecttosql = database.getsqlConnection();
            Statement statement = connecttosql.createStatement();
            ResultSet res = statement.executeQuery("select * from staff");
            while (res.next()){
                if(res.getInt("userType")==0){
                    userType = "Staff";
                }else{
                    userType = "Admin";
                }
               
                staffs.add(new Employee(res.getInt("ID"),res.getString("UserName"), res.getString("Email"),res.getString("phone"),userType));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Mspacecontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    private LocalDateTime dateTime;
    
    public void init4() {
        dateTime = LocalDateTime.now().plusYears(37).plusMonths(3).plusHours(4);
    }
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Employee> getEmployeess() {
        return employeess;
    }

    public void setEmployeess(List<Employee> employeess) {
        this.employeess = employeess;
    }

    public List<Employee> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<Employee> staffs) {
        this.staffs = staffs;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    
    @ManagedProperty(value="#{employee}",name = "employee")
    public Employee staff;
    public Employee Employee;

    public Employee getStaff() {
        return staff;
    }

    public void setStaff(Employee staff) {
        this.staff = staff;
    } 
    
    public Employee getEmployee() {
        return Employee;
    }

    public void setEmployee(Employee Employee) {
        this.Employee = Employee;
    }
//    LocalTime myObj = LocalTime.now();
    public String login(){
        String home = null;
        String username = Employee.getUsername();
        String Password = Employee.getPassword();
       try {
            Connection connecttosql = database.getsqlConnection();
            Statement stmt = connecttosql.createStatement();
            ResultSet rs = stmt.executeQuery("select * from staff where UserName = '"+username+"' AND Password = '"+Password+"'");
            if (rs.next()) {
                if(rs.getInt("UserType")==1){
                    home = "admin/dashboard.xhtml";
                    System.out.println("Log: Admin " + username + " Logged in");
                }else{
                    home = "staffm/dashboard.xhtml";
                    System.out.println("Log: " + username + " Logged in");
                }
                } else {
                    showError();
                    home = "index.xhtml";
                    System.out.println("Log: " + username + " tried to log in");
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
       return home;
    }
    public String updateStaff(int id, String UserName,String email, String phone,String utype){
        String home = "stafflist.xhtml";
            int usertype;
        try {
            Connection conn = database.getConnection();
            Statement stmt = conn.createStatement();
            if((utype == "Admin")||(utype == "ADMIN")||(utype == "admin")){
                usertype = 1;
            }else {
                usertype = 0;
            }
            String query= "update staff set UserName = '"+UserName+"',Email = '"+email+"',Phone = '"+phone+"',UserType="+usertype+" where ID = "+id;
            stmt.execute(query);
        postconstruct();
        } catch (SQLException ex) {
            Logger.getLogger(Mspacecontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return home;
    }
    public void onRowEdit(RowEditEvent event) {
       System.out.println("Editting");
        FacesMessage msg = new FacesMessage("Employee Edited", String.valueOf(((Employee)event.getObject()).getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
        int id=((Employee)event.getObject()).getId();
        String UserName = ((Employee)event.getObject()).getUsername();
        String email = ((Employee)event.getObject()).getEmail();
        String phone = ((Employee)event.getObject()).getPhone();
        String utype = ((Employee)event.getObject()).getUserType();
        updateStaff(id,UserName,email,phone,utype);
       }
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Employee Editing Cancelled", String.valueOf(((Employee)event.getObject()).getId()));
        System.out.println(String.valueOf(((Employee) event.getObject()).getId()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

     public String addstaff() throws SQLException, ClassNotFoundException {
        Connection conn = database.getsqlConnection();
        Statement stmt = conn.createStatement();
        String home = "stafflist.xhtml";
        int id  = Employee.getId();
        String UserName = Employee.getUsername();
        String Password = Employee.getPassword();
        String email = Employee.getEmail();
        String phone = Employee.getPhone();
        int utype = Employee.getUtype();
        String query = "insert into staff values("+id+",'"+UserName+"','"+Password+"','"+email+"','"+phone+"',"+utype+")";
        stmt.execute(query);
        postconstruct();
        System.out.println("Added the staff " + UserName + " whose id is " + id);
        return home;
    }
    public void showError() {  
        FacesContext.getCurrentInstance().
           addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,  "Error", "Wrong credentials"));
    }  
    public String deletestaff(int id) throws SQLException{
       String home ="stafflist.xhtml";
        Connection conn = database.getsqlConnection();
        Statement stmt = conn.createStatement();
        String query = "Delete from staff where ID ="+id;
        System.out.println(id+" deleted");
         stmt.execute(query);
        postconstruct();
       return home;
   }
    public String emplist(){
        String home = "employeelist.xhtml";
        return home;
    }
    public String stafflist(){
        String home = "stafflist.xhtml";
        return home;
    }
    public String checks(){
        String home = "checks.xhtml";
        return home;
      }
    public String dash(){
        String home = "dashboard.xhtml";
        return home;
    }
    public String logout(){
        String home = "index.xhtml";
        return home;
    }
    public String messages(){
        String home = "messages.xhtml";
        return home;
    }
    public String out(){
        String home = "dashboard.xhtml";
        String name = Employee.getName();
        
        System.out.println("You selected: "+name);
        return home;
    }
}
