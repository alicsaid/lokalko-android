import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.lokalko.R
import com.example.lokalko.ui.theme.Poppins
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AppBar(
    userName: String,
    backgroundColor: Color = Color(249, 244, 244, 255),
    contentColor: Color = Color.Black,
    navController: NavController
) {
    val currentDate = getCurrentDate()

    TopAppBar(
        modifier = Modifier
            .background(color = backgroundColor)
            .height(100.dp),
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        contentColor = contentColor
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                painterResource(id = R.drawable.round_person_24),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .clickable {
                        navController.navigate("profile")
                    },
                tint = Color(146, 167, 165, 255)
            )
            Column {
                Text(
                    text = "Hi $userName!",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Bold,
                    ),
                    color = Color(146, 167, 165, 255)
                )
                Text(
                    text = currentDate,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Light,
                        color = Color(146, 167, 165, 255)
                    )
                )
            }
        }
    }
}

private fun getCurrentDate(): String {
    val sdf = SimpleDateFormat("EEEE, dd. MMMM", Locale.getDefault())
    return sdf.format(Date())
}

@Composable
fun OAppBar(
    title: String,
    backgroundColor: Color = Color(249, 244, 244, 255),
    contentColor: Color = Color.Black
) {
    TopAppBar(
        modifier = Modifier
            .background(color = backgroundColor)
            .height(100.dp),
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        contentColor = contentColor
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                    color = Color(146, 167, 165, 255)
                )
            }
        }
    }
}

@Composable
fun RDAppBar(
    title: String,
    backgroundColor: Color = Color(249, 244, 244, 255),
    contentColor: Color = Color.Black,
    navController: NavController,
) {
    TopAppBar(
        modifier = Modifier
            .background(color = backgroundColor)
            .height(100.dp),
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        contentColor = contentColor
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.round_arrow_back_24),
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 20.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                    color = Color(146, 167, 165, 255)
                )
            }
        }
    }
}