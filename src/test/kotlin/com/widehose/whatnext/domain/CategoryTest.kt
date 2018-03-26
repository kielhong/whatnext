package com.widehose.whatnext.domain

import com.widehouse.whatnext.domain.Category
import org.assertj.core.api.BDDAssertions
import org.junit.Test


class CategoryTest {
    @Test
    fun constructorTest() {
        val tested = Category(1, "home", "ffffff").apply {
            BDDAssertions.then(this)
                    .hasFieldOrPropertyWithValue("id", 1)
                    .hasFieldOrPropertyWithValue("name", "home")
                    .hasFieldOrPropertyWithValue("color", "ffffff")

            id = 2
            BDDAssertions.then(id).isEqualTo(2)
            name = "work"
            BDDAssertions.then(name).isEqualTo("work")
            color = "000000"
            BDDAssertions.then(color).isEqualTo("000000")
        }
    }
}