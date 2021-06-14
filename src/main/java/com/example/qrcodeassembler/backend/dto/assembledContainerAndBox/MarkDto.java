package com.example.qrcodeassembler.backend.dto.assembledContainerAndBox;

import com.example.qrcodeassembler.backend.entity.Mark;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;

@Getter
@Setter
@Data
@NoArgsConstructor
public class MarkDto {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("");

    private String cis;
    private String numberBox;
    private String lastUpdate;


    public MarkDto(Mark mark) {
        this.cis = mark.getCis();
        this.numberBox = mark.getNumberBox();
        this.lastUpdate = dateFormat.format(mark.getDate());
    }

    public MarkDto(String cis, String numberBox, String date) {
        this.cis = cis;
        this.numberBox = numberBox;
        this.lastUpdate = date;
    }
}
