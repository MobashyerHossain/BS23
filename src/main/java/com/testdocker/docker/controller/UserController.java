package com.testdocker.docker.controller;

import com.testdocker.docker.entity.LogCrudEntity;
import com.testdocker.docker.entity.UserEntity;
import com.testdocker.docker.service.UserService;
import com.testdocker.docker.service.kafka.KafkaJsonProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    private final KafkaJsonProducerService kafkaJsonProducerService;

    @Autowired
    public UserController(UserService userService, KafkaJsonProducerService kafkaJsonProducerService) {
        this.userService = userService;
        this.kafkaJsonProducerService = kafkaJsonProducerService;
    }

    // Get All User Data
    @GetMapping("")
    public ResponseEntity<List<UserEntity>> index(){
        return new ResponseEntity<>(userService.index(), HttpStatus.OK) ;
    }

    // Get Single User Data by ID
    @GetMapping("{id}")
    public ResponseEntity<UserEntity> show(@PathVariable(name = "id") int id){
        kafkaJsonProducerService.sendMessage(
                new LogCrudEntity(
                        "User",
                        id,
                        "Shown"
                )
        );
        return new ResponseEntity<>(userService.show(id), HttpStatus.OK) ;
    }

    // Create User
    @PostMapping("")
    public ResponseEntity<UserEntity> store(@RequestBody UserEntity userEntity){
        UserEntity user = userService.store(userEntity);
        kafkaJsonProducerService.sendMessage(
                new LogCrudEntity(
                        "User",
                        user.getId(),
                        "Created"
                )
        );
        return new ResponseEntity<>(user, HttpStatus.OK) ;
    }

    // Update User
    @PutMapping("{id}")
    public ResponseEntity<UserEntity> update(@PathVariable(name = "id") int id,
                                            @RequestBody UserEntity userEntity){
        kafkaJsonProducerService.sendMessage(
                new LogCrudEntity(
                        "User",
                        id,
                        "Updated"
                )
        );
        return new ResponseEntity<>(userService.update(id, userEntity), HttpStatus.OK) ;
    }

    //  Delete User
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") int id){
        kafkaJsonProducerService.sendMessage(
                new LogCrudEntity(
                        "User",
                        id,
                        "Deleted"
                )
        );
        return new ResponseEntity<>(userService.delete(id), HttpStatus.OK) ;
    }
}
