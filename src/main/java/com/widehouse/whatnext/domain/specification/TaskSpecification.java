package com.widehouse.whatnext.domain.specification;

import com.widehouse.whatnext.domain.Category;
import com.widehouse.whatnext.domain.Task;
import com.widehouse.whatnext.domain.TaskStatus;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

public class TaskSpecification {
    public static Specification<Task> with(Category category, TaskStatus status) {
        return (root, query, cb) -> {
            Collection<Predicate> predicates = new ArrayList<>();
            if (category != null) {
                final Predicate categoryPredicate = cb.equal(root.get("category"), category);
                predicates.add(categoryPredicate);
            }
            if (status != null) {
                final Predicate categoryPredicate = cb.equal(root.get("status"), status);
                predicates.add(categoryPredicate);
            }

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

}
