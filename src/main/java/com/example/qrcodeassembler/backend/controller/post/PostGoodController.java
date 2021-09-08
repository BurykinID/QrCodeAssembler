package com.example.qrcodeassembler.backend.controller.post;

import com.example.qrcodeassembler.backend.entity.Good;
import com.example.qrcodeassembler.backend.service.item.GoodService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "post/")
public class PostGoodController {

    private final GoodService goodService;

    public PostGoodController(GoodService goodService) {
        this.goodService = goodService;
    }

    @PostMapping("insertGoods")
    public void insertGoods(@RequestBody List<Good> goods) {
        goodService.insertGoods(goods);
    }

    @PostMapping("updateGoods")
    public void updateGoods(@RequestBody List<Good> goods) {
        Map<Boolean, List<Good>> goodSortedByPresentInDataBase = goods.stream()
                .collect(Collectors.partitioningBy(goodService::isGoodInDataBase));
        goodService.insertGoods(goodSortedByPresentInDataBase.get(false));
        goodService.updateGood(goodSortedByPresentInDataBase.get(true));
    }
}
