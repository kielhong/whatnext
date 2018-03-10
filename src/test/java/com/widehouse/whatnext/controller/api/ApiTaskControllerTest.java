package com.widehouse.whatnext.controller.api;

import static com.widehouse.whatnext.domain.TaskStatus.TODO;
import static java.time.ZonedDateTime.now;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.widehouse.whatnext.domain.Category;
import com.widehouse.whatnext.domain.Task;
import com.widehouse.whatnext.service.CategoryService;
import com.widehouse.whatnext.service.TaskService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(ApiTaskController.class)
public class ApiTaskControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private TaskService taskService;
    @MockBean
    private CategoryService categoryService;

    private Category category;
    private List<Task> tasks;

    @Before
    public void setup() {
        category = new Category(1, "home", "ffffff");

        tasks = new ArrayList<>();
        IntStream.range(1, 11)
                .forEach(i -> tasks.add(new Task(i, "task", 1, TODO, category, now())));
    }

    @Test
    public void postTask_thenRegisterNewTask() throws Exception {
        given(categoryService.getCategory(1))
                .willReturn(category);

        mvc.perform(post("/api/task")
                    .with(user("user"))
                    .with(csrf())
                    .param("description", "desc")
                    .param("priority", "1")
                    .param("categoryId", category.getId().toString()))
                .andExpect(status().isOk());

        verify(taskService).register("desc", 1, category);
    }

    @Test
    public void listAll_thenListAllTasks() throws Exception {
        given(taskService.findAll())
                .willReturn(tasks);

        mvc.perform(get("/api/task")
                .with(user("user")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
