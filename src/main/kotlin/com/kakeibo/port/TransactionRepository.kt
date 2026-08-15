package com.kakeibo.port

import com.kakeibo.domain.model.Transaction
import com.kakeibo.domain.model.YearMonthVo

interface TransactionRepository {
    fun save(transaction: Transaction): Transaction
    fun findById(yearMonth: YearMonthVo):List<Transaction>
}