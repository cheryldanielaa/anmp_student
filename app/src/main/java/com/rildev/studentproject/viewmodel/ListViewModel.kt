package com.rildev.studentproject.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rildev.studentproject.model.Student

//sama-sama listview model tapi dia punya context
//nah contextnya itu didapat dari constructor
//app itu nama viariabelnya
class ListViewModel(app: Application):
    AndroidViewModel(app){
    val studentsLD = MutableLiveData<ArrayList<Student>>()
    //boolean, true sdg loading, false loadingnya tuntas
    val loadingLD = MutableLiveData<Boolean>()
    val errorLD = MutableLiveData<Boolean>()

    val TAG = "volleyTAG"
    private var queue: RequestQueue? = null //ini klo dibolehin null ditambahin ?
    override fun onCleared() {
        super.onCleared()
        //method ini akan dipake waktu sebelum dia dihancurkan di viewmodelnya
        queue?.cancelAll(TAG)
    }
    fun refresh() {
        //dia waktu pertama load hrsnya true
        loadingLD.value = true //biar bs buat progress barnya muter
        errorLD.value=false

        queue = Volley.newRequestQueue(getApplication()) //get application itu buat context
        val url = "https://www.jsonkeeper.com/b/LLMW"

        //buat variabel string request disini
        val stringRequest = StringRequest(
            Request.Method.GET, url, //methodnya get
            {
                //klo berhasil maka loading ld false (berhenti muter)
                loadingLD.value = false
                Log.d("showvoley", it)
                //type token list student artinya outputnya itu part of class student
                val sType = object : TypeToken<List<Student>>() { }.type
                val result = Gson().fromJson<List<Student>>(it, sType)
                studentsLD.value = result as ArrayList<Student>
                loadingLD.value = false

                Log.d("showvoley", result.toString())
            },
            {
                //klo error muncul error
                //trs loading ldnya false (berhenti muter), tapi dia munculin
                //value errornya juga
                Log.d("showvoley", it.toString())
                errorLD.value = true
                loadingLD.value = false
            }
        )
        stringRequest.tag = TAG
        queue?.add(stringRequest) //jalanin querynya
    }

}