package com.example.hi_quran.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DailyAyahCard() {
    val yellowBg = Color(0xFFFFD54F) // Warm yellow background
    val darkYellow = Color(0xFF7F6000) // Dark yellow/brown for text
    val whiteCard = Color.White

    Box(modifier = Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = yellowBg)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Header: Icon + Title
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Lightbulb,
                        contentDescription = null,
                        tint = darkYellow,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "AYAT HEBAT HARI INI",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = darkYellow
                    )
                }

                // Inner White Card for Ayah
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = whiteCard)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "إِيَّاكَ نَعْبُدُ وَإِيَّاكَ نَسْتَعِينُ",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "\"Hanya kepada Engkaulah kami menyembah dan hanya kepada Engkaulah kami mohon pertolongan.\"",
                            fontSize = 14.sp,
                            fontStyle = FontStyle.Italic,
                            color = Color.DarkGray,
                            textAlign = TextAlign.Center,
                            lineHeight = 20.sp
                        )
                    }
                }

                // Explanation Section
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top
                ) {
                    Surface(
                        modifier = Modifier.size(32.dp),
                        shape = CircleShape,
                        color = Color(0xFFC6A700) // Darker yellow for icon circle
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.AutoAwesome,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Artinya kita cuma minta tolong sama Allah SWT, karena Allah adalah sebaik-baiknya penolong kita!",
                        fontSize = 13.sp,
                        color = darkYellow,
                        lineHeight = 18.sp
                    )
                }
            }
        }

        // Floating Play Button
        Surface(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 12.dp)
                .size(48.dp),
            shape = CircleShape,
            color = Color(0xFF004D40), // Dark green
            shadowElevation = 4.dp
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Play",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}
