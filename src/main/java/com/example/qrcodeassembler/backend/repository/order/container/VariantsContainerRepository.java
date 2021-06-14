package com.example.qrcodeassembler.backend.repository.order.container;


import com.example.qrcodeassembler.backend.entity.order.container.VariantContainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface VariantsContainerRepository extends JpaRepository<VariantContainer, Long> {

    Optional<VariantContainer> findByNumberVariant(String numberVariant);

    @Query("select variantContainer from VariantContainer variantContainer where variantContainer.orderContainer.number = :number_container")
    List<VariantContainer> findByOrderContainer(@Param("number_container") String numberContainer);

    @Transactional
    @Modifying
    @Query("delete from VariantContainer variantContainer where variantContainer.orderContainer.number = :number_container")
    void deleteByOrderContainerNumber(@Param("number_container") String number_container);

}
