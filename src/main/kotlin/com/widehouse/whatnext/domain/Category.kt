package com.widehouse.whatnext.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Category (
        @Id
        var id: Int = 1,
        var name: String = "",
        var color: String = ""
)