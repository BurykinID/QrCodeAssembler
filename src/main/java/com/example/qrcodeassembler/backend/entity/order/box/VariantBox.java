package com.example.qrcodeassembler.backend.entity.order.box;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity(name = "variant_box")
public class VariantBox {

    @Id
    @Column(name = "number_variant",
            unique = true)
    private String numberVariant;
    private int countInBox;
    private int countBox;

    @OneToMany(mappedBy = "variantBox")
    private List<Box> boxes;

    @OneToMany(mappedBy = "descriptionBox")
    private List<DescriptionBox> descriptionBoxes;

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


    public String getNumberVariant() {
        return numberVariant;
    }

    public void setNumberVariant(String numberVariant) {
        this.numberVariant = numberVariant;
    }

    public int getCountInBox() {
        return countInBox;
    }

    public void setCountInBox(int countInBox) {
        this.countInBox = countInBox;
    }

    public int getCountBox() {
        return countBox;
    }

    public void setCountBox(int countBox) {
        this.countBox = countBox;
    }

    public List<Box> getBoxes() {
        return boxes;
    }

    public void setBoxes(List<Box> boxes) {
        this.boxes = boxes;
    }

    public List<DescriptionBox> getDescriptionBoxes() {
        return descriptionBoxes;
    }

    public void setDescriptionBoxes(List<DescriptionBox> descriptionBoxes) {
        this.descriptionBoxes = descriptionBoxes;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
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

    @Override
    public String toString() {
        return "VariantBox=[" +
                "numberVariant=" + numberVariant +
                ",countInBox=" + countBox +
                ",countBox=" + countBox +
                ",order=" + order.toString() +
                "]";
    }
}
