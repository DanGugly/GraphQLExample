package com.example.graphqlexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.apollographql.apollo.api.Response
import com.example.graphqlexample.utils.UIState
import com.example.graphqlexample.viewmodel.CharacterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel : CharacterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.charactersList.observe(this, ::handleResult)

        viewModel.queryCharacters()
    }

    private fun handleResult(uiState: UIState<Response<CharactersListQuery.Data>>?) {
        when(uiState) {
            is UIState.Loading -> {
                Toast.makeText(
                    baseContext,
                    "LOADING",
                    Toast.LENGTH_LONG
                ).show()
            }
            is UIState.Success -> {
                Toast.makeText(
                    baseContext,
                    uiState.success?.data?.characters?.results?.get(0)?.name,
                    Toast.LENGTH_LONG
                ).show()
            }
            is UIState.Error -> {
                Toast.makeText(
                    baseContext,
                    uiState.error?.localizedMessage,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}