package com.example.qrcodeassembler.backend.repository.order.box;

import com.example.qrcodeassembler.backend.entity.order.box.DescriptionBox;
import com.example.qrcodeassembler.backend.entity.order.box.VariantBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface DescriptionBoxRepository extends JpaRepository<DescriptionBox, Long> {

    @Transactional(readOnly = true)
    List<DescriptionBox> findByVariantBox(VariantBox variantBox);

    @Transactional
    @Modifying
    @Query ("delete from description_box descriptionBox where descriptionBox.variantBox = :variantBox")
    void deleteByNumberVariant (@Param ("variantBox") VariantBox variantBox);

}
