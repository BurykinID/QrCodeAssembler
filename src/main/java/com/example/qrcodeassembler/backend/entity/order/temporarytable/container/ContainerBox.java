package com.example.qrcodeassembler.backend.entity.order.temporarytable.container;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Entity
public class ContainerBox {

    @Id
    @GeneratedValue
    private UUID id;
    private String numberContainer;
    private String numberBox;
    private String macAddress;


    public ContainerBox() {
        id = null;
        numberContainer = "";
        numberBox = "";
        macAddress = "";
    }

    public ContainerBox(UUID id, String numberBox, String numberContainer, String macAddress) {
        this.id = id;
        this.numberContainer = numberContainer;
        this.numberBox = numberBox;
        this.macAddress = macAddress;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNumberBox() {
        return numberBox;
    }

    public void setNumberBox(String numberBox) {
        this.numberBox = numberBox;
    }

    public String getNumberContainer() {
        return numberContainer;
    }

    public void setNumberContainer(String nubmerContainer) {
        this.numberContainer = nubmerContainer;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
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
        return castObject.getId().equals(id) &&
                castObject.getNumberContainer().equals(numberContainer) &&
                castObject.getNumberBox().equals(numberBox) &&
                castObject.getMacAddress().equals(macAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberContainer, numberBox,  macAddress);
    }

    @Override
    public String toString() {
        return "ContainerBox=[" +
                "id=" + id +
                ",numberContainer=" + numberContainer +
                ",numberBox=" + numberBox +
                ",macAddress=" + macAddress +
                "]";
    }
}
