package com.example.qrcodeassembler.backend.entity.database;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class StateDataBase {

    @Id
    @GeneratedValue
    @ToString.Exclude
    private long id;
    private boolean lock;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeEvent;


    public StateDataBase() {
        this.id = 0;
        this.lock = false;
        this.description = "";
        this.timeEvent = new Date();
    }

    public StateDataBase(long id, boolean lock, String description, Date timeEvent) {
        this.id = id;
        this.lock = lock;
        this.description = description;
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

        StateDataBase castObject = (StateDataBase) o;
        return castObject.getId() == id &&
                castObject.isLock() == lock &&
                castObject.getDescription().equals(description) &&
                castObject.getTimeEvent().equals(timeEvent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lock, description, timeEvent);
    }
}
