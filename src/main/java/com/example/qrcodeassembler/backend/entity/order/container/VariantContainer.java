package com.example.qrcodeassembler.backend.entity.order.container;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class VariantContainer {

    @Id
    @Column (unique = true)
    private String numberVariant;
    private int countInBox;
    private int countBox;

    @ToString.Exclude
    @OneToMany (mappedBy = "variantContainer", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<DescriptionContainer> descriptionContainers;

    @ToString.Exclude
    @OneToMany(mappedBy = "variantContainer", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Container> containers;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "order_container")
    private OrderContainer orderContainer;


    public VariantContainer(String numberVariant, int countInBox, int countBox, OrderContainer orderContainer) {
        this.numberVariant = numberVariant;
        this.countInBox = countInBox;
        this.countBox = countBox;
        this.orderContainer = orderContainer;
    }

}
