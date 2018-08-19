package com.codepunk.jetpack.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.codepunk.jetpack.R
import com.codepunk.jetpack.databinding.FragmentMainBinding

/**
 * A simple [Fragment] subclass.
 *
 */
class MainFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.goToSecondFragmentButton.setOnClickListener(this)
        return binding.root
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.goToSecondFragmentButton -> {
                Navigation.findNavController(v).navigate(R.id.action_main_to_secondary)
            }
        }
    }
}
