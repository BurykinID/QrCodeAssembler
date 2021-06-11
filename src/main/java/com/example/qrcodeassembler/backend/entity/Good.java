package com.example.qrcodeassembler.backend.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = "good")
@Getter
@Setter
@ToString
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


    public void updateGood(String name, String article, String color, String size) {
        this.name = name;
        this.article = article;
        this.color = color;
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
}
