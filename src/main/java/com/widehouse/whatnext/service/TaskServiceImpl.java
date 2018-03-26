package com.widehouse.whatnext.service;

import static java.time.ZonedDateTime.now;

import com.widehouse.whatnext.domain.Category;
import com.widehouse.whatnext.domain.Task;
import com.widehouse.whatnext.domain.TaskRepository;
import com.widehouse.whatnext.domain.TaskStatus;
import com.widehouse.whatnext.domain.specification.TaskSpecification;
import com.widehouse.whatnext.exception.TaskNotFoundException;

import java.util.List;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task register(String description, Integer priority, Category category) {
        Task newTask = new Task(1, description, priority, TaskStatus.TODO, category, now());

        return taskRepository.save(newTask);
    }

    @Override
    public Task getTask(Integer id) throws TaskNotFoundException {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isPresent()) {
            return optionalTask.get();
        } else {
            throw new TaskNotFoundException();
        }
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> find(Category category, TaskStatus status) {
        log.info("category={}, status={}", category, status);

        return taskRepository.findAll(TaskSpecification.with(category, status));
    }

    @Override
    public Task update(Task task) {
        return taskRepository.save(task);
    }
}
