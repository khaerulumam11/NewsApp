package com.example.newsapp.fragment.general

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapter.SourceNewsAdapter
import com.example.newsapp.databinding.FragmentEntertainmentBinding
import com.example.newsapp.databinding.FragmentGeneralBinding
import com.example.newsapp.fragment.entertainment.EntertainmentViewModel
import com.example.newsapp.model.SourceNewsModel

class GeneralFragment : Fragment() {

    companion object {
        fun newInstance() = GeneralFragment()
    }

    private lateinit var viewModel: GeneralViewModel
    private var page = 1
    private var adapterSource : SourceNewsAdapter?=null
    private var sourceLiveData = ArrayList<SourceNewsModel.SourcesEntity>()
    private lateinit var bindingFragment: FragmentGeneralBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingFragment = FragmentGeneralBinding.inflate(inflater,container,false)
        var view = bindingFragment.root
        adapterSource = SourceNewsAdapter(requireContext())
        viewModel = ViewModelProvider(this).get(GeneralViewModel::class.java)
        viewModel.getSourceNews(page)
        viewModel.observeSourceLiveData().observe(viewLifecycleOwner, Observer { sourceList ->
            println("Response ${sourceList[0].id}")
            sourceLiveData.clear()
            sourceLiveData.addAll(sourceList)
            adapterSource!!.setPost(sourceList as ArrayList<SourceNewsModel.SourcesEntity>)
        })
        bindingFragment.rvList.layoutManager = LinearLayoutManager(requireContext())
        bindingFragment.rvList.adapter = adapterSource

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(GeneralViewModel::class.java)
        // TODO: Use the ViewModel
    }

}