package com.example.mvvmnotesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dataadding.view.*

class MainActivity : AppCompatActivity() {
    lateinit var alertDialog: AlertDialog.Builder
    lateinit var dialog: AlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        floatingActionButton.setOnClickListener {
            openDialog()
        }
    }
    private fun openDialog(){
        alertDialog=AlertDialog.Builder(this)
        var lyt=LayoutInflater.from(this).inflate(R.layout.dataadding,null)
        dialog=alertDialog.create()
        dialog.setView(lyt)
        lyt.save.setOnClickListener {
            if(lyt.name1.text.isNotEmpty() and lyt.age1.text.isNotEmpty()){
            }
            else{
                Toast.makeText(this,"please enter the name and age",Toast.LENGTH_SHORT).show()
            }
        }
        dialog.show()
    }
}