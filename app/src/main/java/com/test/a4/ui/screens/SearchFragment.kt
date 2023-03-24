package com.test.a4.ui.screens

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.SearchView.OnQueryTextListener
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.onesignal.OneSignal
import com.test.a4.utils.viewBinding
import com.test.a4.R
import com.test.a4.data.network.response.GeneralTeamResponse
import com.test.a4.databinding.FragmentSearchBinding
import com.test.a4.ui.adapters.TeamAdapter
import com.test.a4.ui.viewmodels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private val binding by viewBinding<FragmentSearchBinding>()
    private val viewModel by viewModels<SearchViewModel>()

    private var teams  = mutableListOf<GeneralTeamResponse>()

    private val args: SearchFragmentArgs by navArgs()

    private val adapter by lazy{
        TeamAdapter(LayoutInflater.from(requireContext())){
            val name = viewModel.prepareTeamName(it)
            Log.d("TEAMSADAPTER",it)
            viewModel.saveName(name)
            findNavController().navigate(R.id.action_searchFragment_to_mainFragment)
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Log.i("Permission: ", "Granted")
            } else {
                Log.i("Permission: ", "Denied")
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermission(view,android.Manifest.permission.POST_NOTIFICATIONS)
        }
        cancelPush()
        viewModel.removeAll()
        viewModel.getTeams()
        initRV()
        initObserver()
        initSearchView()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        permissions.forEach {
            Log.d("PERMISSION",it)
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun requestPermission(view: View, permission: String){

        when {
            ContextCompat.checkSelfPermission(
                requireActivity(),
                permission
            ) == PackageManager.PERMISSION_GRANTED -> {

            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                permission
            ) -> {
                requestPermissionLauncher.launch(
                    permission
                )
            }

            else -> {
                requestPermissionLauncher.launch(
                     permission
                )
            }
        }
    }

   private fun initObserver(){
        viewModel.teamsLD.observe(viewLifecycleOwner){
            adapter.updateList(it)
            teams = it.toMutableList()
        }
    }

    private fun initSearchView(){
        binding.search.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                val list = teams.filter { p0?.let { it1 -> it.team_name.contains(it1.uppercase()) } == true }
                adapter.updateList(list)
                return true
            }

        })
    }

    private fun initRV(){
        binding.rvTeams.adapter = adapter
    }

    companion object{
        @JvmStatic
        fun newInstance() =
            SearchFragment()
    }

    fun cancelPush(){
        if(args.id == SplashFragment.Companion.NO)
            OneSignal.disablePush(true)
        else
            OneSignal.disablePush(false)
    }

}
