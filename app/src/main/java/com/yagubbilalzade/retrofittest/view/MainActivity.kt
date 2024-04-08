package com.yagubbilalzade.retrofittest.view

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yagubbilalzade.retrofittest.databinding.MainActivityBinding
import com.yagubbilalzade.retrofittest.model.Users
import com.yagubbilalzade.retrofittest.modelview.RecyAdapter
import com.yagubbilalzade.retrofittest.modelview.RetrofitApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : ComponentActivity() {

    val BASE_URL = "https://yagubbilalzade.com/henexAdmin/"
    lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val recyclerview = binding.ListView1
        val data_image = binding.dataImage
        val progress_bar = binding.progressBar
        val reset_button = binding.resetButton


        recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
        var data = ArrayList<Users>()
        ShowUsers(data)

        val adapter = RecyAdapter(data)
        recyclerview.adapter = adapter

        binding.btnGetData.setOnClickListener {

            lifecycleScope.launch {

                progress_bar.visibility = View.VISIBLE
                val progressAnimator = ObjectAnimator.ofInt(progress_bar, "progress", 0, 100)
                progressAnimator.setDuration(2000) // Set the duration to 2 seconds (2000 milliseconds)
                progressAnimator.start() // Start the animation

                delay(2000)
                data_image.visibility = View.GONE
                recyclerview.visibility = View.VISIBLE
                recyclerview.adapter = adapter
                progress_bar.visibility = View.GONE
            }


        }

        reset_button.setOnClickListener {
            resetElements(data_image, recyclerview, progress_bar)
        }



    }


    private fun resetElements(data_image: ImageView ,recyclerview : RecyclerView, progress_bar : ProgressBar ) {
        data_image.visibility = View.VISIBLE
        recyclerview.visibility = View.GONE
        progress_bar.visibility = View.GONE

    }


    val api = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(RetrofitApi::class.java)

    private fun ShowUsers(data1: ArrayList<Users>) {

        api.fetchUsers().enqueue(object : Callback<List<Users>> {
            override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                if (response.isSuccessful) {

                    response.body()?.let {
                        for (user in it) {
                            data1.add(Users(user.name, user.surname))
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Database Error", Toast.LENGTH_LONG).show()
            }
        })

    }


}
