package com.example.hi_quran.feature.profile

import androidx.activity.ComponentActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.automirrored.outlined.Notes
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.hi_quran.viewmodel.MainViewModel

@Composable
fun ProfileScreen(
    viewModel: MainViewModel = viewModel(
        viewModelStoreOwner = LocalContext.current as ComponentActivity
    )
) {
    val isDarkMode by viewModel.isDarkMode.collectAsState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        contentWindowInsets = WindowInsets.statusBars
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header Profile Card
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box {
                            Surface(
                                modifier = Modifier.size(80.dp),
                                shape = CircleShape,
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                            // Edit Icon badge
                            Surface(
                                modifier = Modifier
                                    .size(24.dp)
                                    .align(Alignment.BottomEnd),
                                shape = CircleShape,
                                color = MaterialTheme.colorScheme.primary,
                                border = BorderStroke(2.dp, MaterialTheme.colorScheme.surface)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier.padding(4.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(20.dp))

                        Column {
                            Text(
                                text = "Utaa",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Hidayatullah",
                                fontSize = 14.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.05f),
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.2f))
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.CheckCircle,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(12.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "Premium Member",
                                        fontSize = 10.sp,
                                        color = MaterialTheme.colorScheme.primary,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Stats Section
            item {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    // Big Stats Card (Khatam)
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
                    ) {
                        Row(
                            modifier = Modifier.padding(24.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "12",
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                                Text(
                                    text = "Khatam Qur'an",
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Pencapaian Luar Biasa!",
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.6f)
                                )
                            }
                            Surface(
                                modifier = Modifier.size(60.dp),
                                shape = RoundedCornerShape(12.dp),
                                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.1f)
                            ) {
                                // Placeholder for icon or badge
                            }
                        }
                    }

                    // Small Stats Row
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Favorite Card
                        Card(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(Icons.Default.FavoriteBorder, null, tint = MaterialTheme.colorScheme.primary)
                                    Text("254", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("AYAT FAVORIT", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f))
                            }
                        }
                        // Streak Card
                        Card(
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(Icons.Default.ElectricBolt, null, tint = MaterialTheme.colorScheme.onSurface)
                                    Text("32", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface)
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                Text("HARI STREAK", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f))
                            }
                        }
                    }
                }
            }

            // Menu Sections
            item {
                ProfileMenuSection(
                    title = "Akun",
                    items = listOf(
                        ProfileMenuItem("Bookmark Saya", Icons.Outlined.Bookmark, MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), MaterialTheme.colorScheme.primary),
                        ProfileMenuItem("Catatan Saya", Icons.AutoMirrored.Outlined.Notes, MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), MaterialTheme.colorScheme.primary)
                    )
                )
            }

            item {
                ProfileMenuSection(
                    title = "Pengaturan",
                    items = listOf(
                        ProfileMenuItem("Pengaturan", Icons.Outlined.Settings, MaterialTheme.colorScheme.surfaceVariant, MaterialTheme.colorScheme.onSurfaceVariant),
                        ProfileMenuItem("Ukuran Font", Icons.Outlined.TextFormat, MaterialTheme.colorScheme.surfaceVariant, MaterialTheme.colorScheme.onSurfaceVariant, trailing = "Standar")
                    ),
                    extraItems = {
                        ProfileMenuToggleItem(
                            label = "Dark Mode",
                            icon = Icons.Outlined.DarkMode,
                            iconBg = MaterialTheme.colorScheme.surfaceVariant,
                            checked = isDarkMode,
                            onCheckedChange = { viewModel.toggleDarkMode(it) }
                        )
                    }
                )
            }

            item {
                ProfileMenuSection(
                    title = "Info",
                    items = listOf(
                        ProfileMenuItem("Tentang Aplikasi", Icons.Outlined.Info, MaterialTheme.colorScheme.surfaceVariant, MaterialTheme.colorScheme.onSurfaceVariant),
                        ProfileMenuItem("Kebijakan Privasi", Icons.Outlined.PrivacyTip, MaterialTheme.colorScheme.surfaceVariant, MaterialTheme.colorScheme.onSurfaceVariant)
                    )
                )
            }

            // Logout Button
            item {
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedButton(
                    onClick = { },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    shape = RoundedCornerShape(28.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red)
                ) {
                    Text("Keluar Akun", fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Versi 2.4.1 (Stable)",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

data class ProfileMenuItem(
    val label: String,
    val icon: ImageVector,
    val iconBg: Color,
    val iconTint: Color,
    val trailing: String? = null
)

@Composable
fun ProfileMenuSection(
    title: String,
    items: List<ProfileMenuItem>,
    extraItems: @Composable ColumnScope.() -> Unit = {}
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column {
                items.forEachIndexed { index, item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { }
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            modifier = Modifier.size(40.dp),
                            shape = RoundedCornerShape(10.dp),
                            color = item.iconBg
                        ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = null,
                                tint = item.iconTint,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            text = item.label,
                            modifier = Modifier.weight(1f),
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        if (item.trailing != null) {
                            Text(text = item.trailing, fontSize = 12.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                        Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, null, tint = MaterialTheme.colorScheme.outline, modifier = Modifier.size(20.dp))
                    }
                    if (index < items.size - 1) {
                        HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                    }
                }
                extraItems()
            }
        }
    }
}

@Composable
fun ProfileMenuToggleItem(
    label: String,
    icon: ImageVector,
    iconBg: Color,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(40.dp),
            shape = RoundedCornerShape(10.dp),
            color = iconBg
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            modifier = Modifier.weight(1f),
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = MaterialTheme.colorScheme.primary,
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = MaterialTheme.colorScheme.outline
            )
        )
    }
}
