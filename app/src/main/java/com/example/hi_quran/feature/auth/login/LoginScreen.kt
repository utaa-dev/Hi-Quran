package com.example.hi_quran.feature.auth.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA)) // Light background
    ) {
        // Optional: Add the star pattern background here if you have the resource
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            
            // Header Section
            Surface(
                modifier = Modifier.size(80.dp),
                shape = CircleShape,
                color = Color(0xFF1B5E20)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "Hi-Qur'an",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1B5E20)
            )
            
            Text(
                text = "Nyalakan Cahaya di Hatimu",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Login Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Selamat Datang Kembali",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    // Email Field
                    Text(text = "Email", fontSize = 12.sp, color = Color.Gray, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = { Text("Masukkan email anda", fontSize = 14.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        leadingIcon = { Icon(Icons.Outlined.Email, null, modifier = Modifier.size(20.dp)) },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFF5F5F5),
                            unfocusedContainerColor = Color(0xFFF5F5F5),
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color(0xFF1B5E20)
                        ),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Password Field
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Kata Sandi", fontSize = 12.sp, color = Color.Gray, fontWeight = FontWeight.Medium)
                        Text(
                            text = "Lupa Password?",
                            fontSize = 12.sp,
                            color = Color(0xFF1B5E20),
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable { /* Handle lupa password */ }
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = { Text("Masukkan kata sandi", fontSize = 14.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        leadingIcon = { Icon(Icons.Outlined.Lock, null, modifier = Modifier.size(20.dp)) },
                        trailingIcon = {
                            val image = if (passwordVisible) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(imageVector = image, contentDescription = null, modifier = Modifier.size(20.dp))
                            }
                        },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFF5F5F5),
                            unfocusedContainerColor = Color(0xFFF5F5F5),
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color(0xFF1B5E20)
                        ),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = onLoginClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B5E20))
                    ) {
                        Text(text = "Masuk", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    
                    // Divider
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        HorizontalDivider(modifier = Modifier.weight(1f), color = Color.LightGray)
                        Text(
                            text = "Atau masuk dengan",
                            modifier = Modifier.padding(horizontal = 16.dp),
                            fontSize = 10.sp,
                            color = Color.Gray
                        )
                        HorizontalDivider(modifier = Modifier.weight(1f), color = Color.LightGray)
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Social Login
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        OutlinedButton(
                            onClick = { },
                            modifier = Modifier.weight(1f).height(45.dp),
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(1.dp, Color.LightGray)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                // Add Google Icon here
                                Text("Google", color = Color.Gray, fontSize = 14.sp)
                            }
                        }
                        OutlinedButton(
                            onClick = { },
                            modifier = Modifier.weight(1f).height(45.dp),
                            shape = RoundedCornerShape(12.dp),
                            border = BorderStroke(1.dp, Color.LightGray)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                // Add Facebook Icon here
                                Text("Facebook", color = Color.Gray, fontSize = 14.sp)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Bottom Navigation Links
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Belum punya akun? ", color = Color.Gray, fontSize = 14.sp)
                Text(
                    text = "Daftar Sekarang",
                    color = Color(0xFF1B5E20),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { onRegisterClick() }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Language Selection
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Indonesia", color = Color(0xFF1B5E20), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Text(text = "  •  ", color = Color.Gray)
                Text(text = "English", color = Color.Gray, fontSize = 12.sp)
                Text(text = "  •  ", color = Color.Gray)
                Text(text = "العربية", color = Color.Gray, fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(48.dp))

            // Footer
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "© 2024 Hi-Qur'an. All rights reserved.",
                    fontSize = 10.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
                Row(
                    modifier = Modifier.padding(top = 4.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Kebijakan Privasi", fontSize = 10.sp, color = Color.Gray)
                    Text(text = "  •  ", color = Color.Gray)
                    Text(text = "Syarat & Ketentuan", fontSize = 10.sp, color = Color.Gray)
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
