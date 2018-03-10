package com.widehouse.whatnext.service;

import com.widehouse.whatnext.domain.Category;
import com.widehouse.whatnext.domain.Task;
import com.widehouse.whatnext.domain.TaskRepository;
import com.widehouse.whatnext.domain.TaskStatus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task register(String description, Integer priority, Category category) {
        Task newTask = new Task(description, priority, TaskStatus.TODO, category);

        return taskRepository.save(newTask);
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> findAll(Category category, TaskStatus status, Integer priority) {
        Task example = new Task(null, priority, status, category);

        return taskRepository.findAll(Example.of(example));
    }

    @Override
    public Task update(Task task) {
        return taskRepository.save(task);
    }
}
