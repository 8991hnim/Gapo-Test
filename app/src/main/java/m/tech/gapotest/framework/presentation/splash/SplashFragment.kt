package m.tech.gapotest.framework.presentation.splash

import android.util.Log
import android.view.View
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import m.tech.gapotest.business.interactors.GetDetail
import m.tech.gapotest.business.interactors.GetNewsFeed
import m.tech.gapotest.databinding.FragmentSplashBinding
import m.tech.gapotest.framework.presentation.common.BaseFragment
import m.tech.gapotest.util.displayToast
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment(
    private val glide: RequestManager
) : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {

    @Inject
    lateinit var getNewsFeed: GetNewsFeed

    @Inject
    lateinit var getDetail: GetDetail

    override fun init(view: View) {
        CoroutineScope(IO).launch {
            getNewsFeed.getNewsFeed(true, 1, 10).collect {
                getData(it)?.let {
                    Log.d(TAG, "getNewsFeed: ${it.size}")

                    Log.d(TAG, "getNewsFeed: $it")
                }

                getErrorCode(it)?.let {
                    Log.e(TAG, "getNewsFeed error: $it")
                }
            }

            getDetail.getDetail(true, "18g3wjklzvj").collect {
                getData(it)?.let {
                    Log.d(TAG, "getDetail: $it")
                }

                getErrorCode(it)?.let {
                    Log.e(TAG, "getDetail error: $it")
                }
            }
        }
    }

    override fun subscribeObserver(view: View) {
        commonViewModel.dummies.observe(viewLifecycleOwner) {
            handleLoadingState(it, binding.progress) //handle loading with a view
//            handleLoadingStateWithDialog(it) //handle loading with dialog

            getData(it)?.let { list ->
                for (dummy in list) {
                    displayToast("It's working")
                    Log.d(TAG, "getData: ${dummy.name}")
                }
            }

            getErrorCode(it)?.let { code ->
                Log.e(TAG, "error: $code")
                displayToast("Error $code")
            }

        }
    }

    companion object {
        const val TAG = "AppDebug"
    }

}
