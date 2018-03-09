package com.widehouse.whatnext.domain;

import static com.widehouse.whatnext.domain.TaskStatus.DONE;
import static com.widehouse.whatnext.domain.TaskStatus.TODO;
import static org.assertj.core.api.BDDAssertions.then;

import org.junit.Before;
import org.junit.Test;

public class TaskTest {
    private Category category;

    @Before
    public void setup() {
        category = new Category(1, "home", "ffffff");
    }

    @Test
    public void constructorTest() {
        Task task = new Task("desc", 1, TODO, category);

        then(task)
                .hasFieldOrPropertyWithValue("description", "desc")
                .hasFieldOrPropertyWithValue("priority", 1)
                .hasFieldOrPropertyWithValue("status", TODO)
                .hasFieldOrPropertyWithValue("category", category);
    }

    @Test
    public void setStatus_thenUpdateStatus() {
        Task task = new Task("desc", 1, TODO, category);

        task.setStatus(DONE);

        then(task.getStatus())
                .isEqualTo(DONE);
    }
}
