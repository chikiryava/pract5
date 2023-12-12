package com.example.collageapp.ui.student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.collageapp.R
import com.example.collageapp.database.CollegeDatabase
import com.example.collageapp.database.DatabaseDao
import com.example.collageapp.databinding.ActivityStudentInfoBinding
import com.example.collageapp.databinding.ActivityTeacherBinding
import com.example.collageapp.databinding.ActivityTeacherInfoBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TeacherInfoActivity : AppCompatActivity() {
    private lateinit var binding:ActivityTeacherInfoBinding
    private lateinit var databaseDao: DatabaseDao
    private lateinit var adapter:TeachersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeacherInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        databaseDao= CollegeDatabase.getDatabase(this).databaseDao()
        adapter = TeachersAdapter()
        binding.teacherInfoRecycler.layoutManager = LinearLayoutManager(this)
        binding.teacherInfoRecycler.adapter = adapter
        val teacherName = intent.getStringExtra("teacherName")
        if(!teacherName.isNullOrEmpty()){
            lifecycleScope.launch(Dispatchers.IO) {
                val teacherInfo = databaseDao.searchTeachersByName(teacherName)
                withContext(Dispatchers.Main){
                    adapter.setData(teacherInfo)
                }
            }
        }
        else{
            Toast.makeText(this@TeacherInfoActivity,"Такого преподавателя нет", Toast.LENGTH_SHORT).show()
        }
    }
}