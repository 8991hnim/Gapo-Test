package m.tech.gapotest.framework.presentation.news_feed

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import m.tech.gapotest.databinding.FragmentContentBinding
import m.tech.gapotest.framework.presentation.common.BaseFragment
import m.tech.gapotest.framework.presentation.news_feed.adapter.NewsFeedAdapter
import m.tech.gapotest.util.Constants
import m.tech.gapotest.util.displayToast
import m.tech.gapotest.util.safeDelay

@AndroidEntryPoint
class ContentFragment : BaseFragment<FragmentContentBinding>(FragmentContentBinding::inflate) {

    private val shouldRefreshNewsFeed: Boolean by lazy {
        arguments?.getBoolean(Constants.SHOULD_REFRESH_NEWS_FEED) ?: true
    }

    private val newsFeedAdapter by lazy {
        NewsFeedAdapter(Glide.with(requireContext())) { position, item ->
            displayToast(item.toString())
        }
    }

    private val viewModel by viewModels<NewsFeedViewModel>()

    override fun init(view: View) {
        binding.sw.setOnRefreshListener {
            safeDelay(500) {
                binding.sw.isRefreshing = false
                loadData(true)
            }
        }

        loadData(shouldRefreshNewsFeed)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        with(binding.rvContent) {
            layoutManager = LinearLayoutManager(context)
            adapter = newsFeedAdapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1)) {
                        viewModel.nextPage()
                    }
                }
            })
        }
    }

    private fun loadData(shouldFetch: Boolean) {
        Log.d(TAG, "loadData: fetching $shouldFetch")
        viewModel.getNewsFeed(shouldFetch)
    }

    override fun subscribeObserver(view: View) {
        viewModel.newsFeed.observe(viewLifecycleOwner) {
            handleLoadingState(it, binding.progress)

            getData(it)?.let {
                Log.d(TAG, "getData: ${it.size}")
                for(i in it){
                    Log.d(TAG, "subscribeObserver: ${i.baseDocument.documentId}: ${i.baseDocument.publishedDate} - ${i.baseDocument.title}")
                }
                newsFeedAdapter.submitList(it)
            }

            getErrorCode(it)?.let {
                Log.e(TAG, "getErrorCode: $it")
            }
        }
    }

    companion object {
        private const val TAG = "ContentFragment"

        fun newInstance(shouldRefreshData: Boolean): ContentFragment {
            val contentFragment = ContentFragment()
            val bundle = Bundle()
            bundle.putBoolean(Constants.SHOULD_REFRESH_NEWS_FEED, shouldRefreshData)
            contentFragment.arguments = bundle
            return contentFragment
        }
    }

}