package com.example.newsapp.fragment.business

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
import com.example.newsapp.databinding.FragmentBusinessBinding
import com.example.newsapp.model.SourceNewsModel

class BusinessFragment : Fragment() {

    companion object {
        fun newInstance() = BusinessFragment()
    }

    private lateinit var viewModel: BusinessViewModel
    private var page = 1
    private var adapterSource : SourceNewsAdapter?=null
    private var sourceLiveData = ArrayList<SourceNewsModel.SourcesEntity>()
    private lateinit var bindingFragment:FragmentBusinessBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingFragment = FragmentBusinessBinding.inflate(inflater,container,false)
        var view = bindingFragment.root
        adapterSource = SourceNewsAdapter(requireContext())
        viewModel = ViewModelProvider(this).get(BusinessViewModel::class.java)
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
    }

}