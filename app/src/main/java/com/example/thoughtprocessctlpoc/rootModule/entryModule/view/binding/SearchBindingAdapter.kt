package com.example.thoughtprocessctlpoc.rootModule.entryModule.view.binding

import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import com.example.thoughtprocessctlpoc.rootModule.entryModule.viewModel.DashBoardViewModel

object SearchBindingAdapter {
    @BindingAdapter("onTextChange")
    @JvmStatic
    fun SearchView.onTextChange(dashBoardViewModel: DashBoardViewModel) {
        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                dashBoardViewModel.searchListItem(newText)
                return true
            }
        })
    }
}