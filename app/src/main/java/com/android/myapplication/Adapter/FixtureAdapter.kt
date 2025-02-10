package com.android.myapplication.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.myapplication.databinding.FragmentFixtureItemBinding

class FixtureAdapter() : RecyclerView.Adapter<FixtureAdapter.ViewHolder>() {
    private var items = Mc()

    fun setItems(items: List<Mc.McItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FixtureAdapter.ViewHolder {

        val binding =
            FragmentFixtureItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FixtureAdapter.ViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(val binding: FragmentFixtureItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItems(item: Mc.McItem) = binding.apply {

//            imgCodyName.load(item.)
//            tvCodyItem.text = item?.cashItemLabel as CharSequence?
            tvCodyItemChar.text = item?.name
            tvCodyItemName.text = item?.length.toString()

        }
    }

}