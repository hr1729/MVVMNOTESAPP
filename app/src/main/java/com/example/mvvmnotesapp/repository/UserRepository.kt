package com.example.mvvmnotesapp.repository

import android.content.Context
import android.provider.SyncStateContract.Helpers.insert
import androidx.lifecycle.LiveData
import com.example.mvvmnotesapp.db.User
import com.example.mvvmnotesapp.db.dbmaking
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UserRepository {
    companion object{
        fun initalize(context: Context):dbmaking?{
            return dbmaking?.getInstance(context)
        }
        fun inserttheuser(user: User,context: Context){
            GlobalScope.launch(Dispatchers.IO) {
                var db= initalize(context)
                 db?.dao()?.insert(user)

            }
        }

        fun getuserdata(context: Context): LiveData<List<User>>? {
            var db= initalize(context)
            return db?.dao()?.getalldata()
        }
    }
}