package com.project.menuflash.controller;

import com.project.menuflash.dto.request.CreateStateDto;
import com.project.menuflash.dto.request.UpdateStateDto;
import com.project.menuflash.dto.response.FindAllStateResponse;
import com.project.menuflash.service.state.StateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path="/state")
public class StateController {
    private final StateService stateService;
    private static final org.apache.logging.log4j.Logger LOG = org.apache.logging.log4j.LogManager.getLogger(StateController.class);

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }
    @GetMapping
    public ResponseEntity<List<FindAllStateResponse>> findAll() throws Exception {
        LOG.info("FindAll begins");
        List<FindAllStateResponse> response = stateService.getStates();
        LOG.info("FindAll ends with response: {} ", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<FindAllStateResponse> findOne(@PathVariable Long id) throws Exception {
        LOG.info("FindOne begins with id: {}", id);
        FindAllStateResponse response = stateService.getStateById(id);
        LOG.info("FindOne ends with response: {} ", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateStateDto createStateDTO) throws Exception {
        LOG.info("Create begins with state: {}", createStateDTO);
        stateService.createState(createStateDTO);
        LOG.info("Create ends");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<?> update(@RequestBody UpdateStateDto updateStateDto) throws Exception {
        LOG.info("Update begins with state: {}", updateStateDto);
        stateService.updateState(updateStateDto);
        LOG.info("Update ends");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
        LOG.info("Delete begins with id: {}", id);
        stateService.deleteState(id);
        LOG.info("Delete ends");
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
