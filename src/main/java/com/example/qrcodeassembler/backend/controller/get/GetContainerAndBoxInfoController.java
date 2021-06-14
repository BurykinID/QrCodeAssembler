package com.example.qrcodeassembler.backend.controller.get;

import com.example.qrcodeassembler.backend.dto.assembledContainerAndBox.AssembledMarkAndContainerDto;
import com.example.qrcodeassembler.backend.service.HierarchyOfBoxesService;
import com.example.qrcodeassembler.backend.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(path = "get/")
public class GetContainerAndBoxInfoController {

    private final HierarchyOfBoxesService hierarchyOfBoxesService;
    private final MarkService markService;

    @Autowired
    public GetContainerAndBoxInfoController(HierarchyOfBoxesService hierarchyOfBoxesService,
                                            MarkService markService) {
        this.hierarchyOfBoxesService = hierarchyOfBoxesService;
        this.markService = markService;
    }


    @GetMapping("assembled/order")
    public AssembledMarkAndContainerDto getAssembledBoxAndContainersFromDateToNow(@RequestParam("dateFrom") String dateFrom) {
        AssembledMarkAndContainerDto completedMarkAndContainer = new AssembledMarkAndContainerDto();
        Date date;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            date = dateFormat.parse(dateFrom);
        } catch (ParseException e) {
            return new AssembledMarkAndContainerDto();
        }
        completedMarkAndContainer.setContainerJson(hierarchyOfBoxesService.findAllAssembledContainerSinceDate(date));
        completedMarkAndContainer.setMarkJson(markService.findAllAssembledMarkSinceDate(date));
        return completedMarkAndContainer;
    }

}
