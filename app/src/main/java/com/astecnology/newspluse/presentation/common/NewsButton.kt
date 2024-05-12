package com.astecnology.newspluse.presentation.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.astecnology.newspluse.ui.theme.NewsPluseTheme


/**
 * Composable function for displaying a styled button.
 * @param text The text to display on the button.
 * @param onClick The callback function to execute when the button is clicked.
 */
@Composable
fun NewsButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(size = 6.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
        )
    }
}

/**
 * Composable function for displaying a styled text button.
 * @param text The text to display on the button.
 * @param onClick The callback function to execute when the button is clicked.
 */
@Composable
fun NewsTextButton(text: String, onClick: () -> Unit) {
    TextButton(onClick = onClick) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color = Color.White
        )
    }
}

/**
 * Preview function for the NewsButton composable.
 */
@Composable
@Preview(showBackground = true)
fun NewsButtonPreview() {
    NewsPluseTheme {
        NewsButton(text = "Click me") {
        }
    }
}

/**
 * Preview function for the NewsTextButton composable.
 */
@Composable
@Preview(showBackground = true)
fun NewsTextButtonPreview() {
    NewsPluseTheme {
        NewsTextButton(text = "Back") {
        }
    }
}