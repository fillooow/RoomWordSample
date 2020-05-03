package fillooow.app.roomwordsample.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity - представляют собой SQLite таблицу
 */
@Entity(tableName = "word_table") // Представляет собой SQLite таблицу с именем word_table
data class WordEntity(

    @PrimaryKey
    @ColumnInfo(name = "word") // по умолчанию имя колонки будет совпадать с именем переменой. Тут для примера так же назвали.
    val word: String // Переменные для БД должны быть публичными
)