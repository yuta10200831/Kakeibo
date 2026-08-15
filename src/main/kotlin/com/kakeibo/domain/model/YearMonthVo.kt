package com.kakeibo.domain.model

import com.kakeibo.domain.exception.ValidationException
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeParseException

data class YearMonthVo(val value: String) {
    val yearMonth: YearMonth = try {
        YearMonth.parse(value)
    } catch (e: DateTimeParseException) {
        throw ValidationException("年月は YYYY-MM 形式で指定してください: $value")
    }

    companion object {
        fun from(date: LocalDate): YearMonthVo =
            YearMonthVo("%04d-%02d".format(date.year, date.monthValue))
    }
}
