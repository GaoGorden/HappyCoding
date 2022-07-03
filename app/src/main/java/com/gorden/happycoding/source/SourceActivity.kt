package com.gorden.happycoding.source

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.gorden.happycoding.databinding.ActivitySourceBinding
import com.gorden.happycoding.okhttp.OkhttpActivity

class SourceActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivitySourceBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.root.addView(TextView(this).apply {
            text = "Gorden"
        })

        binding.sourceJumpActivity.setOnClickListener {
            startActivity(Intent(this, SourceActivity::class.java))
        }

        parent?.let {
            Toast.makeText(this, parent.javaClass.name, Toast.LENGTH_LONG).show()
        }

//        throw IllegalArgumentException("我们来看一下调用流程！")


    }
}