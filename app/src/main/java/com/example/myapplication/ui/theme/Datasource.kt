package com.example.myapplication.ui.theme

object Datasource {
    fun retourneListeCours(): List<CoursUiState> {
        return listOf(
            CoursUiState(1,"karaté","cobra kai never dies",24.2),
            CoursUiState(2,"hip hop","b girl",20.2)
        )
    }
}