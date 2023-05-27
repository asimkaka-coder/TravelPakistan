package com.tp.travelpakistan.ui.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tp.travelpakistan.ui.details.model.DetailTabItem
import com.tp.travelpakistan.ui.details.model.DetailTabType
import com.tp.travelpakistan.ui.details.model.tabItems


@Composable
fun DetailTabRow(
    items:List<DetailTabItem>,
    onTabClick: (DetailTabItem) -> Unit
){
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(items){tabItem->
            DetailTab(
                detailTabItem = tabItem
            ){
                onTabClick(it)
            }
        }
    }
}

@Composable
fun DetailTab(
    detailTabItem: DetailTabItem,
    onClick: (DetailTabItem) -> Unit
) {
    Card(
        modifier = Modifier.width(76.dp).height(38.dp).clickable {
            onClick(detailTabItem)
        },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (detailTabItem.isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.background
        ),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier,
                text = detailTabItem.label,
                style = TextStyle.Default.copy(
                    color = if (!detailTabItem.isSelected) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.onPrimary
                )
            )
        }

    }
}

@Preview
@Composable
fun DetailTabPreview() {
    DetailTab(
        DetailTabItem(
            "Label", true,DetailTabType.NONE
        )
    ){}
}

@Preview
@Composable
fun DetailTabRowPreview(){
    DetailTabRow(tabItems){

    }
}