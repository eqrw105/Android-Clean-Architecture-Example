package com.smin.feature.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smin.core.domain.model.User
import com.smin.core.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface MainState {
    data object Loading : MainState
    data class Success(val user: User) : MainState
    data class Error(val message: String) : MainState
}

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<MainState> = MutableStateFlow(MainState.Loading)
    val uiState: StateFlow<MainState> = _uiState.asStateFlow()

    init {
        getUser()
    }

    private fun getUser() {
        val handler = CoroutineExceptionHandler { _, throwable ->
            _uiState.update { MainState.Error(throwable.message ?: "get user failed") }
        }
        viewModelScope.launch(Dispatchers.IO + handler) {
            val user = getUserUseCase()
            _uiState.update { MainState.Success(user) }
        }
    }
}