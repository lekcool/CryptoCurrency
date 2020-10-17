package com.lkdev.cryptocurrency.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lkdev.cryptocurrency.R

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction()
            .replace(R.id.containerLayout, MainFragment.newInstance())
            .commit()
    }
}