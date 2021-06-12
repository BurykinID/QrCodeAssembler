package com.example.qrcodeassembler.backend.dto.order.container;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
public class VariantContainerDto {

    private String numberVariant;
    private int countInBox;
    private int countBox;

}
