package fillooow.app.roomwordsample.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import fillooow.app.roomwordsample.db.WordRoomDatabase
import fillooow.app.roomwordsample.db.entity.WordEntity
import fillooow.app.roomwordsample.repository.WordRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Разделяет UI и Repository
class WordViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: WordRepository

    val allWords: LiveData<List<WordEntity>>

    init {
        val wordsDao = WordRoomDatabase.getDatabase(application).wordDao()
        repository = WordRepository(wordsDao)
        allWords = repository.allWords
    }

    fun insert(word: WordEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(word)
    }
}