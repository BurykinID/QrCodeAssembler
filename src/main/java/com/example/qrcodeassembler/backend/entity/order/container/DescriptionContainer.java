package com.example.qrcodeassembler.backend.entity.order.container;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@ToString
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DescriptionContainer descriptionContainer = (DescriptionContainer) o;
        return numberLine == descriptionContainer.numberLine &&
                count == descriptionContainer.count &&
                id.equals(descriptionContainer.id) &&
                numberVariantBox.equals(descriptionContainer.numberVariantBox);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberLine, numberVariantBox, count);
    }
}
