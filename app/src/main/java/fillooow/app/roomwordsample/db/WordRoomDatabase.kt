package fillooow.app.roomwordsample.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import fillooow.app.roomwordsample.db.dao.WordDao
import fillooow.app.roomwordsample.db.entity.WordEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(
    entities = [WordEntity::class],
    version = 1,
    exportSchema = false
)
abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    // Singleton для предотвращения нескольких экземпляров бд, взаимодействующих одновременно
    companion object {

        @Volatile
        private var INSTANCE: WordRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): WordRoomDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordRoomDatabase::class.java,
                    "word_database"
                ).addCallback(WordDatabaseCallback(scope = scope))
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }

    private class WordDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
//                    database.wordDao().getAlphabetizedWords() // если захочется сохранять состояние бд
                    populateDatabase(wordDao = database.wordDao())
                }
            }
        }

        private suspend fun populateDatabase(wordDao: WordDao) {

            wordDao.deleteAll()

            wordDao.insert(wordEntity = WordEntity("Hello"))
            wordDao.insert(wordEntity = WordEntity("cruel"))
            wordDao.insert(wordEntity = WordEntity("world"))
        }
    }
}