package com.example.qrcodeassembler.backend.controller.post.container;

import com.example.qrcodeassembler.backend.dto.order.container.ContainerDto;
import com.example.qrcodeassembler.backend.dto.order.container.DescriptionContainerDto;
import com.example.qrcodeassembler.backend.dto.order.container.OrderContainerDto;
import com.example.qrcodeassembler.backend.dto.order.container.VariantContainerDto;
import com.example.qrcodeassembler.backend.entity.order.container.Container;
import com.example.qrcodeassembler.backend.entity.order.container.DescriptionContainer;
import com.example.qrcodeassembler.backend.entity.order.container.OrderContainer;
import com.example.qrcodeassembler.backend.entity.order.container.VariantContainer;
import com.example.qrcodeassembler.backend.repository.order.container.ContainerRepository;
import com.example.qrcodeassembler.backend.repository.order.container.DescriptionContainerRepository;
import com.example.qrcodeassembler.backend.repository.order.container.OrderContainerRepository;
import com.example.qrcodeassembler.backend.repository.order.container.VariantsContainerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "post/")
public class PostContainerController {

    private final OrderContainerRepository orderContainerRepository;
    private final VariantsContainerRepository variantsContainerRepository;
    private final DescriptionContainerRepository descriptionContainerRepository;
    private final ContainerRepository containerRepository;

    public PostContainerController(OrderContainerRepository orderContainerRepository, VariantsContainerRepository variantsContainerRepository, DescriptionContainerRepository descriptionContainerRepository, ContainerRepository containerRepository) {
        this.orderContainerRepository = orderContainerRepository;
        this.variantsContainerRepository = variantsContainerRepository;
        this.descriptionContainerRepository = descriptionContainerRepository;
        this.containerRepository = containerRepository;
    }

    @PostMapping ("insertContainers")
    public void insertContainers(@RequestBody List<OrderContainerDto> containers) {
        List<VariantContainer> variantContainers = new LinkedList<>();
        List<DescriptionContainer> descriptionContainers = new LinkedList<>();
        List<OrderContainer> orderContainers = new LinkedList<>();
        List<Container> containerList = new LinkedList<>();
        for (OrderContainerDto orderDto : containers) {
            try {
                Optional<OrderContainer> orderContainerFromDataBase = orderContainerRepository.findByNumber(orderDto.getNumber());
                OrderContainer orderContainer;
                if (orderContainerFromDataBase.isPresent()) {
                    orderContainer = orderContainerFromDataBase.get();
                    orderContainer.update(orderDto.getDateInObject(),orderDto.getStatus());
                    orderContainerRepository.save(orderContainer);
                    deleteRelatedWithOrderContainerInfo(orderContainer);
                }
                else {
                    orderContainer = new OrderContainer(orderDto.getNumber(), orderDto.getDateInObject(), orderDto.getStatus());
                    orderContainers.add(orderContainer);
                }
                updateRelatedWithOrderContainerInfo(variantContainers, descriptionContainers, containerList, orderDto, orderContainer);
            } catch (ParseException e) {
                throw new IllegalStateException("Error parse date. Please check date format and try again. Error occurred with " + e.getMessage());
            }
        }
        saveOrderInfo(orderContainers, variantContainers, descriptionContainers, containerList);
    }



