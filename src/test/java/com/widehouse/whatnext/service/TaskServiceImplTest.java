package com.widehouse.whatnext.service;

import static com.widehouse.whatnext.domain.TaskStatus.DONE;
import static com.widehouse.whatnext.domain.TaskStatus.TODO;
import static java.time.ZonedDateTime.now;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import com.widehouse.whatnext.domain.Category;
import com.widehouse.whatnext.domain.Task;
import com.widehouse.whatnext.domain.TaskRepository;
import com.widehouse.whatnext.exception.TaskNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Slf4j
public class TaskServiceImplTest {
    @TestConfiguration
    static class TaskServiceImplContextConfiguration {
        @Bean
        public TaskService taskService() {
            return new TaskServiceImpl();
        }
    }

    @Autowired
    private TaskService taskService;

    @MockBean
    private TaskRepository taskRepository;

    private Category category;
    private Category category1;
    private List<Task> tasks;

    @Before
    public void setup() {
        category = new Category(1, "home", "ffffff");
        category1 = new Category(2, "work", "00ff00");

        tasks = new ArrayList<>();
        IntStream.range(0, 2)
                .forEach(i -> tasks.add(new Task("todoDesc", 1, TODO, category)));
        IntStream.range(0, 2)
                .forEach(i -> tasks.add(new Task("todoWorkDesc", 1, DONE, category)));
        IntStream.range(0, 2)
                .forEach(i -> tasks.add(new Task("todoWorkDesc", 2, TODO, category)));
        IntStream.range(0, 2)
                .forEach(i -> tasks.add(new Task("todoWorkDesc", 1, TODO, category1)));
        IntStream.range(0, 2)
                .forEach(i -> tasks.add(new Task("todoWorkDesc", 1, DONE, category1)));
        IntStream.range(0, 2)
                .forEach(i -> tasks.add(new Task("todoWorkDesc", 2, TODO, category1)));
    }

    @Test
    public void register_thenCreateNewTask() {
        given(taskRepository.save(any(Task.class)))
                .willReturn(new Task(1, "task", 1, TODO, category, now()));

        Task result = taskService.register("task", 1, category);

        then(result)
                .hasFieldOrPropertyWithValue("description", "task")
                .hasFieldOrPropertyWithValue("priority", 1)
                .hasFieldOrPropertyWithValue("status", TODO)
                .hasFieldOrPropertyWithValue("category", category);
    }

    @Test
    public void getTask_withId_thenReturnTask() {
        given(taskRepository.findById(1))
                .willReturn(Optional.of(new Task(1, "task", 1, TODO, category, now())));

        Task result = taskService.getTask(1);

        then(result)
                .hasFieldOrPropertyWithValue("id", 1)
                .hasFieldOrPropertyWithValue("description", "task");
    }

    @Test
    public void getTask_withNotExistTaskId_thenRaiseTaskNotFoundException() {
        given(taskRepository.findById(1))
                .willReturn(Optional.empty());

        thenThrownBy(() -> taskService.getTask(1))
                .isInstanceOf(TaskNotFoundException.class);
    }

    @Test
    public void findAll() {
        List<Task> tasks = new ArrayList<>();
        IntStream.range(1, 3)
                .forEach(i -> tasks.add(new Task("todoDesc", 1, TODO, category)));
        given(taskRepository.findAll())
                .willReturn(tasks);

        List<Task> result = taskService.findAll();

        then(result)
                .isEqualTo(tasks);
    }

    @Test
    public void findAll_withCategory_thenListByCategory() {
        Task example = new Task();
        example.setCategory(category);
        example.setStatus(null);
        example.setPriority(null);
        log.info("task = {}", example);

        Task example2 = new Task();
        example2.setCategory(category);
        example2.setStatus(null);
        example2.setPriority(null);

        log.info("equals={}", example.equals(example2));
        given(taskRepository.findAll(Example.of(example)))
                .willReturn(tasks.stream().filter(task -> task.getCategory().equals(category)).collect(toList()));

        List<Task> result = taskService.findAll(category, null, null);

        then(result)
                .extracting("category")
                .containsOnly(category);
    }

    @Test
    public void update_withDone_thenChangeStatus() {
        Task task = new Task("task", 1, TODO, category);
        given(taskRepository.save(new Task("task", 1, DONE, category)))
                .willReturn(new Task(1, "task", 1, DONE, category, now()));

        task.setStatus(DONE);
        Task result = taskService.update(task);

        then(result.getStatus())
                .isEqualTo(DONE);
        verify(taskRepository).save(task);
    }
}
