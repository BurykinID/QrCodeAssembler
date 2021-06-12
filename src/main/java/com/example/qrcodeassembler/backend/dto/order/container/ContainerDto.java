package com.example.qrcodeassembler.backend.dto.order.container;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
public class ContainerDto {

    private String numberVariant;
    private String numberContainer;
    private String status;

}
