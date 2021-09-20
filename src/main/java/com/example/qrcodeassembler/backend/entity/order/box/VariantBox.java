package com.example.qrcodeassembler.backend.entity.order.box;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Entity(name = "variant_box")
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


    public void update(VariantBox variantBox) {
        this.numberVariant = variantBox.getNumberVariant();
        this.countInBox = variantBox.getCountInBox();
        this.countBox = variantBox.getCountBox();
    }
}
