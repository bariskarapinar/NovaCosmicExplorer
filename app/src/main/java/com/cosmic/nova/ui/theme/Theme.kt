package com.cosmic.nova.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = NebulaPurple,
    secondary = StarlightBlue,
    tertiary = SolarGold,
    background = DeepSpace,
    surface = SurfaceGray,
    onPrimary = Color.White,
    onSecondary = DeepSpace,
    onTertiary = DeepSpace,
    onBackground = Color.White,
    onSurface = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = NebulaPurple,
    secondary = StarlightBlue,
    tertiary = SolarGold,
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = DeepSpace,
    onTertiary = DeepSpace,
    onBackground = DeepSpace,
    onSurface = DeepSpace
)

@Composable
fun NovaTheme(
    darkTheme: Boolean = true, // Default to dark for cosmic vibe
    dynamicColor: Boolean = false, // Disable dynamic for branded look
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}