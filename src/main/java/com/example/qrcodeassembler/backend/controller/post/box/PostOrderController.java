package com.example.qrcodeassembler.backend.controller.post.box;

import com.example.qrcodeassembler.backend.dto.order.box.OrderDto;
import com.example.qrcodeassembler.backend.entity.order.box.Order;
import com.example.qrcodeassembler.backend.repository.order.box.OrderRepository;
import com.example.qrcodeassembler.backend.service.order.box.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "post/")
public class PostOrderController {

    private final OrderService orderService;
    private final OrderRepository orderRepository;

    public PostOrderController(OrderService orderService, OrderRepository orderRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }


    @PostMapping("insertOrders")
    public void insertOrders(@RequestBody List<OrderDto> orderDtoList) {
        orderService.insertOrders(getNewOrders(orderDtoList));
    }

    @PostMapping("updateOrders")
    public ResponseEntity<String> updateOrders(@RequestBody List<OrderDto> orderDtoList) {
        Map<Boolean, List<OrderDto>> orderSortedByPresentInDataBase = orderDtoList.stream()
                .collect(Collectors.partitioningBy(orderService::isOrderPresentInDataBase));
        List<Order> newOrders = getNewOrders(orderSortedByPresentInDataBase.get(false));
        List<Order> orderForUpdate = getOrderForUpdate(orderSortedByPresentInDataBase.get(true));

        StringBuilder response = new StringBuilder("Result request: \r\n");
        if (newOrders.size() == orderSortedByPresentInDataBase.get(false).size()) {
            orderService.insertOrders(newOrders);
            response.append("1. New orders were inserted.\r\n");
        }
        else {
            response.append("1. When do new orders insert was throw Parse exception. You must check data format.\r\n");
        }
        if (orderForUpdate.size() == orderSortedByPresentInDataBase.get(true).size()) {
            orderService.updateOrders(orderForUpdate);
            response.append("2. Old orders were updated.\r\n");
        }
        else {
            response.append("2. When do old orders update was throw Parse exception. You must check data format.\r\n");
        }
        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }


    private List<Order> getNewOrders(List<OrderDto> newDtoOrders) {
        return newDtoOrders.stream()
                .map(dto -> {
                    try {
                        return dto.convertDtoToObject();
                    } catch (ParseException e) {
                        return new Order();
                    }
                })
                .filter(order -> order.getNumber() != null)
                .collect(Collectors.toList());
    }

    private List<Order> getOrderForUpdate(List<OrderDto> orderDtoForUpdate) {
        return orderDtoForUpdate.stream()
                .map(dto -> {
                    Order order = orderRepository.findByNumber(dto.getNumber()).get();
                    try {
                        order.update(dto.convertDate(), dto.getStatus());
                        return order;
                    } catch (ParseException e) {
                        return new Order();
                    }
                })
                .collect(Collectors.toList());
    }


/*    public void saveOrderInfo (List<Order> orderList,
                               List<VariantBox> variantBoxes,
                               List<DescriptionBox> descriptionBoxes,
                               List<Box> boxList) {
        orderRepository.saveAll(orderList);
        variantsBoxRepository.saveAll(variantBoxes);
        descriptionBoxRepository.saveAll(descriptionBoxes);
        boxRepository.saveAll(boxList);
    }


    private void updateRelatedWithOrderInfo(List<VariantBox> variantBoxes, List<DescriptionBox> descriptionBoxes, List<Box> boxList, OrderDto orderDto, Order order) {
        List<VariantBox> variantBoxList = convertToVariantBox(orderDto, order);
        variantBoxes.addAll(variantBoxList);
        descriptionBoxes.addAll(convertToDescriptionBox(orderDto, variantBoxList));
        boxList.addAll(convertToBox(orderDto, variantBoxList));
    }

    private void deleteRelatedWithOrderInfo(Order order) {
        List<VariantBox> variantBoxesForDelete = variantsBoxRepository.findByOrderNumber(order.getNumber());

        for (VariantBox varBox : variantBoxesForDelete)
            boxRepository.deleteBoxByVariantBox(varBox);

        for (VariantBox varBox : variantBoxesForDelete)
            descriptionBoxRepository.deleteByNumberVariant(varBox);

        variantsBoxRepository.deleteAll(variantBoxesForDelete);
    }

    private Order convertToOrder(OrderDto orderDto) throws ParseException {
        Date date = orderDto.convertDate();
        return new Order(orderDto.getNumber(), orderDto.getStatus(), date);
    }

    private List<VariantBox> convertToVariantBox(OrderDto orderDto, Order order) {
        List<VariantBox> variantBoxList = new LinkedList<>();
        for (VariantBoxDto dto : orderDto.getVariantBoxes()) {
            VariantBox box = new VariantBox(dto.getNumberVariant(), dto.getCountInBox(), dto.getCountBox(), order);
            variantBoxList.add(box);
        }
        return variantBoxList;
    }

    private List<DescriptionBox> convertToDescriptionBox(OrderDto orderDto, List<VariantBox> variantBoxList) {
        List<DescriptionBox> descriptionBoxList = new LinkedList<>();
        for (DescriptionBoxDto dto : orderDto.getDescriptionBoxes()) {
            VariantBox variantBox = new VariantBox();
            for (VariantBox searchVariantBox: variantBoxList) {
                if (searchVariantBox.getNumberVariant().equals(dto.getNumberVariant())) {
                    variantBox.update(searchVariantBox);
                }
            }
            DescriptionBox box = new DescriptionBox(dto.getBarcode(), dto.getNumberLine(), dto.getCount(), variantBox);
            descriptionBoxList.add(box);
        }
        return descriptionBoxList;
    }

    private List<Box> convertToBox(OrderDto orderDto, List<VariantBox> variantBoxList) {
        List<Box> boxes = new LinkedList<>();
        for (BoxDto dto : orderDto.getBoxes()) {
            VariantBox variantBox = new VariantBox();
            for (VariantBox searchVariantBox : variantBoxList) {
                if (searchVariantBox.getNumberVariant().equals(dto.getNumberVariant())) {
                    variantBox.update(searchVariantBox);
                }
            }
            Box box = new Box(dto.getNumberBox(), dto.getStatus(), variantBox);
            boxes.add(box);
        }
        return boxes;
    }*/

}
