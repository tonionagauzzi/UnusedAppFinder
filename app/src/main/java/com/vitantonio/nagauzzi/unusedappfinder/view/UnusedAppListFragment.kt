package com.vitantonio.nagauzzi.unusedappfinder.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.vitantonio.nagauzzi.unusedappfinder.R
import com.vitantonio.nagauzzi.unusedappfinder.viewmodel.UnusedAppListViewModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.direct
import org.kodein.di.generic.instance
import GridAdapter
import android.widget.AdapterView
import android.widget.GridView
import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.vitantonio.nagauzzi.unusedappfinder.databinding.UnusedAppListFragmentBinding
import kotlinx.android.synthetic.main.unused_app_list_fragment.*
import android.view.*
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.vitantonio.nagauzzi.unusedappfinder.extension.getString

class UnusedAppListFragment : Fragment(), KodeinAware {

    override val kodein by closestKodein()

    private val viewModel by lazy {
        ViewModelProviders.of(this, direct.instance()).get(UnusedAppListViewModel::class.java)
    }

    private lateinit var binding: UnusedAppListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding = UnusedAppListFragmentBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        viewModel.appUsageList.observe(this, Observer { appUsageList ->
            if (appUsageList != null) {
                val gridView = activity!!.findViewById<GridView>(R.id.gridViewUnusedAppList)
                gridView.adapter = GridAdapter(
                    context!!,
                    R.layout.unused_app_item,
                    appUsageList
                )
                gridView.onItemClickListener =
                    AdapterView.OnItemClickListener { parent, _, position, _ ->
                        val selectedItem = parent.getItemAtPosition(position) as AppUsage
                        val intent = Intent()
                        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                        intent.data = Uri.parse("package:${selectedItem.packageName}")
                        startActivity(intent)
                    }
            }
        })
        imageViewHowToPermitAppUsage.setOnClickListener {
            launchSetting()
        }
        swipeRefreshLayoutUnusedAppList.setOnRefreshListener {
            viewModel.getAppUsages()
            swipeRefreshLayoutUnusedAppList.isRefreshing = false
        }
    }

    override fun onResume() {
        super.onResume()
        activity?.title = R.string.app_name.getString(context!!)
        activity?.getSupportActionBar()?.setDisplayHomeAsUpEnabled(false)
        viewModel.getAppUsages()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_about_this_app, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        when (item.itemId) {
            R.id.menuAbout -> {
                Toast.makeText(context, "Not implemented.", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.menuOSSLicense -> {
                findNavController().navigate(R.id.actionUnusedAppListDestToWebViewDest)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun launchSetting() {
        startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
    }
}
