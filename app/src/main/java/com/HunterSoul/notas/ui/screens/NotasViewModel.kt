package com.HunterSoul.notas.ui.screens
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.HunterSoul.notas.NotasApplication
import com.HunterSoul.notas.data.NotasRepository
import com.HunterSoul.notas.model.Nota
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface NotasUiState {
    data class Success(val notas: List<Nota>) : NotasUiState
    object Error : NotasUiState
    object Loading : NotasUiState
}

class NotasViewModel(private val notasRepository: NotasRepository) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    /*var marsUiState: String by mutableStateOf("")
        private set*/


    var notasUiState: NotasUiState by mutableStateOf(NotasUiState.Loading)
        private set
    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {
        getNotas()
    }

    /**
     * Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [MutableList].
     */
    fun getNotas() {
        //marsUiState = "Set the Mars API status response here!"
        viewModelScope.launch {
            notasUiState =  try {
                /*val listResult = MarsApi.retrofitService.getPhotos()
                listResult.forEach { Log.d("${it.id}", "${it.img_src}" ) }*/
                //val marsPhotosRepository = NetworkMarsPhotosRepository(retrofitService)
                //val marsPhotosRepository = DefaultAppContainer().marsPhotosRepository
                val listResult = notasRepository.getNotas()
                NotasUiState.Success(
                    listResult
                    //listResult.forEach{log.d("Nota", it.id)}
                )
            } catch (e: IOException) {
                NotasUiState.Error
            }

        }

    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as NotasApplication)
                val notasRepository = application.container.notasRepository
                NotasViewModel(notasRepository = notasRepository)
            }
        }
    }
}