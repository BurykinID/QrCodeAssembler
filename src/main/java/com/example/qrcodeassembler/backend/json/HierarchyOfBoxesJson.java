package com.example.qrcodeassembler.backend.json;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HierarchyOfBoxesJson {

    private String numberContainer;
    private String numberBox;
    private String date;

    public HierarchyOfBoxesJson() {
    }

    public HierarchyOfBoxesJson(String numberContainer, String numberBox, String date) {
        this.numberContainer = numberContainer;
        this.numberBox = numberBox;
        this.date = date;
    }
}
