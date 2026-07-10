package com.example.hi_quran.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hi_quran.ui.theme.PrimaryGreen
import com.example.hi_quran.ui.theme.LightGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HijriCalendarScreen(
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Kalender Hijriah",
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
                    IconButton(onClick = { /* Menu */ }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "More", tint = PrimaryGreen)
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
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                HijriMonthSelector()
            }

            item {
                CalendarGridSection()
            }

            item {
                ImportantMomentsHeader()
            }

            items(getImportantMoments()) { moment ->
                MomentItem(moment)
            }

            item {
                CalendarQuoteBanner()
            }
        }
    }
}

@Composable
fun HijriMonthSelector() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(containerColor = PrimaryGreen)
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = CircleShape,
                color = Color.White.copy(alpha = 0.2f),
                modifier = Modifier.size(40.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, null, tint = Color.White, modifier = Modifier.size(20.dp))
                }
            }
            
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Ramadhan 1445 H",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = "MARET - APRIL 2024",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Surface(
                shape = CircleShape,
                color = Color.White.copy(alpha = 0.2f),
                modifier = Modifier.size(40.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, null, tint = Color.White, modifier = Modifier.size(20.dp))
                }
            }
        }
    }
}

@Composable
fun CalendarGridSection() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                val days = listOf("MIN", "SEN", "SEL", "RAB", "KAM", "JUM", "SAB")
                days.forEach { day ->
                    Text(
                        text = day,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Mocking a few rows of the calendar
            CalendarRow(listOf("25", "26", "27", "28", "29", "1", "2"))
            CalendarRow(listOf("3", "4", "5", "6", "7", "8", "9"), highlightedDay = "4")
            CalendarRow(listOf("10", "11", "12", "13", "14", "15", "16"))
        }
    }
}

@Composable
fun CalendarRow(days: List<String>, highlightedDay: String? = null) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        days.forEach { day ->
            Box(
                modifier = Modifier.weight(1f).height(40.dp),
                contentAlignment = Alignment.Center
            ) {
                if (day == highlightedDay) {
                    Surface(
                        color = LightGreen,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                            Text(text = day, fontWeight = FontWeight.Bold, color = PrimaryGreen, fontSize = 14.sp)
                            Text(text = "HARI INI", fontSize = 7.sp, fontWeight = FontWeight.ExtraBold, color = PrimaryGreen)
                        }
                    }
                } else {
                    Text(
                        text = day,
                        fontSize = 14.sp,
                        fontWeight = if (day.toIntOrNull() != null && day.toInt() < 10) FontWeight.Bold else FontWeight.Normal,
                        color = if (day == "8") Color.Red else Color.Black
                    )
                }
            }
        }
    }
}

@Composable
fun ImportantMomentsHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Momen Penting",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Surface(
            color = LightGreen,
            shape = RoundedCornerShape(50.dp)
        ) {
            Text(
                text = "Bulan Ini",
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryGreen
            )
        }
    }
}

@Composable
fun MomentItem(moment: ImportantMoment) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp).fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(48.dp),
                shape = RoundedCornerShape(12.dp),
                color = PrimaryGreen
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = moment.dateNum,
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        lineHeight = 18.sp
                    )
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = moment.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                )
                Text(
                    text = moment.dateFull,
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            }
            
            Icon(
                imageVector = moment.icon,
                contentDescription = null,
                tint = Color.LightGray,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun CalendarQuoteBanner() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(24.dp))
    ) {
        // Placeholder for background image
        Box(modifier = Modifier.fillMaxSize().background(Color.Gray.copy(alpha = 0.2f)))
        
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "99",
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "\"Berlombalah kamu dalam kebaikan.\"",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "QS. AL-BAQARAH: 148",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

data class ImportantMoment(
    val title: String,
    val dateFull: String,
    val dateNum: String,
    val icon: ImageVector
)

fun getImportantMoments() = listOf(
    ImportantMoment("Awal Ramadan 1445 H", "Senin, 11 Maret 2024", "1\nMAR", Icons.Outlined.CalendarMonth),
    ImportantMoment("Nuzulul Qur'an", "Kamis, 28 Maret 2024", "28\nMAR", Icons.AutoMirrored.Outlined.MenuBook),
    ImportantMoment("Puasa Ayyamul Bidh", "13-15 Ramadan 1445 H", "25\nMAR", Icons.Outlined.Restaurant)
)
