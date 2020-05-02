package fillooow.app.roomwordsample.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import fillooow.app.roomwordsample.db.entity.Word

/**
 * DAO (data access object) - в дао задаются запросы,
 * которые в нем же связываются с методами их вызова.
 *
 * DAO должен быть интерфейсом или абстрактным классом.
 *
 * Запросы должны вызваться не на UI потоке.
 */
@Dao
interface WordDAO {

    // Получаем все слова в алфавитном порядке
    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): LiveData<List<Word>>

    @Insert(onConflict = OnConflictStrategy.IGNORE) // Если такая запись уже есть в таблице, ничего не делаем
    suspend fun insert(word: Word)

    @Query("DELETE FROM word_table") // Для удаления нескольких сущностей нет отдельной аннотации, так что используем @Query
    suspend fun deleteAll()
}