package com.kakeibo.domain.model

data class MonthlySummary(
    val yearMonth: YearMonthVo,
    val expenseTotal: Long,
    val incomeTotal: Long,
    val specialExpense: Long,
    val normalExpense: Long,
    val normalBalance: Long,
    val balanceIncludingSpecial: Long,
)
