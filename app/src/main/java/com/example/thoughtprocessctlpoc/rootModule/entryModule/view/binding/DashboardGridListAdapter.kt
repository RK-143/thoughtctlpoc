package com.example.thoughtprocessctlpoc.rootModule.entryModule.view.binding

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView
import com.api.networklib.model.Photos
import com.example.thoughtprocessctlpoc.databinding.DashboardUIBinding
import com.example.thoughtprocessctlpoc.databinding.DashboardUIGridBinding
import com.example.thoughtprocessctlpoc.rootModule.entryModule.viewListener.IDashboardViewListener
import java.util.*
import kotlin.collections.ArrayList

class DashboardGridListAdapter(
    private val listOfDashboardModule: List<Photos?>,
    private val context: Context
) :
    RecyclerView.Adapter<DashboardGridListAdapter.DashboardViewHolder>() , Filterable {
    var photoFilterList = listOf<Photos?>()

    init {
        photoFilterList = listOfDashboardModule
    }
    private var previousHolder: DashboardViewHolder? = null
     var holdBinding: DashboardUIGridBinding?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val binding: DashboardUIGridBinding =
            DashboardUIGridBinding.inflate(LayoutInflater.from(parent.context))
        holdBinding=binding
        return DashboardViewHolder(binding, binding.root)

    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val currentDashboardItem = listOfDashboardModule[position]
        previousHolder = holder

        currentDashboardItem?.let {
            holder.bind(it, context)
        }
    }

    override fun getItemCount(): Int {
        return photoFilterList.size
    }

    class DashboardViewHolder(@Nullable val binding: DashboardUIGridBinding, view: View) :
        RecyclerView.ViewHolder(view) {

        fun bind(item: Photos, context: Context) {
            binding.dashboardGridImageList = item
            binding.executePendingBindings()
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                photoFilterList = if (charSearch.isEmpty()) {
                    listOfDashboardModule
                } else {
                    val resultList = ArrayList<Photos>()
                    for (row in listOfDashboardModule) {
                        if (row?.title?.lowercase(Locale.ROOT)!!.contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = photoFilterList
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                photoFilterList = results?.values as List<Photos>
                notifyDataSetChanged()
            }

        }
    }
}