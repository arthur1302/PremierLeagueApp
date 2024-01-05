package com.example.premierleagueapp.ui.components.helpers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder

/**
 * Composable that renders an image
 *
 * @author Arthur Haus
 *
 * @param imageUrl [String]
 * @param painter [Painter]
 *
 * @return [Painter]
 */
@Composable
fun imageHandler(
    imageUrl: String,
    painter: Painter,
): Painter {
    var painterResult = painter
    if (imageUrl.endsWith(".png")) {
        // If an image is a .png file, a normal rememberImagePainter will be used
        painterResult = rememberImagePainter(
            data = imageUrl,
            builder = {
                crossfade(true)
            },
        )
    } else {
        // If an image is not a .png file, a ImageLoader.Builder will be used to render .svg files
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
