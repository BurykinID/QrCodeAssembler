package com.example.qrcodeassembler.backend.repository.order.box;

import com.example.qrcodeassembler.backend.entity.order.box.Box;
import com.example.qrcodeassembler.backend.entity.order.box.VariantBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface BoxRepository extends JpaRepository<Box, Long> {

    @Transactional(readOnly = true)
    Optional<Box> findByNumberBox(@Param("numberBox") String numberBox);

    @Transactional(readOnly = true)
    Optional<Box> findByStatus(@Param("status") String status);

    @Transactional
    @Modifying
    @Query ("delete from box b where b.variantBox = :variantBox")
    void deleteBoxByVariantBox(@Param("variantBox") VariantBox variantBox);

}
