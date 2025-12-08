package com.dane.Task_Management_System.controller;

import com.dane.Task_Management_System.dto.TaskDto;
import com.dane.Task_Management_System.entity.Task;
import com.dane.Task_Management_System.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskservice;


    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody TaskDto taskdto) {
        Task newTask = taskservice.createTask(taskdto);

        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }

    @GetMapping("/readall")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskservice.getAllTasks());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody TaskDto taskdto) {
       Task upTask = taskservice.updateTask(id, taskdto);
       return ResponseEntity.ok(upTask);
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
         taskservice.deleteTaskByID(id);
        return ResponseEntity.ok("Task deleted successfullt");
    }

    @GetMapping("/search" )
    public ResponseEntity<List<Task>> searchTasks(@RequestParam String tittle) {
        List<Task> newTasks = taskservice.findTaskByTittle(tittle);
        return ResponseEntity.ok(newTasks);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Task>> searchTasksByStatus(@RequestParam String status) {
        List<Task> Tasks = taskservice.findTasksByStatus(status);
        return ResponseEntity.ok(Tasks);
    }
    @GetMapping("/super-search")
    public ResponseEntity<List<Task>> superSearch(@RequestParam String tittle ,@RequestParam String status) {
        List<Task> Tasks = taskservice.searchAndFilterTasks(tittle, status);
        return ResponseEntity.ok(Tasks);
    }
}
