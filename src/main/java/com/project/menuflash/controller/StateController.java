package com.project.menuflash.controller;

import com.project.menuflash.domain.StateDomain;
import com.project.menuflash.dto.StateDTO;
import com.project.menuflash.service.state.StateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path="/state")
public class StateController {
    private StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }
    @GetMapping
    public ResponseEntity<List<StateDomain>> getStates() throws Exception {
        List<StateDomain> response = stateService.getStates();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<StateDomain> getStateById(@PathVariable Long id) throws Exception {
        StateDomain response = stateService.getStateById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<?> updateStates(@RequestBody StateDTO stateDto) throws Exception {
        stateService.updateState(stateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteState(@PathVariable Long id) throws Exception {
        stateService.deleteState(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
