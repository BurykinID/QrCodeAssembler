package com.example.qrcodeassembler.backend.entity.order.temporarytable.box;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
public class BoxContent {

    @Id
    @GeneratedValue
    @ToString.Exclude
    private long id;
    private String barcode;
    private String numberBox;
    private String article;
    private String size;
    private String color;
    private int countNow;
    private int countNeed;
    private String macAddress;


    public BoxContent() {
        id = 0;
        barcode = "";
        numberBox = "";
        article = "";
        size = "";
        color = "";
        countNow = 0;
        countNeed = 0;
        macAddress = "";
    }

    public BoxContent(long id, String barcode, String article, String size, String color, int countNow, int countNeed, String macAddress, String numberBox) {
        this.id = id;
        this.barcode = barcode;
        this.article = article;
        this.size = size;
        this.color = color;
        this.countNow = countNow;
        this.countNeed = countNeed;
        this.macAddress = macAddress;
        this.numberBox = numberBox;
    }


    @Override
    public boolean equals(Object comparedObject) {
        if (this == comparedObject) {
            return true;
        }

        if (comparedObject == null || getClass() != comparedObject.getClass()) {
            return false;
        }

        BoxContent castObject = (BoxContent) comparedObject;
        return castObject.getId() == id &&
                castObject.getBarcode().equals(barcode) &&
                castObject.getArticle().equals(article) &&
                castObject.getSize().equals(size) &&
                castObject.getColor().equals(color) &&
                castObject.getCountNow() == countNow &&
                castObject.getCountNeed() == countNeed &&
                castObject.getMacAddress().equals(macAddress) &&
                castObject.getNumberBox().equals(numberBox);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, barcode, article, size, color, countNow, countNeed, macAddress, numberBox);
    }
}
