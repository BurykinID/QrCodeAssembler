package com.example.qrcodeassembler.backend.entity.database.log;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
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

}
