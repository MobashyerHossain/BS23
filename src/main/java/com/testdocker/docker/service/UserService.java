package com.testdocker.docker.service;

import com.testdocker.docker.entity.UserEntity;
import com.testdocker.docker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Cacheable(value = "users")
    public List<UserEntity> index(){
        return userRepository.findAll();
    }

    @Cacheable(value = "users", key = "#id")
    public UserEntity show(int id){
        Optional<UserEntity> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    // Add new user to cache
    @CachePut(value = "users", key = "#result.id")
    public UserEntity store(UserEntity userEntity){
        return userRepository.save(userEntity);
    }

    // update cache data when user data is updated
    @CachePut(value = "users", key = "#id")
    public UserEntity update(int id, UserEntity userEntity){
        UserEntity currentUser = userRepository.findById(id).get();
        currentUser.setName(userEntity.getName());
        currentUser.setAddress(userEntity.getAddress());
        userRepository.save(currentUser);
        return currentUser;
    }

    // delete cache of user when reocrd deleted
    @CacheEvict(value = "users", key = "#id")
    public String delete(int id){
        if (!userRepository.findById(id).equals(Optional.empty())){
            userRepository.deleteById(id);
            return "Record Deleted";
        }
        return "Record Not Found";
    }
}
