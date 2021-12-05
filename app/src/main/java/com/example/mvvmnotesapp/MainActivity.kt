package com.example.mvvmnotesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmnotesapp.db.User
import com.example.mvvmnotesapp.model.viewModel
import com.example.mvvmnotesapp.recylerview.UserAdpater
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dataadding.view.*
class MainActivity : AppCompatActivity() {
    lateinit var alertDialog: AlertDialog.Builder
    lateinit var dialog: AlertDialog
    lateinit var adpater: UserAdpater
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewmodel=ViewModelProvider(this).get(viewModel::class.java)
        recyclerview()
        viewmodel.getAllUserData(this).observe(this, Observer {
            adpater.diff?.submitList(it)
        })
        floatingActionButton.setOnClickListener {
            openDialog(viewmodel)
        }
    }
    private fun openDialog(viewModel: viewModel){
        alertDialog=AlertDialog.Builder(this)
        var lyt=LayoutInflater.from(this).inflate(R.layout.dataadding,null)
        dialog=alertDialog.create()
        dialog.setView(lyt)
        lyt.save.setOnClickListener {
            if(lyt.name1.text.isNotEmpty() and lyt.age1.text.isNotEmpty()){
                val name=lyt.name1.text.toString()
                val age=lyt.age1.text.toString()
                val user= User(name=name,age=age)
                viewModel.insert(this,user)
            }
            else{
                Toast.makeText(this,"please enter the name and age",Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }
    private fun recyclerview(){
        adpater=UserAdpater()
        rv.adapter=adpater
        rv.layoutManager=LinearLayoutManager(this)
    }
}