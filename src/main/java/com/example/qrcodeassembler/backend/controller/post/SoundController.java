package com.example.qrcodeassembler.backend.controller.post;

import com.example.qrcodeassembler.backend.entity.Sound;
import com.example.qrcodeassembler.backend.repository.SoundRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@RestController
public class SoundController {

    private SoundRepository soundRepo;

    public SoundController(SoundRepository soundRepo) {
        this.soundRepo = soundRepo;
    }


    @PostMapping("/post/insertSound")
    public ResponseEntity<String> insertSound(@RequestBody List<MultipartFile> files) {
        try {
            for (MultipartFile file : files) {
                Sound sound = soundRepo.findByFilename(file.getOriginalFilename()).orElse(new Sound());
                if (sound.getId() == 0 || sound.getFilename().isEmpty()) {
                    sound.setFilename(file.getOriginalFilename());
                }
                sound.setSound(file.getBytes());
                soundRepo.save(sound);
            }
        }
        catch (IOException e) {
            return new ResponseEntity<String>("Содержимое файлов повреждено", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("Файлы были сохранены в базу",HttpStatus.OK);
    }

}
