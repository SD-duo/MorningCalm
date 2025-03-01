package com.android.myapplication.UI

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.myapplication.Adapter.IbsAbutmentAdapter
import com.android.myapplication.Adapter.IbsFixtureAdapter
import com.android.myapplication.Adapter.OsstemAbutmentAdapter
import com.android.myapplication.Adapter.OsstemFixtureSSAdapter
import com.android.myapplication.Adapter.OsstemFixtureTSAdapter
import com.android.myapplication.Data.Mc2
import com.android.myapplication.Data.UpdateMc
import com.android.myapplication.ViewModel.InventoryViewModel
import com.android.myapplication.databinding.FragmentFixtureBinding
import com.google.android.material.tabs.TabLayout


class Inventory : Fragment() {
    companion object {
        fun newinstance(): Inventory = Inventory()
    }

    private var _binding: FragmentFixtureBinding? = null
    private val binding get() = _binding!!

    private val osstemfixturetsAdapter: OsstemFixtureTSAdapter by lazy {
        OsstemFixtureTSAdapter { selectedItem -> showItemDetails(selectedItem) }
    }

    private val osstemfixturessAdapter: OsstemFixtureSSAdapter by lazy {
        OsstemFixtureSSAdapter { selectedItem -> showItemDetails(selectedItem) }
    }


    private val dataList1 = mutableListOf<Mc2.ResultData.Result>()
    private val ibsfixtureAdapter by lazy {
        IbsFixtureAdapter({ selectedItem -> showItemDetails(selectedItem) }, dataList1)
    }


    private val osstemabutmentAdapter: OsstemAbutmentAdapter by lazy {
        OsstemAbutmentAdapter { selectedItem -> showItemDetails(selectedItem) }
    }

    private val ibsabutmentAdapter: IbsAbutmentAdapter by lazy {
        IbsAbutmentAdapter { selectedItem -> showItemDetails(selectedItem) }
    }

    private val viewModel: InventoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //asdf
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFixtureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initView()
        initViewModel()


        binding.btnSave.setOnClickListener {
            val updatedItem = UpdateMc(
                id = binding.etId.text.toString().toIntOrNull(),
                category = binding.etCategory.text.toString(),
                code = binding.etCode.text.toString(),
                diameter = binding.etDiameter.text.toString().toDoubleOrNull(),
                length = binding.etLength.text.toString().toDoubleOrNull(),
                name = binding.etName.text.toString(),
                quantity = binding.etQuantitiy.text.toString().toIntOrNull()
            )

            if (updatedItem.id != null) {
                viewModel.postMcData(updatedItem)
            } // 🚀 서버에 업데이트 요청
            else {
                Log.e("Inventory", "Invalid item ID")
            }
            binding.Allview.visibility = View.VISIBLE
            binding.viewCliked.visibility = View.GONE

        }

        //삭제버튼 만들기

        binding.btnAdd.setOnClickListener {
            binding.Allview.visibility = View.GONE
            binding.viewCliked.visibility = View.VISIBLE
        }

        binding.imgCodyBack.setOnClickListener {
            binding.Allview.isVisible = true
            binding.viewCliked.isVisible = false
        }

        binding.apply {
            bgCody.setOnClickListener {
                apiRequest()
            }
        }


        val tabLayout: TabLayout = binding.tabLayout2
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    binding.apply {
                        when (it.position) {
                            0 -> rvMaterial.adapter = ibsfixtureAdapter
                            1 -> rvMaterial.adapter = ibsabutmentAdapter
                            2 -> rvMaterial.adapter = osstemfixturetsAdapter
                            3 -> rvMaterial.adapter = osstemfixturessAdapter
                            4 -> rvMaterial.adapter = osstemabutmentAdapter
                        }
                    }
                } ?: Log.e("InventoryFragment", "Binding is null!")
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab.let {
                    when (it?.position) {
                        0 -> binding.rvMaterial.adapter = ibsfixtureAdapter
                        1 -> binding.rvMaterial.adapter = ibsabutmentAdapter
                        2 -> binding.rvMaterial.adapter = osstemfixturetsAdapter
                        3 -> binding.rvMaterial.adapter = osstemfixturessAdapter
                        4 -> binding.rvMaterial.adapter = osstemabutmentAdapter

                    }
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                tab.let {
                    when (it?.position) {
                        0 -> binding.rvMaterial.adapter = ibsfixtureAdapter
                        1 -> binding.rvMaterial.adapter = ibsabutmentAdapter
                        2 -> binding.rvMaterial.adapter = osstemfixturetsAdapter
                        3 -> binding.rvMaterial.adapter = osstemfixturessAdapter
                        4 -> binding.rvMaterial.adapter = osstemabutmentAdapter

                    }
                }
            }
        })

    }

    private fun initView() {
        binding.rvMaterial.adapter = ibsfixtureAdapter
        binding.rvMaterial.layoutManager = LinearLayoutManager(context)
    }

    private fun initViewModel() {

        Log.d("InventoryFragment", "Observing LiveData")
        viewModel.items.observe(viewLifecycleOwner) { newList ->

            ibsfixtureAdapter.setItems(newList)
            ibsabutmentAdapter.setItems(newList)
            osstemfixturetsAdapter.setItems(newList)
            osstemfixturessAdapter.setItems(newList)
            osstemabutmentAdapter.setItems(newList)

        }
        apiRequest()
    }


    private fun showItemDetails(item: Mc2.ResultData.Result) {
        _binding?.apply {
            Allview.visibility = View.GONE
            viewCliked.visibility = View.VISIBLE

            etId.setText(item.id.toString())
            etCategory.setText(item.category.toString())
            etDiameter.setText(item.diameter.toString())
            etCode.setText(item.code.toString())
            etName.setText(item.name)
            etLength.setText(item.length.toString())
            etQuantitiy.setText(item.quantity.toString())
        } ?: Log.e("ClickedView", "리스트가 클릭되었습니다")
    }

    private fun apiRequest(){
        viewModel.getMcData()
    }

}

