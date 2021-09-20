package com.example.qrcodeassembler.backend.entity.order.container;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Container{

    @Id
    @Column(unique = true)
    private String numberContainer;
    private String status;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn (name = "number_variant")
    private VariantContainer variantContainer;


    public Container(String numberContainer, String status, VariantContainer variantContainer) {
        this.numberContainer = numberContainer;
        this.status = status;
        this.variantContainer = variantContainer;
    }

}
