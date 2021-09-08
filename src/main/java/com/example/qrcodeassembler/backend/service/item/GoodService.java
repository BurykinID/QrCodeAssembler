package com.example.qrcodeassembler.backend.service.item;

import com.example.qrcodeassembler.backend.entity.Good;
import com.example.qrcodeassembler.backend.repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toList;

@Service
public class GoodService {

    private final GoodRepository goodRepository;

    @Autowired
    public GoodService(GoodRepository goodRepository) {
        this.goodRepository = goodRepository;
    }


    public void insertGoods(List<Good> goods) {
        goodRepository.saveAll(goods);
    }

    public void updateGood(List<Good> goods) {
        List<Good> goodList = new ArrayList<>();
        for (Good good : goods) {
            Optional<Good> changesGoodFromDataBase = goodRepository.findByBarcode(good.getBarcode());
            changesGoodFromDataBase.ifPresent(
                    updateGood -> {
                        updateGood.updateGood(good.getName(), good.getArticle(), good.getColor(), good.getSize());
                        goodList.add(updateGood);
                    }
            );
        }
        goodRepository.saveAll(goodList);
    }

    public boolean isGoodInDataBase(Good good) {
        return goodRepository.findByBarcode(good.getBarcode()).isPresent();
    }




}
