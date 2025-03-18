package com.rildev.studentproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rildev.studentproject.model.Student

//classnya hrs diextend ke viewmodel
class DetailViewModel:ViewModel(){
    //ini buat menampung hasil LD detail dari 1 mhs aja
    val studentLD = MutableLiveData<Student>()


    //ini function untuk menampilkan data yg dipilih
    //data yang mau diupdate ditampilkan valuenya
    fun fetch() {
        val student1 = Student("16055","Nonie","1998/03/28","5718444778",
            "http://dummyimage.com/75x100.jpg/cc0000/ffffff")
        studentLD.value = student1 //value dari studentld ini setara sm student1
    }
}