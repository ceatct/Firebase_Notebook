package com.rabbithole.firebasenotebook.ui.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rabbithole.firebasenotebook.data.model.Note
import com.rabbithole.firebasenotebook.data.model.User
import com.rabbithole.firebasenotebook.data.repository.NoteRepository
import com.rabbithole.firebasenotebook.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    val repository: NoteRepository
): ViewModel() {

    private val _notes = MutableLiveData<UiState<List<Note>>>()
    val note: LiveData<UiState<List<Note>>>
        get() = _notes

    private val _addNote = MutableLiveData<UiState<Pair<Note,String>>>()
    val addNote: LiveData<UiState<Pair<Note,String>>>
        get() = _addNote

    private val _updateNote = MutableLiveData<UiState<String>>()
    val updateNote: LiveData<UiState<String>>
        get() = _updateNote

    private val _deleteNote = MutableLiveData<UiState<String>>()
    val deleteNote: LiveData<UiState<String>>
        get() = _deleteNote

    fun getNotes(user: User?) {
        _notes.value = UiState.Loading
        repository.getNotes(user) { _notes.value = it }
    }

    fun addNote(note: Note){
        _addNote.value = UiState.Loading
        repository.addNote(note) { _addNote.value = it }
    }

    fun updateNote(note: Note){
        _updateNote.value = UiState.Loading
        repository.updateNote(note) { _updateNote.value = it }
    }

    fun deleteNote(note: Note){
        _deleteNote.value = UiState.Loading
        repository.deleteNote(note) { _deleteNote.value = it }
    }

}