package com.widehose.whatnext.domain

import com.widehouse.whatnext.domain.Category
import com.widehouse.whatnext.domain.Task
import com.widehouse.whatnext.domain.TaskStatus
import org.assertj.core.api.BDDAssertions
import org.junit.Test
import java.time.ZonedDateTime

class TaskTest {
    @Test
    fun testCodeCoverageForTaskClass() {
        val tested = Task(1, "desc", 1, TaskStatus.TODO, Category(1, "category", ""), ZonedDateTime.now()).apply {
            BDDAssertions.then(this.toString())
                    .contains("id=1")
                    .contains("description=desc")
                    .contains("status=TODO")
                    .contains("category=")
                    .contains("createdDate=")

            id = 2
            BDDAssertions.then(id).isEqualTo(2)
            description = "desc2"
            BDDAssertions.then(description).isEqualTo("desc2")
            priority = 2
            BDDAssertions.then(priority).isEqualTo(2)
            val testHour = ZonedDateTime.now().plusHours(10)
            createdDate = testHour
            BDDAssertions.then(createdDate).isEqualTo(testHour)
        }

        val testedCopy = tested.copy(id = 3).apply {
            BDDAssertions.then(this.equals(tested))
                    .isFalse()
            BDDAssertions.then(this.hashCode())
                    .isNotEqualTo(tested.hashCode())
        }
    }
}