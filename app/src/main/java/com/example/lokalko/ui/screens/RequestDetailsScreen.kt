package com.example.lokalko.ui.screens

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.lokalko.R
import com.example.lokalko.data.model.PostRequest
import com.example.lokalko.ui.components.DropDownMenu
import com.example.lokalko.ui.components.ErrorMessage
import com.example.lokalko.ui.navigation.NavigationDestination
import com.example.lokalko.ui.viewModels.RequestDetailsScreenViewModel
import kotlinx.coroutines.launch

object RequestDetailsDestination : NavigationDestination {
    override val route = "request_details"
    override val titleRes = R.string.request_details
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestDetailsScreen(
    navigateBack: () -> Unit,
    viewModel: RequestDetailsScreenViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = RequestDetailsDestination.titleRes),
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    )
                },
                backgroundColor = Color(67, 160, 71, 255),
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        content = {
            RequestDetails(viewModel = viewModel)
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun RequestDetails(viewModel: RequestDetailsScreenViewModel) {

    val request by viewModel.request.collectAsState()
    val errorMessage = viewModel.errorMessage.value
    var isImageModalOpen by remember { mutableStateOf(false) }
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    val animals = listOf(
        R.drawable.lokalko,
        R.drawable.quizzy,
        R.drawable._01846,
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (errorMessage != "No Error") {
            ErrorMessage(errorMessage)
        } else {
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = "Title:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = request?.title.orEmpty(),
                style = TextStyle(
                    fontSize = 16.sp
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Description:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = request?.description.orEmpty(),
                style = TextStyle(
                    fontSize = 16.sp
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Date reported:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = request?.date.orEmpty(),
                style = TextStyle(
                    fontSize = 16.sp
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Time reported:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = request?.time.orEmpty(),
                style = TextStyle(
                    fontSize = 16.sp
                )
            )

            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Address:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = request?.address.orEmpty(),
                style = TextStyle(
                    fontSize = 16.sp
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "City:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = request?.city.orEmpty(),
                style = TextStyle(
                    fontSize = 16.sp
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Category:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = request?.category.orEmpty(),
                style = TextStyle(
                    fontSize = 16.sp
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Severity:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = request?.severity.orEmpty(),
                style = TextStyle(
                    fontSize = 16.sp
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Service:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
            if (request?.service?.isNotEmpty() == true) {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = request!!.service,
                    style = TextStyle(
                        fontSize = 16.sp
                    )
                )
            } else {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = "Not Assigned",
                    style = TextStyle(
                        fontSize = 16.sp
                    )
                )
            }
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Status:",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = request?.status.orEmpty(),
                style = TextStyle(fontSize = 16.sp)
            )
            if (request?.image?.isNotEmpty() == true) {
                Button(
                    modifier = Modifier.padding(top = 8.dp),
                    onClick = { isImageModalOpen = true },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = (Color(
                            139,
                            146,
                            190,
                            255
                        ))
                    ),
                    contentPadding = PaddingValues(12.dp)
                ) {
                    Text(
                        text = "SEE IMAGES",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            } else {
                Button(
                    modifier = Modifier.padding(top = 8.dp),
                    onClick = { /*isImageModalOpen = true*/ },
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = (Color(
                            139,
                            146,
                            190,
                            255
                        ))
                    ),
                    contentPadding = PaddingValues(12.dp)
                ) {
                    Text(
                        text = "NO IMAGES",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
        if (isImageModalOpen) {
            Dialog(
                onDismissRequest = { isImageModalOpen = false },
                properties = DialogProperties(dismissOnClickOutside = true)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .height(300.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HorizontalPager(
                        pageCount = animals.size,
                        state = pagerState,
                        key = { animals[it] },
                        pageSize = PageSize.Fill
                    ) { index ->
                        Image(
                            painter = painterResource(id = animals[index]),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }
}