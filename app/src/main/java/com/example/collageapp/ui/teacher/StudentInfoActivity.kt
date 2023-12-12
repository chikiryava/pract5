package com.example.collageapp.ui.teacher

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.collageapp.R
import com.example.collageapp.database.CollegeDatabase
import com.example.collageapp.database.DatabaseDao
import com.example.collageapp.database.Student
import com.example.collageapp.database.StudentWithSpeciality
import com.example.collageapp.databinding.ActivityStudentInfoBinding
import com.example.collageapp.ui.admin.students.StudentsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudentInfoActivity : AppCompatActivity() {
    private lateinit var binding:ActivityStudentInfoBinding
    private lateinit var databaseDao: DatabaseDao
    private lateinit var adapter: StudentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        databaseDao = CollegeDatabase.getDatabase(this).databaseDao()
        adapter = StudentAdapter()
        binding.studentInfoRecycler.layoutManager = LinearLayoutManager(this)
        binding.studentInfoRecycler.adapter = adapter

        val studentName = intent.getStringExtra("studentName")
        if(!studentName.isNullOrEmpty()){
            lifecycleScope.launch(Dispatchers.IO){
                val studentsList = databaseDao.getStudentsWithSpecialityByName(studentName.toString())
                Log.d("error",studentName.toString())
                Log.d("error",studentsList.toString())
                withContext(Dispatchers.Main){
                    adapter.setData(studentsList)
                }
            }
        }
        else{
            Toast.makeText(this@StudentInfoActivity,"Такого студента нет",Toast.LENGTH_SHORT).show()
        }
    }
}