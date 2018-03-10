package com.widehouse.whatnext.service;

import com.widehouse.whatnext.domain.Category;
import com.widehouse.whatnext.domain.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category getCategory(Integer id) {
        return categoryRepository.findById(id).get();
    }
}
