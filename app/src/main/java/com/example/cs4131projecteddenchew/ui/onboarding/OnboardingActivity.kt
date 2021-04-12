package com.example.cs4131projecteddenchew.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.cs4131projecteddenchew.R
import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_onboarding.*


class OnboardingActivity : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        // Initialize ViewPager view
        val viewPager = findViewById<ViewPager>(R.id.viewPagerOnBoarding)
        // create ViewPager adapter
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)

        //TODO actually make the designs for the onboarding
        //TODO Login for onboarding four

        // Add All Fragments to ViewPager
        viewPagerAdapter.addFragment(FragmentOnboardingOne())
        viewPagerAdapter.addFragment(FragmentOnboardingTwo())
        viewPagerAdapter.addFragment(FragmentOnboardingThree())
        viewPagerAdapter.addFragment(FragmentOnboardingFour())

        // Set Adapter for ViewPager
        viewPager.adapter = viewPagerAdapter

        // Setup dot's indicator
        tabLayout = findViewById<TabLayout>(R.id.tabLayoutIndicator)
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.setBackgroundResource(R.color.grey_90)
    }

    public fun showTabLayout(){
        tabLayoutIndicator.setVisibility(View.VISIBLE)
    }

    public fun removeTabLayout(){
        tabLayoutIndicator.setVisibility(View.GONE)
    }


    // ViewPager Adapter class
    internal class ViewPagerAdapter(supportFragmentManager: FragmentManager?) :
        FragmentPagerAdapter(supportFragmentManager!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        private val mList: MutableList<Fragment> = ArrayList()
        override fun getItem(i: Int): Fragment {
            return mList[i]
        }

        override fun getCount(): Int {
            return mList.size
        }

        fun addFragment(fragment: Fragment) {
            mList.add(fragment)
        }
    }
}