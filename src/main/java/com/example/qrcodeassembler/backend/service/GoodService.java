package com.example.qrcodeassembler.backend.service;

import com.example.qrcodeassembler.backend.entity.Good;
import com.example.qrcodeassembler.backend.repository.GoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public void updateExistsGoodOrInsertNewGood(List<Good> goods) {
        List<Good> goodList = new ArrayList<>();
        for (Good good : goods) {
            Optional<Good> changesGoodFromDataBase = goodRepository.findByBarcode(good.getBarcode());
            Good changesGood;
            if (changesGoodFromDataBase.isPresent()) {
                changesGood = changesGoodFromDataBase.get();
                changesGood.updateGood(good.getName(), good.getArticle(), good.getColor(), good.getSize());
            }
            else
                changesGood = new Good(good.getBarcode(), good.getName(), good.getArticle(), good.getColor(), good.getSize());
            goodList.add(changesGood);
        }
        goodRepository.saveAll(goodList);
    }
}
