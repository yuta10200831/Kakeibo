package com.kakeibo.usecase.category

import com.kakeibo.domain.exception.ConflictException
import com.kakeibo.domain.model.CategoryAttribute
import com.kakeibo.port.InMemoryCategoryRepository
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class CreateCategoryUseCaseTest {

    private val repo = InMemoryCategoryRepository()
    private val useCase = CreateCategoryUseCase(repo)

    @Test
    fun `カテゴリを登録できる`() {
        val result = useCase.execute(
            name = "食費",
            majorCategory = "食",
            attribute = CategoryAttribute.VARIABLE,
        )

        assertNotNull(result.id)
        assertEquals("食費", result.name)
        assertEquals(1, repo.findAll().size)
    }

    @Test
    fun `同名カテゴリは登録できない`() {
        useCase.execute("食費", "食", CategoryAttribute.VARIABLE)

        assertFailsWith<ConflictException> {
            useCase.execute("食費", "食", CategoryAttribute.VARIABLE)
        }
    }
}
