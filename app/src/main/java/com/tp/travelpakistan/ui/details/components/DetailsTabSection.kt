package com.tp.travelpakistan.ui.details.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tp.travelpakistan.R

@Composable
fun DetailsTabSection(
    title: String,
    icon: Int,
    content: (@Composable () -> Unit)
) {
    val showContent = remember {
        mutableStateOf(true)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp).clickable {
                showContent.value = !showContent.value
            }
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.wrapContentWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        modifier = Modifier.size(22.dp),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                    )
                    Text(
                        text = title, style = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.ic_drop_down),
                    contentDescription = null,
                    modifier = Modifier
                        .size(22.dp)
                        .clickable {
                            showContent.value = !showContent.value
                        },
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                )
            }
            AnimatedVisibility(visible = showContent.value) {
                content()
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.onBackground)
                .height(0.6.dp)
                .align(Alignment.BottomCenter)
        )
    }
}


@Composable
fun PrivacyPolicySection(
    cancellations: List<String>,
    refunds: List<String>,
    chilsPolicies: List<String>

) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            buildAnnotatedString {
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary, fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )){
                    append("Cancellations")
                }
            })

        LazyColumn(modifier = Modifier.padding(start = 16.dp)){
            items(cancellations){inclusion->
                Text(text = inclusion, fontSize = 16.sp, color = MaterialTheme.colorScheme.onBackground)
            }
        }

        Text(
            buildAnnotatedString {
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary, fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )){
                    append("Refunds")
                }
            })

        LazyColumn(modifier = Modifier.padding(start = 16.dp)){
            items(refunds){exclusion->
                Text(text = exclusion, fontSize = 16.sp, color = MaterialTheme.colorScheme.onBackground)
            }
        }

        Text(
            buildAnnotatedString {
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary, fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )){
                    append("Child Policy")
                }
            })

        LazyColumn(modifier = Modifier.padding(start = 16.dp)){
            items(cancellations){exclusion->
                Text(text = exclusion, fontSize = 16.sp, color = MaterialTheme.colorScheme.onBackground)
            }
        }

    }
}

@Composable
fun TravelTipsSections(
    tips: List<String>,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            buildAnnotatedString {
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary, fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )){
                    append("Recommended Gear")
                }
            })

        LazyColumn(modifier = Modifier.padding(start = 16.dp)){
            items(tips){tip->
                Text(text = tip, fontSize = 16.sp, color = MaterialTheme.colorScheme.onBackground)
            }
        }

    }
}

@Composable
fun InclusionExclusionSection(
    inclusions: List<String>,
    exclusions: List<String>
    ) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            buildAnnotatedString {
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary, fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )){
                    append("Inclusive Services")
                }
            })

        LazyColumn(modifier = Modifier.padding(start = 16.dp)){
            items(inclusions){inclusion->
                Text(text = inclusion, fontSize = 16.sp, color = MaterialTheme.colorScheme.onBackground)
            }
        }

        Text(
            buildAnnotatedString {
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary, fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )){
                    append("Exclusive Services")
                }
            })

        LazyColumn(modifier = Modifier.padding(start = 16.dp)){
            items(exclusions){exclusion->
                Text(text = exclusion, fontSize = 16.sp, color = MaterialTheme.colorScheme.onBackground)
            }
        }

    }
}


@Composable
fun TimingSection(
    duration: String,
    departure: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            buildAnnotatedString {
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary, fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )){
                    append("Departure: ")
                }

                withStyle(SpanStyle(color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp,
                    fontWeight = FontWeight.Normal
                )){
                    append(departure)
                }
            })

        Text(
            buildAnnotatedString {
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary, fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )){
                    append("Duration: ")
                }

                withStyle(SpanStyle(color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp,
                    fontWeight = FontWeight.Normal
                )){
                    append(duration)
                }
            })

    }
}

@Composable
fun LocationSection(
    pickup: String,
    routes: List<String>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
        buildAnnotatedString {
            withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary, fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
                )){
                append("Pick-up: ")
            }

            withStyle(SpanStyle(color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp,
                fontWeight = FontWeight.Normal
            )){
                append(pickup)
            }
        })

        Text(
            buildAnnotatedString {
                withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary, fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )){
                    append("Route: ")
                }

                withStyle(SpanStyle(color = MaterialTheme.colorScheme.onBackground, fontSize = 18.sp,
                    fontWeight = FontWeight.Normal
                )){
                    append(routes.joinToString(separator = " --> "))
                }
            })

    }
}

@Preview
@Composable
private fun PreviewLocationSection(){
    MaterialTheme(){
        LocationSection(pickup = "Islamabad", routes = listOf("Naran","Kaghan","Kashmir","Nathia Galli"))
    }
}

@Preview
@Composable
private fun DetailsTabSectionPreview() {
    MaterialTheme {
        DetailsTabSection(
            title = "Title",
            icon = R.drawable.ic_incl_excl,
        ) {
            InclusionExclusionSection(inclusions = listOf("Transport","Food","Drinks"), exclusions = listOf(
                "Jeep Rides","Personal Expenses","Shopping"))
        }
    }
}