package com.example.thoughtprocessctlpoc.rootModule.entryModule.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavGraph
import androidx.navigation.NavInflater
import androidx.navigation.fragment.NavHostFragment
import com.example.thoughtprocessctlpoc.R
import com.example.thoughtprocessctlpoc.databinding.MainActivityBinding
import com.example.thoughtprocessctlpoc.rootModule.entryModule.viewListener.IEntryViewListener
import com.example.thoughtprocessctlpoc.rootModule.entryModule.viewModel.EntryViewModel

class MainActivity : AppCompatActivity(), IEntryViewListener {
    var entryViewModel: EntryViewModel? = null
    var mainActivityBinding: MainActivityBinding? = null
    protected override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main);
        entryViewModel = ViewModelProvider(this)[EntryViewModel::class.java]
        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainActivityBinding?.lifecycleOwner = this
        mainActivityBinding?.entryViewModel = entryViewModel
        entryViewModel?.iEntryViewListener = this
        initView()
    }

    override fun initView() {
        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val inflater: NavInflater = navHostFragment.navController.navInflater
        val graph: NavGraph = inflater.inflate(R.navigation.navigation_graph)
        graph.setStartDestination(R.id.DashboardFragment)
        navHostFragment.navController.graph = graph
    }
}