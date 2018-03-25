package com.widehouse.whatnext.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface TaskRepository : JpaRepository<Task, Int>, JpaSpecificationExecutor<Task> {
    fun findByStatus(status: TaskStatus): List<Task>

    fun findByCategory(category: Category): List<Task>

    fun findByCategoryAndStatus(category: Category, status: TaskStatus): List<Task>
}