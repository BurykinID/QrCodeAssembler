package com.example.qrcodeassembler.backend.service;

import com.example.qrcodeassembler.backend.entity.Sound;
import com.example.qrcodeassembler.backend.repository.SoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SoundService {

    private final SoundRepository soundRepository;

    @Autowired
    public SoundService(SoundRepository soundRepository) {
        this.soundRepository = soundRepository;
    }

    public void insertSounds(List<MultipartFile> files) {
        List<Sound> soundList = new ArrayList<>();
        for (MultipartFile file : files) {
            Sound sound = soundRepository.findByFilename(file.getOriginalFilename()).orElse(new Sound());
            if (sound.getId() == 0 || sound.getFilename().isEmpty()) {
                sound.setFilename(file.getOriginalFilename());
            }
            try {
                sound.setSound(file.getBytes());
            }
            catch (IOException e) {
                throw new IllegalStateException("File with name=\'" + file.getOriginalFilename() + "\' .getBytes() throw an Exception.\n" + e.getMessage());
            }
        }
        soundRepository.saveAll(soundList);
    }
}
