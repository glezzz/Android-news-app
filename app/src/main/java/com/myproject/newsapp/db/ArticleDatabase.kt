package com.myproject.newsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.myproject.newsapp.models.Article

// DB classes for Room always need to be abstract & need Database annotation
@Database(
    entities = [Article::class],
    // version is used to update DB later on and be able to reference DB version what version
    version = 1
)
// add TypeConverters to DB
@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

    companion object {
        // other threads can see when a thread changes this instance
        @Volatile
        // recreate an instance of our DB which will be our singleton
        private var instance: ArticleDatabase? = null

        // to make sure there's only one instance of our DB at once
        private val LOCK = Any()

        // this is called whenever an instance of the DB is created
        // return current instance. If it's null, set that instance with the synchronized block
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            // check again if it's null, if not call createDatabase and also set instance to
            // result of createDatabase.That instance of DB class will then be used to access
            // the ArticleDao which is used to access the actual DB functions
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                "article_db.db"
            ).build()
    }
}