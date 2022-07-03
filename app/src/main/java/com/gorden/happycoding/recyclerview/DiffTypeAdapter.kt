package com.gorden.happycoding.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gorden.happycoding.databinding.RecyclerDifftypeButtonBinding
import com.gorden.happycoding.databinding.RecyclerDifftypeTextBinding

class DiffTypeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class TextViewHolder(binding: RecyclerDifftypeTextBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var text = binding.recyclerDifftypeText
    }

    class ButtonViewHolder(binding: RecyclerDifftypeButtonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val button = binding.recyclerDifftypeButton
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType % 2 == 0) {
            val binding = RecyclerDifftypeTextBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            TextViewHolder(binding)
        } else {
            val binding = RecyclerDifftypeButtonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            ButtonViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position % 2 == 0) {
            holder as TextViewHolder
            holder.text.text = "this is a text"
        } else {
            holder as ButtonViewHolder
            holder.button.text = "Button"
        }
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}