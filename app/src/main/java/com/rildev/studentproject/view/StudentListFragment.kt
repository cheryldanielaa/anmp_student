package com.rildev.studentproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rildev.studentproject.databinding.FragmentStudentListBinding
import com.rildev.studentproject.viewmodel.ListViewModel

class StudentListFragment : Fragment() {
    private val adapter  = StudentListAdapter(arrayListOf()) //arrray list kosongan
    private lateinit var binding: FragmentStudentListBinding
    //buat objek viewmodel biar bisa pake method refreshnya
    private lateinit var viewModel: ListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //onviewcreated biasanya dipake waktu uinya sdh keloaddd,
        // jadi semua logic programming ditulis disitu
        //viewmodelprovider itu functionnya
        //listviewmodel >> panggil nama classnya
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()
        //init recycler view
        //fungsinya ketika listnya punya isi, maka dia bakalan panggil isi"nya
        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = adapter

        //ini buat refresh
        binding.swipeRefresh.setOnRefreshListener {
            //tdk pakai yg di ppt karena dia disembunyikan jd gone
            viewModel.refresh()
            //is refreshing jd false >> untuk menghilangkan loadingnya swipe refresh
            binding.swipeRefresh.isRefreshing = false //dia cmn muncul bntr bcs loadnya cepet
        }


        observeViewModel()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStudentListBinding.inflate(inflater,container,false)
        return binding.root
    }
    fun observeViewModel() {
        //fungsi observe adalah untuk dia mengsubscribe variabel mana yg mau diperhatiin
        viewModel.studentsLD.observe(viewLifecycleOwner,
            Observer {
            adapter.updateStudentList(it) //it itu refers to array list of student (tipe datanya)
        })

        //jika loading ld bernilai true, maka tampilkan progress bar yg muter-muter itu
        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it == true) { //jika loading bernilai true, maka muncul
                binding.progressLoad.visibility = View.VISIBLE
            } else { //jika nilainya false, maka hilang
                binding.progressLoad.visibility = View.GONE
            }
        })

        viewModel.errorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) { //jika error bernilai true, error ld muncul
                binding.txtError.visibility = View.VISIBLE
            }
            else { //jika nilainya false, maka hilang
                binding.txtError.visibility = View.GONE
            }
        })
    }

}