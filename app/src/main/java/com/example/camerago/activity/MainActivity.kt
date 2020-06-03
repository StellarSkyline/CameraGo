package com.example.camerago.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.camerago.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        init()
    }

    private fun init() {
      button_fab.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.button_fab -> { }
        }
    }
}