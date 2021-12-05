package com.example.mvvmnotesapp.db


import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "userdata")
data class User(@PrimaryKey()val name:String, val age:String){

}
