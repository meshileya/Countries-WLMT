package com.example.countries_wlmt.presentation.ui.cmpose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.countries_wlmt.domain.model.CountryUIItem

@Composable
fun PoseCountryHeaderItem(header: CountryUIItem.HeaderUI) {

    Text(
        color = Color.White,
        text = header.header,
        fontSize = 20.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}

@Composable
fun PoseCountryBody(body: CountryUIItem.CountryUI, onClick: (CountryUIItem) -> Unit) {
    Text(
        text = body.countryItem.name,
        color = Color.White,
        fontSize = 18.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick(body) }
    )

}