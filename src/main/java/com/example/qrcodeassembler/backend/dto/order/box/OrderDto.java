package com.example.qrcodeassembler.backend.dto.order.box;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
public class OrderDto {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private String number;
    private String date;
    private String status;
    private List<VariantBoxDto> variantBoxes;
    private List<DescriptionBoxDto> descriptionBoxes;
    private List<BoxDto> boxes;

    public Date convertDate() throws ParseException {
        return dateFormat.parse(date);
    }

}
