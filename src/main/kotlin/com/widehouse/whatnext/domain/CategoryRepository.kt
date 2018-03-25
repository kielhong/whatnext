package com.widehouse.whatnext.domain

import org.springframework.data.repository.CrudRepository

interface CategoryRepository : CrudRepository<Category, Int> {
}