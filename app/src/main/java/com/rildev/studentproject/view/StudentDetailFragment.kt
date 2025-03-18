package com.rildev.studentproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.rildev.studentproject.R
import com.rildev.studentproject.databinding.FragmentStudentDetailBinding
import com.rildev.studentproject.model.Student
import com.rildev.studentproject.viewmodel.DetailViewModel
import com.rildev.studentproject.viewmodel.ListViewModel

class StudentDetailFragment : Fragment() {

    //coba buat tampilin di student detail hasil dari detailview model
    private lateinit var  viewModel:DetailViewModel
    private lateinit var binding:FragmentStudentDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStudentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //inisialisasi view model
        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.fetch() //ambil data dari view model
        viewModel.studentLD.observe(viewLifecycleOwner, Observer //buat ambil live data
        { //it itu sesuai sama type datanya studentld
            if (it != null) {
                binding.txtID.setText(it.id.toString())
                binding.txtName.setText(it.name.toString())
                binding.txtBOD.setText(it.bod.toString())
                binding.txtPhone.setText(it.phone.toString())
            }
        })
    }
}