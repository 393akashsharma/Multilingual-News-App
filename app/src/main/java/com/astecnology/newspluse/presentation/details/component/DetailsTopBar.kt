package com.astecnology.newspluse.presentation.details.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.astecnology.newspluse.R
import com.astecnology.newspluse.ui.theme.NewsPluseTheme


/**
 * Composable function for displaying the top app bar in the details screen.
 * @param onBrowsingClick Callback function for browsing action click.
 * @param onShareClick Callback function for share action click.
 * @param onBookmarkClick Callback function for bookmark action click.
 * @param onBackClick Callback function for back navigation click.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(
    onBrowsingClick: () -> Unit,
    onShareClick: () -> Unit,
    onBookmarkClick: () -> Unit,
    onBackClick: () -> Unit
) {
    TopAppBar(title = {},
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = colorResource(id = R.color.body),
            navigationIconContentColor = colorResource(id = R.color.body)
        ),
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = ""
                )
            }
        }, actions = {
            IconButton(onClick = onBookmarkClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bookmark),
                    contentDescription = ""
                )
            }

            IconButton(onClick = onShareClick) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = ""
                )
            }

            IconButton(onClick = onBrowsingClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_network),
                    contentDescription = ""
                )
            }
        })
}

/**
 * Preview function for the DetailsTopBar composable.
 */
@Composable
@Preview(showBackground = true)
fun DetailsTopBarPreview() {
    NewsPluseTheme {
        DetailsTopBar(
            onBrowsingClick = {},
            onShareClick = {},
            onBookmarkClick = {}) {

        }
    }
}