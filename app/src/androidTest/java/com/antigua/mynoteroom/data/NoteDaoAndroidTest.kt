package com.antigua.mynoteroom.data

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.antigua.mynoteroom.model.NoteEntity
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test


class NoteDaoAndroidTest {

    private lateinit var sut: NoteDao
    private lateinit var mDb: AppDatabase

    @Before
    fun createDb(){
        mDb = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        sut = mDb.noteDao()
    }

    @After
    fun cleanUp(){
        mDb.close()
    }

    @Test
    fun testInsertNoteAndroidReadInList() = runBlocking {
        // arrange
        val fakeText = "some text"
        val fakeNote = NoteEntity(text = fakeText)

        // act
        sut.insert(fakeNote)
        val noteList = sut.getAllFlow().first()

        // assert
        assertThat(noteList.first().text).isEqualTo(fakeText)
    }

    @Test
    fun testUpdateNoteAndroidReadInList() = runBlocking {
        // arrange
        val fakeText = "some text"
        val fakeTextUpdated = "updated text"
        val fakeNote = NoteEntity(text = fakeText)

        // act
        sut.insert(fakeNote)
        val noteList = sut.getAllFlow().first()
        sut.update(noteList.first().copy(text = fakeTextUpdated))
        val noteListUpdated = sut.getAllFlow().first()

        // assert
        assertThat(noteListUpdated.first().text).isEqualTo(fakeTextUpdated)
    }

    @Test
    fun testDeleteNoteRemovesNoteFromList() = runBlocking {
        // arrange
        val fakeText = "some text"
        val fakeNote = NoteEntity(text = fakeText)

        // act
        sut.insert(fakeNote)
        val noteList = sut.getAllFlow().first()
        sut.delete(noteList.first())
        val noteListUpdated = sut.getAllFlow().first()

        // assert
        assertThat(noteListUpdated).isEmpty()
    }
}