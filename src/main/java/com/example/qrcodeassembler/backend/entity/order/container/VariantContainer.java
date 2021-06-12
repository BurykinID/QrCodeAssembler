package com.example.qrcodeassembler.backend.entity.order.container;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@ToString
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VariantContainer variantContainer = (VariantContainer) o;
        return countInBox == variantContainer.countInBox &&
                countBox == variantContainer.countBox &&
                Objects.equals(numberVariant, variantContainer.numberVariant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberVariant, countInBox, countBox);
    }
}
