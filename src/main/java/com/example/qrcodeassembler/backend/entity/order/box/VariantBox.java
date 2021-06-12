package com.example.qrcodeassembler.backend.entity.order.box;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity(name = "variant_box")
@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
public class VariantBox {

    @Id
    @Column(name = "number_variant",
            unique = true)
    private String numberVariant;
    private int countInBox;
    private int countBox;

    @ToString.Exclude
    @OneToMany(mappedBy = "variantBox", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Box> boxes;

    @ToString.Exclude
    @OneToMany(mappedBy = "variantBox", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<DescriptionBox> descriptionBoxes;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "number_order")
    private Order order;


    public VariantBox(String numberVariant, int countInBox, int countBox, Order order) {
        this.numberVariant = numberVariant;
        this.countInBox = countInBox;
        this.countBox = countBox;
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

    public void update(VariantBox variantBox) {
        this.numberVariant = variantBox.getNumberVariant();
        this.countInBox = variantBox.getCountInBox();
        this.countBox = variantBox.getCountBox();
    }
}
