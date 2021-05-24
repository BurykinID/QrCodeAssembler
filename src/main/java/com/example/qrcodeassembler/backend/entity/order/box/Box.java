package com.example.qrcodeassembler.backend.entity.order.box;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "box")
public class Box {

    @Id
    @Column(unique = true)
    private String numberBox;
    private String status;
    @ManyToOne
    @JoinColumn(name = "number_variant")
    private VariantBox variantBox;

    public Box() {
        numberBox = "";
        status = "";
        variantBox = new VariantBox();
    }

    public Box(String numberBox, String status, VariantBox variantBox) {
        this.numberBox = numberBox;
        this.status = status;
        this.variantBox = variantBox;
    }


    public String getNumberBox() {
        return numberBox;
    }

    public void setNumberBox(String numberBox) {
        this.numberBox = numberBox;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public VariantBox getVariantBox() {
        return variantBox;
    }

    public void setVariantBox(VariantBox variantBox) {
        this.variantBox = variantBox;
    }


    @Override
    public boolean equals(Object comparedObject) {
        if (this == comparedObject)  {
            return true;
        }

        if (comparedObject == null) {
            return false;
        }
        else if (getClass() != comparedObject.getClass()) {
            return false;
        }

        Box castObject = (Box) comparedObject;
        return castObject.getNumberBox().equals(numberBox) &&
                castObject.getStatus().equals(status) &&
                castObject.getVariantBox().equals(variantBox);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberBox, status, variantBox);
    }

    @Override
    public String toString() {
        return "Box=[" +
                "numberBox=" + numberBox +
                ",status=" + status +
                ",variantBox=" + variantBox +
                "]";
    }
}
