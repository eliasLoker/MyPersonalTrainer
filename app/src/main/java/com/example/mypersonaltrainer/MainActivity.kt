package com.example.mypersonaltrainer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mypersonaltrainer.start.StartFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_activity_container)

        if (fragment == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_activity_container, StartFragment.newInstance())
                .commit()
        }

    }
}
