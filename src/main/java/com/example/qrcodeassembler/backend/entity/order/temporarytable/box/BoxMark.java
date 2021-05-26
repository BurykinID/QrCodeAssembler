package com.example.qrcodeassembler.backend.entity.order.temporarytable.box;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Entity
public class BoxMark {

    @Id
    @GeneratedValue
    private UUID id;
    private String cis;
    private String numberBox;
    private String macAddress;


    public BoxMark() {
        id = null;
        cis = "";
        numberBox = "";
        macAddress = "";
    }

    public BoxMark(UUID id, String cis, String numberBox, String macAddress) {
        this.id = id;
        this.cis = cis;
        this.numberBox = numberBox;
        this.macAddress = macAddress;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCis() {
        return cis;
    }

    public void setCis(String cis) {
        this.cis = cis;
    }

    public String getNumberBox() {
        return numberBox;
    }

    public void setNumberBox(String numberBox) {
        this.numberBox = numberBox;
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

        BoxMark castObject = (BoxMark) comparedObject;
        return castObject.getId().equals(id) &&
                castObject.getCis().equals(cis) &&
                castObject.getNumberBox().equals(numberBox) &&
                castObject.getMacAddress().equals(macAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cis, numberBox, macAddress);
    }

    @Override
    public String toString() {
        return "BoxMark[" +
                "id=" + id +
                ",cis=" + cis +
                ",numberBox=" + numberBox +
                ",macAddress=" + macAddress +
                "]";
    }
}
