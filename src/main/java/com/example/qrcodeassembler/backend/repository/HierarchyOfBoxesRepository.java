package com.example.qrcodeassembler.backend.repository;

import com.example.qrcodeassembler.backend.entity.HierarchyOfBoxes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface HierarchyOfBoxesRepository extends JpaRepository<HierarchyOfBoxes, Long> {

    Optional<HierarchyOfBoxes> findByNumberBox(String numberBox);
    Optional<HierarchyOfBoxes> findByNumberContainer(String numberContainer);

    @Query ("select cont from HierarchyOfBoxes cont where cont.date >= :date and (cont.numberBox is not null and cont.numberBox <> '')")
    List<HierarchyOfBoxes> findByDate(@Param ("date") Date date);

    Optional<HierarchyOfBoxes> findByNumberContainerAndNumberBox(String numberContainer, String numberBox);;

}
