package com.rildev.studentproject.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.rildev.studentproject.databinding.StudentListItemBinding
import com.rildev.studentproject.model.Student

class StudentListAdapter(val studentList:ArrayList<Student>)
    :RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>()
{
    class StudentViewHolder(var binding: StudentListItemBinding)
        :RecyclerView.ViewHolder(binding.root) //student list item itu cardnya :)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = StudentListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return StudentViewHolder(binding) //ini buat return class cardnya
    }

    override fun getItemCount(): Int {
      //utk mendapatkan brp jumlah item di dlm list
        return studentList.size;
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
       //ini buat kita atur klo diklik apa", isi value yg diubah dinamis disini
        holder.binding.txtID.text = studentList[position].id
        holder.binding.txtName.text = studentList[position].name

        holder.binding.btnDetail.setOnClickListener {
            //atur klo button detail diklik, maka dia pindah ke fragment mana
            val action = StudentListFragmentDirections.actionStudentDetail()
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }
}