package com.widehouse.whatnext.domain;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.Test;

public class CategoryTest {
    @Test
    public void constructorTest() {
        Category category = new Category(1, "home", "ff0000");

        then(category)
                .hasFieldOrPropertyWithValue("id", 1)
                .hasFieldOrPropertyWithValue("name", "home")
                .hasFieldOrPropertyWithValue("color", "ff0000");
    }
}
