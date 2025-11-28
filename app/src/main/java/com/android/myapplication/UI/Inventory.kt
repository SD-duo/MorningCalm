package com.android.myapplication.UI

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.myapplication.Adapter.BoneAdapter
import com.android.myapplication.Adapter.IbsAbutmentAdapter
import com.android.myapplication.Adapter.IbsFixtureAdapter
import com.android.myapplication.Adapter.OsstemTSAbutmentAdapter
import com.android.myapplication.Adapter.OsstemFixtureSSAdapter
import com.android.myapplication.Adapter.OsstemFixtureTSAdapter
import com.android.myapplication.Adapter.OsstemSSAbutmentAdapter
import com.android.myapplication.Data.InsertMc
import com.android.myapplication.Data.Mc2
import com.android.myapplication.Data.UpdateMc
import com.android.myapplication.ViewModel.InventoryViewModel
import com.android.myapplication.databinding.FragmentFixtureBinding
import com.google.android.material.tabs.TabLayout
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class Inventory : Fragment() {
    companion object {
        fun newinstance(): Inventory = Inventory()
    }

    private var selectedItemId: Int? = null  // 선택된 아이템 ID 저장 변수


    private var _binding: FragmentFixtureBinding? = null
    private val binding get() = _binding!!


    private val dataList1 = mutableListOf<Mc2.ResultData.Result>()
    private val ibsfixtureAdapter by lazy {
        IbsFixtureAdapter({ selectedItem -> showItemDetails(selectedItem) }, dataList1)
    }
    private val ibsabutmentAdapter: IbsAbutmentAdapter by lazy {
        IbsAbutmentAdapter { selectedItem -> showItemDetails(selectedItem) }
    }
    private val osstemfixturetsAdapter: OsstemFixtureTSAdapter by lazy {
        OsstemFixtureTSAdapter { selectedItem -> showItemDetails(selectedItem) }
    }

    private val osstemfixturessAdapter: OsstemFixtureSSAdapter by lazy {
        OsstemFixtureSSAdapter { selectedItem -> showItemDetails(selectedItem) }
    }


    private val osstemtsabutmentAdapter: OsstemTSAbutmentAdapter by lazy {
        OsstemTSAbutmentAdapter { selectedItem -> showItemDetails(selectedItem) }
    }
    private val osstemssabutmentAdapter: OsstemSSAbutmentAdapter by lazy {
        OsstemSSAbutmentAdapter { selectedItem -> showItemDetails(selectedItem) }
    }
    private val boneAdapter: BoneAdapter by lazy {
        BoneAdapter { selectedItem -> showItemDetails(selectedItem) }
    }

    private val viewModel: InventoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        //저장하시겠습니까? 만들기
        // 사용후 수량표기 (수량이 1감소하였습니다 -> 수량이 26 -> 25 로 감소 하였습니다.)

        //Save버튼을 눌렀을때
        binding.btnSave.setOnClickListener {
            val updatedItem = UpdateMc(
                id = binding.etId.text.toString().toIntOrNull(),
                category = binding.etCategory.text.toString(),
                code = binding.etCode.text.toString(),
                diameter = binding.etDiameter.text.toString().toDoubleOrNull(),
                height = binding.etHeight.text.toString().toDoubleOrNull(),
                name = binding.etName.text.toString(),
                quantity = binding.etQuantitiy.text.toString().toIntOrNull(),
                gheight = binding.etGLength.text.toString().toDoubleOrNull(),
                cuff = binding.etCuff.text.toString().toIntOrNull(),
                icode = binding.etIcode.text.toString(),
                update_at = getCurrentDateTime()

            )

            val insertItem = InsertMc(
                category = binding.etCategory.text.toString(),
                code = binding.etCode.text.toString(),
                diameter = binding.etDiameter.text.toString().toDoubleOrNull(),
                height = binding.etHeight.text.toString().toDoubleOrNull(),
                name = binding.etName.text.toString(),
                quantity = binding.etQuantitiy.text.toString().toIntOrNull(),
                gheight = binding.etGLength.text.toString().toDoubleOrNull(),
                cuff = binding.etCuff.text.toString().toIntOrNull(),
                icode = binding.etIcode.text.toString(),
                update_at = getCurrentDateTime()

            )


            if (updatedItem.id != null) {
                viewModel.updateMcdata(updatedItem)
            } // 🚀 서버에 업데이트 요청
            else {
                viewModel.insertMcdata(insertItem)
            }
            binding.Allview.visibility = View.VISIBLE
            binding.viewCliked.visibility = View.GONE

        }
    // 사용시 수량 -1
        binding.btnUsed.setOnClickListener {
            val updatedItem = UpdateMc(
                id = binding.etId.text.toString().toIntOrNull(),
                category = binding.etCategory.text.toString(),
                code = binding.etCode.text.toString(),
                diameter = binding.etDiameter.text.toString().toDoubleOrNull(),
                height = binding.etHeight.text.toString().toDoubleOrNull(),
                name = binding.etName.text.toString(),
                quantity = binding.etQuantitiy.text.toString().toIntOrNull()?.let { it - 1 }
                    ?: 0, // 수정
                gheight = binding.etGLength.text.toString().toDoubleOrNull(),
                cuff = binding.etCuff.text.toString().toIntOrNull(),
                icode = binding.etIcode.text.toString(),
                update_at = getCurrentDateTime()
            )

            viewModel.usedMcdata(updatedItem)


            binding.Allview.visibility = View.VISIBLE
            binding.viewCliked.visibility = View.GONE
            Toast.makeText(requireContext(), " 수량이 1 감소했습니다", Toast.LENGTH_LONG).show()
        }

        // Delete 하는부분
        binding.tvDelete.setOnClickListener {
            val id = selectedItemId

            if (id != null) {
                viewModel.deleteMcData(id)  // 선택된 아이템의 ID만 전달하여 삭제 요청
                Log.d("Inventory", "Deleting item with ID: $id")

                binding.Allview.visibility = View.VISIBLE
                binding.viewCliked.visibility = View.GONE
            } else {
                Log.e("Inventory", "No item selected for deletion")
            }
        }

        binding.btnAdd.setOnClickListener {
            binding.Allview.visibility = View.GONE
            binding.viewCliked.visibility = View.VISIBLE


            binding.apply {
                tvDelete.isVisible = false
                etId.isVisible = false
                etId.setText(null)
                etCode.setText("")
                etIcode.setText("")
                etName.setText("")
                etCategory.setText("")
                etCuff.setText("")
                etDiameter.setText("")
                etHeight.setText("")
                etGLength.setText("")
                etQuantitiy.setText("")

            }
        }

        binding.imgCodyBack.setOnClickListener {
            binding.Allview.isVisible = true
            binding.viewCliked.isVisible = false
        }
        //그림(api조회)
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
                            4 -> rvMaterial.adapter = osstemtsabutmentAdapter
                            5 -> rvMaterial.adapter = osstemssabutmentAdapter
                            6 -> rvMaterial.adapter = boneAdapter
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
                        4 -> binding.rvMaterial.adapter = osstemtsabutmentAdapter
                        5 -> binding.rvMaterial.adapter = osstemssabutmentAdapter
                        6 -> binding.rvMaterial.adapter = boneAdapter

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
                        4 -> binding.rvMaterial.adapter = osstemtsabutmentAdapter
                        5 -> binding.rvMaterial.adapter = osstemssabutmentAdapter
                        6 -> binding.rvMaterial.adapter = boneAdapter

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

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.laoutLoading.isVisible = isLoading  // 🚀 로딩 이미지 표시/숨김 true면 보이고 false면 안보임
        }

        Log.d("InventoryFragment", "Observing LiveData")
        viewModel.items.observe(viewLifecycleOwner) { newList ->

            ibsfixtureAdapter.setItems(newList)
            ibsabutmentAdapter.setItems(newList)
            osstemfixturetsAdapter.setItems(newList)
            osstemfixturessAdapter.setItems(newList)
            osstemtsabutmentAdapter.setItems(newList)
            osstemssabutmentAdapter.setItems(newList)
            boneAdapter.setItems(newList)

        }
    }


    private fun showItemDetails(item: Mc2.ResultData.Result) {
        _binding?.apply {
            Allview.visibility = View.GONE
            viewCliked.visibility = View.VISIBLE
            tvDelete.isVisible = true

            etId.setText(item.id.toString())
            etCategory.setText(item.category.toString())
            etDiameter.setText(item.diameter.toString())
            etCode.setText(item.code.toString())
            etIcode.setText(item.icode.toString())
            etName.setText(item.name)
            etHeight.setText(item.height.toString())
            etCuff.setText(item.cuff.toString())
            etGLength.setText(item.gheight.toString())
            etQuantitiy.setText(item.quantity.toString())

            selectedItemId = item.id  // 선택된 아이템의 ID 저장
        } ?: Log.e("ClickedView", "리스트가 클릭되었습니다")
    }

    private fun apiRequest() {
        viewModel.getMcData()
    }

    fun getCurrentDateTime(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        return current.format(formatter)
    }


}

