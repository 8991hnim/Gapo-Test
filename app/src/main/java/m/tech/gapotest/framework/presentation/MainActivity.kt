package m.tech.gapotest.framework.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import m.tech.gapotest.R
import m.tech.gapotest.framework.presentation.common.DataStateChangeListener

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), DataStateChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}