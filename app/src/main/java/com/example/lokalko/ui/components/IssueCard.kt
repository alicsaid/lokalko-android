package com.example.lokalko.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lokalko.R
import com.example.lokalko.ui.theme.Poppins

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
            val icon: Painter = when (status) {
                IssueStatus.NOT_STARTED.status -> painterResource(id = R.drawable.not_started_lokalko)
                IssueStatus.STARTED.status -> painterResource(id = R.drawable.started_lokalko)
                IssueStatus.FINISHED.status -> painterResource(id = R.drawable.finished_lokalko)
                else -> painterResource(id = R.drawable.not_started_lokalko)
            }

            Image(
                painter = icon,
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterVertically)
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.ExtraBold,
                    ),
                    color = Color(65, 65, 65, 255)
                )
                Text(
                    text = "Reported on $dateReported",
                    fontWeight = FontWeight.Light,
                    color = Color(65, 65, 65, 255)
                )
            }
        }
    }
}

enum class IssueStatus(val status: String) {
    NOT_STARTED("Not started"),
    STARTED("Started"),
    FINISHED("Finished")
}
