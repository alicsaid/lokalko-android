package com.example.lokalko.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.toSize
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.example.lokalko.R
import com.example.lokalko.data.model.Category
import com.example.lokalko.data.model.City
import com.example.lokalko.data.model.Severity
import com.example.lokalko.ui.theme.Poppins

@Composable
fun DropDownMenuCities(label: String, cityOptions: List<City>, onCitySelected: (City) -> Unit) {

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }

    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        R.drawable.round_keyboard_arrow_up_24
    else
        R.drawable.round_keyboard_arrow_down_24

    Column() {
        OutlinedTextField(
            readOnly = true,
            textStyle = TextStyle.Default.copy(fontFamily = Poppins),
            value = selectedText,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color(249, 244, 244, 255),
                focusedBorderColor = Color(65, 65, 65, 255),
                unfocusedBorderColor = Color(65, 65, 65, 255),
                focusedLabelColor = Color(65, 65, 65, 255),
                unfocusedLabelColor = Color(65, 65, 65, 255),
                cursorColor = Color(65, 65, 65, 255),
                textColor = Color(65, 65, 65, 255),
            ),
            onValueChange = { selectedText = it },
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },
            label = { Text(label, fontFamily = Poppins, color = Color(65, 65, 65, 255)) },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "dropdown icon",
                    modifier = Modifier.clickable { expanded = !expanded }
                )
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
        ) {
            cityOptions.forEach { city ->
                DropdownMenuItem(onClick = {
                    onCitySelected(city)
                    expanded = false
                    selectedText = city.city
                }) {
                    Text(text = city.city, fontFamily = Poppins, color = Color(65, 65, 65, 255))
                }
            }
        }
    }
}

@Composable
fun DropDownMenuSeverities(label: String, severityOptions: List<Severity>, onSeveritySelected: (Severity) -> Unit) {

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }

    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        R.drawable.round_keyboard_arrow_up_24
    else
        R.drawable.round_keyboard_arrow_down_24

    Column() {
        OutlinedTextField(
            readOnly = true,
            textStyle = TextStyle.Default.copy(fontFamily = Poppins),
            value = selectedText,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color(249, 244, 244, 255),
                focusedBorderColor = Color(65, 65, 65, 255),
                unfocusedBorderColor = Color(65, 65, 65, 255),
                focusedLabelColor = Color(65, 65, 65, 255),
                unfocusedLabelColor = Color(65, 65, 65, 255),
                cursorColor = Color(65, 65, 65, 255),
                textColor = Color(65, 65, 65, 255),
            ),
            onValueChange = { selectedText = it },
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },
            label = { Text(label, fontFamily = Poppins, color = Color(65, 65, 65, 255)) },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "dropdown icon",
                    modifier = Modifier.clickable { expanded = !expanded }
                )
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
        ) {
            severityOptions.forEach { severity ->
                DropdownMenuItem(onClick = {
                    onSeveritySelected(severity)
                    expanded = false
                    selectedText = severity.severity
                }) {
                    Text(text = severity.severity, fontFamily = Poppins, color = Color(65, 65, 65, 255))
                }
            }
        }
    }
}

@Composable
fun DropDownMenuCategories(label: String, categoryOptions: List<Category>, onCategorySelected: (Category) -> Unit) {

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }

    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded)
        R.drawable.round_keyboard_arrow_up_24
    else
        R.drawable.round_keyboard_arrow_down_24

    Column() {
        OutlinedTextField(
            readOnly = true,
            textStyle = TextStyle.Default.copy(fontFamily = Poppins),
            value = selectedText,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Color(249, 244, 244, 255),
                focusedBorderColor = Color(65, 65, 65, 255),
                unfocusedBorderColor = Color(65, 65, 65, 255),
                focusedLabelColor = Color(65, 65, 65, 255),
                unfocusedLabelColor = Color(65, 65, 65, 255),
                cursorColor = Color(65, 65, 65, 255),
                textColor = Color(65, 65, 65, 255),
            ),
            onValueChange = { selectedText = it },
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textfieldSize = coordinates.size.toSize()
                },
            label = { Text(label, fontFamily = Poppins, color = Color(65, 65, 65, 255)) },
            trailingIcon = {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "dropdown icon",
                    modifier = Modifier.clickable { expanded = !expanded }
                )
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
        ) {
            categoryOptions.forEach { category ->
                DropdownMenuItem(onClick = {
                    onCategorySelected(category)
                    expanded = false
                    selectedText = category.category
                }) {
                    Text(text = category.category, fontFamily = Poppins, color = Color(65, 65, 65, 255))
                }
            }
        }
    }
}