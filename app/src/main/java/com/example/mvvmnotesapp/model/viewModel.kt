package com.example.mvvmnotesapp.model

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmnotesapp.db.User
import com.example.mvvmnotesapp.repository.UserRepository

class viewModel: ViewModel(){
    fun insert(context: Context, user: User)
    {
        UserRepository.inserttheuser(context,user)
    }

    fun getAllUserData(context: Context): LiveData<List<User>>
    {
        return UserRepository.getuserdata(context)
    }

}