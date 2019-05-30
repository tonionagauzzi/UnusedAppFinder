package com.vitantonio.nagauzzi.unusedappfinder.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.vitantonio.nagauzzi.unusedappfinder.R
import com.vitantonio.nagauzzi.unusedappfinder.viewmodel.UnusedAppListViewModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.direct
import org.kodein.di.generic.instance
import GridAdapter
import android.widget.GridView

class UnusedAppListFragment : Fragment(), KodeinAware {

    override val kodein by closestKodein()

    private val viewModel by lazy {
        ViewModelProviders.of(this, direct.instance()).get(UnusedAppListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.unused_app_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.appUsageList.observe(this, Observer { appUsageList ->
            if (appUsageList != null) {
                activity!!.findViewById<GridView>(R.id.grid_view_unused_app_list).adapter = GridAdapter(
                    context!!,
                    R.layout.unused_app_item,
                    appUsageList
                )
            }
        })

        viewModel.getAppUsages()
    }
}
