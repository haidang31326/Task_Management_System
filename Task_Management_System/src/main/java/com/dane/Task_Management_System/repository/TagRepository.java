package com.dane.Task_Management_System.repository;

import com.dane.Task_Management_System.entity.Tag;
import com.dane.Task_Management_System.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

}
