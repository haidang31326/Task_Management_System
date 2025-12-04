package com.dane.Task_Management_System.controller;

import com.dane.Task_Management_System.dto.TaskDto;
import com.dane.Task_Management_System.entity.Task;
import com.dane.Task_Management_System.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
