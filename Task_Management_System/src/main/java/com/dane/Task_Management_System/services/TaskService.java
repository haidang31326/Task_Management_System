package com.dane.Task_Management_System.services;

import com.dane.Task_Management_System.dto.TaskDto;
import com.dane.Task_Management_System.entity.Category;
import com.dane.Task_Management_System.entity.Task;
import com.dane.Task_Management_System.repository.CategoryRepository;
import com.dane.Task_Management_System.repository.TagRepository;
import com.dane.Task_Management_System.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;

    public Task createTask(TaskDto task){
        Category category = categoryRepository.findById(task.getCategoryID())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Task newTask = new Task();
        newTask.setTitle(task.getTitle());
        newTask.setDescription(task.getDescription());
        newTask.setCreatedAt(LocalDateTime.now());
        newTask.setStatus("Pending");


        newTask.setCategory(category);

        return taskRepository.save(newTask);
    }
}
