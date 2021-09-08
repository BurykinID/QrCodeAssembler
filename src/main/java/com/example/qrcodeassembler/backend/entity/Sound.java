package com.example.qrcodeassembler.backend.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "sound")
@Getter
@Setter
@Data
@NoArgsConstructor
@ToString
public class Sound {

    @Id
    @GeneratedValue
    @ToString.Exclude
    private long id;
    private String filename;
    private byte[] sound;


    public Sound(long id, String filename, byte[] sound) {
        this.id = id;
        this.filename = filename;
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
        return castObject.getId() == id &&
                castObject.getFilename().equals(filename) &&
                Arrays.equals(castObject.getSound(), sound);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, filename, sound);
    }
}
