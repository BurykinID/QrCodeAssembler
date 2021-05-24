package com.example.qrcodeassembler.backend.entity;

import com.example.qrcodeassembler.backend.json.HierarchyOfBoxesJson;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
public class HierarchyOfBoxes {

    @Id
    @GeneratedValue
    private UUID id;
    private String numberContainer;
    private String numberBox;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public HierarchyOfBoxes() {
        numberContainer = "";
        numberBox = "";
        date = new Date();
    }

    public HierarchyOfBoxes(UUID id, String numberContainer, String numberBox, Date date) {
        this.id = id;
        this.numberContainer = numberContainer;
        this.numberBox = numberBox;
        this.date = date;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNumberContainer() {
        return numberContainer;
    }

    public void setNumberContainer(String numberContainer) {
        this.numberContainer = numberContainer;
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

        HierarchyOfBoxes castObject = (HierarchyOfBoxes) comparedObject;

        return castObject.getId().equals(id) &&
                castObject.getNumberContainer().equals(numberContainer) &&
                castObject.getNumberBox().equals(numberBox) &&
                castObject.getDate().equals(date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberContainer, numberBox, date);
    }

    @Override
    public String toString() {
        return "Container=[" +
                "id=" + id +
                ",numberContainer=" + numberContainer +
                ",numberBox=" + numberBox +
                ",date=" + date +
                "]";
    }


    public void update(HierarchyOfBoxesJson json) throws ParseException {
        this.numberBox = json.getNumberBox();
        this.numberContainer = json.getNumberContainer();
        this.date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(json.getDate());
    }

}
