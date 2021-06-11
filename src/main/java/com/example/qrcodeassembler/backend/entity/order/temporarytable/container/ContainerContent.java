package com.example.qrcodeassembler.backend.entity.order.temporarytable.container;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
public class ContainerContent {

    @Id
    @GeneratedValue
    @ToString.Exclude
    private long id;
    private String numberContainer;
    private String numberVariantBox;
    private int countNow;
    private int countNeed;
    private String macAddress;


    public ContainerContent() {
        id = 0;
        numberContainer = "";
        numberVariantBox = "";
        countNow = 0;
        countNeed = 0;
        macAddress = "";
    }

    public ContainerContent(long id, String numberContainer, String numberVariantBox, int countNow, int countNeed, String macAddress) {
        this.id = id;
        this.numberContainer = numberContainer;
        this.numberVariantBox = numberVariantBox;
        this.countNow = countNow;
        this.countNeed = countNeed;
        this.macAddress = macAddress;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ContainerContent castObject = (ContainerContent) o;
        return castObject.getId() == id &&
                castObject.getNumberContainer().equals(numberContainer) &&
                castObject.getNumberVariantBox().equals(numberVariantBox) &&
                castObject.getCountNow() == countNow &&
                castObject.getCountNeed() == countNeed &&
                castObject.getMacAddress().equals(macAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberContainer, numberVariantBox, countNow, countNeed, macAddress);
    }
}
