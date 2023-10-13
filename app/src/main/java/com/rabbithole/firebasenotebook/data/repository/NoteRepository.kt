package com.rabbithole.firebasenotebook.data.repository

import com.rabbithole.firebasenotebook.data.model.Note
import com.rabbithole.firebasenotebook.data.model.User
import com.rabbithole.firebasenotebook.util.UiState

interface NoteRepository {
    fun getNotes(user: User?, result: (UiState<List<Note>>) -> Unit)
    fun addNote(note: Note, result: (UiState<Pair<Note,String>>) -> Unit)
    fun updateNote(note: Note, result: (UiState<String>) -> Unit)
    fun deleteNote(note: Note, result: (UiState<String>) -> Unit)
}