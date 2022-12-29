package com.sun.android.ex_slide11

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sun.android.R
import com.sun.android.databinding.ItemWordBinding
import com.sun.android.ex_slide11.RecyclerViewExActivity.Companion.mWordList
import java.util.*


class WordListAdapter(val mWordListRecyclerView: LinkedList<String>) :
    RecyclerView.Adapter<WordListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemWordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mWordListRecyclerView.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemWord?.text = mWordListRecyclerView[position]
    }

    class ViewHolder(itemView: ItemWordBinding) : RecyclerView.ViewHolder(itemView.root) {
        var itemWord: TextView? = null

        init {
            itemWord = itemView.textViewWord
            itemWord?.setOnClickListener() {
                val position: Int = adapterPosition
                itemView.textViewWord.setText(R.string.click)
                itemView.textViewWord.append(" " + mWordList[position])
            }
        }
    }

}
