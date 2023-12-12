package com.example.collageapp.ui.admin.students

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.collageapp.AddStudentActivity
import com.example.collageapp.ChangeStudentActivity
import com.example.collageapp.database.CollegeDatabase
import com.example.collageapp.database.DatabaseDao
import com.example.collageapp.databinding.FragmentStudentsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StudentsFragment : Fragment() {
    private lateinit var binding: FragmentStudentsBinding
    private lateinit var adapter: StudentsAdapter
    private lateinit var databaseDao: DatabaseDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStudentsBinding.inflate(layoutInflater, container, false)
        databaseDao = CollegeDatabase.getDatabase(requireContext()).databaseDao()

        adapter = StudentsAdapter()
        binding.studentsRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.studentsRecycler.adapter = adapter

        //загрузка из бд в адаптер
        lifecycleScope.launch(Dispatchers.IO) {
            val studentsList = databaseDao.getStudentsWithSpeciality()
            withContext(Dispatchers.Main) {
                adapter.setData(studentsList)
            }
        }
        adapter.setOnDeleteClickListener { position ->
            onDeleteStudent(position)
        }

        //событие при нажатии на кнопку добавить студента
        binding.addStudentButton.setOnClickListener {
            startActivity(Intent(requireContext(), AddStudentActivity::class.java))
        }
        adapter.setOnChangeClickListener { position ->
            onChangeStudent(position)
        }

        return binding.root
    }

    private fun onDeleteStudent(position: Int) {
        lifecycleScope.launch(Dispatchers.IO) {
            val student = adapter.getStudentsList().getOrNull(position)?.student
            student?.let {
                databaseDao.deleteStudent(it)
                val updatedStudents = databaseDao.getStudentsWithSpeciality()
                withContext(Dispatchers.Main) {
                    adapter.setData(updatedStudents)
                }
            }
        }
    }

    private fun onChangeStudent(position: Int) {
        val student = adapter.getStudentsList().getOrNull(position)
        val intent = Intent(requireContext(), ChangeStudentActivity::class.java)
        intent.putExtra("studentId", student?.student?.studentId.toString())
        startActivity(intent)
    }

}
