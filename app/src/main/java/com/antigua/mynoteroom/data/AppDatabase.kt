package com.antigua.mynoteroom.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.antigua.mynoteroom.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase (){

    abstract  fun noteDao(): NoteDao

    companion object{
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context) : AppDatabase {
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, "note.db")
                    // add migration here
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE as AppDatabase
        }
    }
}