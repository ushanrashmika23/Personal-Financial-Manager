package model;


import javafx.scene.control.Button;

import java.util.Date;

public class TransactionTableModel {
    private Date date;
    private int id;
    private String type;
    private double amount;
    private String description;
    private Button button;

    public TransactionTableModel(int id, Date date, String type, double amount, String description, Button button) {
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.button = button;
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
