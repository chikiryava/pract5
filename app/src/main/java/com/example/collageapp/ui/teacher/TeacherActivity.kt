package com.example.collageapp.ui.teacher

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.collageapp.R
import com.example.collageapp.database.CollegeDatabase
import com.example.collageapp.database.DatabaseDao
import com.example.collageapp.databinding.ActivityTeacherBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TeacherActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTeacherBinding
    private lateinit var databaseDao: DatabaseDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeacherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        databaseDao = CollegeDatabase.getDatabase(this).databaseDao()
        binding.searchStudentBt.setOnClickListener {
            if (binding.searchStudentByNameEd.text.isEmpty()) {
                Toast.makeText(this@TeacherActivity, "Вы не ввели имя", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent = Intent(this@TeacherActivity, StudentInfoActivity::class.java)
            intent.putExtra("studentName", binding.searchStudentByNameEd.text.toString())
            startActivity(intent)

        }
    }
}
