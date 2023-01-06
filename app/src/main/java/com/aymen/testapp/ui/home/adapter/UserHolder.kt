package com.aymen.testapp.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aymen.core.domain.user.User
import com.aymen.testapp.databinding.ItemUserBinding
import com.squareup.picasso.Picasso


class UserHolder private constructor(
    private val binding: ItemUserBinding,
    private val enableAction: (Int) -> Unit,
    private val context: Context,
    private val picasso: Picasso,
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(item: User) {
        binding.user = item
        binding.picasso = picasso


        binding.item.setOnClickListener {
            enableAction(adapterPosition)
        }


    }

    companion object {
        fun create(
            parent: ViewGroup,
            enableAction: (Int) -> Unit,
            context: Context,
            picasso: Picasso,
        ) =
            LayoutInflater.from(parent.context)
                .let { ItemUserBinding.inflate(it, parent, false) }
                .let { UserHolder(it, enableAction, context, picasso) }
    }
}