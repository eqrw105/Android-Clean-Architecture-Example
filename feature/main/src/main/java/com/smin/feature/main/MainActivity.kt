package com.smin.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.smin.core.designsystem.theme.CoreTheme
import com.smin.core.domain.repository.UserRepository
import com.smin.core.domain.usecase.GetUserUseCase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoreTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                    when (val state = uiState) {
                        is MainState.Loading -> {
                            Loading()
                        }

                        is MainState.Error -> {
                            Error()
                        }

                        is MainState.Success -> {
                            User(
                                id = state.user.id,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Loading(modifier: Modifier = Modifier) {
    Text(
        text = "loading...",
        modifier = modifier
    )
}

@Composable
fun User(id: Int, modifier: Modifier = Modifier) {
    Text(
        text = "User Id : $id",
        modifier = modifier
    )
}


@Composable
fun Error(modifier: Modifier = Modifier) {
    Text(
        text = "failed...",
        modifier = modifier
    )
}