package com.example.qrcodeassembler.backend.dto.assembledContainerAndBox;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
public class AssembledMarkAndContainerDto {

    List<MarkDto> markJson;
    List<HierarchyOfBoxDto> containerJson;

}
