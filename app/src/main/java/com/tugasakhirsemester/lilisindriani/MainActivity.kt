package com.tugasakhirsemester.lilisindriani

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import android.R.layout.simple_list_item_1
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File.separator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val retrofit= Retrofit.Builder()
            .baseUrl("https://coronavirus-19-api.herokuapp.com/" )
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        val api = retrofit.create(api::class.java)
        val mcall: Call<List<ayo>> = api.getInfo()
        mcall.enqueue(object : Callback<List<ayo>> {
            override fun onFailure(call: Call<List<ayo>>, t: Throwable) {
                Log.e("Error",t.message.toString())
            }

            override fun onResponse(call: Call<List<ayo>>, response: Response<List<ayo>>) {
                val mayo:List<ayo> = response.body()!!

                    val stringBuilder = StringBuilder()
                    for(i in mayo){
                        stringBuilder[i].append(("Negara : " +i.country))
                        stringBuilder.append(("\n"))
                        stringBuilder.append(("Sembuh : " +i.recovered))
                        stringBuilder.append(("\n"))
                        stringBuilder.append(("Positif : " + i.active))
                        stringBuilder.append(("\n"))
                        stringBuilder.append(("Meninggal : " + i.deaths))
                        stringBuilder.append(("\n"))
                        stringBuilder.append(("\n"))

                    }
//                    txtUser.movementMethod= ScrollingMovementMethod()
//                    txtUser.text=stringBuilder

                listView.adapter = ArrayAdapter(this, simple_list_item_1, stringBuilder)



            }

        })

    }
}