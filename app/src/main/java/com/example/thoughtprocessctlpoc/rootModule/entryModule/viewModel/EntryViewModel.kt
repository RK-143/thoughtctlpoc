package com.example.thoughtprocessctlpoc.rootModule.entryModule.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.thoughtprocessctlpoc.rootModule.entryModule.viewListener.IDashboardViewListener
import com.example.thoughtprocessctlpoc.rootModule.entryModule.viewListener.IEntryViewListener

class EntryViewModel(application: Application): AndroidViewModel(application) {
    private var mContext: Application
    init {
        mContext = application
    }
    var iEntryViewListener: IEntryViewListener? = null

}