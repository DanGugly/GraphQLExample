package com.example.graphqlexample.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.api.Response
import com.example.graphqlexample.CharactersListQuery
import com.example.graphqlexample.repository.CharacterRepository
import com.example.graphqlexample.utils.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: CharacterRepository
) : ViewModel(){

    private val _charactersListLiveData by lazy {
        MutableLiveData<UIState<Response<CharactersListQuery.Data>>>(UIState.Loading())
    }
    val charactersList : LiveData<UIState<Response<CharactersListQuery.Data>>>
        get() = _charactersListLiveData

    fun queryCharacters(){
        _charactersListLiveData.postValue(UIState.Loading())
        viewModelScope.launch {
            try {
                val response = repository.queryCharactersList()
                _charactersListLiveData.postValue(UIState.Success(response))
            } catch (e : Exception){
                Log.e("CharacterViewModel", "Failure", e)
                _charactersListLiveData.postValue(UIState.Error(e))
            }
        }
    }
}