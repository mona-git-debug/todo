package com.example.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todo.models.Todo;
import com.example.todo.services.TodoService;

@RestController
@RequestMapping("/api")
public class TodoController {
    @Autowired TodoService todoService;

    @PostMapping("/todos")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        Todo createdTodo = todoService.createTodo(todo);
        return ResponseEntity.status(201).body(createdTodo);
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable int id) {
        Todo todo = todoService.getTodoById(id);
        return ResponseEntity.status(200).body(todo);
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable int id, @RequestBody Todo todo) {
        Todo updatedTodo = todoService.updateTodo(id, todo);
        return ResponseEntity.status(200).body(updatedTodo);
    }   

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable int id) {
        todoService.deleteTodo(id);
        return ResponseEntity.status(204).build();
    }  
    @GetMapping("/todos/user/{userId}")
    public ResponseEntity<List<Todo>> getTodosByUserId(@PathVariable int userId) {
        List<Todo> todos = todoService.getTodosByUserId(userId);
        return ResponseEntity.status(200).body(todos);
    }
    
    
    
}
