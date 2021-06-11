package com.example.qrcodeassembler.backend.controller.post;

import com.example.qrcodeassembler.backend.entity.order.box.Box;
import com.example.qrcodeassembler.backend.entity.order.box.DescriptionBox;
import com.example.qrcodeassembler.backend.entity.order.box.Order;
import com.example.qrcodeassembler.backend.entity.order.box.VariantBox;
import com.example.qrcodeassembler.backend.repository.order.box.BoxRepository;
import com.example.qrcodeassembler.backend.repository.order.box.DescriptionBoxRepository;
import com.example.qrcodeassembler.backend.repository.order.box.OrderRepository;
import com.example.qrcodeassembler.backend.repository.order.box.VariantsBoxRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

@RestController
public class PostOrderController {

    private final BoxRepository boxRepository;
    private final DescriptionBoxRepository descriptionBoxRepository;
    private final VariantsBoxRepository variantsBoxRepository;
    private final OrderRepository orderRepository;

    public PostOrderController (BoxRepository boxRepository, DescriptionBoxRepository descriptionBoxRepository, VariantsBoxRepository variantsBoxRepository, OrderRepository orderRepository) {
        this.boxRepository = boxRepository;
        this.descriptionBoxRepository = descriptionBoxRepository;
        this.variantsBoxRepository = variantsBoxRepository;
        this.orderRepository = orderRepository;
    }


    @PostMapping("/post/insertOrders")
    public ResponseEntity<String> insertOrders(@RequestBody List<OrderDao> orderDaoList) {
        long countOrdersBeforeInsert = orderRepository.count();
        List<Order> orderList = new LinkedList<>();
        List<VariantBox> variantBoxes = new LinkedList<>();
        List<DescriptionBox> descriptionBoxes = new LinkedList<>();
        List<Box> boxList = new LinkedList<>();
        for (OrderDao orderDao : orderDaoList) {
            try {
                Order order = new Order(orderDao);
                orderList.add(order);

                List<VariantBox> variantBoxList = variantListFormation(orderDao, order);
                variantBoxes.addAll(variantBoxList);

                List<DescriptionBox> descriptionBoxList = descriptionBoxFormation(orderDao, variantBoxList);
                descriptionBoxes.addAll(descriptionBoxList);

                List<Box> boxes = boxFormation(orderDao, variantBoxList);
                boxList.addAll(boxes);

            } catch (ParseException e) {
                return new ResponseEntity<>("Incorrect data format.", HttpStatus.BAD_REQUEST);
            }
        }
        saveOrderInfo(orderList, variantBoxes, descriptionBoxes, boxList);
        long countInsertInTable = orderRepository.count() - countOrdersBeforeInsert;
        return new ResponseEntity<>("Добавлено записей: " + countInsertInTable, HttpStatus.OK);
    }

}
