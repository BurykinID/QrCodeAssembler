package com.example.qrcodeassembler.backend.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
public class HierarchyOfBoxes {

    @Id
    @GeneratedValue
    @ToString.Exclude
    private long id;
    private String numberContainer;
    private String numberBox;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public HierarchyOfBoxes() {
        numberContainer = "";
        numberBox = "";
        date = new Date();
    }

    public HierarchyOfBoxes(long id, String numberContainer, String numberBox, Date date) {
        this.id = id;
        this.numberContainer = numberContainer;
        this.numberBox = numberBox;
        this.date = date;
    }

    public HierarchyOfBoxes(String numberContainer, String numberBox, Date date) {
        this.numberContainer = numberContainer;
        this.numberBox = numberBox;
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

        HierarchyOfBoxes castObject = (HierarchyOfBoxes) comparedObject;

        return castObject.getId() == id &&
                castObject.getNumberContainer().equals(numberContainer) &&
                castObject.getNumberBox().equals(numberBox) &&
                castObject.getDate().equals(date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberContainer, numberBox, date);
    }


    public void update(String numberContainer, String numberBox, Date date) {
        this.numberContainer = numberContainer;
        this.numberBox = numberBox;
        this.date = date;
    }

}
