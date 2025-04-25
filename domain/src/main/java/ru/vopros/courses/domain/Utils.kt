package ru.vopros.courses.domain

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Utils {

    const val BASE_URL = "https://drive.usercontent.google.com/u/0/"
    const val ID = "15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q"
    const val EXPORT = "download"

    fun Date.formatDateToRussian(): String {
        return SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("ru")).format(this)
    }

    const val COURSE_CARD_CONTENT_TYPE = 1

}