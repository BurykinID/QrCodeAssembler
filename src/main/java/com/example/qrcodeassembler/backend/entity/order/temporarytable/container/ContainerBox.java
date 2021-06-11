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
public class ContainerBox {

    @Id
    @GeneratedValue
    @ToString.Exclude
    private long id;
    private String numberContainer;
    private String numberBox;
    private String macAddress;


    public ContainerBox() {
        id = 0;
        numberContainer = "";
        numberBox = "";
        macAddress = "";
    }

    public ContainerBox(long id, String numberBox, String numberContainer, String macAddress) {
        this.id = id;
        this.numberContainer = numberContainer;
        this.numberBox = numberBox;
        this.macAddress = macAddress;
    }


    @Override
    public boolean equals(Object comparedObject) {
        if (this == comparedObject) {
            return true;
        }

        if (comparedObject == null || getClass() != comparedObject.getClass()) {
            return false;
        }

        ContainerBox castObject = (ContainerBox) comparedObject;
        return castObject.getId() == id &&
                castObject.getNumberContainer().equals(numberContainer) &&
                castObject.getNumberBox().equals(numberBox) &&
                castObject.getMacAddress().equals(macAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberContainer, numberBox,  macAddress);
    }
}
