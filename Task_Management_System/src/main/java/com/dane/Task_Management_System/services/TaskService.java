package com.dane.Task_Management_System.services;

import com.dane.Task_Management_System.dto.TaskDto;
import com.dane.Task_Management_System.entity.Category;
import com.dane.Task_Management_System.entity.Tag;
import com.dane.Task_Management_System.entity.Task;
import com.dane.Task_Management_System.repository.CategoryRepository;
import com.dane.Task_Management_System.repository.TagRepository;
import com.dane.Task_Management_System.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

        List<Long> tagsID = task.getTagsID();

        if(tagsID != null && !tagsID.isEmpty()) {
            List<Tag> tags = tagRepository.findAllById(tagsID);

            newTask.setTags(tags);
        }
        return taskRepository.save(newTask);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    public Task updateTask(Long id, TaskDto task) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());

        if (task.getCategoryID() != null) {
            Category category = categoryRepository.findById(task.getCategoryID())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            existingTask.setCategory(category);
        }

        if (task.getTagsID() != null) {
            List<Tag> tags = tagRepository.findAllById(task.getTagsID());

            existingTask.setTags(tags);
        }
        return taskRepository.save(existingTask);
    }

    public void deleteTaskByID(Long id) {
        if(!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found");
        }
        taskRepository.deleteById(id);
    }

    public List<Task> findTaskByTittle(String tittle) {
        return taskRepository.findByTitleContaining(tittle);
    }

    public List<Task> findTasksByStatus(String status) {
        return taskRepository.findByStatus(status);
    }

    public List<Task> searchAndFilterTasks(String title, String status) {
        if (title != null && !title.trim().isEmpty()) {
            title = "%" + title + "%";
        } else {
            title = null;
        }
        if (status != null && status.trim().isEmpty()) {
            status = null;
        }
        return taskRepository.searchAndFilter(title, status);
    }
}
