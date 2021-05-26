package com.example.qrcodeassembler.backend.entity.order.box;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "description_box")
public class DescriptionBox {

    @Id
    @GeneratedValue
    private UUID id;
    @NotNull
    private String barcode;
    private int count;
    private int numberLine;

    @ManyToOne
    @JoinColumn(name = "number_variant")
    private VariantBox variantBox;

    public DescriptionBox() {
        this.barcode = "";
        this.count = 0;
        this.numberLine = 0;
    }

    public DescriptionBox(UUID id, String barcode, int count, int numberLine, VariantBox variantBox) {
        this.id = id;
        this.barcode = barcode;
        this.count = count;
        this.numberLine = numberLine;
        this.variantBox = variantBox;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getNumberLine() {
        return numberLine;
    }

    public void setNumberLine(int numberLine) {
        this.numberLine = numberLine;
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
        return castObject.getId().equals(id) &&
                castObject.getBarcode().equals(barcode) &&
                castObject.getCount() == count &&
                castObject.getNumberLine() == numberLine;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, barcode, count, numberLine);
    }

    @Override
    public String toString() {
        return "DescriptionBox=[" +
                "id=" + id +
                ",barcode=" + barcode +
                ",count=" + count +
                ",numberLine=" + numberLine +
                "]";
    }
}
