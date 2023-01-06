package com.aymen.testapp.ui.home.adapter

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aymen.core.domain.user.User
import com.squareup.picasso.Picasso

class UserAdapter(
    private val enableAction: (Int) -> Unit,
    private val context: Context,
    private val picasso: Picasso
) : RecyclerView.Adapter<UserHolder>() {

    private val userList = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserHolder.create(parent, enableAction,context,picasso)


    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount() =
        userList.size


    fun submitList(newTypes: List<User>) {
        DiffCallback(userList, newTypes)
            .let {
                DiffUtil.calculateDiff(it)
            }
            .also {
                userList.clear()
                userList.addAll(newTypes)
                it.dispatchUpdatesTo(this)
            }

    }

    private class DiffCallback(
        private val old: List<User>,
        private val new: List<User>

    ) : DiffUtil.Callback() {

        override fun getOldListSize() = old.size

        override fun getNewListSize() = new.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            (old[oldItemPosition] == new[newItemPosition])

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            old[oldItemPosition].phone == new[newItemPosition].phone
    }

}