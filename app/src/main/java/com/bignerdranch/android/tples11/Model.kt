package com.bignerdranch.android.tples11


class Model {

    private var data = mutableListOf<String>()

    fun addRemark(remark: String): String {
        if (remark.isEmpty()) {
            return "Введите текст заметки"
        }

        data.add(remark)
        return ""
    }

    fun getRemarks(): List<String> {
        return data
    }

    fun clear() {
        data.clear()
    }

}