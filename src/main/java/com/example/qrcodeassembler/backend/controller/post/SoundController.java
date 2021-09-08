package com.example.qrcodeassembler.backend.controller.post;

import com.example.qrcodeassembler.backend.service.order.SoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path = "post/")
public class SoundController {

    private final SoundService soundService;

    @Autowired
    public SoundController(SoundService soundService) {
        this.soundService = soundService;
    }

    @PostMapping("insertSound")
    public void insertSound(@RequestBody List<MultipartFile> files) {
        soundService.insertSounds(files);
    }

}
