package com.example.qrcodeassembler.backend.controller.post.container;

import com.example.qrcodeassembler.backend.dto.HierarchyOfBoxesDto;
import com.example.qrcodeassembler.backend.entity.HierarchyOfBoxes;
import com.example.qrcodeassembler.backend.repository.HierarchyOfBoxesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class HierarchyController {

    private final HierarchyOfBoxesRepository hierarchyOfBoxesRepository;

    public HierarchyController (HierarchyOfBoxesRepository hierarchyOfBoxesRepository) {
        this.hierarchyOfBoxesRepository = hierarchyOfBoxesRepository;
    }


    @PostMapping ("/post/insertHierarchy")
    public ResponseEntity<String> insertHierarchyContainers(@RequestBody List<HierarchyOfBoxesDto> hierarchyList) {
        long countHierarchyListBeforeInsert = hierarchyOfBoxesRepository.count();
        ArrayList<HierarchyOfBoxes> hierarchyOfBoxes = new ArrayList<>();
        for (HierarchyOfBoxesDto hierarchyOfBoxesDto : hierarchyList) {
            try {
                Optional<HierarchyOfBoxes> hierarchyOfBoxesFromDataBase = hierarchyOfBoxesRepository.findByNumberContainerAndNumberBox(hierarchyOfBoxesDto.getNumberContainer(), hierarchyOfBoxesDto.getNumberBox());
                HierarchyOfBoxes hierarchyOfBox;
                if (hierarchyOfBoxesFromDataBase.isPresent()) {
                    hierarchyOfBox = hierarchyOfBoxesFromDataBase.get();
                    hierarchyOfBox.update(hierarchyOfBoxesDto.getNumberContainer(), hierarchyOfBoxesDto.getNumberBox(), hierarchyOfBoxesDto.convertDate());
                    hierarchyOfBoxesRepository.save(hierarchyOfBox);
                }
                else {
                    hierarchyOfBox = new HierarchyOfBoxes(hierarchyOfBoxesDto.getNumberContainer(), hierarchyOfBoxesDto.getNumberBox(), hierarchyOfBoxesDto.convertDate());
                    hierarchyOfBoxes.add(hierarchyOfBox);
                }
            } catch (ParseException e) {
                return new ResponseEntity<>("Incorrect date format " + hierarchyOfBoxesDto.getDate(), HttpStatus.BAD_REQUEST);
            }
        }
        hierarchyOfBoxesRepository.saveAll(hierarchyOfBoxes);
        long countInsertInTable = hierarchyOfBoxesRepository.count() - countHierarchyListBeforeInsert;
        return new ResponseEntity<>("Добавлено записей: " + countInsertInTable + "\nОбновлено записей: " + (hierarchyList.size() - countInsertInTable), HttpStatus.OK);
    }

    @PostMapping ("/post/updateHierarchy")
    public ResponseEntity<String> updateHierarchyContainers(@RequestBody List<HierarchyOfBoxesDto> hierarchyList) {
        long countHierarchyListBeforeInsert = hierarchyOfBoxesRepository.count();
        List<HierarchyOfBoxes> hierarchyOfBoxesList = new ArrayList<>();
        for (HierarchyOfBoxesDto hierarchyOfBoxesDto : hierarchyList) {
            try {
                Optional<HierarchyOfBoxes> hierarchyOfBoxesFromDataBase = hierarchyOfBoxesRepository.findByNumberContainerAndNumberBox(hierarchyOfBoxesDto.getNumberContainer(), hierarchyOfBoxesDto.getNumberBox());
                HierarchyOfBoxes hierarchyOfBoxes;
                if (hierarchyOfBoxesFromDataBase.isPresent()) {
                    hierarchyOfBoxes = hierarchyOfBoxesFromDataBase.get();
                    hierarchyOfBoxes.update(hierarchyOfBoxesDto.getNumberContainer(), hierarchyOfBoxesDto.getNumberBox(), hierarchyOfBoxesDto.convertDate());
                }
                else {
                    hierarchyOfBoxes = new HierarchyOfBoxes(hierarchyOfBoxesDto.getNumberContainer(), hierarchyOfBoxesDto.getNumberBox(), hierarchyOfBoxesDto.convertDate());
                }
                hierarchyOfBoxesList.add(hierarchyOfBoxes);
            } catch (ParseException e) {
                return new ResponseEntity<>("Incorrect date format " + hierarchyOfBoxesDto.getDate(), HttpStatus.BAD_REQUEST);
            }
        }
        hierarchyOfBoxesRepository.saveAll(hierarchyOfBoxesList);
        long countInsertInTable = hierarchyOfBoxesRepository.count() - countHierarchyListBeforeInsert;
        return new ResponseEntity<>("Добавлено записей: " + countInsertInTable + "\nОбновлено записей: " + (hierarchyList.size() - countInsertInTable), HttpStatus.OK);
    }

}
