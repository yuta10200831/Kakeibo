package com.kakeibo.usecase.category

import com.kakeibo.domain.exception.ConflictException
import com.kakeibo.domain.model.Category
import com.kakeibo.domain.model.CategoryAttribute
import com.kakeibo.port.CategoryRepository

class CreateCategoryUseCase(
    private val categoryRepository: CategoryRepository,
) {
    fun execute(
        name: String,
        majorCategory: String,
        attribute: CategoryAttribute,
    ): Category {
        if (categoryRepository.findByName(name) != null) {
            throw ConflictException("カテゴリ名が既に存在します: $name")
        }

        return categoryRepository.save(
            Category(
                name = name,
                majorCategory = majorCategory,
                attribute = attribute,
            ),
        )
    }
}
