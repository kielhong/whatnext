package com.widehouse.whatnext.service;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;

import com.widehouse.whatnext.domain.Category;
import com.widehouse.whatnext.domain.CategoryRepository;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CategoryServiceImplTest {
    @TestConfiguration
    static class CategoryServiceImplContextConfiguration {
        @Bean
        public CategoryService taskService() {
            return new CategoryServiceImpl();
        }
    }

    @Autowired
    private CategoryService categoryService;

    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    public void getCategory_thenReturnCategory() {
        Category category = new Category(1, "home", "ffffff");
        given(categoryRepository.findById(1))
                .willReturn(Optional.of(category));

        Category result = categoryService.getCategory(1);

        then(result)
                .isEqualTo(category);
    }
}
