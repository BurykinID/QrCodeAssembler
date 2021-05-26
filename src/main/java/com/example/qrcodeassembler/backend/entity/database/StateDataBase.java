package com.example.qrcodeassembler.backend.entity.database;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
public class StateDataBase {

    @Id
    @GeneratedValue
    private UUID id;
    private boolean lock;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStartBlock;


    public StateDataBase() {
        this.id = null;
        this.lock = false;
        this.description = "";
        this.timeStartBlock = new Date();
    }

    public StateDataBase(UUID id, boolean lock, String description, Date timeStartBlock) {
        this.id = id;
        this.lock = lock;
        this.description = description;
        this.timeStartBlock = timeStartBlock;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean getLock() {
        return lock;
    }

    public void setLock(boolean lock) {
        this.lock = lock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTimeStartBlock() {
        return timeStartBlock;
    }

    public void setTimeStartBlock(Date timeStartBlock) {
        this.timeStartBlock = timeStartBlock;
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
        return castObject.getId().equals(id) &&
                castObject.getLock() == lock &&
                castObject.getDescription().equals(description) &&
                castObject.getTimeStartBlock().equals(timeStartBlock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lock, description, timeStartBlock);
    }

    @Override
    public String toString() {
        return "StateDataBase=[" +
                "id=" + id +
                ",lock=" + lock +
                ",description=" + description +
                ",timeStartBlock=" + timeStartBlock +
                "]";
    }
}
