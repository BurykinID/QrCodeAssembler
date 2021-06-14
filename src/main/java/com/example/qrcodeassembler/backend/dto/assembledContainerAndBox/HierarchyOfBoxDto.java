package com.example.qrcodeassembler.backend.dto.assembledContainerAndBox;

import com.example.qrcodeassembler.backend.entity.HierarchyOfBoxes;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;

@Getter
@Setter
@Data
@NoArgsConstructor
public class HierarchyOfBoxDto {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private String numberBox;
    private String numberContainer;
    private String lastUpdate;


    public HierarchyOfBoxDto(HierarchyOfBoxes hierarchyOfBoxes) {
        this.numberBox = hierarchyOfBoxes.getNumberBox();
        this.numberContainer = hierarchyOfBoxes.getNumberContainer();
        this.lastUpdate = dateFormat.format(hierarchyOfBoxes.getDate());
    }

    public HierarchyOfBoxDto(String numberBox, String numberContainer, String lastUpdate) {
        this.numberBox = numberBox;
        this.numberContainer = numberContainer;
        this.lastUpdate = lastUpdate;
    }

}
