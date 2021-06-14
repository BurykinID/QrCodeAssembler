package com.example.qrcodeassembler.backend.repository.order.box;

import com.example.qrcodeassembler.backend.entity.order.box.VariantBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface VariantsBoxRepository extends JpaRepository<VariantBox, Long> {

    Optional<VariantBox> findByNumberVariant(String numberVariant);

    @Query("select variantBox from variant_box variantBox where variantBox.order.number = :order_number")
    List<VariantBox> findByOrderNumber(@Param("order_number") String orderNumber);

    @Transactional
    @Modifying
    @Query ("delete from variant_box variantBox where variantBox.order.number = :order_number")
    void deleteByOrderNumber (@Param("order_number") String orderNumber);

}
