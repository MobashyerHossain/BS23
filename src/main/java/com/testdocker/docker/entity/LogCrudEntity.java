package com.testdocker.docker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "log_cruds", schema = "testspringbootdb")
public class LogCrudEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    @Column(name = "data_type")
    private String dataType;

    @Setter
    @Column(name = "data_id")
    private int dataId;

    @Setter
    private String action;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    public LogCrudEntity(String dataType, int dataId, String action) {
        this.dataType = dataType;
        this.dataId = dataId;
        this.action = action;
        this.timestamp = LocalDateTime.now();
    }
}
