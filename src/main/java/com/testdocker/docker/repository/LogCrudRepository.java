package com.testdocker.docker.repository;

import com.testdocker.docker.entity.LogCrudEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogCrudRepository extends JpaRepository<LogCrudEntity, Integer> {
}
