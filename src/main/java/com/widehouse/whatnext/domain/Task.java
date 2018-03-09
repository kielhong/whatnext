package com.widehouse.whatnext.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Getter
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String description;

    private Integer priority;

    @Setter
    private TaskStatus status;

    @ManyToOne
    private Category category;

    @CreatedDate
    private Date createdDate;

    public Task(String description, Integer priority, TaskStatus status, Category category) {
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.category = category;
    }
}
