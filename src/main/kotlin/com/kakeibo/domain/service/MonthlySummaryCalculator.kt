package com.kakeibo.domain.service

import com.kakeibo.domain.model.BalanceType
import com.kakeibo.domain.model.MonthlySummary
import com.kakeibo.domain.model.Transaction
import com.kakeibo.domain.model.YearMonthVo

object MonthlySummaryCalculator {

    fun calculate(yearMonth: YearMonthVo, transactions: List<Transaction>): MonthlySummary {
        val expenseTotal = transactions
            .filter { it.balanceType == BalanceType.EXPENSE }
            .sumOf { it.amount.amount }

        val incomeTotal = transactions
            .filter {
                it.balanceType == BalanceType.INCOME ||
                    it.balanceType == BalanceType.OTHER_INCOME
            }
            .sumOf { it.amount.amount }

        val specialExpense = transactions
            .filter { it.balanceType == BalanceType.EXPENSE && it.specialFlag }
            .sumOf { it.amount.amount }

        val normalExpense = expenseTotal - specialExpense

        return MonthlySummary(
            yearMonth = yearMonth,
            expenseTotal = expenseTotal,
            incomeTotal = incomeTotal,
            specialExpense = specialExpense,
            normalExpense = normalExpense,
            normalBalance = incomeTotal - normalExpense,
            balanceIncludingSpecial = incomeTotal - expenseTotal,
        )
    }
}