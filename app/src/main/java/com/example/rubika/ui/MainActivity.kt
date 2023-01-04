package com.example.rubika.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rubika.R
import com.example.rubika.databinding.ActivityMainBinding
import com.example.rubika.utility.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layoutId: Int get() = R.layout.activity_main


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}