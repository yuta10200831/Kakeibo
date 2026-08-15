package com.kakeibo.domain.model

import com.kakeibo.domain.exception.ValidationException

data class Category(
    val id: Long? = null,
    val name: String,
    val majorCategory: String,
    val attribute: CategoryAttribute,
) {
    init {
        if (name.isBlank()) throw ValidationException("カテゴリ名は必須です")
        if (majorCategory.isBlank()) throw ValidationException("大分類は必須です")
    }
}
