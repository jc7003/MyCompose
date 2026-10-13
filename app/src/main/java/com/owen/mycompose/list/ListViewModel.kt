package com.owen.mycompose.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.milliseconds

class ListViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(ListUiState())
    val uiState: StateFlow<ListUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            // 模擬載入資料
            _uiState.value = ListUiState(
                names = List(100) { "Item $it" },
                isLoading = false
            )

            withContext(Dispatchers.IO) {
                delay(2000.milliseconds)

                // 模擬載入資料
                _uiState.value = ListUiState(
                    names = List(100) { "Item $it $it" },
                    isLoading = false
                )
            }
        }
    }
}