package com.example.todo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todo.models.Todo;
import com.example.todo.models.User;
import com.example.todo.repository.TodoRepo;

@Service

public class TodoService  {
    @Autowired
    private TodoRepo todoRepo;

    public Todo createTodo(Todo todo) {
        return todoRepo.save(todo);
    }  
    
    public Todo deleteTodo(int id) {
        Todo todo = todoRepo.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
        todoRepo.delete(todo);
        return todo;
    }   

    public Todo updateTodo(int id, Todo newTodo) {
        Todo existingTodo = todoRepo.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
        User user = newTodo.getUser();
        user.getTodos().add(existingTodo); // bidirectional sync
        existingTodo.setUser(user);
        return todoRepo.save(existingTodo);
    }

    public Todo getTodoById(int id) {
        return todoRepo.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
    }

    public List<Todo> getAllTodos() {
        return todoRepo.findAll();
    }
    
    public List<Todo> getTodosByUserId(int userId){
        return todoRepo.findByUserId(userId);
    }

    


    
}
