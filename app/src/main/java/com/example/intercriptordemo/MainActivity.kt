package com.example.intercriptordemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.intercriptordemo.api.RestClient
import com.example.intercriptordemo.model.Wallet
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    val TAG = "MOTHAFAKA"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_main.setOnClickListener {
            var call : Call<Wallet?>? = RestClient.getClient().getWalletById("1")
                call?.enqueue(object :Callback<Wallet?>{
                    override fun onFailure(call: Call<Wallet?>, t: Throwable) {
                        Log.d(TAG, "Failure " + t.message)
                    }

                    override fun onResponse(call: Call<Wallet?>, response: Response<Wallet?>) {
                        val wallet = response.body()
                        Log.d(TAG, wallet?.id.toString())
                    }

                })
        }
    }

}
