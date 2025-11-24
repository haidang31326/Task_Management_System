package com.dane.Task_Management_System.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "tags")
@Data
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    String name;
    @ManyToMany(mappedBy = "tags", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Task> tasks;
}
