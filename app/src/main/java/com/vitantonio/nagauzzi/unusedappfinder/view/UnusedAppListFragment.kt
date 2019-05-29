package com.vitantonio.nagauzzi.unusedappfinder.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vitantonio.nagauzzi.unusedappfinder.R
import com.vitantonio.nagauzzi.unusedappfinder.viewmodel.UnusedAppListViewModel

class UnusedAppListFragment : Fragment() {

    private lateinit var viewModel: UnusedAppListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.unused_app_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UnusedAppListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
