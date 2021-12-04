package com.example.mvvmnotesapp.recylerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmnotesapp.R
import com.example.mvvmnotesapp.db.User
import kotlinx.android.synthetic.main.each_row.view.*

class UserAdpater:RecyclerView.Adapter<UserAdpater.ViewHolder>() {
    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view)
    private val diffCallBack=object: DiffUtil.ItemCallback<User>(){
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.name==newItem.name
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem==newItem
        }

    }
    val diff= AsyncListDiffer(this,diffCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdpater.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.each_row, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data=diff.currentList[position]
        holder.itemView.apply {
            name.text=data.name
            age.text=data.age.toString()
        }
    }

    override fun getItemCount(): Int {
        return diff.currentList.size
    }
}