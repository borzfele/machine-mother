package com.borzfele.machinemother.model;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @Column(name = "name")
    String name;
    @Column(name = "value")
    long value;
    @Column(name = "description")
    String description;
    @Column(name = "year")
    private int year;
    @Column(name = "month")
    private int month;
    @Column(name = "day")
    private int day;
    @Column(name = "continous")
    boolean isContinous;

    public Transaction() {
    }

    public Transaction(String name, long value) {
        this.name = name;
        this.value = value;
    }

    public Transaction(String name, long value, String description) {
        this.name = name;
        this.value = value;
        this.description = description;
    }

    public Transaction(String name, long value, boolean isContinous) {
        this.name = name;
        this.value = value;
        this.isContinous = isContinous;
    }

    public Transaction(String name, long value, String description, boolean isContinous) {
        this.name = name;
        this.value = value;
        this.description = description;
        this.isContinous = isContinous;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public boolean isContinous() {
        return isContinous;
    }

    public void setContinous(boolean continous) {
        isContinous = continous;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", value=" + value +
                ", description='" + description + '\'' +
                //", date=" + date +
                ", isContinous=" + isContinous +
                '}';
    }
}
