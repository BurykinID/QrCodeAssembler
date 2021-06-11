package com.example.qrcodeassembler.backend.entity.order.box;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity(name = "variant_box")
@Getter
@Setter
@ToString
public class VariantBox {

    @Id
    @Column(name = "number_variant",
            unique = true)
    private String numberVariant;
    private int countInBox;
    private int countBox;

    @ToString.Exclude
    @OneToMany(mappedBy = "variantBox")
    private List<Box> boxes;

    @ToString.Exclude
    @OneToMany(mappedBy = "variantBox")
    private List<DescriptionBox> descriptionBoxes;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "number_order")
    private Order order;

    public VariantBox() {
        this.numberVariant = "";
        this.countInBox = 0;
        this.countBox = 0;
        this.descriptionBoxes = new LinkedList<>();
        this.boxes = new LinkedList<>();
        this.order = new Order();
    }

    public VariantBox(String numberVariant, int countInBox, int countBox, List<Box> boxes, List<DescriptionBox> descriptionBoxes, Order order) {
        this.numberVariant = numberVariant;
        this.countInBox = countInBox;
        this.countBox = countBox;
        this.boxes = boxes;
        this.descriptionBoxes = descriptionBoxes;
        this.order = order;
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

        VariantBox castObject = (VariantBox) comparedObject;
        return castObject.getNumberVariant().equals(numberVariant) &&
                castObject.getCountInBox() == countInBox &&
                castObject.getCountBox() == countBox &&
                castObject.getOrder().equals(order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberVariant, countInBox, countBox, order);
    }

}
