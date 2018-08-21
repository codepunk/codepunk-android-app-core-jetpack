package com.codepunk.jetpack.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.codepunk.jetpack.AppExecutors
import com.codepunk.jetpack.R
import com.codepunk.jetpack.databinding.FragmentSecondaryBinding
import com.codepunk.jetpack.di.Injectable
import com.codepunk.jetpack.ui.user.UserViewModel
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class SecondaryFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var appExecutors: AppExecutors

    private lateinit var binding: FragmentSecondaryBinding

    private lateinit var userViewModel: UserViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        userViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(UserViewModel::class.java)
        userViewModel.setUserId(1) // TODO TEMP

        userViewModel.user.observe(this, Observer { userResource ->
            val status = userResource.status
            val message = userResource.message
            val user = userResource.data
            Log.d(this::class.java.simpleName, "In userViewModel.user.observe!")
        })

        val user = userViewModel.user
        val value = user.value
        Log.d(this::class.java.simpleName, "onActivityCreated: user=$user")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_secondary,
            container,
            false
        )
        return binding.root
    }

}
