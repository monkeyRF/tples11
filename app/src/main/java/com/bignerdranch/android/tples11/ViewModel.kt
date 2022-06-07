package com.bignerdranch.android.tples11

import android.view.View
import androidx.lifecycle.MutableLiveData


class ViewModel {

    private val model = Model()
    var error = MutableLiveData("")
    var remarks = MutableLiveData(listOf<String>())
    var input = MutableLiveData("")

    fun addRemark(view: View) {
        val res = model.addRemark(input.value!!)
        if (res.isNotEmpty()) {
            error.value = res
            return
        }
        remarks.value = model.getRemarks()
        input.value = ""
    }

    fun addText(s: CharSequence, start: Int, before: Int, count: Int) {
        //Log.w("tagADD", "onTextChanged " + s);

        if (s != "")
            remarks.value = model.getRemarks().filter { s in it }
    }

    fun clear(view: View) {
        model.clear()
        remarks.value = model.getRemarks()
    }

}