package com.example.thoughtprocessctlpoc.rootModule.entryModule.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.api.networklib.model.CloudImageListResponse
import com.api.networklib.model.Photos
import com.api.networklib.repository.ImageAPIRequestRepository
import com.api.networklib.repository.responseListener.IOnGetImageAPIResponse
import com.example.thoughtprocessctlpoc.rootModule.entryModule.viewListener.IDashboardViewListener

class DashBoardViewModel(application: Application) : AndroidViewModel(application),
    IOnGetImageAPIResponse {
    private var mContext: Application
    var iDashboardViewListener: IDashboardViewListener? = null
    var photosList: MutableLiveData<List<Photos>> = MutableLiveData()

    init {
        mContext = application
    }

    override fun onGetSuccess(objCloudResponse: CloudImageListResponse) {
        photosList.value = objCloudResponse.photos
    }

    override fun onGetFailure(errorRes: String) {

    }

    fun switchToggleClicked(isGridView: Boolean) {
        iDashboardViewListener?.onSwitchToggle(isGridView)
    }

    fun getImageList() {
        ImageAPIRequestRepository.getImgeAPIRequest(this)
    }

    fun searchListItem(charSeq: String) {
        iDashboardViewListener?.onSearchFilter(charSeq)
    }
}