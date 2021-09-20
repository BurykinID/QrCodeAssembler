package com.example.qrcodeassembler.backend.entity.order.box;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "box")
@Data
@NoArgsConstructor
public class Box {

    @Id
    @Column(unique = true)
    private String numberBox;
    private String status;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "number_variant")
    private VariantBox variantBox;

}
