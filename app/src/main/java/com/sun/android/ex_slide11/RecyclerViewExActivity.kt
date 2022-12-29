package com.sun.android.ex_slide11

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sun.android.databinding.ActivityRecyclerViewExBinding
import java.util.*
import kotlin.collections.ArrayList

class RecyclerViewExActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityRecyclerViewExBinding.inflate(layoutInflater)
    }
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<WordListAdapter.ViewHolder>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        for (i in 0..19) {
            mWordList.addLast("Word $i")

        }
        layoutManager = LinearLayoutManager(this)
        binding.recyclerViewWordList.layoutManager = layoutManager
        adapter = WordListAdapter(mWordList)
        binding.recyclerViewWordList.adapter = adapter
    }

    fun addWordClick(view: View) {
        val wordListSize: Int = mWordList.size
        mWordList.addLast("+ Word $wordListSize")
        binding.recyclerViewWordList.adapter?.notifyItemInserted(wordListSize);
        binding.recyclerViewWordList.smoothScrollToPosition(wordListSize);
    }

    companion object {
        var mWordList: LinkedList<String> = LinkedList()
    }

}
