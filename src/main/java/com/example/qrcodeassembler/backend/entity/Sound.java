package com.example.qrcodeassembler.backend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "sound")
public class Sound {

    @Id
    @GeneratedValue
    private UUID id;
    private String filename;
    private byte[] sound;

    public Sound() {
        this.filename = "";
        this.sound = null;
    }

    public Sound(UUID id, String filename, byte[] sound) {
        this.id = id;
        this.filename = filename;
        this.sound = sound;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getSound() {
        return sound;
    }

    public void setSound(byte[] sound) {
        this.sound = sound;
    }


    @Override
    public boolean equals(Object comparedObject) {
        if (this == comparedObject) {
            return true;
        }

        if (comparedObject == null) {
            return false;
        }
        else if (getClass() != comparedObject.getClass()) {
            return false;
        }

        Sound castObject = (Sound) comparedObject;
        return castObject.getId().equals(id) &&
                castObject.getFilename().equals(filename) &&
                Arrays.equals(castObject.getSound(), sound);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, filename, sound);
    }

    @Override
    public String toString() {
        return "Sound=[" +
                "id=" + id +
                ",filename=" + filename +
                ",sound=" + sound +
                "]";
    }

}
