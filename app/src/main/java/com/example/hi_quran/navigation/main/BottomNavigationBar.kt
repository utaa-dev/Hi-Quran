package com.example.hi_quran.navigation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.hi_quran.ui.theme.PrimaryGreen

@Composable
fun BottomNavigationBar(
    navController: NavController,
    items: List<MainScreen>
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry.value?.destination?.route

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEachIndexed { index, screen ->
                if (index == 2) { // Middle Item (Dzikir)
                    Box(
                        modifier = Modifier
                            .offset(y = (-20).dp)
                            .size(60.dp)
                            .shadow(8.dp, CircleShape)
                            .clip(CircleShape)
                            .background(PrimaryGreen)
                            .clickable {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = screen.icon,
                                contentDescription = screen.title,
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                text = screen.title,
                                color = Color.White,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                } else {
                    val isSelected = currentRoute == screen.route
                    Column(
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = screen.title,
                            tint = if (isSelected) PrimaryGreen else Color.Gray,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = screen.title,
                            color = if (isSelected) PrimaryGreen else Color.Gray,
                            fontSize = 10.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }
        }
    }
}
