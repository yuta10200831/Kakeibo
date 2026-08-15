package com.kakeibo.domain.model

import com.kakeibo.domain.exception.ValidationException
import java.time.LocalDate

data class Transaction(
    val id: Long? = null,
    val date: LocalDate,
    val categoryId: Long,
    val amount: Money,
    val balanceType: BalanceType,
    val specialFlag: Boolean = false,
    val paymentMethod: String? = null,
    val memo: String? = null,
    val source: TransactionSource = TransactionSource.MANUAL,
    val fixedCostId: Long? = null,
) {
    init {
        if (balanceType != BalanceType.EXPENSE && specialFlag) {
            throw ValidationException("収入系取引に specialFlag=true は指定できません")
        }
    }
}