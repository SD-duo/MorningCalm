package com.android.myapplication.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.android.myapplication.Data.Mc
import com.android.myapplication.R
import com.android.myapplication.databinding.FragmentFixtureItemBinding

class IbsFixtureAdapter() : RecyclerView.Adapter<IbsFixtureAdapter.ViewHolder>() {
    private var items = Mc()

    fun setItems(items: List<Mc.McItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IbsFixtureAdapter.ViewHolder {

        val binding =
            FragmentFixtureItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IbsFixtureAdapter.ViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(val binding: FragmentFixtureItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItems(item: Mc.McItem) = binding.apply {

            if(item?.category.toString() == "1"){imgOsstemType.load(R.drawable.fixture_ts)}
            else(imgOsstemType.load(R.drawable.fixture_ss))
            tvSize.text = item?.diameter.toString() + " ø " + " x " + item?.length.toString() + " mm "
            tvQuantity.text = item?.code
            tvType.text = item?.name

        }
    }

}