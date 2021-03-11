package m.tech.gapotest.framework.presentation

import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import m.tech.gapotest.R
import m.tech.gapotest.databinding.FragmentMainBinding
import m.tech.gapotest.framework.presentation.common.BaseFragment
import m.tech.gapotest.util.gone
import m.tech.gapotest.util.setupWithNavController
import m.tech.gapotest.util.show

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {
    override fun init(view: View) {
        setupNavigation(view)
    }

    private fun setupNavigation(view: View) {
        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottom_nav_view)

        val navGraphIds = listOf(
            R.navigation.nav_home,
            R.navigation.nav_account
        )

        bottomNavigationView.setupWithNavController(
            navGraphIds,
            childFragmentManager,
            R.id.nav_host,
            null
        )

    }

    override fun subscribeObserver(view: View) {
        commonViewModel.showBottomNav.observe(viewLifecycleOwner) {
            if (it) {
                binding.bottomNavView.show()
            } else {
                binding.bottomNavView.gone()
            }
        }
    }
}