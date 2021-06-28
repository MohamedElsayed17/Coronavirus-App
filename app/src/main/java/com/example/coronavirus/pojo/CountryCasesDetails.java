package com.example.coronavirus.pojo;

public class CountryCasesDetails {
    String detailName;
    String detailNumber;
    int color;

    public CountryCasesDetails(String detailName, String detailNumber, int color) {
        this.detailName = detailName;
        this.detailNumber = detailNumber;
        this.color = color;
    }

    public String getDetailName() {
        return detailName;
    }

    public String getDetailNumber() {
        return detailNumber;
    }

    public int getColor() {
        return color;
    }
}
