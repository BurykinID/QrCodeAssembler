package com.example.qrcodeassembler.backend.controller.post.box;

import com.example.qrcodeassembler.backend.dto.order.box.BoxDto;
import com.example.qrcodeassembler.backend.dto.order.box.DescriptionBoxDto;
import com.example.qrcodeassembler.backend.dto.order.box.VariantBoxDto;
import com.example.qrcodeassembler.backend.entity.order.box.Box;
import com.example.qrcodeassembler.backend.entity.order.box.DescriptionBox;
import com.example.qrcodeassembler.backend.entity.order.box.Order;
import com.example.qrcodeassembler.backend.entity.order.box.VariantBox;
import com.example.qrcodeassembler.backend.dto.order.box.OrderDto;
import com.example.qrcodeassembler.backend.repository.order.box.BoxRepository;
import com.example.qrcodeassembler.backend.repository.order.box.DescriptionBoxRepository;
import com.example.qrcodeassembler.backend.repository.order.box.OrderRepository;
import com.example.qrcodeassembler.backend.repository.order.box.VariantsBoxRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "post/")
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


    @PostMapping("insertOrders")
    public ResponseEntity<String> insertOrders(@RequestBody List<OrderDto> orderDtoList) {
        long countOrdersBeforeInsert = orderRepository.count();
        List<Order> orderList = new LinkedList<>();
        List<VariantBox> variantBoxes = new LinkedList<>();
        List<DescriptionBox> descriptionBoxes = new LinkedList<>();
        List<Box> boxList = new LinkedList<>();
        for (OrderDto orderDto : orderDtoList) {
            try {
                Optional<Order> orderFromDataBase = orderRepository.findByNumber(orderDto.getNumber());
                Order order;
                if (orderFromDataBase.isPresent()) {
                    order = orderFromDataBase.get();
                    order.update(orderDto.convertDate(), orderDto.getStatus());
                    orderRepository.save(order);
                    deleteRelatedWithOrderInfo(order);
                    updateRelatedWithOrderInfo(variantBoxes, descriptionBoxes, boxList, orderDto, order);
                }
                else {
                    order = convertToOrder(orderDto);
                    orderList.add(order);
                    updateRelatedWithOrderInfo(variantBoxes, descriptionBoxes, boxList, orderDto, order);
                }
            } catch (ParseException e) {
                return new ResponseEntity<>("Incorrect data format.", HttpStatus.BAD_REQUEST);
            }
        }
        saveOrderInfo(orderList, variantBoxes, descriptionBoxes, boxList);
        long countInsertInTable = orderRepository.count() - countOrdersBeforeInsert;
        return new ResponseEntity<>("Добавлено записей: " + countInsertInTable + "\nОбновлено записей: " + (orderDtoList.size() - countInsertInTable), HttpStatus.OK);
    }

    @PostMapping("updateOrders")
    public ResponseEntity<String> updateOrders(@RequestBody List<OrderDto> orderDtoList) {
        long countOrdersBeforeInsert = orderRepository.count();
        List<VariantBox> variantBoxes = new LinkedList<>();
        List<DescriptionBox> descriptionBoxes = new LinkedList<>();
        List<Box> boxList = new LinkedList<>();
        for (OrderDto orderDto : orderDtoList) {
            try {
                Optional<Order> optionalOrder = orderRepository.findByNumber(orderDto.getNumber());
                Order order;
                if (optionalOrder.isPresent()) {
                    order = optionalOrder.get();
                    order.update(orderDto.convertDate(), orderDto.getStatus());
                }
                else {
                    order = convertToOrder(orderDto);
                }
                orderRepository.save(order);
                deleteRelatedWithOrderInfo(order);
                updateRelatedWithOrderInfo(variantBoxes, descriptionBoxes, boxList, orderDto, order);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        variantsBoxRepository.saveAll(variantBoxes);
        descriptionBoxRepository.saveAll(descriptionBoxes);
        boxRepository.saveAll(boxList);
        long countInsertInTable = orderRepository.count() - countOrdersBeforeInsert;
        return new ResponseEntity<>("Добавлено записей: " + countInsertInTable + "\nОбновлено записей: " + (orderDtoList.size() - countInsertInTable), HttpStatus.OK);
    }


    public void saveOrderInfo (List<Order> orderList,
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
            boxRepository.deleteByVariantBox(varBox);

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
    }

}
