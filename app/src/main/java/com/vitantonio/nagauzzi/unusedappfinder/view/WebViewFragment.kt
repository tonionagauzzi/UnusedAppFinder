package com.vitantonio.nagauzzi.unusedappfinder.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.vitantonio.nagauzzi.unusedappfinder.R
import com.vitantonio.nagauzzi.unusedappfinder.databinding.WebViewFragmentBinding
import com.vitantonio.nagauzzi.unusedappfinder.extension.getString
import com.vitantonio.nagauzzi.unusedappfinder.viewmodel.WebViewViewModel
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel

class WebViewFragment : Fragment() {

    private val viewModel: WebViewViewModel by viewModel()

    private lateinit var binding: WebViewFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WebViewFragmentBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel

        // ライセンスHTMLのURLを指定
        // 汎用化する際はthis.argumentsで外部から指定できる
        viewModel.url.value = "file:///android_asset/licenses.html"
    }

    override fun onResume() {
        super.onResume()
        activity?.getSupportActionBar()?.apply {
            title = R.string.label_oss_license.getString(context!!)
            activity?.getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        }
        activity?.getToolBar()?.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}