package com.example.qrcodeassembler.backend.controller.get;

import com.example.qrcodeassembler.backend.service.database.StateDataBaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetStateDataBaseController {

    private StateDataBaseService stateDataBaseService;

    public GetStateDataBaseController(StateDataBaseService stateDataBaseService) {
        this.stateDataBaseService = stateDataBaseService;
    }

    @GetMapping("/get/state")
    public ResponseEntity<String> getStateDB() {
        return stateDataBaseService.getStateDataBase();
    }

}
