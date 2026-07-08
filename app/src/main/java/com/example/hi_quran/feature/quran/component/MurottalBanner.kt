package com.example.hi_quran.feature.quran.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MurottalBanner(
    modifier: Modifier = Modifier,
    onPilihQariClick: () -> Unit = {}
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF006D3E)
        )
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            // Background Icon (Volume Icon as watermark)
            Icon(
                imageVector = Icons.Default.VolumeUp,
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.BottomEnd)
                    .offset(x = 30.dp, y = 30.dp),
                tint = Color.White.copy(alpha = 0.1f)
            )

            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Murottal Audio",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Text(
                    text = "Dengarkan lantunan ayat suci dari Qari ternama dunia.",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth(0.7f)
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Button(
                    onClick = onPilihQariClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFF006D3E)
                    ),
                    shape = RoundedCornerShape(50.dp),
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 8.dp),
                    modifier = Modifier.height(36.dp)
                ) {
                    Text(
                        text = "Pilih Qari",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
