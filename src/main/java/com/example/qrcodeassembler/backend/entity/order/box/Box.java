package com.example.qrcodeassembler.backend.entity.order.box;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "box")
@Getter
@Setter
@Data
@NoArgsConstructor
@ToString
public class Box {

    @Id
    @Column(unique = true)
    private String numberBox;
    private String status;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "number_variant")
    private VariantBox variantBox;


    public Box(String numberBox, String status, VariantBox variantBox) {
        this.numberBox = numberBox;
        this.status = status;
        this.variantBox = variantBox;
    }


    @Override
    public boolean equals(Object comparedObject) {
        if (this == comparedObject)  {
            return true;
        }

        if (comparedObject == null) {
            return false;
        }
        else if (getClass() != comparedObject.getClass()) {
            return false;
        }

        Box castObject = (Box) comparedObject;
        return castObject.getNumberBox().equals(numberBox) &&
                castObject.getStatus().equals(status) &&
                castObject.getVariantBox().equals(variantBox);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberBox, status, variantBox);
    }

}
