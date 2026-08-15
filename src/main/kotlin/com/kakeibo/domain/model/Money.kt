package com.kakeibo.domain.model

import com.kakeibo.domain.exception.ValidationException

data class Money(val amount: Long) {
    init {
        if (amount <= 0) {
            throw ValidationException("金額は1以上である必要があります: $amount")
        }
    }

    operator fun plus(other: Money): Money = Money(amount + other.amount)

    // 集計で「収入　- 支出」を計算するためのメソッド
    fun minusToLong(other: Money): Long = amount - other.amount
}