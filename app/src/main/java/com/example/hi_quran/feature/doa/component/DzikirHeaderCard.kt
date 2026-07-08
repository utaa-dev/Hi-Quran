package com.example.hi_quran.feature.doa.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DzikirHeaderCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Subtle Pattern/Decoration
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .offset(x = 20.dp, y = (-20).dp)
                    .size(120.dp)
                    .background(Color.White.copy(alpha = 0.05f), CircleShape)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color.White.copy(alpha = 0.2f),
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.LightMode,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.padding(6.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Surface(
                        shape = CircleShape,
                        color = Color.White.copy(alpha = 0.2f)
                    ) {
                        Text(
                            text = "HARIAN",
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                            color = Color.White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Dzikir Pagi & Petang",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Amalan sunnah pembuka dan penutup hari yang penuh keberkahan.",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 0.dp),
                    modifier = Modifier.height(36.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Mulai Sekarang",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
