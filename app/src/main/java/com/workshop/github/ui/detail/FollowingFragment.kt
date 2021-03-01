package com.workshop.github.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.workshop.github.R
import com.workshop.github.databinding.FragmentFollowBinding
import com.workshop.github.ui.main.UsersAdapter

class FollowingFragment: Fragment(R.layout.fragment_follow) {

    private var _binding: FragmentFollowBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: FollowingViewModel
    private lateinit var adapter: UsersAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        username = arguments?.getString(DetailUserActivity.EXTRA_USERNAME).toString().trim()
        _binding = FragmentFollowBinding.bind(view)

        adapter = UsersAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            rvFollowers.setHasFixedSize(true)
            rvFollowers.layoutManager = LinearLayoutManager(activity)
            rvFollowers.adapter = adapter
        }

        showLoading(true)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[FollowingViewModel::class.java]
        viewModel.setListFolowers(username)
        viewModel.getListFollowing().observe(viewLifecycleOwner){
            if (it != null){
                adapter.setListUsers(it)
                showLoading(false)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showLoading(state: Boolean){
        if (state){
            binding.progressBarFollowers.visibility = View.VISIBLE
        }else{
            binding.progressBarFollowers.visibility = View.GONE
        }
    }

}