package model;

import java.sql.Date;
import java.time.LocalDate;

public class Transaction {
    private double amount ;
    private String description ;
    private Boolean isExpense;
    private LocalDate date;


    public Transaction(double amount, String description, Boolean isExpense, LocalDate date) {
        this.amount = amount;
        this.description = description;
        this.isExpense = isExpense;
        this.date = date;
    }

    public Date getDate() {
        return Date.valueOf(date);
    }

    public void setDate(String date) {
        this.date = LocalDate.parse(date);
    }

    public Boolean getExpense() {
        return isExpense;
    }

    public void setExpense(Boolean expense) {
        isExpense = expense;
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

    public void setAmount(String amount) {
        this.amount = Double.parseDouble(amount);
    }


}
