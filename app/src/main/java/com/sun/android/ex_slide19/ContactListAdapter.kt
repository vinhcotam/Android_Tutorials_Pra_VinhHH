package com.sun.android.ex_slide19

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sun.android.databinding.ItemContactBinding

class ContactListAdapter(items: List<ContactClass>) : RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {
    private val listContact = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listContact.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listContact[position])

    }

    class ViewHolder(private val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {
        private var contact: ContactClass? = null

        init {
            binding.imageButtonCall.setOnClickListener {
                contact?.let {
                    val intentCall = Intent(Intent.ACTION_DIAL)
                    intentCall.data = Uri.parse("tel:${it.number}")
                    binding.root.context.startActivity(intentCall)
                }
            }
        }

        fun bind(items: ContactClass) {
            contact = items
            binding.textViewNameContact.text = items.name
            binding.textViewContactNumber.text = items.number
        }
    }
}
