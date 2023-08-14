/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.ke.mspace;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author mspace_developer
 */
@ManagedBean
public class barmodel {

    public barmodel() {
    }
    private BarChartModel barModel;

    @ManagedProperty(value = "#{employee}", name = "employee")

    public Employee Employee;

    @PostConstruct
    public void init() {
        createBarModel();
    }

    public void createBarModel() {
        barModel = initBarModel();
        barModel.setTitle("My bar Model");
        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Year");
        xAxis.setMax(10);

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("usage");
        yAxis.setMin(0);
        yAxis.setMax(200);
       

    }

    private BarChartModel initBarModel() {

        BarChartModel model = new BarChartModel();
        try {
            Integer userid = getEmployee().getUSERID();
            int wts = 0;
            String query2 = "select * from CHECKINOUT where USERID = ? AND CAST(CHECKTIME AS DATE) = ?";
            Connection conn = database.getConnection();
            PreparedStatement stmt;
            LocalDate today = LocalDate.now();
            List<ChartSeries> listSeries = new ArrayList<>();
            ChartSeries attend1 = new  ChartSeries();
            ChartSeries attend2 = new  ChartSeries();
            ChartSeries attend3 = new  ChartSeries();
            ChartSeries attend4 = new  ChartSeries();
            listSeries.add(attend1);
            listSeries.add(attend2);
            listSeries.add(attend3);
            listSeries.add(attend4);
            for (int i = 0; i < listSeries.size(); i++) {

                String leo = today.minusWeeks(wts).toString();
                stmt = conn.prepareStatement(query2);
                stmt.setInt(1, userid);
                stmt.setString(2, leo);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    listSeries.get(i).set(leo, 200);
                    listSeries.get(i).setLabel("attend");
                    model.setSeriesColors("00ff00");
                } else {
                    listSeries.get(i).set(leo, 100);
                    listSeries.get(i).setLabel("!not");
                    model.setSeriesColors("ff0000");
                }
                wts++;
                 model.addSeries(listSeries.get(i));
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }

        return model;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public Employee getEmployee() {
        return Employee;
    }

    public void setEmployee(Employee Employee) {
        this.Employee = Employee;
    }

}
