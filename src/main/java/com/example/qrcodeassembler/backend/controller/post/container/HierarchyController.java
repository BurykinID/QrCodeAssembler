package com.example.qrcodeassembler.backend.controller.post.container;

import com.example.qrcodeassembler.backend.dto.HierarchyOfBoxesDto;
import com.example.qrcodeassembler.backend.service.item.HierarchyOfBoxesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HierarchyController {

    private final HierarchyOfBoxesService hierarchyOfBoxesService;

    @Autowired
    public HierarchyController(HierarchyOfBoxesService hierarchyOfBoxesService) {
        this.hierarchyOfBoxesService = hierarchyOfBoxesService;
    }


    @PostMapping ("/post/insertHierarchy")
    public void insertHierarchyContainers(@RequestBody List<HierarchyOfBoxesDto> hierarchyList) {
        hierarchyOfBoxesService.updateExistsHierarchyContainersOrInsertNewHierarchyContainers(hierarchyList);
    }

    @PostMapping ("/post/updateHierarchy")
    public void updateHierarchyContainers(@RequestBody List<HierarchyOfBoxesDto> hierarchyList) {
        hierarchyOfBoxesService.updateExistsHierarchyContainersOrInsertNewHierarchyContainers(hierarchyList);
    }

}
