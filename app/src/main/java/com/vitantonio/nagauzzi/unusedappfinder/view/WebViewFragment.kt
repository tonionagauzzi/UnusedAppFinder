package com.vitantonio.nagauzzi.unusedappfinder.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.vitantonio.nagauzzi.unusedappfinder.R
import com.vitantonio.nagauzzi.unusedappfinder.databinding.WebViewFragmentBinding
import com.vitantonio.nagauzzi.unusedappfinder.extension.getString
import com.vitantonio.nagauzzi.unusedappfinder.viewmodel.WebViewViewModel
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.direct
import org.kodein.di.generic.instance
import androidx.navigation.fragment.findNavController

class WebViewFragment : Fragment(), KodeinAware {

    override val kodein by closestKodein()

    private val viewModel by lazy {
        ViewModelProviders.of(this, direct.instance()).get(WebViewViewModel::class.java)
    }

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
        activity?.title = R.string.label_oss_license.getString(context!!)
        activity?.getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        activity?.getToolBar()?.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}