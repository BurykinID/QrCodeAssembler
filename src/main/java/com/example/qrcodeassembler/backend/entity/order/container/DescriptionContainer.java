package com.example.qrcodeassembler.backend.entity.order.container;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class DescriptionContainer {

    @Id
    @GeneratedValue
    private Long id;
    private int numberLine;
    private String numberVariantBox;
    private int count;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn (name = "number_variant")
    private VariantContainer variantContainer;


    public DescriptionContainer(int numberLine, String numberVariantBox, int count, VariantContainer variantContainer) {
        this.numberLine = numberLine;
        this.numberVariantBox = numberVariantBox;
        this.count = count;
        this.variantContainer = variantContainer;
    }
}
