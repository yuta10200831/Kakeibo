package com.kakeibo.port

import com.kakeibo.domain.model.Category

class InMemoryCategoryRepository : CategoryRepository {
    private val store = linkedMapOf<Long, Category>()
    private var seq = 1L

    override fun save(category: Category): Category {
        val saved = if (category.id == null) {
            category.copy(id = seq++)
        } else {
            category
        }
        store[saved.id!!] = saved
        return saved
    }

    override fun findById(id: Long): Category? = store[id]

    override fun findByName(name: String): Category? =
        store.values.firstOrNull { it.name == name }

    override fun findAll(): List<Category> = store.values.toList()
}
