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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Container container = (Container) o;
        return Objects.equals(numberContainer, container.numberContainer) &&
                Objects.equals(status, container.status) &&
                Objects.equals(variantContainer, container.variantContainer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberContainer, status, variantContainer);
    }
}
