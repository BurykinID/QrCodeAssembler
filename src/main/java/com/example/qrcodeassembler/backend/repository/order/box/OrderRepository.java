package com.example.qrcodeassembler.backend.repository.order.box;

import com.example.qrcodeassembler.backend.entity.order.box.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Optional<Order> findByNumber(String numberOrder);

}
