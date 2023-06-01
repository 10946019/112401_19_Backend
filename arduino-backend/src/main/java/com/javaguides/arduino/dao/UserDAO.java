package com.javaguides.arduino.dao;

import com.javaguides.arduino.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserDAO extends BaseDAO<User, Integer> {
    @Query("FROM User d where d.id = :id")
    Optional<User> login(@Param("id") Integer id);
}
