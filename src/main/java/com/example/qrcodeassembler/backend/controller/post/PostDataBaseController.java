package com.example.qrcodeassembler.backend.controller.post;

import com.example.qrcodeassembler.backend.entity.database.StateDataBase;
import com.example.qrcodeassembler.backend.repository.database.StateDataBaseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class PostDataBaseController {

    private StateDataBaseRepository stateDataBaseRepository;

    public PostDataBaseController(StateDataBaseRepository stateDataBaseRepository) {
        this.stateDataBaseRepository = stateDataBaseRepository;
    }

    @PostMapping("/post/lockDB")
    public ResponseEntity<String> lockDB(@RequestBody StateDataBase lock) {
        Date timeEvent = new Date();
        lock.setTimeEvent(timeEvent);
        stateDataBaseRepository.save(lock);
        if (lock.isLock()) {
            return new ResponseEntity<>("Блокировка установлена", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Блокировка снята", HttpStatus.OK);
        }
    }

}
