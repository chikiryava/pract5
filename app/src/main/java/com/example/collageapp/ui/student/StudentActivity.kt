package com.example.collageapp.ui.student

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.collageapp.R
import com.example.collageapp.database.CollegeDatabase
import com.example.collageapp.database.DatabaseDao
import com.example.collageapp.databinding.ActivityStudentBinding

class StudentActivity : AppCompatActivity() {
    private lateinit var binding:ActivityStudentBinding
    private lateinit var databaseDao: DatabaseDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        databaseDao = CollegeDatabase.getDatabase(this).databaseDao()
        binding.searchTeacherBt.setOnClickListener{
            if(binding.searchTeacherByNameEd.text.isEmpty()){
                Toast.makeText(this@StudentActivity, "Вы не ввели имя", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(this@StudentActivity,TeacherInfoActivity::class.java)
            intent.putExtra("teacherName",binding.searchTeacherByNameEd.text.toString())
            startActivity(intent)
        }
    }
}