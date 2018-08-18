package com.codepunk.jetpack.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.codepunk.jetpack.R
import com.codepunk.jetpack.databinding.FragmentSecondaryBinding

/**
 * A simple [Fragment] subclass.
 *
 */
class SecondaryFragment : Fragment() {

    private lateinit var binding: FragmentSecondaryBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_secondary, container, false)
        return binding.root
    }

}
