package com.widehouse.whatnext.service;

import com.widehouse.whatnext.domain.Category;
import com.widehouse.whatnext.domain.Task;
import com.widehouse.whatnext.domain.TaskStatus;

import java.util.List;

public interface TaskService {
    Task register(String description, Integer priority, Category category);

    Task getTask(Integer id);

    List<Task> findAll();

    List<Task> findAll(Category category, TaskStatus status, Integer priority);

    Task update(Task task);
}
