package com.kakeibo.domain.service

import com.kakeibo.domain.model.BalanceType
import com.kakeibo.domain.model.Money
import com.kakeibo.domain.model.Transaction
import com.kakeibo.domain.model.YearMonthVo
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals

class MonthlySummaryCalculatorTest {

    @Test
    fun `付録Aの7月合計が再現できる`() {
        val ym = YearMonthVo("2025-07")
        val txs = listOf(
            expense(9900),
            expense(33000),
            expense(63000),
            expense(1500),
            expense(1500),
            expense(800),
            expense(6000),
            expense(20000),
            expense(20000),
            expense(10000),
            expense(6000),
            expense(1800),
            expense(12000),
            expense(8000),
            expense(2200),
            expense(170000, special = true), // 家具家電
            expense(20000),
            expense(65000),
            expense(8800),
            expense(20000),
            expense(25000, special = true),  // Wi-Fi工事
            income(150000),
        )

        val summary = MonthlySummaryCalculator.calculate(ym, txs)

        assertEquals(504_500, summary.expenseTotal)
        assertEquals(150_000, summary.incomeTotal)
        assertEquals(195_000, summary.specialExpense)
        assertEquals(309_500, summary.normalExpense)
        assertEquals(-159_500, summary.normalBalance)
        assertEquals(-354_500, summary.balanceIncludingSpecial)
    }

    private fun expense(amount: Long, special: Boolean = false) =
        Transaction(
            date = LocalDate.of(2025, 7, 1),
            categoryId = 1L,
            amount = Money(amount),
            balanceType = BalanceType.EXPENSE,
            specialFlag = special,
        )

    private fun income(amount: Long) =
        Transaction(
            date = LocalDate.of(2025, 7, 15),
            categoryId = 2L,
            amount = Money(amount),
            balanceType = BalanceType.INCOME,
        )
}
