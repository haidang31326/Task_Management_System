package com.dane.Task_Management_System.repository;

import com.dane.Task_Management_System.entity.Tag;
import com.dane.Task_Management_System.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

}
