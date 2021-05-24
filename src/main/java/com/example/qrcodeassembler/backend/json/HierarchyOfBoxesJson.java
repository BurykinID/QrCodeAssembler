package com.example.qrcodeassembler.backend.json;

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


    public String getNumberContainer() {
        return numberContainer;
    }

    public void setNumberContainer(String numberContainer) {
        this.numberContainer = numberContainer;
    }

    public String getNumberBox() {
        return numberBox;
    }

    public void setNumberBox(String numberBox) {
        this.numberBox = numberBox;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
