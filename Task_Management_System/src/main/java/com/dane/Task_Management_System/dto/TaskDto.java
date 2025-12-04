package com.dane.Task_Management_System.dto;

import lombok.Data;

import java.util.List;

@Data
public class TaskDto {
    private String title;
    private String description;
    private List<Long> tagsID;
    private Long categoryID;
}