    @PostMapping ("updateContainers")
    public void updateContainers(@RequestBody List<OrderContainerDto> containers) {
        List<VariantContainer> variantContainers = new LinkedList<>();
        List<DescriptionContainer> descriptionContainers = new LinkedList<>();
        List<Container> containerList = new LinkedList<>();
        for (OrderContainerDto orderContainerDto : containers) {
            try {
                Optional<OrderContainer> orderContainerFromDataBase = orderContainerRepository.findByNumber(orderContainerDto.getNumber());
                OrderContainer orderContainer;
                if (orderContainerFromDataBase.isPresent()) {
                    orderContainer = orderContainerFromDataBase.get();
                    orderContainer.update(orderContainerDto.getDateInObject(), orderContainerDto.getStatus());
                }
                else {
                    orderContainer = new OrderContainer(orderContainerDto.getNumber(), orderContainerDto.getDateInObject(), orderContainerDto.getStatus());;
                }
                orderContainerRepository.save(orderContainer);
                deleteRelatedWithOrderContainerInfo(orderContainer);
                updateRelatedWithOrderContainerInfo(variantContainers, descriptionContainers, containerList, orderContainerDto, orderContainer);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        variantsContainerRepository.saveAll(variantContainers);
        descriptionContainerRepository.saveAll(descriptionContainers);
        containerRepository.saveAll(containerList);
    }


    public void saveOrderInfo (List<OrderContainer> orderContainers,
                               List<VariantContainer> variantContainers,
                               List<DescriptionContainer> descriptionContainers,
                               List<Container> containerList) {
        orderContainerRepository.saveAll(orderContainers);
        variantsContainerRepository.saveAll(variantContainers);
        descriptionContainerRepository.saveAll(descriptionContainers);
        containerRepository.saveAll(containerList);
    }


    private void updateRelatedWithOrderContainerInfo(List<VariantContainer> variantContainers, List<DescriptionContainer> descriptionContainers, List<Container> containerList, OrderContainerDto orderDto, OrderContainer orderContainer) {
        List<VariantContainer> variantContainerList = convertToVariantContainer(orderDto, orderContainer);
        variantContainers.addAll(variantContainerList);
        descriptionContainers.addAll(convertToDescriptionContainer(orderDto, variantContainerList));
        containerList.addAll(convertToContainer(orderDto, variantContainerList));
    }

    private void deleteRelatedWithOrderContainerInfo(OrderContainer orderContainer) {
        List<VariantContainer> variantContainersForDelete = variantsContainerRepository.findByOrderContainer(orderContainer.getNumber());

        for (VariantContainer varContainer: variantContainersForDelete)
            descriptionContainerRepository.deleteByNumberVariant(varContainer);

        for (VariantContainer varContainer: variantContainersForDelete)
            containerRepository.deleteByVariantContainer(varContainer);

        variantsContainerRepository.deleteAll(variantContainersForDelete);
    }


    private List<VariantContainer> convertToVariantContainer(OrderContainerDto orderContainerDto, OrderContainer orderContainer) {
        List<VariantContainer> variantContainerList = new LinkedList<>();
        for (VariantContainerDto dto : orderContainerDto.getVariantContainers()) {
            VariantContainer variantContainer = new VariantContainer(dto.getNumberVariant(), dto.getCountInBox(), dto.getCountBox(), orderContainer);
            variantContainerList.add(variantContainer);
        }
        return variantContainerList;
    }

    private List<DescriptionContainer> convertToDescriptionContainer(OrderContainerDto orderContainerDto, List<VariantContainer> variantContainerList) {
            List<DescriptionContainer> descriptionBoxList = new LinkedList<>();
            for (DescriptionContainerDto dto : orderContainerDto.getDescriptionContainers()) {
                VariantContainer variantContainerForDescription = new VariantContainer();
                for (VariantContainer variantContainer : variantContainerList) {
                    if (variantContainer.getNumberVariant().equals(dto.getNumberVariant())) {
                        variantContainerForDescription = variantContainer;
                    }
                }
                DescriptionContainer descriptionContainer = new DescriptionContainer(dto.getNumberLine(), dto.getNumberVariantBox(), dto.getCount(),variantContainerForDescription);
                descriptionBoxList.add(descriptionContainer);
            }
            return descriptionBoxList;
    }

    private List<Container> convertToContainer(OrderContainerDto orderContainerDao, List<VariantContainer> variantContainerList) {
        List<Container> containers = new LinkedList<>();
        for (ContainerDto dto : orderContainerDao.getContainers()) {
            VariantContainer variantContainerForContainer = new VariantContainer();
            for (VariantContainer variantContainer : variantContainerList) {
                if (variantContainer.getNumberVariant().equals(dto.getNumberVariant())) {
                    variantContainerForContainer = variantContainer;
                }
            }
            Container container = new Container(dto.getNumberContainer(), dto.getStatus(), variantContainerForContainer);
            containers.add(container);
        }
        return containers;
    }
}
