package com.example.qrcodeassembler.backend.dto.order.box;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
public class DescriptionBoxDto {

    private String numberVariant;
    private int numberLine;
    private String barcode;
    private int count;

}
