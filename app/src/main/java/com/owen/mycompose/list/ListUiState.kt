package com.owen.mycompose.list

data class ListUiState(
    val names: List<String> = emptyList(),
    val isLoading: Boolean = false,
)
