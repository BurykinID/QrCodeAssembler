package com.example.qrcodeassembler.backend.entity.database.log;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
public class LogSession {

    @Id
    @GeneratedValue
    private long id;
    @Enumerated(EnumType.STRING)
    private LvlEvent lvlEvent;
    private String barcode;
    private String macAddress;
    private String phase;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeEvent;


    public LogSession() {
    }

    public LogSession(long id, LvlEvent lvlEvent, String barcode, String macAddress, String phase, Date timeEvent) {
        this.id = id;
        this.lvlEvent = lvlEvent;
        this.barcode = barcode;
        this.macAddress = macAddress;
        this.phase = phase;
        this.timeEvent = timeEvent;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LogSession castObject = (LogSession) o;
        return castObject.getId() == id &&
                castObject.getLvlEvent().equals(lvlEvent) &&
                castObject.getBarcode().equals(barcode) &&
                castObject.getMacAddress().equals(macAddress) &&
                castObject.getPhase().equals(phase) &&
                castObject.getTimeEvent().equals(timeEvent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lvlEvent, barcode, macAddress, phase, timeEvent);
    }
}
