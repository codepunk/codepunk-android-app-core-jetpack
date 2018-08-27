/*
 * Copyright (C) 2018 Codepunk, LLC
 *               Author(s): Scott Slater
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.codepunk.jetpack.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.codepunk.jetpack.AppExecutors
import com.codepunk.jetpack.R
import com.codepunk.jetpack.databinding.FragmentSecondaryBinding
import com.codepunk.jetpack.di.Injectable
import com.codepunk.jetpack.ui.user.UserViewModel
import com.codepunk.jetpack.util.AuthorizationInterceptor
import com.codepunk.jetpack.vo.Resource
import com.codepunk.jetpack.vo.Status
import com.codepunk.jetpack.vo.User
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class SecondaryFragment : Fragment(), Injectable {

    // region Properties

    /**
     * The [ViewModelProvider.Factory] to use when obtaining [ViewModel] instances.
     */
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    /**
     * An instance of [AppExecutors]
     */
    @Inject
    lateinit var appExecutors: AppExecutors

    /**
     * View binding for this fragment.
     */
    private lateinit var binding: FragmentSecondaryBinding

    /**
     * The [UserViewModel] backing this fragment.
     */
    private lateinit var userViewModel: UserViewModel

    // endregion Properties

    // region Lifecycle methods

    /**
     * Sets up the [UserViewModel] and listens for changes.
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO TEMP
        AuthorizationInterceptor.accessToken =
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjZjNDM5NWNhNzc5OTdjMjVlZjIyMTI4NTUyZjc4MDZiYjFhNjVkNjhhYTA3ZDRiZjUzNDVjYjE0ZjgxOWQ0NDgzY2NjYWFiOWU0NWUzY2U4In0.eyJhdWQiOiIyIiwianRpIjoiNmM0Mzk1Y2E3Nzk5N2MyNWVmMjIxMjg1NTJmNzgwNmJiMWE2NWQ2OGFhMDdkNGJmNTM0NWNiMTRmODE5ZDQ0ODNjY2NhYWI5ZTQ1ZTNjZTgiLCJpYXQiOjE1MzQ5NDk5MDksIm5iZiI6MTUzNDk0OTkwOSwiZXhwIjoxNTY2NDg1OTA5LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.PtdOIz2ngWKNKDFYMPG4DRoySloniag15O5ANEwpQHs1Fe5BKKeInnyOpSbzqgN0x8RpCBb0NB5jKnmD8xIAITiLEnbHw2wlHaZwyC5LDitYKXLZJ6xAd9pDx_Gp8erLpsbuhT4ikW_ru5O6nOclbBNik_nTF48TOQS0VaDL96K21Vhei0IE5daGtWtspHSafqHtNfxK2pcKrgug8IrSlgo7rQa8NxHgOIuRSaPE-0BMKfqd3hb-qsIV8U8CEeXkXCl1Zm889Wcop3xxSwuN_0LenNy5YPt6Jc0y-0ookOFdNiUXDp1j_xfD7DfNA5WlhR5qh9QCj3v0ryCjLPmGgUGmaHNpiEIMLLszDReZYzOqz2DEglmcA6303sERyaz_FSIJ5Wudy_jeRIJKqn3ojnVUHI9Y9XQfSCXOGHIPnpBIsRTwifvif481o1dsnjVO5Y5gMU7BFalKttzu5wUyn1uBpI0xL3ErzYEY1L2gycooOQa8tCCsXlKN_HFCTNU72uePw14qSowALi1CgesiJ7hv5R1CjYO_gbc1E77jF7u0LUW4tXnC7Ytt8HSv6BkbTNpq-IYQ8STdtK2BVf1Oqq7YKNiuDLGABXsCu4iaQA6YKGAdpyiPICQ1EAlq0ynzaRjuQzKoqiZoy3WW__hq7LRiI5LdgOBn8AEecLo_Fyg"

        // This is a "bad" token
        /*
        AuthorizationInterceptor.accessToken =
                "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjZjNDM5NWNhNzc5OTdjMjVlZjIyMTI4NTUyZjc4MDZiYjFhNjVkNjhhYTA3ZDRiZjUzNDVjYjE0ZjgxOWQ0NDgzY2NjYWFiOWU0NWUzY2U4In0.eyJhdWQiOiIyIiwianRpIjoiNmM0Mzk1Y2E3Nzk5N2MyNWVmMjIxMjg1NTJmNzgwNmJiMWE2NWQ2OGFhMDdkNGJmNTM0NWNiMTRmODE5ZDQ0ODNjY2NhYWI5ZTQ1ZTNjZTgiLCJpYXQiOjE1MzQ5NDk5MDksIm5iZiI6MTUzNDk0OTkwOSwiZXhwIjoxNTY2NDg1OTA5LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.PtdOIz2ngWKNKDFYMPG4DRoySloniag15O5ANEwpQHs1Fe5BKKeInnyOpSbzqgN0x8RpCBb0NB5jKnmD8xIAITiLEnbHw2wlHaZwyC5LDitYKXLZJ6xAd9pDx_Gp8erLpsbuhT4ikW_ru5O6nOclbBNik_nTF48TOQS0VaDL96K21Vhei0IE5daGtWtspHSafqHtNfxK2pcKrgug8IrSlgo7rQa8NxHgOIuRSaPE-0BMKfqd3hb-qsIV8U8CEeXkXCl1Zm889Wcop3xxSwuN_0LenNy5YPt6Jc0y-0ookOFdNiUXDp1j_xfD7DfNA5WlhR5qh9QCj3v0ryCjLPmGgUGmaHNpiEIMLLszDReZYzOqz2DEglmcA6303sERyaz_FSIJ5Wudy_jeRIJKqn3ojnVUHI9Y9XQfSCXOGHIPnpBIsRTwifvif481o1dsnjVO5Y5gMU7BFalKttzu5wUyn1uBpI0xL3ErzYEY1L2gycooOQa8tCCsXlKN_HFCTNU72uePw14qSowALi1CgesiJ7hv5R1CjYO_gbc1E77jF7u0LUW4tXnC7Ytt8HSv6BkbTNpq-IYQ8STdtK2BVf1Oqq7YKNiuDLGABXsCu4iaQA6YKGAdpyiPICQ1EAlq0ynzaRjuQzKoqiZoy3WW__hq7LRiI5LdgOBn8AEecLo_Fyg"
        */

        userViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(UserViewModel::class.java)
        userViewModel.setUserId(1) // TODO TEMP

        userViewModel.user.observe(this, Observer { userResource ->
            onUserResourceChanged(userResource)
        })
    }

    /**
     * Inflates the view and sets up view binding.
     */
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

    // endregion Lifecycle methods

    // region Methods

    /**
     * Reacts to user resource changes.
     */
    private fun onUserResourceChanged(userResource: Resource<User>) {
        val status = userResource.status
        val message = userResource.message
        val user = userResource.data
        when (status) {
            Status.SUCCESS -> {
                binding.message.text = "Hello, ${user?.name}!" // TODO TEMP
            }
            Status.ERROR -> {
                // TODO Maybe parse "message" from userResource.message JSON?
            }
        }
        Log.d(this::class.java.simpleName, "In onUserResourceChanged!")
    }

    // endregion Methods

}
