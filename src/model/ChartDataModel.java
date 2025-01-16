package model;

public class ChartDataModel {
    private String label;
    private int value;
    private int id;

    public ChartDataModel(String label, int value, int id) {
        this.label = label;
        this.value = value;
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
