package com.vitantonio.nagauzzi.unusedappfinder.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.vitantonio.nagauzzi.unusedappfinder.R
import com.vitantonio.nagauzzi.unusedappfinder.viewmodel.UnusedAppListViewModel
import android.widget.AdapterView
import android.widget.GridView
import com.vitantonio.nagauzzi.unusedappfinder.model.AppUsage
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.vitantonio.nagauzzi.unusedappfinder.databinding.UnusedAppListFragmentBinding
import android.view.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.vitantonio.nagauzzi.unusedappfinder.extension.getString
import com.vitantonio.nagauzzi.unusedappfinder.usecase.GetAppUsages
import com.vitantonio.nagauzzi.unusedappfinder.view.adapter.GridAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class UnusedAppListFragment : Fragment() {

    private val viewModel: UnusedAppListViewModel by viewModels()

    @Inject lateinit var getAppUsages: GetAppUsages

    private lateinit var binding: UnusedAppListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = UnusedAppListFragmentBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        viewModel.showingList.onEach { appUsageList ->
            val gridView = requireActivity().findViewById<GridView>(R.id.gridViewUnusedAppList)
                ?: return@onEach
            gridView.adapter = GridAdapter(
                requireContext(),
                R.layout.unused_app_item,
                appUsageList
            )
            (gridView.adapter as GridAdapter).notifyDataSetChanged()
            gridView.onItemClickListener =
                AdapterView.OnItemClickListener { parent, _, position, _ ->
                    val selectedItem = parent.getItemAtPosition(position) as AppUsage
                    val intent = Intent()
                    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    intent.data = Uri.parse("package:${selectedItem.packageName}")
                    startActivity(intent)
                }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
        binding.imageViewHowToPermitAppUsage.setOnClickListener {
            launchSetting()
        }
        binding.swipeRefreshLayoutUnusedAppList.setOnRefreshListener {
            getAppUsages()
            binding.swipeRefreshLayoutUnusedAppList.isRefreshing = false
        }
    }

    override fun onResume() {
        super.onResume()
        activity?.getSupportActionBar()?.apply {
            title = R.string.app_name.getString(requireContext())
            setDisplayHomeAsUpEnabled(false)
        }
        getAppUsages()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_about_this_app, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        when (item.itemId) {
            R.id.menuAbout -> {
                context?.run {
                    startActivity(Intent().apply {
                        action = Intent.ACTION_VIEW
                        data = Uri.parse("market://details?id=${this@run.packageName}")
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    })
                }
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

    private fun getAppUsages() {
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            getAppUsages.execute()
        }
    }
}
