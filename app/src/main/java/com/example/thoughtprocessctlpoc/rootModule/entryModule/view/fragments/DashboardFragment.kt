package com.example.thoughtprocessctlpoc.rootModule.entryModule.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.api.networklib.model.Photos
import com.example.thoughtprocessctlpoc.R
import com.example.thoughtprocessctlpoc.databinding.DashboardFragmentBinding
import com.example.thoughtprocessctlpoc.rootModule.entryModule.view.binding.viewAdapter.DashboardGridListAdapter
import com.example.thoughtprocessctlpoc.rootModule.entryModule.view.binding.viewAdapter.DashboardListAdapter
import com.example.thoughtprocessctlpoc.rootModule.entryModule.viewListener.IDashboardViewListener
import com.example.thoughtprocessctlpoc.rootModule.entryModule.viewModel.ContentHeaderViewModel
import com.example.thoughtprocessctlpoc.rootModule.entryModule.viewModel.DashBoardViewModel
import com.example.thoughtprocessctlpoc.rootModule.utils.decorate.GridSpacingItemDecoration

class DashboardFragment : Fragment(), IDashboardViewListener {
    var dashboardViewModel: DashBoardViewModel? = null
    var dashboardFragmentBinding: DashboardFragmentBinding? = null
    lateinit var contentHeaderViewModel: ContentHeaderViewModel
    var dashboardAdapter: DashboardListAdapter? = null
    var dashboardAdapterGrid: DashboardGridListAdapter? = null
    val gridLayoutManager = GridLayoutManager(context, 3)
    lateinit var imageList: List<Photos>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        contentHeaderViewModel = ViewModelProvider(this)[ContentHeaderViewModel::class.java]
        dashboardViewModel = ViewModelProvider(this)[DashBoardViewModel::class.java]
        dashboardFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.dashboard_fragment, container, false)
        dashboardFragmentBinding?.lifecycleOwner = this
        dashboardViewModel?.iDashboardViewListener = this
        contentHeaderViewModel.updateMoudleInstance(dashboardViewModel!!)
        dashboardFragmentBinding?.contentHeaderModel = contentHeaderViewModel
        dashboardFragmentBinding?.dashboardViewModel = dashboardViewModel
        initView()
        return dashboardFragmentBinding?.root
    }

    override fun initView() {
        dashboardFragmentBinding?.dashboardModulesRecyclerView?.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )
        dashboardFragmentBinding?.dashboardModulesRecyclerView?.addItemDecoration(
            GridSpacingItemDecoration(3, 20, false)
        )
        isToggleGrid()
        observeTransactionList()
        dashboardViewModel?.getImageList()
    }

    fun setDashboardList() {
        try {
            if (contentHeaderViewModel.isGridClicked.get()!!) {
                dashboardFragmentBinding?.dashboardModulesRecyclerView?.layoutManager =
                    gridLayoutManager
                dashboardAdapterGrid =
                    DashboardGridListAdapter(
                        imageList, requireContext()
                    )
                dashboardFragmentBinding?.dashboardModulesRecyclerView?.adapter =
                    dashboardAdapterGrid
            } else {
                dashboardFragmentBinding?.dashboardModulesRecyclerView?.layoutManager =
                    LinearLayoutManager(requireContext())
                dashboardAdapter =
                    DashboardListAdapter(
                        imageList, requireContext()
                    )
                dashboardFragmentBinding?.dashboardModulesRecyclerView?.adapter = dashboardAdapter
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onNavigateToNext() {
        NavHostFragment.findNavController(this).navigate(R.id.from_entry_to_NextFragmentTwo)
    }

    override fun onSwitchToggle(isGridView: Boolean) {
        if (isGridView) {
            contentHeaderViewModel.switchBtnTitle.set("Grid")
            contentHeaderViewModel.isGridClicked.set(false)
        } else {
            contentHeaderViewModel.switchBtnTitle.set("ListView")
            contentHeaderViewModel.isGridClicked.set(
                true
            )
        }
        isToggleGrid()
    }

    override fun onSearchFilter(inputSearch: String) {
        dashboardAdapter?.filter?.filter(inputSearch)
        dashboardAdapterGrid?.filter?.filter(inputSearch)
    }

    fun isToggleGrid() {
        dashboardFragmentBinding?.dashboardModulesRecyclerView?.layoutManager?.removeAllViews()
        setDashboardList()
    }

    fun observeTransactionList() {
        dashboardViewModel?.photosList?.observe(viewLifecycleOwner) { imageList ->
            if (imageList.isNotEmpty()) {
                this.imageList = imageList
                setDashboardList()
            }
        }
    }

}