package com.example.qrcodeassembler.backend.dto.order.box;

import com.example.qrcodeassembler.backend.entity.order.box.Box;
import com.example.qrcodeassembler.backend.entity.order.box.DescriptionBox;
import com.example.qrcodeassembler.backend.entity.order.box.Order;
import com.example.qrcodeassembler.backend.entity.order.box.VariantBox;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    public Order convertDtoToObject() throws ParseException {
        return new Order(number, status, convertDate());
    }

}
