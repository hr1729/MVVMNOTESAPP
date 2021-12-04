package com.example.mvvmnotesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
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
    lateinit var adapter:UserAdpater
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      val  viewModel=ViewModelProvider(this).get(viewModel::class.java)
        recyclerview()
        viewModel.getAllUserData(this).observe(this, Observer {
            adapter.diff?.submitList(it)
        })

        floatingActionButton.setOnClickListener {
            openDialog(viewModel)
        }
    }
    private fun openDialog(viewModel: viewModel){
        alertDialog=AlertDialog.Builder(this)
        var lyt=LayoutInflater.from(this).inflate(R.layout.dataadding,null)
        dialog=alertDialog.create()
        dialog.setView(lyt)
        lyt.save.setOnClickListener {
            if(lyt.name1.text.isNotEmpty() and lyt.age1.text.isNotEmpty()){
                val user= User(lyt.name1.text.toString(),lyt.age1.text.toString())
                viewModel.insert(this,user)
            }
            else{
                Toast.makeText(this,"please enter the name and age",Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }
    private fun recyclerview(){
        adapter= UserAdpater()
        rv.adapter=adapter
        rv.layoutManager= LinearLayoutManager(this)

    }
}