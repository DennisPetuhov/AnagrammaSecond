package com.example.anagrammasecond

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class SharedViewModel:ViewModel()  {
    private var _incomingText = MutableStateFlow("")
    val incomingText: StateFlow<String> get() = _incomingText
    private var _filter = MutableStateFlow("")
    val filter: StateFlow<String> get() = _filter
    private var _anagram = MutableStateFlow("")
    val anagram: StateFlow<String> get() = _anagram

    init {
        makeAnagramAuto()
    }

    fun onValueChangedTextInput(newText: String) {
        _incomingText.value = newText
    }

    fun onValueChangedFilterInput(newText: String) {
        _filter.value = newText
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun makeAnagramAuto() {
        viewModelScope.launch {
            _incomingText.flatMapLatest { text ->
                _filter.flatMapLatest { filter -> makeAnagramGreatAgain(text, filter) }
            }.collect { _anagram.emit(it) }
        }
    }
}

