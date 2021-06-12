package com.example.qrcodeassembler.backend.dto.order.container;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
public class DescriptionContainerDto {

    private int numberLine;
    private String numberVariantBox;
    private int count;
    private String numberVariant;

}
