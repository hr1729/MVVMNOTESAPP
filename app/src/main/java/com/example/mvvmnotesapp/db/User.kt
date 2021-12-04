package com.example.mvvmnotesapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userdata")
data class User(val name:String,val age:String){
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null
}
