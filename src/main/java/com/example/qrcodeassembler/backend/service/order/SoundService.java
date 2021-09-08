package com.example.qrcodeassembler.backend.service.order;

import com.example.qrcodeassembler.backend.entity.Sound;
import com.example.qrcodeassembler.backend.repository.SoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SoundService {

    private final SoundRepository soundRepository;

    @Autowired
    public SoundService(SoundRepository soundRepository) {
        this.soundRepository = soundRepository;
    }

    public void insertSounds(List<MultipartFile> files) {
        files.forEach(
            file -> {
                Optional<Sound> sound = soundRepository.findByFilename(file.getOriginalFilename());
                sound.ifPresentOrElse(
                    soundForUpdate -> {
                        try {
                            soundForUpdate.setSound(file.getBytes());
                            soundRepository.save(soundForUpdate);
                        } catch (IOException e) {
                            throw new IllegalStateException("File with name=\'" + file.getOriginalFilename() + "\' .getBytes() throw an Exception.\n" + e.getMessage());
                        }
                    },
                    () -> {
                        try {
                            Sound updateSound = new Sound();
                            updateSound.setFilename(file.getOriginalFilename());
                            updateSound.setSound(file.getBytes());
                            soundRepository.save(updateSound);
                        } catch (IOException e) {
                            throw new IllegalStateException("File with name=\'" + file.getOriginalFilename() + "\' .getBytes() throw an Exception.\n" + e.getMessage());
                        }
                    }
                );
            }
        );
    }
}
