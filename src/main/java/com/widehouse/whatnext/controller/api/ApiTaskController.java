package com.widehouse.whatnext.controller.api;

import com.widehouse.whatnext.domain.Category;
import com.widehouse.whatnext.domain.Task;
import com.widehouse.whatnext.domain.TaskStatus;
import com.widehouse.whatnext.service.CategoryService;
import com.widehouse.whatnext.service.TaskService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class ApiTaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private CategoryService categoryService;

    /**
     * POST /api/task Create New Task.
     * @param description task description
     * @param priority task priority
     * @param categoryId id of category
     * @return com.widehouse.whatnext.domain.Task
     */
    @PostMapping("tasks")
    public Task create(@RequestParam String description,
                       @RequestParam(defaultValue = "1") Integer priority,
                       @RequestParam Integer categoryId) {
        Category category = categoryService.getCategory(categoryId);

        return taskService.register(description, priority, category);
    }

    @GetMapping("tasks")
    List<Task> listTasks(@RequestParam(value = "category", required = false) Integer categoryId,
                         @RequestParam(required = false) TaskStatus status) {
        Category category = null;
        if (categoryId != null) {
            category = categoryService.getCategory(categoryId);
        }

        return taskService.find(category, status);
    }

    @PutMapping(value = "tasks/{taskId}")
    Task updateStatus(@PathVariable Integer taskId, @RequestParam TaskStatus status) {
        Task task = taskService.getTask(taskId);
        task.setStatus(status);

        return taskService.update(task);
    }

    /**
     * TaskStatus request parameter converter.
     * @param dataBinder web request data binder
     */
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(TaskStatus.class, new TaskStatusConverter());
    }
}
