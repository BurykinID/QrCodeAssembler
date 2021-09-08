package com.example.qrcodeassembler.backend.entity.order.box;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity(name = "ord")
@Getter
@Setter
@ToString
@NoArgsConstructor
@Data
public class Order {

    @Id
    @Column(unique = true)
    private String number;
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ToString.Exclude
    @OneToMany(mappedBy = "order", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<VariantBox> variantBoxes;


    public Order(String number, String status, Date date, List<VariantBox> variantBoxes) {
        this.number = number;
        this.status = status;
        this.date = date;
        this.variantBoxes = variantBoxes;
    }

    public Order(String number, String status, Date date) {
        this.number = number;
        this.status = status;
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

        Order castObject = (Order) comparedObject;

        return castObject.getNumber().equals(number) &&
                castObject.getStatus().equals(status) &&
                castObject.getDate().equals(date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, status, date);
    }

    public void update(Date date, String status) {
        this.status = status;
        this.date = date;
    }
}
