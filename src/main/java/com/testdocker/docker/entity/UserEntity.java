package com.testdocker.docker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "users", schema = "testspringbootdb")
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Setter
    private String name;
    @Setter
    private String address;

    private LocalDateTime timestamp;

    public UserEntity(String name, String address) {
        this.name = name;
        this.address = address;
        this.timestamp = LocalDateTime.now();
    }
}
