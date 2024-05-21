package com.mohammedalaa.challenge001

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mohammedalaa.challenge001.model.PaletteResult
import com.mohammedalaa.challenge001.ui.PalettesViewModel
import com.mohammedalaa.challenge001.ui.PalettesViewState
import com.mohammedalaa.challenge001.ui.widget.PaletteItem
import com.mohammedalaa.challenge001.ui.widget.PaletteItemType
import com.mohammedalaa.challenge001.ui.getKeyForValue
import com.mohammedalaa.challenge001.ui.theme.Challenge001Theme
import com.mohammedalaa.challenge001.ui.theme.Typography
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val palettesViewModel: PalettesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val scope = rememberCoroutineScope()
            Challenge001Theme {
                ScreenContent(scope, palettesViewModel)
            }
        }
    }
}


@Composable
private fun ScreenContent(
    scope: CoroutineScope,
    palettesViewModel: PalettesViewModel
) {
    val viewState = palettesViewModel.viewState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        // Box with text centered
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .fillMaxHeight(0.5f),
            contentAlignment = Alignment.Center
        ) {

            val color = if (viewState.value.itemContent?.color != null) {
                Color(
                    android.graphics.Color.parseColor(
                        viewState.value.itemContent!!.color
                    )
                )
            } else Black

            val gradientColors =
                if (viewState.value.itemContent?.colors != null) listOf(
                    Color(android.graphics.Color.parseColor(viewState.value.itemContent?.colors!![0])),
                    Color(android.graphics.Color.parseColor(viewState.value.itemContent?.colors!![1]))
                ) else listOf(color, color)

            val brush =
                Brush.horizontalGradient(gradientColors) // Create a horizontal gradient brush

            Text(
                style = TextStyle(
                    brush = brush
                ),
                text = "Palettes",
                fontSize = 56.sp,
                textAlign = TextAlign.Center,
            )
        }

        BottomSheetContent(scope, viewState, palettesViewModel)


    }
}

@Composable
private fun BottomSheetContent(
    scope: CoroutineScope,
    viewState: State<PalettesViewState>,
    palettesViewModel: PalettesViewModel
) {
    var isVisible by remember { mutableStateOf(true) }

    val offset by animateDpAsState(
        targetValue = if (isVisible) 0.dp else 300.dp,
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing),
        label = "animateBottomSheet"
    )
    Button(
        onClick = { isVisible = !isVisible }, modifier = Modifier
            .wrapContentSize()
            .padding(16.dp)
    ) {
        Text(text = if (isVisible) "Hide Card" else "Show Card")
    }
    Card(
        colors = CardDefaults.cardColors(
            contentColor = White,
            containerColor = White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .wrapContentHeight()
            .offset(y = offset)
            .wrapContentHeight()
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Fill Colors",
                    style = Typography.bodyMedium,
                    textAlign = TextAlign.Start,
                    color = Black,
                    modifier = Modifier.padding(start = 16.dp)
                )
                IconButton(onClick = {
                    scope.launch { isVisible = !isVisible }
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp),
                        tint = Gray
                    )
                }

            }
            CombinedTwoLazyLists(
                palettes = viewState.value.pallets,
                modifier = Modifier,
                palettesViewModel = palettesViewModel
            )

        }
    }
}

