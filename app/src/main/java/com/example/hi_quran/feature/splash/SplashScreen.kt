package com.example.hi_quran.feature.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hi_quran.R
import com.example.hi_quran.viewmodel.SplashViewModel

@Composable
fun SplashScreen(
    onDataLoaded: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val isImporting by viewModel.isImporting.collectAsState()

    LaunchedEffect(isImporting) {
        if (!isImporting) {
            onDataLoaded()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Replace with your app logo
            // Image(
            //     painter = painterResource(id = R.drawable.ic_logo),
            //     contentDescription = "Logo",
            //     modifier = Modifier.size(120.dp)
            // )
            Text(text = "Hi Quran", style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(16.dp))
            CircularProgressIndicator()
            Text(text = "Memuat Data...", modifier = Modifier.padding(top = 8.dp))
        }
    }
}
