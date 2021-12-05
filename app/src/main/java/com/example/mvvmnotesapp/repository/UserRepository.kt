package com.example.mvvmnotesapp.repository

import android.content.Context
import android.provider.SyncStateContract.Helpers.insert
import androidx.lifecycle.LiveData
import com.example.mvvmnotesapp.db.User
import com.example.mvvmnotesapp.db.dbmaking
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserRepository {
    companion object{
        var userDatabase:dbmaking?=null

        private fun intialiseDB(context:Context): dbmaking?
        {
            return dbmaking.getInstance(context)!!
        }

        fun inserttheuser(context: Context,user:User)
        {
            userDatabase= intialiseDB(context)

            CoroutineScope(IO).launch {
                userDatabase!!.dao().insert(user)
            }
        }

        fun getuserdata(context: Context): LiveData<List<User>>
        {
            userDatabase= intialiseDB(context)
            return userDatabase!!.dao().getalldata()
        }
    }
}