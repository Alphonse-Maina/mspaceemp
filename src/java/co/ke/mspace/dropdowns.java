/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.ke.mspace;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

/**
 *
 * @author mspace_developer
 */
@ManagedBean
public class dropdowns {
    private String selectedOption;
    private String rtl;
    private String hideNoSelectOption;

    private String countryGroup;
    private List<SelectItem> countriesGroup;
    
    private String contacts;
    private List<SelectItem> contact;
    
    private String city;
    private Map<String, String> cities = new HashMap<>();
    
    private String option;
    private List<String> options;

    private String longItemLabel;
    private String labeled;

    private String icon = "flag";

    @PostConstruct
    public void init(){
        init1();
        init2();
    }
    public void init1() {

        countriesGroup = new ArrayList<>();
        try{
         Connection conn = database.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("select * from USERINFO where PAGER <> ''");
         while(rs.next()){
        SelectItemGroup Employees = new SelectItemGroup();
        Employees.setSelectItems(new SelectItem[]{
            new SelectItem(rs.getInt("UserID"), rs.getString("Name")),
            
        });
        countriesGroup.add(Employees);
         }}catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void init2() {

        contact = new ArrayList<>();
        try{
         Connection conn = database.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("select NAME,PAGER from USERINFO where PAGER <> ''");
         while(rs.next()){
        SelectItemGroup Employees = new SelectItemGroup();
        Employees.setSelectItems(new SelectItem[]{
            new SelectItem(rs.getInt("PAGER"), rs.getString("Name")),
            
        });
        contact.add(Employees);
         }}catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }

    public String getRtl() {
        return rtl;
    }

    public String getHideNoSelectOption() {
        return hideNoSelectOption;
    }

    public void setHideNoSelectOption(String hideNoSelectOption) {
        this.hideNoSelectOption = hideNoSelectOption;
    }

    public void setRtl(String rtl) {
        this.rtl = rtl;
    }

    public String getCountryGroup() {
        return countryGroup;
    }

    public void setCountryGroup(String countryGroup) {
        this.countryGroup = countryGroup;
    }

    public List<SelectItem> getCountriesGroup() {
        return countriesGroup;
    }

    public void setCountriesGroup(List<SelectItem> countriesGroup) {
        this.countriesGroup = countriesGroup;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Map<String, String> getCities() {
        return cities;
    }

    public void setCities(Map<String, String> cities) {
        this.cities = cities;
    }

   
    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getLongItemLabel() {
        return longItemLabel;
    }

    public void setLongItemLabel(String longItemLabel) {
        this.longItemLabel = longItemLabel;
    }

    public String getLabeled() {
        return labeled;
    }

    public void setLabeled(String labeled) {
        this.labeled = labeled;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public List<SelectItem> getContact() {
        return contact;
    }

    public void setContact(List<SelectItem> contact) {
        this.contact = contact;
    }

 
}

