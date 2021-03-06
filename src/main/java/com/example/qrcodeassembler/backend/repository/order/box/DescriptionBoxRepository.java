package com.example.qrcodeassembler.backend.repository.order.box;

import com.example.qrcodeassembler.backend.entity.order.box.DescriptionBox;
import com.example.qrcodeassembler.backend.entity.order.box.VariantBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface DescriptionBoxRepository extends JpaRepository<DescriptionBox, Long> {

    List<DescriptionBox> findByVariantBox(VariantBox variantBox);

    @Transactional
    @Modifying
    @Query ("delete from description_box descriptionBox where descriptionBox.variantBox = :variantBox")
    void deleteByNumberVariant (@Param ("variantBox") VariantBox variantBox);

}
