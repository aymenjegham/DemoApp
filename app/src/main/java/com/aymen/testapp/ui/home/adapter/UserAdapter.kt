package com.aymen.testapp.ui.home.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.aymen.core.domain.user.User
import com.squareup.picasso.Picasso

class UserAdapter(
    private val enableAction: (Int) -> Unit,
    private val context: Context,
    private val picasso: Picasso,
) : PagingDataAdapter<User, UserHolder>(DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserHolder.create(parent, enableAction, context, picasso)


    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        getItem(position)?.let { user ->
            holder.bind(user)
        }
    }
    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<User>() {

            override fun areItemsTheSame(oldItem: User, newItem: User) =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: User, newItem: User) =
                oldItem != newItem

        }

    }

}