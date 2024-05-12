package com.astecnology.newspluse.presentation.Inshort

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Inshort() {
    val data = arrayOf(
        " Integer id cursus neque. Etiam venenatis " +
                "diam lectus, non faucibus elit fermentum " +
                "at. Suspendisse vel ante eget nisl ultricies varius id " +
                "ac ex. Sed bibendum, leo sed porttitor vulputate, " +
                "magna lacus sodales mi, ac condimentum nulla mauris sed urna. ",
        "Item 2 Item 1Lorem ipsum dolor sit amet, consectetur adipiscing elit." +
                " Proin ultrices maximus tempor. Morbi arcu dolor, " +
                "consequat vel est eleifend, finibus semper metus. Nullam ultrices ipsum vel" +
                " leo malesuada dignissim.", "Curabitur nec tincidunt libero. Praesent in dignissim risus. Proin vel molestie neque. Cras ut enim ex. Phasellus hendrerit, velit a dictum condimentum, " +
                "erat dolor convallis erat, pulvinar dignissim ligula urna vel leo. Morbi porttitor commodo turpis non tristique.Item 3", "Item 4", "Item 5",
        "Item 1", "Item 2", "Item 3", "Item 4", "Item 5"
    ) // Array of strings

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val itemHeight = screenHeight / data.size

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp),
        reverseLayout = true
    ) {
        items(data) { item ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(itemHeight)
            ) {
                Card(
                    modifier = Modifier.fillMaxSize(),

                ) {
                    Text(
                        text = item,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}


