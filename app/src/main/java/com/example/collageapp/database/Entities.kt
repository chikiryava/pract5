package com.example.collageapp.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "speciality")
data class Speciality(
    @PrimaryKey(autoGenerate = false)
    val speciality: String
)

@Entity(tableName = "students")
data class Student(
    @PrimaryKey(autoGenerate = true)
    val studentId: Long = 0,
    val name: String,
    val birthday: String,
    var speciality: String,
    val course: Int,
    val isBudget: Boolean,
    val photo: String
)
@Entity(
    tableName = "teachers",
    foreignKeys = [
        ForeignKey(
            entity = Speciality::class,
            parentColumns = ["speciality"],
            childColumns = ["speciality"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Teacher(
    @PrimaryKey(autoGenerate = true)
    val teacherId: Long = 0,
    val name: String,
    val salary: Double,
    val speciality: String, // Новое поле для связи с таблицей Speciality
    val hoursPerYear: Int // Новая колонка "количество часов"
)


data class StudentWithSpeciality(
    @Embedded val student: Student,
    val studentSpeciality: String
)





