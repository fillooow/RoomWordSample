package fillooow.app.roomwordsample.repository

import androidx.lifecycle.LiveData
import fillooow.app.roomwordsample.db.dao.WordDao
import fillooow.app.roomwordsample.db.entity.WordEntity

// Является средством доставки данных в приложение
class WordRepository(
    private val wordDao: WordDao // Передаем DAO, так как хватит и его для взаимодействия с БД
) {

    val allWords: LiveData<List<WordEntity>> = wordDao.getAlphabetizedWords()

    suspend fun insert(word: WordEntity) = wordDao.insert(word)
}