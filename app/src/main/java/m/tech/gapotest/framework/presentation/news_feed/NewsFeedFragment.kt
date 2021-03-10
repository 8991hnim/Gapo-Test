package m.tech.gapotest.framework.presentation.news_feed

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import m.tech.gapotest.R
import m.tech.gapotest.databinding.FragmentNewsFeedBinding
import m.tech.gapotest.framework.presentation.common.BaseFragment

@AndroidEntryPoint
class NewsFeedFragment : BaseFragment<FragmentNewsFeedBinding>(FragmentNewsFeedBinding::inflate) {

    private val fakeTabs =
        arrayOf("Theo dõi", "Cho bạn", "Bóng đá", "Công nghệ", "Đồ ăn", "Du lịch", "Phim ảnh")
    private val tabsLoaded = HashMap<String, Boolean>()

    override fun init(view: View) {
        initTabsWithViewPager(view)
    }

    override fun subscribeObserver(view: View) {

    }

    private fun initTabsWithViewPager(view: View) {
        val fontDefault = context?.let { ResourcesCompat.getFont(it, R.font.baloochettan2_regular) }
        val fontSelect = context?.let { ResourcesCompat.getFont(it, R.font.baloochettan2_bold) }

        val tabs: TabLayout = view.findViewById(R.id.tabs)
        val viewPager: ViewPager2 = view.findViewById(R.id.viewPager)
        viewPager.adapter = activity?.let { ContentPagerAdapter(it) }
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        TabLayoutMediator(tabs, viewPager) { tab: TabLayout.Tab, position: Int ->
            tab.text = fakeTabs[position]
        }.attach()

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val tabLayout =
                    (tabs.getChildAt(0) as ViewGroup).getChildAt(tab!!.position) as LinearLayout
                val tabTextView =
                    tabLayout.getChildAt(1) as TextView
                tabTextView.typeface = fontDefault
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val tabLayout: LinearLayout =
                    (tabs.getChildAt(0) as ViewGroup).getChildAt(tab!!.position) as LinearLayout
                val tabTextView: TextView = tabLayout.getChildAt(1) as TextView
                tabTextView.typeface = fontSelect

            }

        })

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

            }
        })
    }

    private inner class ContentPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = fakeTabs.size

        override fun createFragment(position: Int): Fragment {
            val frag = ContentFragment.newInstance(tabsLoaded[fakeTabs[position]] == null)
            tabsLoaded[fakeTabs[position]] = true
            return frag
        }
    }

}