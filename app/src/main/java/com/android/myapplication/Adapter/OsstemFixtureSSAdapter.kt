package com.android.myapplication.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.android.myapplication.Data.Mc2
import com.android.myapplication.R
import com.android.myapplication.databinding.FragmentFixtureItemBinding

class OsstemFixtureSSAdapter(private val onItemClick: (Mc2.ResultData.Result) -> Unit) :
    RecyclerView.Adapter<OsstemFixtureSSAdapter.ViewHolder>() {

    private var items = mutableListOf<Mc2.ResultData.Result>()

    fun setItems(items: List<Mc2.ResultData.Result>) {
        this.items.clear()
        this.items.addAll(items.filter { it.category == "4" })
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OsstemFixtureSSAdapter.ViewHolder {

        val binding =
            FragmentFixtureItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OsstemFixtureSSAdapter.ViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(val binding: FragmentFixtureItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItems(item: Mc2.ResultData.Result) = binding.apply {


            ivImg.load(R.drawable.fixture_ss)
            tvSize.text =
                item?.diameter.toString() + " ø " + " x " + item?.length.toString() + " mm "
            tvQuantity.text = item?.code
            tvName.text = item?.name
            tvCode.text = item?.code
            tvQuantity.text = item.quantity.toString()

            root.setOnClickListener {
                onItemClick(item)
            }

        }
    }

}