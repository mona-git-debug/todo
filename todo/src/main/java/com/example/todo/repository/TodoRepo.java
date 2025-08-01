package com.example.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.todo.models.Todo;

public interface TodoRepo extends JpaRepository<Todo, Integer> {

    @Query("SELECT t FROM Todo t WHERE t.user.id = ?1")
    List<Todo> findByUserId(int userId);
  

}
