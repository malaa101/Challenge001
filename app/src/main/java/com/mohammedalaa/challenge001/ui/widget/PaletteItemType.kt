package com.mohammedalaa.challenge001.ui.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mohammedalaa.challenge001.model.PaletteResult
import com.mohammedalaa.challenge001.ui.theme.Challenge001Theme
import com.mohammedalaa.challenge001.ui.theme.GrayLight53

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaletteItemType(content: PaletteResult.Content?, isSelected: Boolean, onSelected: () -> Unit) {
    val backgroundColor = if (isSelected) GrayLight53 else Color.White

    Card(
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
            contentColor = backgroundColor
        ),
        border = BorderStroke(1.dp, backgroundColor),
        shape = RoundedCornerShape(16.dp),
        onClick = { onSelected() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {

        Row(
            modifier = Modifier.padding(start = 4.dp, end = 4.dp, top = 0.dp, bottom = 0.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier.size(36.dp).padding(4.dp).clip(CircleShape),
                model = content?.icon,
                contentScale = ContentScale.FillBounds,
                contentDescription = null,
            )
            Text(
                color = Color.Black,
                text = "${content?.name}"
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PaletteItemTypePreview() {
    Challenge001Theme {
        LazyRow(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxSize()
        ) {
            items(10) {
                PaletteItemType(
                    content = PaletteResult.Content(
                        isNew = true,
                        id = "2",
                        type = "Type",
                        itemContent = emptyList(),
                        name = "Name",
                        icon = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"
                    ),
                    isSelected = false,
                    onSelected = {}
                )


            }
        }
    }
}