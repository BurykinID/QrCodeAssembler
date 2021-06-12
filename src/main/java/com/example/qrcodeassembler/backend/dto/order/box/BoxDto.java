package com.example.qrcodeassembler.backend.dto.order.box;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
public class BoxDto {

    private String numberVariant;
    private String numberBox;
    private String status;

}
