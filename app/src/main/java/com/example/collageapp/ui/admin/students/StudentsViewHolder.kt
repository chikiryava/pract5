package com.example.collageapp.ui.admin.students

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.collageapp.R
import com.example.collageapp.database.StudentWithSpeciality
import com.squareup.picasso.Picasso

class StudentsViewHolder(
    itemView: View,
    private val onDeleteClickListener: ((Int) -> Unit)? = null,
    private val onChangeClickListener:((Int)->Unit)? = null
) : RecyclerView.ViewHolder(itemView) {
    private val studentNameTextView: TextView = itemView.findViewById(R.id.studentNameTextView)
    private val studentBirthdayTextView: TextView = itemView.findViewById(R.id.studentBirthdayTextView)
    private val studentCoursesTextView: TextView = itemView.findViewById(R.id.studentCoursesTextView)
    private val deleteStudentButton: Button = itemView.findViewById(R.id.deleteStudentButton)
    private val studentSpeciality:TextView = itemView.findViewById(R.id.specialityNameTextView)
    private val changeSpecialityButton:Button = itemView.findViewById(R.id.editStudentButton)
    private val budgetTextView:TextView = itemView.findViewById(R.id.budgetTextView)
    private val studentImage:ImageView = itemView.findViewById(R.id.studentImage)

    fun bind(studentWithSpeciality: StudentWithSpeciality, position: Int) {
        studentNameTextView.text = "Студент: ${studentWithSpeciality.student.name}"
        studentBirthdayTextView.text = "Дата рождения:${studentWithSpeciality.student.birthday}"
        studentCoursesTextView.text = "Курс: ${studentWithSpeciality.student.course}"
        studentSpeciality.text = "Специальность: ${studentWithSpeciality.studentSpeciality}"
        if(studentWithSpeciality.student.isBudget){
            budgetTextView.text = "Бюджет: Да"
        }
        else{
            budgetTextView.text = "Бюджет: Нет"
        }
        Picasso.get()
            .load(studentWithSpeciality.student.photo)
            .placeholder(R.drawable.placeholder_poster) // Replace with your placeholder image resource
            .error(R.drawable.placeholder_poster) // Replace with your error image resource
            .into(studentImage)

        // Handle delete button click
        deleteStudentButton.setOnClickListener {
            onDeleteClickListener?.invoke(position)
        }
        changeSpecialityButton.setOnClickListener{
            onChangeClickListener?.invoke(position)
        }
    }
}
