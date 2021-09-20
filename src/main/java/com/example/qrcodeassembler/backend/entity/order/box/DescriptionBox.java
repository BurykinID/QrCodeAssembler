package com.example.qrcodeassembler.backend.entity.order.box;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "description_box")
@Data
@NoArgsConstructor
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

}