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
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.myapplication.Service.RetrofitModule
import com.android.myapplication.Adapter.IbsFixtureAdapter
import com.android.myapplication.Data.Mc
import com.android.myapplication.databinding.FragmentFixtureBinding
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Inventory : DialogFragment() {
    companion object {

        fun newinstance():Crown = Crown()

    }

    private var _binding: FragmentFixtureBinding? = null
    private val binding get() = _binding!!
    private lateinit var ibsfixtureAdapter: IbsFixtureAdapter

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
        getMcData()


        //텝레이아웃 사용하는 코드
        //텝레이아웃초기화

        val tabLayout: TabLayout = binding.tabLayout2
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    when (it.position) {
                        0 -> binding.rvMaterial.adapter = ibsfixtureAdapter
//                        1 -> binding.rvMaterial.adapter = ibsabutmentAdapter
//                        2 -> binding.rvMaterial.adapter = osstemfixtureAdapter
//                        3 -> binding.rvMaterial.adapter = osstemabutmentAdapter
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.let {
                    when (it.position) {
                        0 -> binding.rvMaterial.adapter = ibsfixtureAdapter
//                        1 -> binding.rvMaterial.adapter = ibsabutmentAdapter
//                        2 -> binding.rvMaterial.adapter = osstemfixtureAdapter
//                        3 -> binding.rvMaterial.adapter = osstemabutmentAdapter
                    }

                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                tab?.let {
                    when (it.position) {
                        0 -> binding.rvMaterial.adapter = ibsfixtureAdapter
//                        1 -> binding.rvMaterial.adapter = ibsabutmentAdapter
//                        2 -> binding.rvMaterial.adapter = osstemfixtureAdapter
//                        3 -> binding.rvMaterial.adapter = osstemabutmentAdapter
                    }
                }
            }

        })

    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
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
    }
    private fun getMcData() {
        val service = RetrofitModule.createSonnyApiService()
        val call: Call<Mc> = service.getMcdata()

        call.enqueue(object : Callback<Mc> {
            override fun onResponse(call: Call<Mc>, response: Response<Mc>) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    Log.d("Test","${response.body()}")

                    // responseBody가 null일 경우 빈 리스트 반환
                    val itemList = responseBody ?: emptyList<Mc.McItem>()

                    // 어댑터에 데이터 설정
                    ibsfixtureAdapter.setItems(itemList)

                } else {
                    Log.d("responseError", "Error: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Mc>, t: Throwable) {
                Log.d("responseError", "Failure: ${t.message}")
            }
        })
    }


}