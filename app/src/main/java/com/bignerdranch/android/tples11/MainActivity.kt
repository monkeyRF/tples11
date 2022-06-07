package com.bignerdranch.android.tples11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.tples11.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val myAdapter = MyAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(ActivityMainBinding.inflate(layoutInflater).also { it ->
            it.recyclerView.adapter = myAdapter
            it.recyclerView.layoutManager = LinearLayoutManager(this)
            it.lifecycleOwner = this
            it.viewModel = viewModel
            viewModel.error.observe(this) { err ->
                err?.let {
                    Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                    viewModel.error.value = null
                }
            }
        }.root)


    }

    companion object {
        val viewModel = ViewModel()
    }
}

@BindingAdapter("data")
fun setData(recyclerView: RecyclerView, data: List<String>){
    (recyclerView.adapter as MyAdapter).data = data
}


class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {
    var data = listOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MyViewHolder(TextView(parent.context))

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = data[position]
    }

    override fun getItemCount() = data.size

}

class MyViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView) {

}
