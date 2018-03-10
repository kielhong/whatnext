package com.widehouse.whatnext.domain;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    private Integer priority;

    private TaskStatus status;

    @ManyToOne
    private Category category;

    @CreatedDate
    private ZonedDateTime createdDate;

    public Task(String description, Integer priority, TaskStatus status, Category category) {
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return Objects.equals(description, task.description)
                && Objects.equals(priority, task.priority)
                && status == task.status
                && Objects.equals(category, task.category);
    }
}