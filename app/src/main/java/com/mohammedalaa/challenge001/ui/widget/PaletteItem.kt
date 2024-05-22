package com.mohammedalaa.challenge001.ui.widget

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mohammedalaa.challenge001.model.PaletteResult
import com.mohammedalaa.challenge001.ui.theme.Challenge001Theme

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PaletteItem(
    itemContent: PaletteResult.Content.ItemContent,
    modifier: Modifier = Modifier,
    paletteType: String?,
    isSelected: Boolean,
    onSelected: () -> Unit
) {


    val offsetTarget = if (isSelected) {
        IntOffset(4, 4)
    } else {
        IntOffset.Zero
    }
    val offset = animateIntOffsetAsState(
        animationSpec = tween(durationMillis = 100, easing = FastOutSlowInEasing),
        targetValue = offsetTarget, label = "offset"
    )





    when (paletteType) {
        "solid" -> {
            Box(
                modifier = modifier
                    .height(48.dp)
                    .width(48.dp)
                    .background(
                        color = Color(
                            android.graphics.Color.parseColor(
                                itemContent.color ?: "#FF5722"
                            )
                        ),
                        shape = RoundedCornerShape(50)
                    )
                    .clickable(
                        onClick = onSelected
                    )
            ) {
                if (isSelected) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                color = Color(
                                    android.graphics.Color.parseColor(
                                        itemContent.color ?: "#FF5722"
                                    )
                                ),
                                shape = RoundedCornerShape(50)

                            )
                            .border(
                                4.dp,
                                Color.Red,
                                shape = RoundedCornerShape(50)
                            )
                            .padding(4.dp)
                            .background(
                                color = Color.Transparent,
                                shape = RoundedCornerShape(50)
                            )
                            .border(
                                4.dp,
                                Color.White,
                                shape = RoundedCornerShape(50)
                            )
                            .layout { measurable, constraints ->
                                val offsetValue = if (isLookingAhead) offsetTarget else offset.value
                                val placeable = measurable.measure(constraints)
                                layout(
                                    placeable.width + offsetValue.x,
                                    placeable.height + offsetValue.y
                                ) {
                                    placeable.placeRelative(offsetValue)
                                }

                            }
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {}

                    )

                }

            }
        }

        "gradient" -> {

            val color1 = Color(
                android.graphics.Color.parseColor(
                    itemContent.colors?.get(0) ?: "#FF5722"
                )
            )
            val color2 = Color(
                android.graphics.Color.parseColor(
                    itemContent.colors?.get(1) ?: "#FF5722"
                )
            )
            val colors = listOf(color1, color2)
            Box(
                modifier = modifier
                    .height(48.dp)
                    .width(48.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = colors,
                            start = Offset(0.0f, 0.0f), // Starting position (x, y)
                            end = Offset(100f, 100f) // Ending position (x, y)
                        ),
                        shape = RoundedCornerShape(50)
                    )
                    .clickable(
                        onClick = onSelected
                    )
            ) {
                if (isSelected) {
                    Box(
                        modifier = Modifier
                            .height(48.dp)
                            .width(48.dp)
                            .background(
                                brush = Brush.linearGradient(
                                    colors = colors,
                                    start = Offset(0.0f, 0.0f), // Starting position (x, y)
                                    end = Offset(100f, 100f) // Ending position (x, y)
                                ),
                                shape = RoundedCornerShape(50)

                            )
                            .border(
                                4.dp,
                                Color.Red,
                                shape = RoundedCornerShape(50)
                            )
                            .padding(4.dp)
                            .background(
                                color = Color.Transparent,
                                shape = RoundedCornerShape(50)
                            )
                            .border(
                                4.dp,
                                Color.White,
                                shape = RoundedCornerShape(50)
                            )
                            .layout { measurable, constraints ->
                                val offsetValue = if (isLookingAhead) offsetTarget else offset.value
                                val placeable = measurable.measure(constraints)
                                layout(
                                    placeable.width + offsetValue.x,
                                    placeable.height + offsetValue.y
                                ) {
                                    placeable.placeRelative(offsetValue)
                                }

                            }
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {}

                    )

                }
            }
        }

        "image" -> {
            Box(
                modifier = modifier
                    .height(48.dp)
                    .width(48.dp)
                    .background(
                        shape = RoundedCornerShape(50),
                        color = Color.White
                    )
                    .clickable(
                        onClick = onSelected
                    )
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(48.dp)
                        .fillMaxSize(),
                    model = itemContent.image,
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null,
                )

                if (isSelected) {
                    Box(
                        modifier = Modifier
                            .height(50.dp)
                            .width(50.dp)
                            .background(
                                color = Color.Red,
                                shape = RoundedCornerShape(20)
                            )
                            .border(
                                4.dp,
                                Color.Red,
                                shape = RoundedCornerShape(20)
                            )

                    )
                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PaletteItemPreview() {
    Challenge001Theme {
        LazyHorizontalGrid(
            rows = GridCells.Fixed(2),
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(10) {
                PaletteItem(
                    itemContent = PaletteResult.Content.ItemContent(
                        id = "1",
                        isNew = true,
                        isPremium = true,
                        positions = listOf(0.0, 1.2),
                        angle = 0.0,
                        color = "#FF6722",
                        colors = listOf("#FF5722", "#FF5722"),
                        image = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"
                    ),
                    paletteType = "image",
                    isSelected = false,
                    onSelected = {}
                )
            }
        }
    }
}