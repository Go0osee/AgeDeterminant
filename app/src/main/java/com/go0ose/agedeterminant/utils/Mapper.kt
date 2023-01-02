package com.go0ose.agedeterminant.utils

import com.go0ose.agedeterminant.data.retrofit.AgeResponse
import com.go0ose.agedeterminant.data.storage.cache.entity.AgeEntity
import com.go0ose.agedeterminant.data.storage.favorite.entity.FavoriteAgeEntity
import com.go0ose.agedeterminant.presentation.model.Age
import com.go0ose.agedeterminant.presentation.model.ItemAge

fun Age.toAgeEntity() =
    AgeEntity(
        age = this.age,
        name = this.name
    )

fun Age.toFavoriteAgeEntity() =
    FavoriteAgeEntity(
        age = this.age,
        name = this.name
    )

fun FavoriteAgeEntity.toAge() =
    Age(
        age = this.age,
        name = this.name
    )


fun AgeEntity.toAge() =
    Age(
        age = this.age,
        name = this.name
    )

fun AgeResponse.toAge() =
    Age(
        age = this.age,
        name = this.name
    )

fun String.convertToLatin(): String {
    val charArray = this.toCharArray()
    val latinChars = arrayOfNulls<String>(charArray.size)
    var i = 0
    while (i < charArray.size) {
        val currentChar = charArray[i]
        latinChars[i] = when (currentChar) {
            'а', 'А' -> "a"
            'б', 'Б' -> "b"
            'в', 'В' -> "v"
            'г', 'Г' -> "g"
            'д', 'Д' -> "d"
            'е', 'Е' -> "e"
            'ё', 'Ё' -> "yo"
            'ж', 'Ж' -> "zh"
            'з', 'З' -> "z"
            'и', 'И' -> "i"
            'й', 'Й' -> "j"
            'к', 'К' -> "k"
            'л', 'Л' -> "l"
            'м', 'М' -> "m"
            'н', 'Н' -> "n"
            'о', 'О' -> "o"
            'п', 'П' -> "p"
            'р', 'Р' -> "r"
            'с', 'С' -> "s"
            'т', 'Т' -> "t"
            'у', 'У' -> "u"
            'ф', 'Ф' -> "f"
            'х', 'Х' -> "h"
            'ц', 'Ц' -> "c"
            'ч', 'Ч' -> "ch"
            'ш', 'Ш' -> "sh"
            'щ', 'Щ' -> "sh'"
            'ъ', 'Ъ' -> ""
            'ы', 'Ы' -> "y"
            'ь', 'Ь' -> ""
            'э', 'Э' -> "e"
            'ю', 'Ю' -> "yu"
            'я', 'Я' -> "ya"
            else -> currentChar.toString()
        }
        i++
    }
    return latinChars.joinToString(separator = "")
}

fun Age.toItemAge() =
    ItemAge(
        isChecked = false,
        name = this.name,
        age = this.age
    )
fun ItemAge.toAge() =
    Age(
        name = this.name,
        age = this.age
    )