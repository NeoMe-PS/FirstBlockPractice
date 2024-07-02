package com.psbn.news.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.psbn.firstblockpractice.core.uiUtills.Navigator
import com.psbn.news.data.WORKER_ID_KEY
import com.psbn.news.di.DaggerNewsComponent
import com.psbn.news.di.NewsDepsProvider
import com.psbn.news.presentation.models.EventUI
import com.psbn.news.presentation.ui.view.newsfragment.NewsFragmentScreen
import com.psbn.news.presentation.viewmodel.NewsModelFactory
import com.psbn.news.presentation.viewmodel.NewsViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

fun Fragment.navigator(): Navigator = requireActivity() as Navigator
class NewsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: NewsModelFactory
    private lateinit var viewModel: NewsViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val componentDeps = (context.applicationContext as NewsDepsProvider).getNewsDeps()
        val component =
            DaggerNewsComponent.factory().create(requireActivity().application, componentDeps)
        component.inject(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireContext()).apply {
            viewModel =
                ViewModelProvider(this@NewsFragment, viewModelFactory)[NewsViewModel::class.java]
            setContent {
                NewsFragmentScreen(
                    viewModel = viewModel,
                    onFilterIconClickListener = { navigateToFilterFragment() },
                    onEventClickListener = { event ->
                        readEvent(event)
                        navigateToDetailFragment(event)
                    }
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        val item = arguments
        item?.let {
            val id = it.getInt(WORKER_ID_KEY)
            val event =
                viewModel.uiState.value.events.find { it.id == id } ?: return
            navigateToDetailFragment(event)
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.badges.collect { badges ->
                    navigator().setNewsBadges(badges)
                }
            }
        }
    }

    private fun readEvent(event: EventUI) {
        if (!event.isRead) {
            viewModel.readEvent(event.id)
        }
    }

    private fun navigateToDetailFragment(event: EventUI) {
        val direction =
            NewsFragmentDirections.actionNewsFragmentToNewsDetailFragment(
                event
            )
        findNavController().navigate(direction)
    }

    private fun navigateToFilterFragment() {
        val direction =
            NewsFragmentDirections.actionNewsFragmentToFilterFragment()
        findNavController().navigate(direction)
    }
}
