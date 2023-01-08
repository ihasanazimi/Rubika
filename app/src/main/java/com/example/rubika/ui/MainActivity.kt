package com.example.rubika.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.rubika.R
import com.example.rubika.databinding.ActivityMainBinding
import com.example.rubika.repository.datasource.db.RoomDB
import com.example.rubika.repository.datasource.fake_data.FakeData
import com.example.rubika.utility.base.BaseActivity
import com.example.rubika.utility.extentions.changeStatusBarColor
import kotlinx.coroutines.DelicateCoroutinesApi

@DelicateCoroutinesApi
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layoutId: Int get() = R.layout.activity_main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changeStatusBarColor(window,R.color.gray)
        // create posts nad save they`s on DB
        val items = RoomDB.database!!.postDao().allPosts()
        if (items.isEmpty()) lifecycleScope.launchWhenCreated { FakeData.generateFakePost() }
    }
}