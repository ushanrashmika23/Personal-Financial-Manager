package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.ChartDataModel;
import model.TransactionTableModel;

import java.io.IOException;
import java.sql.*;

public class GraphController {
//    public BarChart<String, Number> barChart;
    public AnchorPane context;
    public LineChart<String,Number> lineChart;
    public DatePicker date1;
    public DatePicker date2;

    public void initialize() {
        System.out.println("init");
        setAllChartData();

    }

    private void setAllChartData() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pfmv1",
                    "root",
                    "1234");
            String sql = "SELECT date, SUM(amount) AS amount, MIN(id) AS id FROM transaction where type=1 GROUP BY date LIMIT 30";


            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            lineChart.getData().clear();
            while (rs.next()) {
                ChartDataModel model = new ChartDataModel(rs.getString("date"), rs.getInt("amount"), rs.getInt("id"));
                System.out.println(rs);
                series.getData().add(new XYChart.Data<>(model.getLabel(), model.getValue()));
            }

            lineChart.getData().clear();
            lineChart.getData().addAll(series);

        } catch (Exception e) {
            System.out.println("err");
            System.out.println(e.getMessage());
        }
    }

    public void onActionBack2Dashboard(ActionEvent actionEvent) throws IOException {
        setUi("dashboardForm");

    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
    }

    public void onActinMonthly(ActionEvent actionEvent) {
    }

    public void onActionMonthly(ActionEvent actionEvent) {
    }


    public void onActionShowGraph(ActionEvent actionEvent) {
        Date dateBox1= Date.valueOf(date1.getValue());
        Date dateBox2= Date.valueOf(date2.getValue());
        System.out.println(dateBox2);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pfmv1",
                    "root",
                    "1234");
            String sql = "SELECT date, SUM(amount) AS amount, MIN(id) AS id FROM transaction where type=1 and date between ? and ? GROUP BY date LIMIT 21";


            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setDate(1, dateBox1);
            statement.setDate(2, dateBox2);
            ResultSet rs = statement.executeQuery();
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            while (rs.next()) {
                ChartDataModel model = new ChartDataModel(rs.getString("date"), rs.getInt("amount"), rs.getInt("id"));
                series.getData().add(new XYChart.Data<>(model.getLabel(), model.getValue()));
            }

            lineChart.getData().clear();
            lineChart.getData().addAll(series);

        } catch (Exception e) {
            System.out.println("err");
            System.out.println(e.getMessage());
        }
    }
}
