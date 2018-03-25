package com.widehouse.whatnext.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@EntityListeners(AuditingEntityListener::class)
data class Task (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int = 0,
        var description: String = "",
        var priority: Int = 1,
        var status: TaskStatus = TaskStatus.TODO,
        @ManyToOne
        var category: Category = Category(1, "", ""),
        @CreatedDate
        var createdDate: ZonedDateTime = ZonedDateTime.now()
) {
    constructor(description: String, priority: Int, status: TaskStatus, category: Category) : this() {
        this.description = description
        this.priority = priority
        this.status = status
        this.category = category
    }

}