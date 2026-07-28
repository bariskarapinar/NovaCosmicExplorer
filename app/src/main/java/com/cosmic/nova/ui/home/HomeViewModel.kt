package com.cosmic.nova.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cosmic.nova.data.model.Planet
import com.cosmic.nova.data.repository.SpaceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: SpaceRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadPlanets()
    }

    private fun loadPlanets() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            try {
                val planets = repository.getPlanets()
                _uiState.value = HomeUiState.Success(planets)
            } catch (e: Exception) {
                _uiState.value = HomeUiState.Error(e.message ?: "Unknown error")
            }
        }
    }
}

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(val planets: List<Planet>) : HomeUiState()
    data class Error(val message: String) : HomeUiState()
}
