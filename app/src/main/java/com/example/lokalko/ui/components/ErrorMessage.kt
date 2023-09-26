package com.example.lokalko.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lokalko.ui.theme.Poppins

@Composable
fun ErrorMessage(errorMessage: String) {
    Text(
        text = errorMessage,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp),
        style = TextStyle(
            fontSize = 16.sp,
            fontFamily = Poppins,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        ),
        color = Color(65, 65, 65, 255)
    )
}