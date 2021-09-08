package com.example.qrcodeassembler.backend.service.order.box;

import com.example.qrcodeassembler.backend.dto.order.box.OrderDto;
import com.example.qrcodeassembler.backend.entity.order.box.Order;
import com.example.qrcodeassembler.backend.repository.order.box.OrderRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public boolean isOrderPresentInDataBase(OrderDto orderDto) {
        return orderRepository.findByNumber(orderDto.getNumber()).isPresent();
    }

    public void insertOrders(List<Order> orders) {
        orderRepository.saveAll(orders);
    }

    public void updateOrders(List<Order> orders) {
        orderRepository.saveAll(orders);
    }

    private Order convertToOrder(OrderDto orderDto) throws ParseException {
        Date date = orderDto.convertDate();
        return new Order(orderDto.getNumber(), orderDto.getStatus(), date);
    }



/*
    private void updateRelatedWithOrderInfo(List< VariantBox > variantBoxes, List< DescriptionBox > descriptionBoxes, List< Box > boxList, OrderDto orderDto, Order order) {
        List<VariantBox> variantBoxList = convertToVariantBox(orderDto, order);
        variantBoxes.addAll(variantBoxList);
        descriptionBoxes.addAll(convertToDescriptionBox(orderDto, variantBoxList));
        boxList.addAll(convertToBox(orderDto, variantBoxList));
    }*/



}
