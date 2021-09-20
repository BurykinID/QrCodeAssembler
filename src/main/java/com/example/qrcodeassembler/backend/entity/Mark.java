package com.example.qrcodeassembler.backend.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity(name = "mark")
@Data
@NoArgsConstructor
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


    public void updateMark(Mark mark) {
        this.barcode = mark.getBarcode();
        this.numberBox = mark.getNumberBox();
        this.numberOrder = mark.getNumberOrder();
        this.date = mark.getDate();
    }
}
