package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Transaction;
import model.TransactionTableModel;

import java.sql.*;
import java.sql.PreparedStatement;
import java.util.Date;

public class DashboardController {

    public TextField txtAmount;
    public TextField txtDescription;
    public RadioButton rdType;
    public ToggleGroup type;
    public DatePicker txtDate;
    public TableView tbl;
    public TableColumn colData;
    public TableColumn colType;
    public TableColumn colAmount;
    public TableColumn colDescription;
    public TableColumn colAction;
    public Label txtExpends;
    public Label txtIncome;
    public Label txtAvailable;

    public void initialize() {
        colData.setCellValueFactory(new PropertyValueFactory<>("date"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("button"));

        setData();
    }

    public DashboardController() {

    }

    public void onActionRecord(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {

        Transaction trans=new Transaction(
                Double.parseDouble(txtAmount.getText()),
                txtDescription.getText(),
                rdType.isSelected(),
                txtDate.getValue());

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/pfmv1",
                    "root",
                    "1234");
            String sql="INSERT INTO transaction (amount, discription, date, type) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setDouble(1, trans.getAmount());
            statement.setString(2, trans.getDescription());
            statement.setDate(3, trans.getDate());
            statement.setBoolean(4, trans.getExpense());
            int res=statement.executeUpdate();
            if(res>0){
                System.out.println("success");
            }else {
                System.out.println("err...");
            }
            setData();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public void setData(){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/pfmv1",
                    "root",
                    "1234");
            String sql="SELECT * from transaction";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs=statement.executeQuery();
            ObservableList<TransactionTableModel> obList= FXCollections.observableArrayList();
//            ResultSetMetaData metaData = rs.getMetaData();
//            int columnCount = metaData.getColumnCount();
            while(rs.next()){

//                for (int i = 1; i <= columnCount; i++) {
//                    String columnName = metaData.getColumnName(i);
//                    String columnValue = rs.getString(i);
//                    System.out.print(columnName + ": " + columnValue + " | ");
//                }
//                System.out.println();

                Button button=new Button("Delete");

                TransactionTableModel model=new TransactionTableModel(
                        rs.getInt(1),
                        rs.getDate("date"),
                        rs.getInt("type")==1?"-":"+",
                        rs.getDouble("amount"),
                        rs.getString("discription"),
                        button
                );
                System.out.println(model.getId());
                button.setOnAction(e->{
    Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"DO you want to delete this transaction",ButtonType.YES,ButtonType.NO);
    alert.showAndWait();
    if(alert.getResult()==ButtonType.YES){
        try{
            PreparedStatement statement1 = conn.prepareStatement("DELETE FROM transaction WHERE id=?");
            statement1.setInt(1, model.getId());

            int res=statement1.executeUpdate();
            System.out.println(res);
            if(res>0){
                System.out.println("success");
            }else {
                Alert alert1=new Alert(Alert.AlertType.ERROR,"Transaction not deleted");
                alert1.show();
                System.out.println("err...");
            }
            setData();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
                }
                );

                obList.add(model);

            }

            tbl.setItems(obList);

            String sqlExp="SELECT SUM(amount) from transaction where type=true";
            PreparedStatement statement1 = conn.prepareStatement(sqlExp);
            ResultSet rs1=statement1.executeQuery();
            if(rs1.next()){
                txtExpends.setText(rs1.getString(1));
            }
            String sqlInc="SELECT SUM(amount) from transaction where type=false";
            PreparedStatement statement2 = conn.prepareStatement(sqlInc);
            ResultSet rs2=statement2.executeQuery();
            if(rs2.next()){
                txtIncome.setText(rs2.getString(1));
            }

            txtAvailable.setText(String.valueOf(Double.parseDouble(txtIncome.getText())-Double.parseDouble(txtExpends.getText())));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
