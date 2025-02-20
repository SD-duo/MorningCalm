package com.android.myapplication.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.android.myapplication.Data.Mc
import com.android.myapplication.R
import com.android.myapplication.databinding.FragmentFixtureItemBinding

class IbsFixtureAdapter : RecyclerView.Adapter<IbsFixtureAdapter.ViewHolder>() {
    private var items = mutableListOf<Mc.McItem>()

    fun setItems(items: List<Mc.McItem>) {
        Log.d("IbsFixtureAdapter", "Adapter updated, new size: ${items.size}")
        this.items.clear()
        this.items.addAll(items.filter { it.category == "1" })
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            FragmentFixtureItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(val binding: FragmentFixtureItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItems(item: Mc.McItem) = binding.apply {
            imgOsstemType.load(R.drawable.ibs)
            tvSize.text = "${item.diameter} ø x ${item.length} mm"
            tvQuantity.text = item.code
            tvType.text = item.name
        }
    }
}


