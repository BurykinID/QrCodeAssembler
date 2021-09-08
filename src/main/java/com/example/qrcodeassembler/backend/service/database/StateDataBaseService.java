package com.example.qrcodeassembler.backend.service.database;

import com.example.qrcodeassembler.backend.entity.database.StateDataBase;
import com.example.qrcodeassembler.backend.repository.database.StateDataBaseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateDataBaseService {

    private final StateDataBaseRepository stateDataBaseRepository;

    public StateDataBaseService(StateDataBaseRepository stateDataBaseRepository) {
        this.stateDataBaseRepository = stateDataBaseRepository;
    }


    public ResponseEntity<String> getStateDataBase() {
        List<StateDataBase> states = stateDataBaseRepository.findAllSortByIdDesc();
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
