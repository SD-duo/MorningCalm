package com.android.myapplication.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.android.myapplication.Data.Mc2
import com.android.myapplication.R
import com.android.myapplication.databinding.FragmentFixtureItemBinding

class BoneAdapter(
    private val onItemClick: (Mc2.ResultData.Result) -> Unit
) :
    RecyclerView.Adapter<BoneAdapter.ViewHolder>() {

    private var items = mutableListOf<Mc2.ResultData.Result>()

    fun setItems(items: List<Mc2.ResultData.Result>) {
        Log.d("setItems", "Adapter updated, new size: ${items.size}")
        this.items.clear()
        this.items.addAll(items.filter { it.category == "7" })
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BoneAdapter.ViewHolder {

        val binding =
            FragmentFixtureItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BoneAdapter.ViewHolder, position: Int) {
        holder.bindItems(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(val binding: FragmentFixtureItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindItems(item: Mc2.ResultData.Result) = binding.apply {

            when(item?.diameter){
                0.0 -> ivImg.load(R.drawable.osteon)
                3.0 -> ivImg.load(R.drawable.sureoss)
                4.0 -> ivImg.load(R.drawable.ibs)
                5.0 -> ivImg.load(R.drawable.tg2)

            }
            tvName.text = item?.name // 이름
            tvQuantity.text = "재고 : " + item.quantity.toString()
            tvSize.isVisible = false
            tvCode.isVisible = false
            root.setOnClickListener {
                onItemClick(item) // 클릭된 아이템을 리스너로 전달
                Log.d("ClickedView", "오스템 어버트먼트 어뎁터에서 아이템 클릭됨: ${item.name}")

            }
        }
    }
}