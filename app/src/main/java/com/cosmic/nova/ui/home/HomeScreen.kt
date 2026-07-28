package com.cosmic.nova.ui.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.cosmic.nova.data.model.Planet
import com.cosmic.nova.ui.theme.DeepSpace
import com.cosmic.nova.ui.theme.NebulaPurple
import com.cosmic.nova.ui.theme.StarlightBlue

@Composable
fun HomeScreen(
    onPlanetClick: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(DeepSpace, NebulaPurple.copy(alpha = 0.3f), DeepSpace)
                )
            )
    ) {
        when (val state = uiState) {
            is HomeUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = StarlightBlue
                )
            }
            is HomeUiState.Success -> {
                PlanetPager(state.planets, onPlanetClick)
            }
            is HomeUiState.Error -> {
                Text(
                    text = state.message,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun PlanetPager(
    planets: List<Planet>,
    onPlanetClick: (String) -> Unit
) {
    val pagerState = rememberPagerState(pageCount = { planets.size })

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(64.dp))
        
        Text(
            text = "NOVA",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 8.sp,
                color = Color.White
            )
        )
        
        Text(
            text = "EXPLORE THE COSMOS",
            style = MaterialTheme.typography.bodySmall.copy(
                letterSpacing = 4.sp,
                color = StarlightBlue
            )
        )

        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 48.dp),
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) { page ->
            val planet = planets[page]
            
            // Parallax effect calculation
            val pageOffset = (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
            
            PlanetCard(
                planet = planet,
                pageOffset = pageOffset,
                onClick = { onPlanetClick(planet.id) }
            )
        }
        
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun PlanetCard(
    planet: Planet,
    pageOffset: Float,
    onClick: () -> Unit
) {
    val scale by animateFloatAsState(targetValue = 1f - (kotlin.math.abs(pageOffset) * 0.2f), label = "scale")
    
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                alpha = 1f - (kotlin.math.abs(pageOffset) * 0.5f)
            }
            .clip(RoundedCornerShape(32.dp))
            .background(Color(0xFF1E1E2C).copy(alpha = 0.8f))
            .clickable { onClick() }
    ) {
        AsyncImage(
            model = planet.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    translationX = pageOffset * 200f // Subtle parallax
                }
        )
        
        // Gradient Overlay
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f)),
                        startY = 300f
                    )
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(24.dp)
        ) {
            Text(
                text = planet.name.uppercase(),
                style = MaterialTheme.typography.displaySmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
            Text(
                text = planet.type,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = StarlightBlue
                )
            )
        }
    }
}
