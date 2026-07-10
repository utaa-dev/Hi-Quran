package com.example.hi_quran.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.NotificationsActive
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material.icons.outlined.WbTwilight
import androidx.compose.material.icons.outlined.NightsStay
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.CloudQueue
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hi_quran.ui.theme.PrimaryGreen
import com.example.hi_quran.ui.theme.LightGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrayerScheduleScreen(
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Jadwal Sholat",
                        fontWeight = FontWeight.Bold,
                        color = PrimaryGreen
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = PrimaryGreen)
                    }
                },
                actions = {
                    IconButton(onClick = { /* Settings */ }) {
                        Icon(Icons.Outlined.Explore, contentDescription = "Location Settings", tint = PrimaryGreen)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                LocationInfoSection()
            }

            item {
                NextPrayerDetailCard()
            }

            items(getPrayerTimes()) { prayer ->
                PrayerTimeRow(
                    name = prayer.name,
                    time = prayer.time,
                    icon = prayer.icon,
                    status = prayer.status,
                    isNext = prayer.isNext
                )
            }

            item {
                PrayerQuoteInfo()
            }
        }
    }
}

@Composable
fun LocationInfoSection() {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Outlined.LocationOn,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Jakarta, Indonesia",
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ) {
            Column {
                Text(
                    text = "Selasa, 21 Mei 2024",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "13 Dzulqa'dah 1445 H",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
            Surface(
                color = LightGreen,
                shape = RoundedCornerShape(50.dp)
            ) {
                Text(
                    text = "Metode: Kemenag RI",
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryGreen
                )
            }
        }
    }
}

@Composable
fun NextPrayerDetailCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(32.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFF006D3E), Color(0xFF00A35C))
                    )
                )
                .padding(24.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "SHOLAT BERIKUTNYA",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "Maghrib",
                    color = Color.White,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TimeCountUnit(value = "02", label = "JAM")
                    Text(":", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    TimeCountUnit(value = "45", label = "MENIT")
                    Text(":", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                    TimeCountUnit(value = "12", label = "DETIK")
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Surface(
                    color = Color.White.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(50.dp)
                ) {
                    Text(
                        text = "17:54 WIB",
                        modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun TimeCountUnit(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = label,
            color = Color.White.copy(alpha = 0.7f),
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun PrayerTimeRow(
    name: String,
    time: String,
    icon: ImageVector,
    status: String? = null,
    isNext: Boolean = false
) {
    val backgroundColor = if (isNext) LightGreen else MaterialTheme.colorScheme.surface
    val contentColor = if (isNext) PrimaryGreen else MaterialTheme.colorScheme.onSurface
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = if (isNext) 0.dp else 1.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(44.dp),
                shape = CircleShape,
                color = if (isNext) PrimaryGreen else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = if (isNext) Color.White else Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = contentColor
                    )
                    if (status != null) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Surface(
                            color = if (status == "SELESAI") Color.LightGray.copy(alpha = 0.4f) else PrimaryGreen,
                            shape = RoundedCornerShape(50.dp)
                        ) {
                            Text(
                                text = status,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                                fontSize = 8.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = if (status == "SELESAI") Color.Gray else Color.White
                            )
                        }
                    }
                }
                Text(
                    text = time,
                    fontSize = 13.sp,
                    color = contentColor.copy(alpha = 0.7f)
                )
            }
            
            Icon(
                imageVector = if (isNext) Icons.Outlined.NotificationsActive else Icons.Outlined.Notifications,
                contentDescription = null,
                tint = if (isNext) PrimaryGreen else Color.Gray,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun PrayerQuoteInfo() {
    Surface(
        color = LightGreen,
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                tint = PrimaryGreen,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "\"Sholat adalah tiang agama. Barangsiapa mendirikannya, maka ia telah mendirikan agamanya.\"",
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = PrimaryGreen,
                lineHeight = 20.sp
            )
        }
    }
}

data class PrayerTimeInfo(
    val name: String,
    val time: String,
    val icon: ImageVector,
    val status: String? = null,
    val isNext: Boolean = false
)

fun getPrayerTimes() = listOf(
    PrayerTimeInfo("Imsak", "04:22", Icons.Outlined.WbTwilight),
    PrayerTimeInfo("Subuh", "04:32", Icons.Outlined.NightsStay),
    PrayerTimeInfo("Terbit", "05:49", Icons.Outlined.WbSunny),
    PrayerTimeInfo("Dzuhur", "11:49", Icons.Outlined.LightMode, status = "SELESAI"),
    PrayerTimeInfo("Ashar", "15:10", Icons.Outlined.CloudQueue),
    PrayerTimeInfo("Maghrib", "17:54", Icons.Outlined.WbTwilight, status = "BERIKUTNYA", isNext = true),
    PrayerTimeInfo("Isya", "19:07", Icons.Outlined.NightsStay)
)
