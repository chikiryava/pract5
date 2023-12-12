package com.example.collageapp.ui.student

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.collageapp.R
import com.example.collageapp.database.Teacher
import com.example.collageapp.ui.admin.teachers.TeacherViewHolder

class TeachersAdapter: RecyclerView.Adapter<TeachersViewHolder>() {

    private var teachersList: List<Teacher> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeachersViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_teacher_info, parent, false)
        return TeachersViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TeachersViewHolder, position: Int) {
        holder.bind(teachersList[position])
    }

    override fun getItemCount(): Int = teachersList.size

    fun setData(newTeachers: List<Teacher>) {
        teachersList = newTeachers
        notifyDataSetChanged()
    }
    fun getTeachersList(): List<Teacher> {
        return teachersList
    }
}