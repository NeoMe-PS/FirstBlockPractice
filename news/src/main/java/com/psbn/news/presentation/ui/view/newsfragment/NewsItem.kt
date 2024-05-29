package com.psbn.news.presentation.ui.view.newsfragment

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.psbn.firstblockpractice.core.R
import com.psbn.news.presentation.models.EventUI
import com.psbn.news.R as newsR

@Composable
fun NewsItem(event: EventUI, onEventClickListener: (EventUI) -> Unit) {
    Card(
        modifier = Modifier
            .padding(all = dimensionResource(id = R.dimen.spec_spacing_xs))
            .clickable {
                onEventClickListener(event)
            },
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.spec_spacing_xxs)),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .padding(all = dimensionResource(id = R.dimen.spec_spacing_xxs)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EventImageWithBackground(
                painter = painterResource(id = newsR.drawable.fade),
                backgroundDrawableResId = event.thumbnail,
                contentDescription = stringResource(
                    id = R.string.desc_news_img
                )
            )
            EventMainInformation(event)
        }
        EventDateFooter(event)
    }

}

@Composable
fun EventImageWithBackground(
    painter: Painter,
    @DrawableRes backgroundDrawableResId: Int,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.FillWidth,
) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(backgroundDrawableResId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = contentScale
        )
        Image(
            painter = painter,
            contentDescription = contentDescription,
            alignment = alignment,
            contentScale = contentScale,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun EventMainInformation(event: EventUI) {
    Column(
        modifier = Modifier
            .width(dimensionResource(id = R.dimen.news_short_desc_width))
            .padding(bottom = dimensionResource(id = R.dimen.spec_spacing_small)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = event.label,
            fontFamily = FontFamily(
                Font(R.font.officina_sans_extra_bold, FontWeight.ExtraBold)
            ),
            color = colorResource(id = R.color.blue_grey),
            fontSize = textSizeResource(resId = R.dimen.font_large),
            textAlign = TextAlign.Center
        )
        Image(
            painter = painterResource(id = newsR.drawable.decor),
            contentDescription = stringResource(
                id = R.string.desc_decor_img
            ),
            modifier = Modifier
                .padding(
                    top = dimensionResource(id = R.dimen.spec_spacing_xs),
                    bottom = dimensionResource(id = R.dimen.spacing_xxs)
                )
                .width(dimensionResource(id = R.dimen.news_decor_width))
        )
        Text(
            text = event.shortDesc,
            textAlign = TextAlign.Center,
            color = colorResource(id = R.color.black_70),
            fontSize = textSizeResource(resId = R.dimen.font_small)
        )
    }
}

@Composable
fun EventDateFooter(event: EventUI) {
    Row(
        modifier = Modifier
            .background(colorResource(id = R.color.turtle_green))
            .padding(
                top = dimensionResource(id = R.dimen._9dp),
                bottom = dimensionResource(id = R.dimen._7dp)
            )
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = newsR.drawable.icon_calendar_2),
            contentDescription = stringResource(
                id = R.string.desc_calendar_icon
            ),
            tint = Color.White
        )
        Text(text = event.date, color = Color.White)
    }
}

@Composable
fun textSizeResource(resId: Int): TextUnit {
    return with(LocalDensity.current) {
        dimensionResource(resId).toSp()
    }
}
