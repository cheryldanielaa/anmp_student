package com.rildev.studentproject.model

import com.google.gson.annotations.SerializedName

data class Student(
    var id:String?, //mksdnya adalah kita tulis id dgn var id, serialized name adlh nama kolom di db
    //yg pny function sama
    @SerializedName("student_name")
    var name:String?,
    @SerializedName("birth_of_date")
    var bod:String?,
    var phone:String?,
    @SerializedName("photo_url")
    var photoUrl:String?
)

