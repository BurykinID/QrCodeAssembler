package com.example.qrcodeassembler.backend.dto.order.container;

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
public class OrderContainerDto {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private String number;
    private String date;
    private String status;
    private List<VariantContainerDto> variantContainers;
    private List<DescriptionContainerDto> descriptionContainers;
    private List<ContainerDto> containers;


    public Date getDateInObject() throws ParseException {
        return dateFormat.parse(date);
    }

}
