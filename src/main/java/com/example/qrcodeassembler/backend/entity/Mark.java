package com.example.qrcodeassembler.backend.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity(name = "mark")
public class Mark {

    @Id
    @NotNull
    @Column(unique = true)
    private String cis;
    @NotNull
    private String barcode;
    private String numberOrder;
    private String numberBox;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Mark() {
        this.cis = "";
        this.barcode = "";
        this.numberBox = "";
        this.numberOrder = "";
        this.date = new Date();
    }

    public Mark(String cis, String barcode, String numberBox, String numberOrder, Date date) {
        this.cis = cis;
        this.barcode = barcode;
        this.numberBox = numberBox;
        this.numberOrder = numberOrder;
        this.date = date;
    }


    public String getCis() {
        return cis;
    }

    public void setCis(String cis) {
        this.cis = cis;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getNumberOrder() {
        return numberOrder;
    }

    public void setNumberOrder(String numberOrder) {
        this.numberOrder = numberOrder;
    }

    public String getNumberBox() {
        return numberBox;
    }

    public void setNumberBox(String numberBox) {
        this.numberBox = numberBox;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object comparedObject) {
        if (this == comparedObject) {
            return true;
        }

        if (comparedObject == null) {
            return false;
        }
        else if (getClass() != comparedObject.getClass()) {
            return false;
        }

        Mark castObject = (Mark) comparedObject;

        return castObject.getCis().equals(cis) &&
                castObject.getBarcode().equals(barcode) &&
                castObject.getNumberOrder().equals(numberOrder) &&
                castObject.getNumberBox().equals(numberBox) &&
                castObject.getDate().equals(date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cis, barcode, numberOrder, numberBox, date);
    }

    @Override
    public String toString() {
        return "Mark=[" +
                "cis=" + cis +
                ",barcode=" + barcode +
                ",numberOrder=" + numberOrder +
                ",numberBox=" + numberBox +
                ",date=" + date +
                "]";
    }


    public void updateMark(String barcode, String numberBox, String numberOrder, Date date) {
        this.barcode = barcode;
        this.numberBox = numberBox;
        this.numberOrder = numberOrder;
        this.date = date;
    }

}
