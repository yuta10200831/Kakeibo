package com.kakeibo.port

import com.kakeibo.domain.model.Category

interface CategoryRepository {
    fun save(category: Category): Category
    fun findById(id: Long): Category?
    fun findByName(name: String): Category?
    fun findAll(): List<Category>
}