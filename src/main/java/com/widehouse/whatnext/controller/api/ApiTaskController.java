package com.widehouse.whatnext.controller.api;

import com.widehouse.whatnext.domain.Category;
import com.widehouse.whatnext.domain.Task;
import com.widehouse.whatnext.service.CategoryService;
import com.widehouse.whatnext.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
    @PostMapping("task")
    public Task create(@RequestParam String description,
                       @RequestParam(defaultValue = "1") Integer priority,
                       @RequestParam Integer categoryId) {
        Category category = categoryService.getCategory(categoryId);

        return taskService.register(description, priority, category);
    }
}
