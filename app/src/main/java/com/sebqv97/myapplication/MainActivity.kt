package com.sebqv97.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.DestinationsNavHost
import com.sebqv97.myapplication.core.util.network_connectivity.ConnectivityObserver
import com.sebqv97.myapplication.core.util.network_connectivity.ConnectivityObserverImpl
import com.sebqv97.myapplication.feature_users.presentation.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var connectivityObserver: ConnectivityObserver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Check Network Connection
        connectivityObserver = ConnectivityObserverImpl(applicationContext)

        setContent {

            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val status by connectivityObserver.observe()
                        .collectAsState(initial = ConnectivityObserver.Status.Unavailable)
                    if (status == ConnectivityObserver.Status.Available) {
                        DestinationsNavHost(navGraph = NavGraphs.root)
                    } else {
                        Box(modifier = Modifier.padding(80.dp)) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                CircularProgressIndicator(modifier = Modifier.fillMaxSize())

                                Toast.makeText(
                                    applicationContext,
                                    "Could not connect to Internet",
                                    Toast.LENGTH_LONG
                                ).show()


                            }

                        }


                    }
                }

            }
        }
    }
}







