package com.example.hi_quran.feature.home.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeHeader(userName: String = "Utaa") {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier
                    .size(56.dp)
                    .border(
                        BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                        RoundedCornerShape(20.dp)
                    )
                    .padding(4.dp),
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "Assalamu'alaikum,",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 18.sp
                    )
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "$userName!",
                        style = MaterialTheme.typography.headlineSmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 24.sp
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "👋", fontSize = 22.sp)
                }
            }
        }
        IconButton(
            onClick = { },
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.08f), CircleShape)
                .size(44.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "Notification",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
