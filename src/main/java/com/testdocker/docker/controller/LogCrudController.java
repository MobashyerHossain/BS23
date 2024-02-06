package com.testdocker.docker.controller;

import com.testdocker.docker.entity.LogCrudEntity;
import com.testdocker.docker.service.LogCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logCruds")
public class LogCrudController {
    private final LogCrudService LogCrudService;

    @Autowired
    public LogCrudController(LogCrudService LogCrudService) {
        this.LogCrudService = LogCrudService;
    }

    @GetMapping("")
    public ResponseEntity<List<LogCrudEntity>> index(){
        return new ResponseEntity<>(LogCrudService.index(), HttpStatus.OK) ;
    }

//    @GetMapping("{id}")
//    public ResponseEntity<LogCrudEntity> show(@PathVariable(name = "id") int id){
//        return new ResponseEntity<>(LogCrudService.show(id), HttpStatus.OK) ;
//    }

//    @PostMapping("")
//    public ResponseEntity<LogCrudEntity> store(@RequestBody LogCrudEntity logCrudEntity){
//        return new ResponseEntity<>(LogCrudService.store(logCrudEntity), HttpStatus.OK) ;
//    }
//
//    @PutMapping("{id}")
//    public ResponseEntity<LogCrudEntity> update(@PathVariable(name = "id") int id,
//                                                @RequestBody LogCrudEntity logCrudEntity){
//        return new ResponseEntity<>(LogCrudService.update(id, logCrudEntity), HttpStatus.OK) ;
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<String> delete(@PathVariable(name = "id") int id){
//        return new ResponseEntity<>(LogCrudService.delete(id), HttpStatus.OK) ;
//    }
}
