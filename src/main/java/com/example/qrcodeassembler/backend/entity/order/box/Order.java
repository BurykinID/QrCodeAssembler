package com.example.qrcodeassembler.backend.entity.order.box;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity(name = "ord")
@Getter
@Setter
@ToString
public class Order {

    @Id
    @Column(unique = true)
    private String number;
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ToString.Exclude
    @OneToMany(mappedBy = "order")
    private List<VariantBox> variantBoxes;


    public Order() {
    }

    public Order(String number, String status, Date date, List<VariantBox> variantBoxes) {
        this.number = number;
        this.status = status;
        this.date = date;
        this.variantBoxes = variantBoxes;
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

}
