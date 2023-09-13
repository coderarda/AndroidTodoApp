package com.example.androidtodopractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val taskArr = ArrayList<String>()
        val listView = findViewById<ListView>(R.id.list_view)
        val btn = findViewById<ImageButton>(R.id.btn_send)
        val adapter = TodoItemAdapter(this, R.layout.fragment_list_item, R.id.todo_item_root, taskArr)
        val textBox = findViewById<EditText>(R.id.text_box)
        listView.adapter = adapter
        btn.setOnClickListener {
            adapter.add(textBox.text.toString())
        }
    }
}