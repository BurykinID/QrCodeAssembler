package com.example.qrcodeassembler.backend.entity.order.box;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "description_box")
@Getter
@Setter
@ToString
public class DescriptionBox {

    @Id
    @GeneratedValue
    @ToString.Exclude
    private long id;
    @NotNull
    private String barcode;
    private int count;
    private int numberLine;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "number_variant")
    private VariantBox variantBox;

    public DescriptionBox() {
        this.barcode = "";
        this.count = 0;
        this.numberLine = 0;
    }

    public DescriptionBox(long id, String barcode, int count, int numberLine, VariantBox variantBox) {
        this.id = id;
        this.barcode = barcode;
        this.count = count;
        this.numberLine = numberLine;
        this.variantBox = variantBox;
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

        DescriptionBox castObject = (DescriptionBox) comparedObject;
        return castObject.getId() == id &&
                castObject.getBarcode().equals(barcode) &&
                castObject.getCount() == count &&
                castObject.getNumberLine() == numberLine;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, barcode, count, numberLine);
    }

}
