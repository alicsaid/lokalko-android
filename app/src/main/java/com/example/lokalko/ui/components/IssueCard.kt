package com.example.lokalko.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun IssueCard(
    title: String,
    dateReported: String,
    status: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .wrapContentHeight()
            .clickable { onClick() },
        elevation = 4.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(getStatusColor(status))
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                // You can add icons or other indicators here
            }
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                Text(text = title, style = TextStyle(fontWeight = FontWeight.Bold))
                Text(text = "Reported on $dateReported")
            }
        }
    }
}

enum class IssueStatus(val status: String) {
    NOT_STARTED("Not started"),
    STARTED("Started"),
    FINISHED("Finished")
}

@Composable
fun getStatusColor(status: String): Color {
    return when (status) {
        IssueStatus.NOT_STARTED.status -> Color.Red
        IssueStatus.STARTED.status -> Color.Blue
        IssueStatus.FINISHED.status -> Color.Green
        else -> Color.Gray
    }
}
