package ru.vopros.courses.domain

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Utils {

    const val BASE_URL = "https://drive.usercontent.google.com/u/0/"
    const val ID = "15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q"
    const val EXPORT = "download"
    private const val RUB = "₽"

    private val RU_DATE_FORMAT = SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("ru"))

    fun Date.formatDateToRussian(): String {
        return RU_DATE_FORMAT.format(this)
    }

    fun String.parseRussianToDate(): Date {
        return RU_DATE_FORMAT.parse(this)
    }

    fun String.plusRub(): String {
        return plus(" $RUB")
    }

    fun String.minusRub(): String {
        return dropLast(2)
    }

    const val COURSE_CARD_CONTENT_TYPE = 1

}