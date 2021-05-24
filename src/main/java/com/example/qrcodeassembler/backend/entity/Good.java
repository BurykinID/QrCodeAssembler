package com.example.qrcodeassembler.backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = "good")
public class Good {

    @Id
    @Column(unique = true)
    private String barcode;
    private String name;
    private String article;
    private String color;
    private String size;


    public Good() {
        barcode = "";
        name = "";
        article = "";
        color = "";
        size = "";
    }

    public Good(String barcode, String name, String article, String color, String size) {
        this.barcode = barcode;
        this.name = name;
        this.article = article;
        this.color = color;
        this.size = size;
    }


    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }


    @Override
    public boolean equals(Object comparedObject) {
        if (this == comparedObject) {
            return true;
        }

        if (comparedObject == null) {
            return false;
        }
        else if (comparedObject.getClass() != getClass()) {
            return false;
        }

        Good castObject = (Good) comparedObject;
        return castObject.getBarcode().equals(barcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(barcode, name, article, color, size);
    }

    @Override
    public String toString() {
        return "Good=[" +
                "barcode=" + barcode +
                ",name=" + name +
                ",article=" + article +
                ",color=" + color +
                ",size=" + size +
                "]";
    }


    public void update(String name, String article, String color, String size) {
        this.name = name;
        this.article = article;
        this.color = color;
        this.size = size;
    }

}
