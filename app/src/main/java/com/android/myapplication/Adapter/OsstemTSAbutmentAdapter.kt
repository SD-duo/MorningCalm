package com.android.myapplication.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.android.myapplication.Data.Mc2
import com.android.myapplication.R
import com.android.myapplication.databinding.FragmentFixtureItemBinding

class OsstemTSAbutmentAdapter(
    private val onItemClick: (Mc2.ResultData.Result) -> Unit
) :
    RecyclerView.Adapter<OsstemTSAbutmentAdapter.ViewHolder>() {

    private var items = mutableListOf<Mc2.ResultData.Result>()

    fun setItems(items: List<Mc2.ResultData.Result>) {
        Log.d("setItems", "Adapter updated, new size: ${items.size}")
        this.items.clear()
        this.items.addAll(items.filter { it.category == "5" })
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OsstemTSAbutmentAdapter.ViewHolder {

        val binding =
            FragmentFixtureItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OsstemTSAbutmentAdapter.ViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(val binding: FragmentFixtureItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItems(item: Mc2.ResultData.Result) = binding.apply {

            ivImg.load(R.drawable.teeth1)
            tvName.text = item?.name
            tvSize.text =
                item?.diameter.toString() + " ø " + " x " + item?.height.toString() + " mm "  + " (G/H)" + item?.gheight.toString()
            tvCode.text = item?.code
            tvQuantity.text = "재고 : " + item.quantity.toString()
            root.setOnClickListener {
                onItemClick(item) // 클릭된 아이템을 리스너로 전달
                Log.d("ClickedView", "오스템 어버트먼트 어뎁터에서 아이템 클릭됨: ${item.name}")

            }
        }
    }
}
