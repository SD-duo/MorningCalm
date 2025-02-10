package com.android.myapplication.Data

import androidx.fragment.app.Fragment

data class MainTabs(
    val fragment: Fragment, // 탭 fragment
    val title: Int, // 탭 제목
    val icon: Int, // 탭 아이콘
)