@SuppressLint("MutableCollectionMutableState")
@Composable
fun CombinedTwoLazyLists(
    palettes: List<PaletteResult.Content>?,
    modifier: Modifier,
    palettesViewModel: PalettesViewModel
) {
    var selectedPaletteIndex: Int by remember { mutableIntStateOf(0) }
    var selectedColorIndex by remember { mutableStateOf(mutableMapOf(0 to 0)) }
    val listScrollState = rememberLazyListState()
    val gridScrollState = rememberLazyGridState()
    var effect1Running by remember { mutableStateOf(false) }
    var effect2Running by remember { mutableStateOf(false) }

    val map = mutableMapOf<Int, Int>()
    var counter = 0
    for (i in 0 until palettes?.size!!) {
        for (j in 0 until palettes[i].itemContent?.size!!) {
            map[counter] = i
            counter++
        }
    }

    Column(
        horizontalAlignment = Alignment.Start, modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp)
    )
    {
        // Compose two LazyColumns with different content
        palettes.size.let {
            LazyRow(
                state = listScrollState,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(it) { index ->
                    val isSelected = index == selectedPaletteIndex
                    // Your item content for the first list
                    PaletteItemType(
                        content = palettes.getOrNull(index),
                        isSelected = isSelected,
                        onSelected = {
                            selectedPaletteIndex = index

                        }
                    )
                }
            }
        }


        LazyRow {

            item {
                LazyHorizontalGrid(
                    state = gridScrollState,
                    rows = GridCells.Fixed(2),
                    modifier = Modifier
                        .height(100.dp)
                        .fillParentMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {


                    //
                    items(3) { index ->
                        if (index % 2 == 1) {
                            GridItem(
                                type = "solid",
                                itemContent = palettes[0].itemContent?.get(index)!!,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                                    .offset(x = 24.dp),
                                isSelected = false,
                                onSelected = {

                                }
                            )
                        } else {
                            GridItem(
                                type = "solid",
                                itemContent = palettes[0].itemContent?.get(index)!!,
                                isSelected = false,
                                onSelected = {

                                }
                            )
                        }

                    }

                    item(span = { GridItemSpan(2) }) {
                        Divider(
                            color = Black, thickness = 4.dp, modifier = Modifier
                                .fillMaxHeight()
                                .width(2.dp)
                        )
                    }

                    palettes.forEachIndexed { index1, palette ->
                        palette.itemContent?.let { itemContents ->
                            items(itemContents.size) { index2 ->

                                val isSelected =
                                    (selectedColorIndex.containsKey(index1) && selectedColorIndex[index1] == index2)
                                if(isSelected) palettesViewModel.selectedColor(itemContents[index2])
                                if (index2 % 2 == 1) {
                                    GridItem(
                                        type = palette.type,
                                        itemContent = itemContents[index2],
                                        modifier = Modifier.offset(x = 24.dp),
                                        isSelected = isSelected,
                                        onSelected = {
                                            selectedColorIndex = mutableMapOf(Pair(index1, index2))
                                            palettesViewModel.selectedColor(itemContents[index2])
                                        }
                                    )
                                } else {
                                    GridItem(
                                        type = palette.type,
                                        itemContent = itemContents[index2],
                                        isSelected = isSelected,
                                        onSelected = {
                                            selectedColorIndex = mutableMapOf(Pair(index1, index2))
                                            palettesViewModel.selectedColor(itemContents[index2])
                                        })
                                }

                            }


                        }
                        if (palettes.last() != palette) {
                            item(span = { GridItemSpan(2) }) {
                                Divider(
                                    color = Black, thickness = 4.dp, modifier = Modifier
                                        .fillMaxHeight()
                                        .padding(start = 24.dp)
                                        .width(2.dp)
                                )
                            }
                        }


                    }


                }
            }
        }

        // Sync the scroll positions
        LaunchedEffect(key1 = gridScrollState.firstVisibleItemIndex) {

            if (!effect2Running) {
                effect1Running = true

                if (!listScrollState.isScrollInProgress) {
                    map[gridScrollState.firstVisibleItemIndex]?.let {
                        listScrollState.animateScrollToItem(
                            it,
                            0
                        )
                        if (selectedPaletteIndex != it) {
                            selectedPaletteIndex = it
                        }
                    }
                }
                effect1Running = false
            }
        }

        LaunchedEffect(key1 = selectedPaletteIndex) {
            if (!effect1Running) {
                effect2Running = true
                if (!gridScrollState.isScrollInProgress) {

                    val key = getKeyForValue(map, selectedPaletteIndex)
                    key?.let {
                        gridScrollState.animateScrollToItem(
                            it,
                            0
                        )
                    }
                }
                effect2Running = false
            }
        }
    }
}

@Composable
fun GridItem(
    type: String?,
    itemContent: PaletteResult.Content.ItemContent,
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    onSelected: () -> Unit
) {

    PaletteItem(
        modifier = modifier,
        paletteType = type,
        itemContent = itemContent,
        isSelected = isSelected,
        onSelected = onSelected
    )
}
