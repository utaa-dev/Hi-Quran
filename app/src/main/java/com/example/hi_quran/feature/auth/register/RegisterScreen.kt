package com.example.hi_quran.feature.auth.register

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var agreeToTerms by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA)) // Light background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // Header Icon
            Surface(
                modifier = Modifier.size(70.dp),
                shape = RoundedCornerShape(16.dp),
                color = Color(0xFF1B5E20)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.MenuBook,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(35.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Buat Akun Baru",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1B5E20)
            )

            Text(
                text = "Mulai perjalanan spiritual Anda bersama Hi-Qur'an.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 32.dp, end = 32.dp, top = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Register Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    // Name Field
                    Text(text = "Nama Lengkap", fontSize = 12.sp, color = Color.Gray, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = fullName,
                        onValueChange = { fullName = it },
                        placeholder = { Text("Fulan bin Fulan", fontSize = 14.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        leadingIcon = { Icon(Icons.Outlined.Person, null, modifier = Modifier.size(20.dp)) },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFF5F5F5),
                            unfocusedContainerColor = Color(0xFFF5F5F5),
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color(0xFF1B5E20)
                        ),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Email Field
                    Text(text = "Email", fontSize = 12.sp, color = Color.Gray, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = { Text("example@gmail.com", fontSize = 14.sp) },
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
                    Text(text = "Kata Sandi", fontSize = 12.sp, color = Color.Gray, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = { Text("Min. 8 karakter", fontSize = 14.sp) },
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

                    Spacer(modifier = Modifier.height(16.dp))

                    // Confirm Password Field
                    Text(text = "Konfirmasi Kata Sandi", fontSize = 12.sp, color = Color.Gray, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        placeholder = { Text("Ulangi kata sandi", fontSize = 14.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        leadingIcon = { Icon(Icons.Outlined.Shield, null, modifier = Modifier.size(20.dp)) },
                        visualTransformation = PasswordVisualTransformation(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFF5F5F5),
                            unfocusedContainerColor = Color(0xFFF5F5F5),
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = Color(0xFF1B5E20)
                        ),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Terms Checkbox
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = agreeToTerms,
                            onCheckedChange = { agreeToTerms = it },
                            colors = CheckboxDefaults.colors(checkedColor = Color(0xFF1B5E20))
                        )
                        Text(
                            text = "Saya setuju dengan Ketentuan Layanan dan Kebijakan Privasi.",
                            fontSize = 11.sp,
                            color = Color.Gray,
                            lineHeight = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = onRegisterClick,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B5E20))
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "Daftar", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(Icons.AutoMirrored.Filled.ArrowForward, null, modifier = Modifier.size(20.dp))
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))
                    
                    // Divider
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        HorizontalDivider(modifier = Modifier.weight(1f), color = Color.LightGray)
                        Text(
                            text = "Atau daftar dengan",
                            modifier = Modifier.padding(horizontal = 16.dp),
                            fontSize = 10.sp,
                            color = Color.Gray
                        )
                        HorizontalDivider(modifier = Modifier.weight(1f), color = Color.LightGray)
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Social Login (Google only like image)
                    OutlinedButton(
                        onClick = { },
                        modifier = Modifier.fillMaxWidth().height(45.dp),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, Color.LightGray)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            // Add Google Icon here
                            Text("Google", color = Color.Gray, fontSize = 14.sp)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Bottom Navigation Link
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Sudah punya akun? ", color = Color.Gray, fontSize = 14.sp)
                Text(
                    text = "Masuk",
                    color = Color(0xFF1B5E20),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { onLoginClick() }
                )
            }
            
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}
