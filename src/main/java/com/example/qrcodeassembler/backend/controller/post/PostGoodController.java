package com.example.qrcodeassembler.backend.controller.post;

import com.example.qrcodeassembler.backend.entity.Good;
import com.example.qrcodeassembler.backend.repository.GoodRepository;
import com.example.qrcodeassembler.backend.service.GoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        goodService.updateExistsGoodOrInsertNewGood(goods);
    }
}
