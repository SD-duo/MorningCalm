package com.android.myapplication.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.android.myapplication.Adapter.ViewPager2Adapter
import com.android.myapplication.R
import com.android.myapplication.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {


    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewPagerAdapter by lazy { ViewPager2Adapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        viewPager2State()
        setupTabIcons()

        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager2
        ) { tab, position ->
            tab.setText(viewPagerAdapter.getTitme(position))
            tab.setIcon(viewPagerAdapter.getIcon(position))
        }.attach()

        pageChangeCallBack()
        binding.viewPager2.offscreenPageLimit = viewPagerAdapter.itemCount











        // 총 비용 업데이트 함수


    }
    private fun viewPager2State() {
        binding.viewPager2.apply {
            adapter = viewPagerAdapter
            setCurrentItem(
                viewPagerAdapter.findFragmentTabIndex(R.string.one),
                false
            )
            setUserInputEnabled(false) //  viewpager 의 슬라이드를 담당하는 메서드
        }
    }
    private fun pageChangeCallBack() {
        binding.viewPager2.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            var currentState = 0
            var currentPosition = 0

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                if (currentState == ViewPager2.SCROLL_STATE_DRAGGING && currentPosition == position) {
                    if (currentPosition == 0) binding.viewPager2.currentItem = 4
                    else if (currentPosition == 4) binding.viewPager2.currentItem = 0
                }
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                currentPosition = position
                super.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                currentState = state
                super.onPageScrollStateChanged(state)
            }
        })
    }
    /**
     * 작성자:윤동현
     * 내용: 홈 탭에서 다른 탭으로 이동시 사용
     * ViewPagerAdapter에서 Tab 이름을 검색하여 나온
     * index값을 가져와 Tab 현재위치를 바꿔줌
     * */
    fun moveTabFragment(title: Int) {
        val index = viewPagerAdapter.findFragmentTabIndex(title)
        binding.viewPager2.setCurrentItem(index, false)
    }
    private fun setupTabIcons() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val icon = when (tab?.position) {
                    0 -> R.drawable.teeth1
                    1 -> R.drawable.teeth1
                    2 -> R.drawable.teeth1
                    else -> R.drawable.teeth2
                }
                tab?.setIcon(icon)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                //반전이미지 만드는법 -> 성환님한테 피그마 물어보기
                val icon = when (tab?.position) {
                    0 -> R.drawable.teeth2
                    1 -> R.drawable.teeth2
                    2 -> R.drawable.teeth2

                    else -> R.drawable.teeth2
                }
                tab?.setIcon(icon)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                val icon = when (tab?.position) {
                    0 -> R.drawable.teeth2
                    1 -> R.drawable.teeth2
                    2 -> R.drawable.teeth2
                    else -> R.drawable.teeth2
                }
                tab?.setIcon(icon)
            }
        })
    }
}
