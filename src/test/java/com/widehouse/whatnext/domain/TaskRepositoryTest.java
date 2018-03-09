package com.widehouse.whatnext.domain;

import static com.widehouse.whatnext.domain.TaskStatus.TODO;
import static org.assertj.core.api.BDDAssertions.then;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
public class TaskRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private TaskRepository taskRepository;

    private Category category;

    @Before
    public void setup() {
        category = new Category(1, "home", "ffffff");
        entityManager.persist(category);
    }

    @Test
    public void createDateTest() {
        Task result = taskRepository.save(new Task("desc", 1, TODO, category));

        then(result)
                .hasFieldOrPropertyWithValue("description", "desc")
                .hasFieldOrPropertyWithValue("priority", 1)
                .hasFieldOrPropertyWithValue("status", TODO)
                .hasFieldOrPropertyWithValue("category", category);
    }

    @Test
    public void findByStatus_thenListTasksByStatus() {
        IntStream.range(1, 6)
                .forEach(i -> entityManager.persist(new Task("desc", 1, TODO, category)));

        List<Task> result = taskRepository.findByStatus(TODO);

        then(result)
                .hasSize(5);
    }

    @Test
    public void findByCategory_thenListTasksByCategory() {
        IntStream.range(1, 6)
                .forEach(i -> entityManager.persist(new Task("desc", 1, TODO, category)));

        List<Task> result = taskRepository.findByCategory(category);

        then(result)
                .hasSize(5);
    }
}
