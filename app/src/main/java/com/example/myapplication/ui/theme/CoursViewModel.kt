package com.example.myapplication.ui.theme

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CoursViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CoursUiState(id = 4, nom = "karaté", description = "cobra kai never dies", prix = 24.2))
    val uiState: StateFlow<CoursUiState> = _uiState.asStateFlow()
}