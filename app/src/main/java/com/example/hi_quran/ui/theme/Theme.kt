package com.example.hi_quran.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF81C784), // Hijau lebih terang untuk dark mode
    secondary = Color(0xFF4CAF50),
    tertiary = Color(0xFF8BC34A),
    background = Color(0xFF121212),
    surface = Color(0xFF121212),
    onPrimary = Color(0xFF00390A),
    onBackground = Color(0xFFE1E3E1),
    onSurface = Color(0xFFE1E3E1),
    onSurfaceVariant = Color(0xFFC1C9BF),
    outline = Color(0xFF8C9389)
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryGreen,
    secondary = LightGreen,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onBackground = Color(0xFF1A1C19),
    onSurface = Color(0xFF1A1C19),
    onSurfaceVariant = Color(0xFF40493F),
    outline = Color(0xFF70796E)
)

@Composable
fun HiQuranTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Color.Transparent.toArgb()
            window.navigationBarColor = Color.Transparent.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
