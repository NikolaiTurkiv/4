package com.test.a4.ui.screens

import android.R.attr.capitalize
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.test.a4.R
import com.test.a4.databinding.FragmentSplashBinding
import com.test.a4.ui.viewmodels.SplashViewModel
import com.test.a4.utils.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class SplashFragment : Fragment(R.layout.fragment_splash) {
    val binding by viewBinding<FragmentSplashBinding>()
    private val viewModel by viewModels<SplashViewModel>()

    private var progress = 0

    private var countDownTimer: CountDownTimer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         startTimer()
        val locale = resources.configuration.locale
        val deviceName = getDeviceName()
        val uniqueID = UUID.randomUUID().toString()

        Log.d("PHONE_INFO",locale.toString())
        Log.d("PHONE_INFO",deviceName.toString())
        Log.d("PHONE_INFO",uniqueID.toString())

        viewModel.fetchPhoneStatus(deviceName.toString(),locale.toString(),uniqueID)
    }

    private fun startTimer(){
        countDownTimer = object : CountDownTimer(3000,30){
            override fun onTick(p0: Long) {
                progress += 1
                binding.progressBar.progress = progress
            }

            override fun onFinish() {
                findNavController().navigate(R.id.action_splashFragment_to_webViewFragment)
             }

        }.start()




    }

    fun getDeviceName(): String? {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        return "$manufacturer $model"

    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }
}