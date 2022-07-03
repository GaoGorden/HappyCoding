package com.gorden.happycoding.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.gorden.happycoding.R
import com.gorden.happycoding.databinding.ActivityRecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityRecyclerViewBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.recyclerRv.adapter = DiffTypeAdapter()
        binding.recyclerRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }


}