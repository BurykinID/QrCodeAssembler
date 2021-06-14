package com.example.qrcodeassembler.backend.repository;

import com.example.qrcodeassembler.backend.entity.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GoodRepository extends JpaRepository<Good, Long> {

    Optional<Good> findByBarcode(String barcode);

}
