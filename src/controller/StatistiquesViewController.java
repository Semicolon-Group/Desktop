/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.MemberService;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.Chart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.jfree.chart.JFreeChart;

/**
 * FXML Controller class
 *
 * @author badis
 */
public class StatistiquesViewController implements Initializable {

    @FXML
    private AnchorPane statmois = new AnchorPane();
    @FXML
    private AnchorPane statgender = new AnchorPane();
    @FXML
    private AnchorPane start2= new AnchorPane();

    private List<Chart> charts = new ArrayList<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            start();
            createPie();
            start2();
        } catch (SQLException ex) {
            Logger.getLogger(StatistiquesViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    TextField yourTextField = new TextField();

    public void start() throws SQLException {

        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Mois");
        //creating the chart
        final LineChart<Number, Number> lineChart
                = new LineChart<Number, Number>(xAxis, yAxis);

        lineChart.setTitle("Joined Members, 2018");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        //series.setName("My portfolio");

        //populating the series with data
        MemberService ms = MemberService.getInstance();
        ResultSet set = ms.getStats();
        int counter;
        int jan = 0, feb = 0, mar = 0, apr = 0, mai = 0, jun = 0, jul = 0, aug = 0, sep = 0, oct = 0, nov = 0, dec = 0;
        while (set.next()) {
            counter = set.getInt("created");

            switch (counter) {
                case 1:
                    jan++;
                    break;
                case 2:
                    feb++;
                    break;
                case 3:
                    mar++;
                    break;
                case 4:
                    apr++;
                    break;
                case 5:
                    mai++;
                    break;
                case 6:
                    jun++;
                    break;
                case 7:
                    jul++;
                    break;
                case 8:
                    aug++;
                    break;
                case 9:
                    sep++;
                    break;
                case 10:
                    oct++;
                    break;
                case 11:
                    nov++;
                    break;
                case 12:
                    dec++;
                    break;

            }
        }

        series.getData().add(new XYChart.Data(1, jan));
        series.getData().add(new XYChart.Data(2, feb));
        series.getData().add(new XYChart.Data(3, mar));
        series.getData().add(new XYChart.Data(4, apr));
        series.getData().add(new XYChart.Data(5, mai));
        series.getData().add(new XYChart.Data(6, jun));
        series.getData().add(new XYChart.Data(7, jul));
        series.getData().add(new XYChart.Data(8, aug));
        series.getData().add(new XYChart.Data(9, sep));
        series.getData().add(new XYChart.Data(10, oct));
        series.getData().add(new XYChart.Data(11, nov));
        series.getData().add(new XYChart.Data(12, dec));

        lineChart.getData().add(series);
        statmois.getChildren().add(lineChart);
        charts.add(lineChart);
    }

    /**
     *
     * @throws SQLException
     */
    public void createPie() throws SQLException {
        PieChart pie = new PieChart();
        ObservableList<PieChart.Data> data
                = FXCollections.observableArrayList();
        MemberService ms = MemberService.getInstance();
        ResultSet set = ms.getGender();
        int counter;
        int hommes = 0, femmes = 0;
        while (set.next()) {
            counter = set.getInt("sex");

            switch (counter) {
                case 0:
                    hommes++;
                    break;
                case 1:
                    femmes++;
                    break;
            }
        }

        data.addAll(new PieChart.Data("Men", hommes),
                new PieChart.Data("Women", femmes)
        );

        pie.setData(data);
        pie.setTitle("Users Men vs Women");
        statgender.getChildren().add(pie);
        charts.add(pie);
    }
 
    public void start2() throws SQLException {
      
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
         xAxis.setLabel("Month");
        final LineChart<String,Number> lineChart = 
                
                new LineChart<String,Number>(xAxis,yAxis);
          MemberService ms = MemberService.getInstance();
        ResultSet set1 = ms.getLike();
        int counter;
        int jan = 0, feb = 0, mar = 0, apr = 0, mai = 0, jun = 0, jul = 0, aug = 0, sep = 0, oct = 0, nov = 0, dec = 0;
        while (set1.next()) {
            counter = set1.getInt("created1");

            switch (counter) {
                case 1:
                    jan++;
                    break;
                case 2:
                    feb++;
                    break;
                case 3:
                    mar++;
                    break;
                case 4:
                    apr++;
                    break;
                case 5:
                    mai++;
                    break;
                case 6:
                    jun++;
                    break;
                case 7:
                    jul++;
                    break;
                case 8:
                    aug++;
                    break;
                case 9:
                    sep++;
                    break;
                case 10:
                    oct++;
                    break;
                case 11:
                    nov++;
                    break;
                case 12:
                    dec++;
                    break;

            }
        }
          
        ResultSet set2 = ms.getBlock();
        int counter2;
        int jan2 = 0, feb2 = 0, mar2 = 0, apr2 = 0, mai2 = 0, jun2 = 0, jul2 = 0, aug2 = 0, sep2 = 0, oct2 = 0, nov2 = 0, dec2 = 0;
        while (set2.next()) {
            counter2 = set2.getInt("created2");

            switch (counter2) {
                case 1:
                    jan2++;
                    break;
                case 2:
                    feb2++;
                    break;
                case 3:
                    mar2++;
                    break;
                case 4:
                    apr2++;
                    break;
                case 5:
                    mai2++;
                    break;
                case 6:
                    jun2++;
                    break;
                case 7:
                    jul2++;
                    break;
                case 8:
                    aug2++;
                    break;
                case 9:
                    sep2++;
                    break;
                case 10:
                    oct2++;
                    break;
                case 11:
                    nov2++;
                    break;
                case 12:
                    dec2++;
                    break;

            }
        }

        ResultSet set3 = ms.getSignal();
        int counter3;
        int jan3 = 0, feb3 = 0, mar3 = 0, apr3 = 0, mai3 = 0, jun3 = 0, jul3 = 0, aug3 = 0, sep3 = 0, oct3 = 0, nov3 = 0, dec3 = 0;
        while (set3.next()) {
            counter3 = set3.getInt("created3");

            switch (counter3) {
                case 1:
                    jan3++;
                    break;
                case 2:
                    feb3++;
                    break;
                case 3:
                    mar3++;
                    break;
                case 4:
                    apr3++;
                    break;
                case 5:
                    mai3++;
                    break;
                case 6:
                    jun3++;
                    break;
                case 7:
                    jul3++;
                    break;
                case 8:
                    aug3++;
                    break;
                case 9:
                    sep3++;
                    break;
                case 10:
                    oct3++;
                    break;
                case 11:
                    nov3++;
                    break;
                case 12:
                    dec3++;
                    break;

            }
        }
        
       
        lineChart.setTitle("Likes ,blocks , signaux 2017");
                          
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Likes");
        
        series1.getData().add(new XYChart.Data("Jan", jan));
        series1.getData().add(new XYChart.Data("Feb", feb));
        series1.getData().add(new XYChart.Data("Mar", mar));
        series1.getData().add(new XYChart.Data("Apr", apr));
        series1.getData().add(new XYChart.Data("May", mai));
        series1.getData().add(new XYChart.Data("Jun", jun));
        series1.getData().add(new XYChart.Data("Jul", jul));
        series1.getData().add(new XYChart.Data("Aug", aug));
        series1.getData().add(new XYChart.Data("Sep", sep));
        series1.getData().add(new XYChart.Data("Oct", oct));
        series1.getData().add(new XYChart.Data("Nov", nov));
        series1.getData().add(new XYChart.Data("Dec", dec));
        
        XYChart.Series series2 = new XYChart.Series();
         series2.setName("Blocks");
        
        series2.getData().add(new XYChart.Data("Jan", jan2));
        series2.getData().add(new XYChart.Data("Feb", feb2));
        series2.getData().add(new XYChart.Data("Mar", mar2));
        series2.getData().add(new XYChart.Data("Apr", apr2));
        series2.getData().add(new XYChart.Data("May", mai2));
        series2.getData().add(new XYChart.Data("Jun", jun2));
        series2.getData().add(new XYChart.Data("Jul", jul2));
        series2.getData().add(new XYChart.Data("Aug", aug2));
        series2.getData().add(new XYChart.Data("Sep", sep2));
        series2.getData().add(new XYChart.Data("Oct", oct2));
        series2.getData().add(new XYChart.Data("Nov", nov2));
        series2.getData().add(new XYChart.Data("Dec", dec2));
        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Signaux");
        
        series3.getData().add(new XYChart.Data("Jan", jan3));
        series3.getData().add(new XYChart.Data("Feb", feb3));
        series3.getData().add(new XYChart.Data("Mar", mar3));
        series3.getData().add(new XYChart.Data("Apr", apr3));
        series3.getData().add(new XYChart.Data("May", mai3));
        series3.getData().add(new XYChart.Data("Jun", jun3));
        series3.getData().add(new XYChart.Data("Jul", jul3));
        series3.getData().add(new XYChart.Data("Aug", aug3));
        series3.getData().add(new XYChart.Data("Sep", sep3));
        series3.getData().add(new XYChart.Data("Oct", oct3));
        series3.getData().add(new XYChart.Data("Nov", nov3));
        series3.getData().add(new XYChart.Data("Dec", dec3));
        
        
        lineChart.getData().addAll(series1, series2, series3);
       
       start2.getChildren().add(lineChart);
    }

    @FXML
    private void print(ActionEvent event) {
        
    }
}
