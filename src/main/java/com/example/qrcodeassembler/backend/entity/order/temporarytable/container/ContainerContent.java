package com.example.qrcodeassembler.backend.entity.order.temporarytable.container;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Entity
public class ContainerContent {

    @Id
    @GeneratedValue
    private UUID id;
    private String numberContainer;
    private String numberVariantBox;
    private int countNow;
    private int countNeed;
    private String macAddress;


    public ContainerContent() {
        id = null;
        numberContainer = "";
        numberVariantBox = "";
        countNow = 0;
        countNeed = 0;
        macAddress = "";
    }

    public ContainerContent(UUID id, String numberContainer, String numberVariantBox, int countNow, int countNeed, String macAddress) {
        this.id = id;
        this.numberContainer = numberContainer;
        this.numberVariantBox = numberVariantBox;
        this.countNow = countNow;
        this.countNeed = countNeed;
        this.macAddress = macAddress;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNumberContainer() {
        return numberContainer;
    }

    public void setNumberContainer(String numberContainer) {
        this.numberContainer = numberContainer;
    }

    public String getNumberVariantBox() {
        return numberVariantBox;
    }

    public void setNumberVariantBox(String numberVariantBox) {
        this.numberVariantBox = numberVariantBox;
    }

    public int getCountNow() {
        return countNow;
    }

    public void setCountNow(int countNow) {
        this.countNow = countNow;
    }

    public int getCountNeed() {
        return countNeed;
    }

    public void setCountNeed(int countNeed) {
        this.countNeed = countNeed;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
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
        return castObject.getId().equals(id) &&
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

    @Override
    public String toString() {
        return "ContainerContent=[" +
                "id=" + id +
                ",numberContainer=" + numberContainer +
                ",numberVariantBox=" + numberVariantBox +
                ",countNow=" + countNow +
                ",countNeed=" + countNeed +
                ",macAddress=" + macAddress +
                "]";
    }
}
