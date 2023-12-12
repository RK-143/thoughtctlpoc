package com.example.thoughtprocessctlpoc.rootModule.entryModule.viewModel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.thoughtprocessctlpoc.rootModule.entryModule.viewListener.IDashboardViewListener

class ContentHeaderViewModel (application: Application): AndroidViewModel(application){
    private var mContext: Application
    init {
        mContext = application
    }
    var switchBtnTitle = ObservableField("ListView")
    var isGridClicked = ObservableField(true)
    var rootViewModel: ViewModel? = null
    fun updateMoudleInstance(viewModel: ViewModel) {
        this.rootViewModel = viewModel
    }
    fun onSwitchToggleClick() {
        try {
            rootViewModel?.let {
                when (it) {
                    is DashBoardViewModel->{
                        it.switchToggleClicked( isGridClicked.get()!!)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}