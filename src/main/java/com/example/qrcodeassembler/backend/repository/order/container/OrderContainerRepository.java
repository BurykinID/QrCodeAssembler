package com.example.qrcodeassembler.backend.repository.order.container;

import com.example.qrcodeassembler.backend.entity.order.container.OrderContainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderContainerRepository extends JpaRepository<OrderContainer, Long> {

    Optional<OrderContainer> findByNumber(String number);

}
