package com.example.hi_quran.feature.doa

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasbihDigitalScreen(
    onBackClick: () -> Unit,
) {
    var count by remember { mutableIntStateOf(0) }
    var target by remember { mutableIntStateOf(33) }
    var isVibrateEnabled by remember { mutableStateOf(value = true) }
    var isSoundEnabled by remember { mutableStateOf(value = true) }
    val targets = listOf(33, 99, 100, -1) // -1 for infinity

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Tasbih Digital",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF006D3E)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO: Settings */ }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Reading Selection Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF1F5F2)
                ),
                shape = MaterialTheme.shapes.large
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            "SEDANG MEMBACA",
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.Gray
                        )
                        Text(
                            "Subhanallah",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF006D3E)
                        )
                    }
                    Button(
                        onClick = { /* TODO: Pilih Doa */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF006D3E)
                        ),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Icon(
                            Icons.Default.FormatListBulleted,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Pilih Doa", style = MaterialTheme.typography.labelLarge)
                    }
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            // Counter Circle
            Box(
                modifier = Modifier
                    .size(280.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF1F5F2))
                    .clickable {
                        if (target == -1 || (count < target)) {
                            count++
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                // Inner Circle for visual effect
                Surface(
                    modifier = Modifier.size(240.dp),
                    shape = CircleShape,
                    color = Color.White,
                    shadowElevation = 2.dp
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = count.toString(),
                            style = MaterialTheme.typography.displayLarge.copy(
                                fontSize = 80.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color(0xFF006D3E)
                        )
                        Text(
                            "TAP UNTUK HITUNG",
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.Gray,
                            letterSpacing = 1.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Reset Button
            Button(
                onClick = { count = 0 },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFF1F5F2),
                    contentColor = Color.Black
                ),
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.height(48.dp)
            ) {
                Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Reset Hitungan", style = MaterialTheme.typography.labelLarge)
            }

            Spacer(modifier = Modifier.weight(1f))

            // Target Selection
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    "TARGET HITUNGAN",
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    targets.forEach { value ->
                        val isSelected = target == value
                        Surface(
                            modifier = Modifier
                                .weight(1f)
                                .height(48.dp)
                                .clickable { target = value },
                            shape = MaterialTheme.shapes.medium,
                            color = if (isSelected) Color(0xFFC8E6C9) else Color(0xFFF1F5F2),
                            border = if (isSelected) null else null
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = if (value == -1) "∞" else value.toString(),
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSelected) Color(0xFF006D3E) else Color.Gray
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Settings Toggles
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                SettingsToggle(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.Vibration,
                    label = "Getar",
                    checked = isVibrateEnabled,
                    onCheckedChange = { isVibrateEnabled = it },
                )
                SettingsToggle(
                    modifier = Modifier.weight(1f),
                    icon = Icons.Default.VolumeUp,
                    label = "Suara",
                    checked = isSoundEnabled,
                    onCheckedChange = { isSoundEnabled = it },
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Composable
fun SettingsToggle(
    modifier: Modifier = Modifier,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Surface(
        modifier = modifier.height(56.dp),
        shape = MaterialTheme.shapes.medium,
        color = Color(0xFFF1F5F2)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(24.dp), tint = Color.Gray)
            Spacer(modifier = Modifier.width(8.dp))
            Text(label, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.weight(1f))
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color(0xFF006D3E),
                    uncheckedThumbColor = Color.Gray,
                    uncheckedTrackColor = Color.LightGray
                )
            )
        }
    }
}
