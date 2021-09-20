package com.example.qrcodeassembler.backend.entity;

import lombok.*;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class HierarchyOfBoxes {

    @Id
    @GeneratedValue
    @ToString.Exclude
    private long id;
    private String numberContainer;
    private String numberBox;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;


    public HierarchyOfBoxes(String numberContainer, String numberBox, Date date) {
        this.numberContainer = numberContainer;
        this.numberBox = numberBox;
        this.date = date;
    }

    public void update(String numberContainer, String numberBox, Date date) {
        this.numberContainer = numberContainer;
        this.numberBox = numberBox;
        this.date = date;
    }
}
