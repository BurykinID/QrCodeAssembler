package com.example.qrcodeassembler.backend.entity.order.temporarytable.container;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class ContainerContent {

    @Id
    @GeneratedValue
    @ToString.Exclude
    private long id;
    private String numberContainer;
    private String numberVariantBox;
    private int countNow;
    private int countNeed;
    private String macAddress;

}
