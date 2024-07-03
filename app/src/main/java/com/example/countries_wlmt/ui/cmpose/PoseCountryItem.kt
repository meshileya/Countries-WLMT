package com.example.countries_wlmt.ui.cmpose

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.countries_wlmt.domain.model.CountryUIItem

@Composable
fun PoseCountryHeaderItem(header: CountryUIItem.HeaderUI) {

    Text(
        text = header.header,
        fontSize = 20.sp,
        modifier = Modifier
            .padding(8.dp)
//            .clickable { onClick(header) }
    )
}

@Composable
fun PoseCountryBody(body: CountryUIItem.CountryUI, onClick: (CountryUIItem) -> Unit) {
    Text(text = body.countryItem.name)
}