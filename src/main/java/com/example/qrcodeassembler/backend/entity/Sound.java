package com.example.qrcodeassembler.backend.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "sound")
@Data
@NoArgsConstructor
public class Sound {

    @Id
    @GeneratedValue
    @ToString.Exclude
    private long id;
    private String filename;
    private byte[] sound;


}
