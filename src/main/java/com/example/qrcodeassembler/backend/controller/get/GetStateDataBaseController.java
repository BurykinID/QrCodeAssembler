package com.example.qrcodeassembler.backend.controller.get;

import com.example.qrcodeassembler.backend.entity.database.StateDataBase;
import com.example.qrcodeassembler.backend.repository.database.StateDataBaseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetStateDataBaseController {

    private StateDataBaseRepository dataBaseRepository;

    public GetStateDataBaseController(StateDataBaseRepository dataBaseRepository) {
        this.dataBaseRepository = dataBaseRepository;
    }

    @GetMapping("/get/state")
    public ResponseEntity<String> getStateDB() {
        List<StateDataBase> states = dataBaseRepository.findAllSortByIdDesc();
        if (states.size() > 0) {
            if (states.get(0).isLock()) {
                    return new ResponseEntity<>("База заблокирована", HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>("База разблокирована", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("База разблокирована", HttpStatus.OK);
    }

}
