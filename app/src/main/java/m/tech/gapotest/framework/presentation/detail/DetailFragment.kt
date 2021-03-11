package m.tech.gapotest.framework.presentation.detail

import android.annotation.SuppressLint
import android.util.Base64
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import m.tech.gapotest.business.domain.DetailDocument
import m.tech.gapotest.databinding.FragmentDetailBinding
import m.tech.gapotest.framework.presentation.common.BaseFragment

@AndroidEntryPoint
class DetailFragment(
    private val glide: RequestManager
) : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val viewModel by viewModels<DetailViewModel>()

    private val args by navArgs<DetailFragmentArgs>()

    private val documentId by lazy { args.documentId }

    override fun init(view: View) {
        hideBottomNav()

//        viewModel.getDetail(isFetch = true, id = documentId) //in real case
        viewModel.getDetail(isFetch = true, id = TEMP_DOC_ID)
    }

    override fun subscribeObserver(view: View) {
        viewModel.detailDoc.observe(viewLifecycleOwner) {
            handleLoadingState(it, binding.progress)

            getData(it)?.let {
                Log.d(TAG, "detailDoc: $it")
                updateUI(it)
            }

            getErrorCode(it)?.let {
                Log.e(TAG, "detailDoc: $it")
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateUI(doc: DetailDocument) {
        //images
        val adapter = DetailHeaderAdapter(glide)
        val filteredList =
            doc.section.filter { (it.content.href != null && it.content.href != "")}
        Log.d(TAG, "updateUI: $filteredList")
        adapter.setItem(filteredList.map { it.content.href!! })
        binding.header.vp2.adapter = adapter
        binding.header.indicator.setViewPager(binding.header.vp2)

        //others
        binding.tvTitle.text = doc.baseDocument.title
        binding.tvDesc.text = doc.baseDocument.description
        binding.tvPublisher.text = "${doc.baseDocument.publisher.name} - ${doc.templateType}"
        binding.tvPublishDate.text = doc.baseDocument.publishedDate
            .replace("T", " ")
            .replace("Z", " ")
    }

    companion object {
        private const val TAG = "DetailFragment"
        private const val TEMP_DOC_ID = "18g3wjklzvj"
    }
}
