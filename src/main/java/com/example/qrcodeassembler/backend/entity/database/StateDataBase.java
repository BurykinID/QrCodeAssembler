package com.example.qrcodeassembler.backend.entity.database;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class StateDataBase {

    @Id
    @GeneratedValue
    @ToString.Exclude
    private long id;
    private boolean lock;
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeEvent;

}
