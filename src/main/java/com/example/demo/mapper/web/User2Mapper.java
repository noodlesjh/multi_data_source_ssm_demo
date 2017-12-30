package com.example.demo.mapper.web;


import com.example.demo.entity.UserEntity;

import java.util.HashMap;
import java.util.List;

public interface User2Mapper {

    List<UserEntity> getAll();

    UserEntity getOne(Long id);

    void insert(UserEntity entity);

    void update(UserEntity entity);

    void delete(Long id);

    List<UserEntity> listByMap(HashMap<String, Object> map);
}