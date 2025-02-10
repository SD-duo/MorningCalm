package com.android.myapplication.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.myapplication.Data.MainTabs
import com.android.myapplication.R
import com.android.myapplication.UI.Abutment
import com.android.myapplication.UI.Crown
import com.android.myapplication.UI.Fixture

class ViewPager2Adapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    private val fragments = ArrayList<MainTabs>().apply {
        add(
            MainTabs(
                fragment = Crown(),
                title = R.string.member_first,
                icon = R.drawable.teeth1,
            )
        )
        add(
            MainTabs(
                fragment = Fixture(),
                title = R.string.member_second,
                icon = R.drawable.teeth1,
            )
        )
        add(
            MainTabs(
                fragment = Abutment(),
                title = R.string.member_third,
                icon = R.drawable.teeth1,
            )
        )
    }

    // HomeFragment의 index를 찾아서 반환해줌
    fun findFragmentTabIndex(name: Int): Int {
        return when (name) {
            R.string.member_first -> {
                val element = fragments.find { it.title == name }
                fragments.indexOf(element)
            }

            R.string.member_second -> {
                val element = fragments.find { it.title == name }
                fragments.indexOf(element)
            }

            R.string.member_third -> {
                val element = fragments.find { it.title == name }
                fragments.indexOf(element)
            }

            else -> 0
        }
    }

    fun getTitme(position: Int): Int = fragments[position].title
    fun getIcon(position: Int): Int = fragments[position].icon

    override fun getItemCount(): Int = fragments.size
    override fun createFragment(position: Int): Fragment = fragments[position].fragment
}