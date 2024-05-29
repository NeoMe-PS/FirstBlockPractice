package com.psbn.news.presentation.ui.view.newsfragment

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.psbn.firstblockpractice.core.R
import com.psbn.news.presentation.models.EventUI
import com.psbn.news.presentation.viewmodel.NewsUiState
import com.psbn.news.presentation.viewmodel.NewsViewModel
import com.psbn.news.R as newsR


@Composable
fun NewsFragmentScreen(
    viewModel: NewsViewModel,
    onFilterIconClickListener: () -> Unit,
    onEventClickListener: (EventUI) -> Unit
) {
    val state = viewModel.uiState.collectAsState()
    Scaffold(topBar = {
        NewsTopBar(onFilterIconClickListener = onFilterIconClickListener)
    }) { scafPadding ->
        Column(
            modifier = Modifier
                .padding(scafPadding)
                .background(colorResource(id = R.color.light_grey_two))
                .fillMaxSize()
        ) {
            if (state.value.isLoading) {
                CircularProgressIndicator(strokeWidth = dimensionResource(id = R.dimen.progress_bar_size))
            } else {
                NewsList(state = state, onEventClickListener = onEventClickListener)
            }
        }
    }
}


@Composable
fun NewsList(state: State<NewsUiState>, onEventClickListener: (EventUI) -> Unit) {
    LazyColumn {
        items(state.value.events) { event ->
            NewsItem(
                event = event,
                onEventClickListener = onEventClickListener
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsTopBar(onFilterIconClickListener: () -> Unit) {
    CenterAlignedTopAppBar(
        modifier = Modifier.background(
            colorResource(id = R.color.turtle_green)
        ),
        title = {
            Text(
                text = stringResource(id = R.string.label_auth),
                fontFamily = FontFamily(
                    Font(R.font.officina_sans_extra_bold, FontWeight.ExtraBold)
                )
            )
        },
        navigationIcon = {


        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.turtle_green),
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
            actionIconContentColor = MaterialTheme.colorScheme.onSecondary
        ),
        actions = {
            Icon(
                painter = painterResource(id = newsR.drawable.filter),
                contentDescription = stringResource(id = R.string.desc_news_filter),
                modifier = Modifier
                    .clickable {
                        onFilterIconClickListener()
                    }
                    .padding(end = dimensionResource(R.dimen.spacing_large))
            )
        }
    )
}
