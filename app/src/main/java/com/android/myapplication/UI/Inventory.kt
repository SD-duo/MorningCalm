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
import com.android.myapplication.ViewModel.InventoryViewModel
import com.android.myapplication.databinding.FragmentFixtureBinding
import com.google.android.material.tabs.TabLayout



class Inventory : DialogFragment() {
    companion object {
        fun newinstance(): Inventory = Inventory()
    }

    private var _binding: FragmentFixtureBinding? = null
    private val binding get() = _binding!!
    private lateinit var ibsfixtureAdapter: IbsFixtureAdapter

    private val viewModel: InventoryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
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

        val tabLayout: TabLayout = binding.tabLayout2
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    viewModel.filterItems(it.position)  // 탭 선택 시 해당하는 카테고리로 필터링
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        dialog?.window?.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }

    private fun initView() {
        binding.imgCodyBack.setOnClickListener {
            dismiss()
        }
    }

    private fun initViewModel() = binding.apply {
        ibsfixtureAdapter = IbsFixtureAdapter()
        rvMaterial.adapter = ibsfixtureAdapter
        rvMaterial.layoutManager = LinearLayoutManager(context)
        // ViewModel의 데이터 변화 감지 후 어댑터에 업데이트
        viewModel.items.observe(viewLifecycleOwner) { newList ->
            Log.d("InventoryFragment", "Observer triggered! New list size: ${newList.size}") // 확인용 로그 추가
            ibsfixtureAdapter.setItems(newList)
        }
        // API 호출 (최초 한 번만 호출)
        viewModel.getMcData()
    }
}