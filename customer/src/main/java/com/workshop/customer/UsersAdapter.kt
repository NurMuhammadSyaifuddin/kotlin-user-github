package com.workshop.customer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.workshop.customer.databinding.ItemUserBinding

class UsersAdapter: RecyclerView.Adapter<UsersAdapter.UserViewHolder>() {

    private val list = ArrayList<User>()

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){

        this.onItemClickCallback = onItemClickCallback

    }

    fun setListUsers(user: ArrayList<User>){

        list.clear()
        list.addAll(user)
        notifyDataSetChanged()

    }

    inner class UserViewHolder(binding: ItemUserBinding): RecyclerView.ViewHolder(binding.root){

        val binding = binding
        fun bind(user: User){
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(user)
            }

            binding.apply {
                Glide.with(itemView)
                        .load(user.avatar_url)
                        .into(imgUser)

                tvUser.text = user.login
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickCallback{
        fun onItemClicked(data: User)
    }

}