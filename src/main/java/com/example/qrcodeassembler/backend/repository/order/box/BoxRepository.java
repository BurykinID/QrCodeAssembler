package com.example.qrcodeassembler.backend.repository.order.box;

import com.example.qrcodeassembler.backend.entity.order.box.Box;
import com.example.qrcodeassembler.backend.entity.order.box.VariantBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface BoxRepository extends JpaRepository<Box, Long> {

    @Query("select box from box box where box.numberBox = :numberBox")
    Optional<Box> findByNumberBox(@Param("numberBox") String numberBox);

    @Query("select box from box box where box.status = :status")
    Optional<Box> findByStatus(@Param("status") String status);

    @Transactional
    @Modifying
    @Query ("delete from box b where b.variantBox = :variantBox")
    void deleteByVariantBox (@Param("variantBox") VariantBox variantBox);

}
