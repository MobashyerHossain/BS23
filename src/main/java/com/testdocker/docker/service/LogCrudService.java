package com.testdocker.docker.service;

import com.testdocker.docker.entity.LogCrudEntity;
import com.testdocker.docker.repository.LogCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogCrudService {
    private final LogCrudRepository logCrudRepository;

    @Autowired
    public LogCrudService(LogCrudRepository logCrudRepository) {
        this.logCrudRepository = logCrudRepository;
    }

    @Cacheable(value = "logCruds")
    public List<LogCrudEntity> index(){
        return logCrudRepository.findAll();
    }

    @Cacheable(value = "logCruds", key = "#id")
    public LogCrudEntity show(int id){
        return logCrudRepository.findById(id).get();
    }

    @CachePut(value = "logCruds", key = "#result.id")
    public LogCrudEntity store(LogCrudEntity logCrudEntity){
        return logCrudRepository.save(logCrudEntity);
    }

    @CachePut(value = "logCruds", key = "#id")
    public LogCrudEntity update(int id, LogCrudEntity logCrudEntity){
        LogCrudEntity currentLog = logCrudRepository.findById(id).get();
        currentLog.setDataType(logCrudEntity.getDataType());
        currentLog.setDataId(logCrudEntity.getDataId());
        currentLog.setAction(logCrudEntity.getAction());
        logCrudRepository.save(currentLog);
        return currentLog;
    }

    @CacheEvict(value = "logCruds", key = "#id")
    public String delete(int id){
        if (!logCrudRepository.findById(id).equals(Optional.empty())){
            logCrudRepository.deleteById(id);
            return "Record Deleted";
        }
        return "Record Not Found";
    }
}
