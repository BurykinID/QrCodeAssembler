package com.example.qrcodeassembler.backend.controller.post;

import com.example.qrcodeassembler.backend.entity.Mark;
import com.example.qrcodeassembler.backend.repository.MarkRepository;
import com.example.qrcodeassembler.backend.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
        markService.updateExistsMarkOrInsertNewMark(marks);
    }
}
