package com.example.qrcodeassembler.backend.entity.order.temporarytable.box;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Entity
public class BoxContent {

    @Id
    @GeneratedValue
    private UUID id;
    private String barcode;
    private String numberBox;
    private String article;
    private String size;
    private String color;
    private int countNow;
    private int countNeed;
    private String macAddress;


    public BoxContent() {
        id = null;
        barcode = "";
        numberBox = "";
        article = "";
        size = "";
        color = "";
        countNow = 0;
        countNeed = 0;
        macAddress = "";
    }

    public BoxContent(UUID id, String barcode, String article, String size, String color, int countNow, int countNeed, String macAddress, String numberBox) {
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


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCountNow() {
        return countNow;
    }

    public void setCountNow(int countNow) {
        this.countNow = countNow;
    }

    public int getCountNeed() {
        return countNeed;
    }

    public void setCountNeed(int countNeed) {
        this.countNeed = countNeed;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getNumberBox() {
        return numberBox;
    }

    public void setNumberBox(String numberBox) {
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
        return castObject.getId().equals(id) &&
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

    @Override
    public String toString() {
        return "BoxContent=[" +
                "id=" + id +
                ",barcode=" + barcode +
                ",numberBox=" + numberBox +
                ",article=" + article +
                ",size=" + size +
                ",color=" + color +
                ",countNow=" + countNow +
                ",countNeed=" + countNeed +
                ",macAddress=" + macAddress +
                "]";
    }
}
