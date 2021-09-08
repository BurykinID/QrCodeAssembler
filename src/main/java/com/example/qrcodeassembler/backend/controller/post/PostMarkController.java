package com.example.qrcodeassembler.backend.controller.post;

import com.example.qrcodeassembler.backend.entity.Mark;
import com.example.qrcodeassembler.backend.service.item.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class PostMarkController {

    private final MarkService markService;

    @Autowired
    public PostMarkController(MarkService markService) {
        this.markService = markService;
    }

    @PostMapping("/post/insertMarks")
    public void insertMark(@RequestBody List<Mark> marks) {
        markService.insertMark(marks);
    }

    @PostMapping("/post/updateMarks")
    public void updateMark(@RequestBody List<Mark> marks) {
        Map<Boolean, List<Mark>> sortedMarkByPresentInDataBase = marks.stream().collect(Collectors.partitioningBy(markService::isMarkInDataBase));
        markService.insertMark(sortedMarkByPresentInDataBase.get(false));
        markService.updateMark(sortedMarkByPresentInDataBase.get(true));
    }
}
