package com.example.qrcodeassembler.backend.repository;


import com.example.qrcodeassembler.backend.entity.Sound;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SoundRepository extends JpaRepository<Sound, Long> {

    Optional<Sound> findByFilename(String filename);

}
