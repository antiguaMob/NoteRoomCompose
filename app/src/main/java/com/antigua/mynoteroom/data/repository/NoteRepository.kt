package com.antigua.mynoteroom.data.repository

import kotlinx.coroutines.flow.Flow
import com.antigua.mynoteroom.data.NoteDao
import com.antigua.mynoteroom.model.NoteEntity


class NoteRepository (
    private val noteDao : NoteDao
        ){
    fun getAllFlow() : Flow<List<NoteEntity>> = noteDao.getAllFlow()
    suspend fun insert(note: NoteEntity) = noteDao.insert(note = note)
    suspend fun update(note: NoteEntity) = noteDao.update(note = note)
    suspend fun delete(note: NoteEntity) = noteDao.delete(note = note)

}