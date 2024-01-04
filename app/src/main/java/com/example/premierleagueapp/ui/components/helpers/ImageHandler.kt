package com.example.premierleagueapp.ui.components.helpers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder

@Composable
fun ImageHandler(
    imageUrl: String,
    painter: Painter,
): Painter {
    var painterResult = painter
    if (imageUrl.endsWith(".png")) {
        painterResult = rememberImagePainter(
            data = imageUrl,
            builder = {
                crossfade(true)
            },
        )
    } else {
        val imageLoader = ImageLoader.Builder(LocalContext.current)
            .componentRegistry {
                add(SvgDecoder(LocalContext.current))
            }
            .build()

        CompositionLocalProvider(LocalImageLoader provides imageLoader) {
            painterResult = rememberImagePainter(imageUrl)
        }
    }
    return painterResult
}
