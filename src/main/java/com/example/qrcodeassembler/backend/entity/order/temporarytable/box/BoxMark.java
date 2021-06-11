package com.example.qrcodeassembler.backend.entity.order.temporarytable.box;

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
public class BoxMark {

    @Id
    @GeneratedValue
    @ToString.Exclude
    private long id;
    private String cis;
    private String numberBox;
    private String macAddress;


    public BoxMark() {
        id = 0;
        cis = "";
        numberBox = "";
        macAddress = "";
    }

    public BoxMark(long id, String cis, String numberBox, String macAddress) {
        this.id = id;
        this.cis = cis;
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

        BoxMark castObject = (BoxMark) comparedObject;
        return castObject.getId() == id &&
                castObject.getCis().equals(cis) &&
                castObject.getNumberBox().equals(numberBox) &&
                castObject.getMacAddress().equals(macAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cis, numberBox, macAddress);
    }
}
