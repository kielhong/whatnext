package com.widehouse.whatnext.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByStatus(TaskStatus status);

    List<Task> findByCategory(Category category);
}
