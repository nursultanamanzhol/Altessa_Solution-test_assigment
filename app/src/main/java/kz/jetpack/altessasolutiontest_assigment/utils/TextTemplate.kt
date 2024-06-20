package kz.jetpack.altessasolutiontest_assigment.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kz.jetpack.altessasolutiontest_assigment.ui.theme.White

@Composable
fun UserName(
    text: String,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        style = MaterialTheme.typography.h6,
        color = White,
        modifier = Modifier.padding(bottom = 4.dp),
        textAlign = textAlign
    )
}

@Composable
fun UserId(text: String) {
    Text(
        text = text,
        color = White,
        style = MaterialTheme.typography.body2
    )
}

@Composable
fun UserData(
    text: String,
    textAlign: TextAlign = TextAlign.Start
) {
    Text(
        text = text,
        color = White,
        style = MaterialTheme.typography.body1,
        textAlign = textAlign
    )
}

