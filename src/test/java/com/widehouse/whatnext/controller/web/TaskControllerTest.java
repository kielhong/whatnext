package com.widehouse.whatnext.controller.web;

import static com.widehouse.whatnext.domain.TaskStatus.TODO;
import static java.time.ZonedDateTime.now;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.widehouse.whatnext.domain.Category;
import com.widehouse.whatnext.domain.Task;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private TaskService taskService;

    private Category category;
    private Task task;
    private List<Task> tasks;

    @Before
    public void setup() {
        category = new Category(1, "home", "ffffff");

        task = new Task(1, "task", 1, TODO, category, now());

        tasks = new ArrayList<>();
        IntStream.range(2, 12)
                .forEach(i -> tasks.add(new Task(i, "task", 1, TODO, category, now())));
    }

    @Test
    @WithMockUser
    public void taskMain() throws Exception {
        mvc.perform(get("/task"))
                .andExpect(status().isOk());
    }
}
