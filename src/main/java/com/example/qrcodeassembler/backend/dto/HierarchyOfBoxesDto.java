package com.example.qrcodeassembler.backend.dto;

import lombok.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@ToString
@Data
@NoArgsConstructor
public class HierarchyOfBoxesDto {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private String numberContainer;
    private String numberBox;
    private String date;


    public Date convertDate() throws ParseException {
        return dateFormat.parse(date);
    }

}
