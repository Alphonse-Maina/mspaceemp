/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.ke.mspace;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author mspace_developer
 */
@ManagedBean(name = "employee")
public class Employee {

   
    public Employee(){}
    
    public Employee(String recipient, String message, String time){
        this.recipient = recipient;
        this.Message = message;
        this.time = time;
    }
    
    public Employee( String Badgenumber, String Name, String Gender, String PAGER) {
        
        this.Badgenumber = Badgenumber;
        this.Name = Name;
        this.Gender = Gender;
        this.PAGER = PAGER;
        
    }
   public Employee(int USERID,String Name ,String CHECKTIME, int LOGID, String sn){
       this.USERID = USERID;
       this.Name = Name;
       this.CHECKTIME = CHECKTIME;
       this.LOGID = LOGID;
       this.sn = sn;
   }
   public Employee(int ID, String UserName, String Email, String Phone, String UserType){
       this.id = ID;
       this.username = UserName;
       this.email = Email;
       this.phone = Phone;
       this.UserType = UserType;
   }
    private String username;
    private String Password;
    private int USERID;
    private String Badgenumber;
    private String Name;
    private String Gender;
    private String PAGER;
    private String CHECKTIME;
    private int LOGID;
    private String sn;
    private int id;
    private String email;
    private String phone;
    private String UserType;
    private String timenow;
    private int Utype;
    private String Message;
    private String recipient;
    private String time;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public int getUSERID() {
        return USERID;
    }

    public void setUSERID(int USERID) {
        this.USERID = USERID;
    }

    public String getBadgenumber() {
        return Badgenumber;
    }

    public void setBadgenumber(String Badgenumber) {
        this.Badgenumber = Badgenumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getPAGER() {
        return PAGER;
    }

    public void setPAGER(String PAGER) {
        this.PAGER = PAGER;
    }

    public String getCHECKTIME() {
        return CHECKTIME;
    }

    public void setCHECKTIME(String CHECKTIME) {
        this.CHECKTIME = CHECKTIME;
    }

    public int getLOGID() {
        return LOGID;
    }

    public void setLOGID(int LOGID) {
        this.LOGID = LOGID;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String UserType) {
        this.UserType = UserType;
    }

    public String getTimenow() {
        return timenow;
    }

    public void setTimenow(String timenow) {
        this.timenow = timenow;
    }

    public int getUtype() {
        return Utype;
    }

    public void setUtype(int Utype) {
        this.Utype = Utype;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
      
}
