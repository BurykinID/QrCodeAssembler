package com.example.qrcodeassembler.backend.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity(name = "good")
@Data
@NoArgsConstructor
public class Good {

    @Id
    @Column(unique = true)
    private String barcode;
    private String name;
    private String article;
    private String color;
    private String size;


    public void updateGood(String name, String article, String color, String size) {
        this.name = name;
        this.article = article;
        this.color = color;
        this.size = size;
    }
}
