package com.example.thoughtprocessctlpoc.rootModule.entryModule.viewListener

interface IDashboardViewListener {
    fun initView()
    fun onNavigateToNext()
    fun onSwitchToggle(isGrid:Boolean)
    fun onSearchFilter(inputSearch:String)
}