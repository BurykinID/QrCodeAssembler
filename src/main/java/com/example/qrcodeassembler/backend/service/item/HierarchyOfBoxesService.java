package com.example.qrcodeassembler.backend.service.item;

import com.example.qrcodeassembler.backend.dto.HierarchyOfBoxesDto;
import com.example.qrcodeassembler.backend.dto.assembledContainerAndBox.HierarchyOfBoxDto;
import com.example.qrcodeassembler.backend.entity.HierarchyOfBoxes;
import com.example.qrcodeassembler.backend.repository.HierarchyOfBoxesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;

@Service
public class HierarchyOfBoxesService {

    private final HierarchyOfBoxesRepository hierarchyOfBoxesRepository;

    @Autowired
    public HierarchyOfBoxesService(HierarchyOfBoxesRepository hierarchyOfBoxesRepository) {
        this.hierarchyOfBoxesRepository = hierarchyOfBoxesRepository;
    }


    public void updateExistsHierarchyContainersOrInsertNewHierarchyContainers(List<HierarchyOfBoxesDto> hierarchyList) {
        ArrayList<HierarchyOfBoxes> hierarchyOfBoxes = new ArrayList<>();
        for (HierarchyOfBoxesDto hierarchyOfBoxesDto : hierarchyList) {
            Optional<HierarchyOfBoxes> hierarchyOfBoxesFromDataBase = hierarchyOfBoxesRepository.findByNumberContainerAndNumberBox(hierarchyOfBoxesDto.getNumberContainer(), hierarchyOfBoxesDto.getNumberBox());
            try {
                Date date = hierarchyOfBoxesDto.convertDate();
                hierarchyOfBoxesFromDataBase.ifPresentOrElse(
                        hierarchyOfBoxesForUpdate -> {
                            hierarchyOfBoxesForUpdate.update(hierarchyOfBoxesDto.getNumberContainer(), hierarchyOfBoxesDto.getNumberBox(), date);
                            hierarchyOfBoxes.add(hierarchyOfBoxesForUpdate);
                        },
                        () -> {
                            HierarchyOfBoxes hierarchyOfBox = new HierarchyOfBoxes(hierarchyOfBoxesDto.getNumberContainer(), hierarchyOfBoxesDto.getNumberBox(), date);
                            hierarchyOfBoxes.add(hierarchyOfBox);
                        }
                );
            } catch (ParseException e) {
                throw new IllegalStateException("In a " + hierarchyOfBoxesDto + " have a problem with a date format. " +
                        "Please correct it and try again. Info in a database didn't change.");
            }
        }
        hierarchyOfBoxesRepository.saveAll(hierarchyOfBoxes);
    }

    public List<HierarchyOfBoxDto> findAllAssembledContainerSinceDate(Date fromDate) {
        List<HierarchyOfBoxDto> containerDtoList = new ArrayList<>();
        List<HierarchyOfBoxes> container = hierarchyOfBoxesRepository.findByDate(fromDate);
        if (container.size() > 0) {
            for (HierarchyOfBoxes hierarchy : container) {
                containerDtoList.add(new HierarchyOfBoxDto(hierarchy));
            }
        }
        return containerDtoList;
    }
}
