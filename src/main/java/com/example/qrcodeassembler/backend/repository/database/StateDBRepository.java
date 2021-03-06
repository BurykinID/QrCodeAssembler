package com.example.qrcodeassembler.backend.repository.database;

import com.example.qrcodeassembler.backend.entity.database.StateDataBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StateDBRepository extends JpaRepository<StateDataBase, Long> {

    @Query("select state from StateDataBase state order by state.id desc")
    List<StateDataBase> findAllSortByIdDesc();

}
