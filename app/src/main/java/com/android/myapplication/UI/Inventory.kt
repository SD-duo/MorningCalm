package com.android.myapplication.UI

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.myapplication.Adapter.IbsFixtureAdapter
import com.android.myapplication.Adapter.OsstemFixtureAdapter
import com.android.myapplication.Data.Mc2
import com.android.myapplication.ViewModel.InventoryViewModel
import com.android.myapplication.databinding.FragmentFixtureBinding
import com.google.android.material.tabs.TabLayout



class Inventory : DialogFragment() {
    companion object {
        fun newinstance(): Inventory = Inventory()
    }

    private var _binding: FragmentFixtureBinding? = null
    private val binding get() = _binding!!

    private val osstemfixtureAdapter: OsstemFixtureAdapter by lazy {
        OsstemFixtureAdapter{ selectedItem -> showItemDetails(selectedItem)} }

    // 이미 클릭 리스너를 생성자에서 설정했으므로 추가 설정이 필요 없음
    private val ibsfixtureAdapter: IbsFixtureAdapter by lazy {
        IbsFixtureAdapter { selectedItem -> showItemDetails(selectedItem) }
    }

    private val viewModel: InventoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFixtureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgCodyBack.setOnClickListener {
            binding.Allview.visibility = View.VISIBLE
                binding.viewCliked.visibility = View.GONE

        }

        binding.apply {
            bgCody.setOnClickListener {
                viewModel.getMcData()
            }
        }

        _binding?.let { binding ->
            initView()
            initViewModel()

            val tabLayout: TabLayout = binding.tabLayout2
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.let {
                        binding.apply {
                            when (it.position) {
                                0 -> rvMaterial.adapter = ibsfixtureAdapter
                                2 -> rvMaterial.adapter = osstemfixtureAdapter
                            }
                        }
                    } ?: Log.e("InventoryFragment", "Binding is null!")
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        } ?: Log.e("InventoryFragment", "Binding is null during onViewCreated!")
    }

    private fun initView() {

        binding.rvMaterial.adapter = ibsfixtureAdapter
        binding.rvMaterial.layoutManager = LinearLayoutManager(context)
    }

    private fun initViewModel() = binding.apply {
        viewModel.items.observe(viewLifecycleOwner) { newList ->
            Log.d("InventoryFragment", "Observer triggered! New list size: ${newList.size}")
            ibsfixtureAdapter.setItems(newList)
            osstemfixtureAdapter.setItems(newList)
        }
        viewModel.getMcData()
    }

    private fun showItemDetails(item: Mc2.ResultData.Result) {
        _binding?.apply {
            Allview.visibility = View.GONE
            viewCliked.visibility = View.VISIBLE

            etId.setText(item.id.toString())
            etCategory.setText(item.category.toString())
            etDiameter.setText(item.diameter.toString())
            etName.setText(item.name)
            etLength.setText(item.length.toString())
            etQuantitiy.setText(item.quantity.toString())

        } ?: Log.e("ClickedView", "리스트가 클릭되었습니다")
    }
}
