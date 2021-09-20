package com.example.qrcodeassembler.backend.entity.order.temporarytable.box;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class BoxMark {

    @Id
    @GeneratedValue
    @ToString.Exclude
    private long id;
    private String cis;
    private String numberBox;
    private String macAddress;


}
